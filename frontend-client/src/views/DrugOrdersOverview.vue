<template>
    <b-container>
    <h1> Drug order list </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table sticky-header striped hover :items="drugOrders" :fields="fields">
          <template #cell(actions)="row">
            <button v-if="row.item.Order_status === 'Pending' && selectedOrder != row.item.Order_id" class="btn btn-dark" @click="showOffersForOrder(row)">Select offer</button>
            <button v-if="row.item.Order_status === 'Pending' && selectedOrder == row.item.Order_id" class="btn btn-dark" @click="hideOffers">Hide offers</button>
          </template>
        </b-table>
    </div>

    <b-container v-if="showOferrs">
      <b-card style="margin:40px">
        <h2 style="margin-top:5px"><b> Available offers for selected order: </b></h2>
        <div style="margin:40px; border-style:solid;">
            <b-table selectable select-mode="single" sticky-header striped hover :items="offers" @row-selected="onRowSelected"></b-table>
        </div>
        <b-button type="submit" variant="outline-hub" size="lg" style="margin-right:20px;" @click="completeOrder">Accept offer</b-button>
        <b-button type="submit" variant="outline-hub" size="lg" style="margin-left:20px;" @click="decline">Decline all offers</b-button>
      </b-card>
    </b-container>

    <b-container v-if="!showOferrs" style="margin-top:15px">
        <b-row align-h="center">
            <b-form style="width:40%">
                <p style="margin-top:20px">Filter orders by status:</p>
                <b-form-select placeholder="All" v-model="selected" :options="options"></b-form-select>
                <div>
                <b-button type="submit" style="margin-top:40px; margin-bottom:40px" variant="outline-hub" size="lg" @click="filterByStatus">Submit</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>

    <b-modal id="declineConfirmation" title="Warning! You are going to decline all offers!" align="center" hide-footer>
      <b-form>
        <b-row align-h="center">
        <b-label> Are you sure that you want to decline all offers for this order?</b-label>
        </b-row>
        <b-row align-h="center">
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="declineAllOffers">Continue</b-button>
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="cancel" >Cancel</b-button>
        </b-row>
      </b-form>
    </b-modal>
    </b-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    },
    data: function() {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return {
        minDate: minDate,
        drugstoreId: '',
        drugOrders: [],
        offers: [],
        selected: 'All_orders',
        options: [
          { value: 'All_orders', text: 'All orders' },
          { value: 'Active', text: 'Active' },
          { value: 'Expired', text: 'Expired' },
          { value: 'Pending', text: 'Pending' },
          { value: 'Accepted', text: 'Accepted' },
          { value: 'Declined', text: 'Declined' },
        ],
        fields: [
      {
        key: "Drugs_in_order",
        label: "Drugs in order",
      },
      {
        key: "Order_status",
        label: "Order status",
      },
      {
        key: "Expiration_date",
        label: "Expiration date",
      },
      {
        key: "Expiration_time",
        label: "Expiration time",
      },
      { key: "Actions" }
    ],
    showOferrs: false,
    selectedOrder: "",
    selectedOffer: ""
      }
    },
    methods: {
        initialize() {
            this.$http.get("http://localhost:8081/employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.drugstoreId = response.data.id;
              this.getDrugOrders();
              })
              .catch(error => console.log(error));
        },
        getDrugOrders() {
            this.$http.get("http://localhost:8081/drug-orders", {
              params: {
                drugstoreId: this.drugstoreId,
                filter: this.selected
              }
              })
              .then(response => {
                this.drugOrders = response.data.map(order => 
                (
                    {
                        Drugs_in_order: order.drugs,
                        Order_status: order.status,
                        Expiration_date: order.expirationDate,
                        Expiration_time: order.expirationTime,
                        Order_id: order.orderId
                    }
                ));
            })
              .catch(error => console.log(error));
        },
        filterByStatus(event) {
            event.preventDefault()
            this.getDrugOrders()
        },
        showModal(event) {
          event.preventDefault()
          if (this.orderAtributtes.selectedDrugs.length == 0) {
            alert("You need to select at least one drug for which you want to create order!")
          }  else {
            var flag = false;
            var i;
            for (i = 0; i < this.orderAtributtes.selectedDrugs.length; i ++) {
                if (this.orderAtributtes.selectedDrugs[i].available_in_this_drugstore.localeCompare("No") === 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                alert("You need first to add drugs that are not currently available in drugstore to create order for them!");
            } else {
                this.$root.$emit('bv::show::modal', 'createOrderModal');
            }
          }
        },
        onRowSelected(item) {
          this.selectedOffer = item[0].Offer_id;
        }, showOffersForOrder(row) {
          this.selectedOrder = row.item.Order_id;
           this.$http.get("http://localhost:8081/offers", {
              params: {
                orderId: this.selectedOrder
              }
              })
              .then(response => {
                  this.offers = response.data.map(offer => 
                  (
                      {
                          Supplier: offer.supplier.name,
                          Price: offer.price,
                          Delivery_date: offer.deliveryDate,
                          Delivery_time: offer.deliveryTime,
                          Offer_id  : offer.id
                      }
                  ));
                  if (this.offers.length > 0) {
                    this.showOferrs = true;
                  } else {
                    alert("For this order there are no offers, so this expired order will be automatically archived.")
                    this.orderExpired();
                    //ovde treba da se ugasi order
                  }
            })
              .catch(error => console.log(error));
        }, hideOffers() {
          this.showOferrs = false;
          this.selectedOrder = "";
        }, completeOrder() {
          if (this.selectedOffer == "") {
            alert("To complete order you need to select one of available offers!");
          } else {
            this.$http.post("http://localhost:8081/drug-orders/accepted/" + this.selectedOffer)
              .then(() => {
                this.getDrugOrders();
                this.showOferrs = false;
              })
              .catch(error => console.log(error));
          }
        }, decline() {
            this.$root.$emit('bv::show::modal', 'declineConfirmation');
        }, declineAllOffers() {
          this.$http.post("http://localhost:8081/drug-orders/declined/" + this.selectedOrder)
              .then(() => {
                this.getDrugOrders();
                this.showOferrs = false;
                this.$root.$emit('bv::hide::modal', 'declineConfirmation');
              })
              .catch(error => console.log(error));
        }, cancel() {
            this.$root.$emit('bv::hide::modal', 'declineConfirmation');
        }, orderExpired() {
          this.$http.post("http://localhost:8081/drug-orders/expired/" + this.selectedOrder)
              .then(() => {
                this.getDrugOrders();
              })
              .catch(error => console.log(error));
        }
    },
    mounted: function(){
        this.initialize();
    }
  }
</script>