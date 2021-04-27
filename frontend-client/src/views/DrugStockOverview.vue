<template>
  <b-container>
    <h1> Drugs on stock </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="drugStocks" selectable select-mode='single' @row-selected="onRowSelected"></b-table>
        <p v-if="drugStocks.length == 0">There are no certain drugs in this drugstore at the moment.</p>
    </div>
    <div>
        <router-link to="/drug-stock-create-order">
          <b-button style="margin-right:50px" variant="success">Create order</b-button>
        </router-link>
        <router-link to="/drug-stock-check-offers">
          <b-button style="margin-left:50px; margin-right:50px" variant="success">Check offers</b-button>
        </router-link>
          <b-button style="margin-left:50px; margin-right:50px" @click="newPriceAssign" variant="success">Assign new price</b-button>
        <router-link to="#">
          <b-button style="margin-left:50px" @click="newPromotion" variant="success">Create promotion</b-button>
        </router-link>
    </div>
    <b-container style="margin:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="search">
                <p style="margin-top:20px">Search text:</p>
                <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
                <div>
                <b-button type="button" style="margin:40px" variant="outline-primary" size="lg" @click="search">Search</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>

    <b-modal id="newPriceModal" title="Create new price" hide-footer>
      <b-form>
        <b-form-group
          label="New price"
          label-for="price-input"
          invalid-feedback="Price is required">
          <b-form-input
            id="price-input"
            v-model="inputValuesForNewPrice.price" 
            required
            :min=0
            type="number"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Price expiration date"
          label-for="date-input"
          invalid-feedback="Expiration date is required">
          <b-form-datepicker
            id="date-input"
            v-model="inputValuesForNewPrice.endDate"
            :min="minDate"
            required >
          </b-form-datepicker>
        </b-form-group>

        <b-button type="button" variant="primary" @click="addNewPrice">Save</b-button>
        <b-button type="button" variant="danger" @click="handleClose" >Cancel</b-button>

      </b-form>
    </b-modal>

    <b-modal id="newPromotionModal" title="Create new promotion" hide-footer>
      <b-form>
        <b-form-group
          label="Promotion price"
          label-for="price-input"
          invalid-feedback="Price is required">
          <b-form-input
            id="price-input"
            v-model="inputValuesForNewPromotion.price" 
            required
            :min=0
            type="number"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Promotion expiration date"
          label-for="date-input"
          invalid-feedback="Expiration date is required">
          <b-form-datepicker
            id="date-input"
            v-model="inputValuesForNewPromotion.endDate"
            :min="minDate"
            required >
          </b-form-datepicker>
        </b-form-group>

        <b-button type="button" variant="primary" @click="createNewPromotion">Save</b-button>
        <b-button type="button" variant="danger" @click="handleClose">Cancel</b-button>

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
        drugStocks: [],
        searchText: '',
        selected: [],
        minDate: minDate,
        drugstoreId:'',
        inputValuesForNewPrice: {
          price: '',
          drugName: '',
          drugStoreId: '',
          startDate: minDate,
          endDate: ''
        },
        inputValuesForNewPromotion: {
          price: '',
          drugName: '',
          drugStoreId: '',
          startDate: minDate,
          endDate: ''
        }
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
              this.inputValuesForNewPrice.drugStoreId = response.data.id;
              this.inputValuesForNewPromotion.drugStoreId = response.data.id;
              this.drugstoreId = response.data.id;
              this.getDrugStockForDrugstore();
              })
              .catch(error => console.log(error));
        },
        getDrugStockForDrugstore : function(){
            this.$http.get('http://localhost:8081/drug-stock', {
            params: {
              drugstoreId: this.drugstoreId
            }})
            .then(response => {
            this.drugStocks = response.data.map(drugStockDto => 
            (
                    {
                        drug: drugStockDto.drugName,
                        amount: drugStockDto.drugAmount,
                        price: drugStockDto.drugPrice,
                        priceExpirationDate: drugStockDto.priceExpirationDate.slice(0,10)             
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        onRowSelected(item) {
            this.selected = item
        },
        search(event) {
            event.preventDefault()
            this.$http.get('http://localhost:8081/drug-stock/search', {
            params: {
              searchedText: this.searchText,
              drugstoreId: this.drugstoreId
            }
          })
          .then(response => {
            this.drugStocks = response.data.map(drugStockDto => 
            (
                    {
                        drug: drugStockDto.drugName,
                        amount: drugStockDto.drugAmount,
                        price: drugStockDto.drugPrice,
                        priceExpirationDate: drugStockDto.priceExpirationDate.slice(0,10)             
                    }
                ));
            })
          .catch(error => console.log(error));
        },
        newPriceAssign(event) {
          event.preventDefault()
          if (this.selected.length == 0) {
            alert("You need to select drug for which you want to assign new price.")
          } else {
            this.$root.$emit('bv::show::modal', 'newPriceModal');
          }
        },
        newPromotion(event) {
            event.preventDefault()
            if (this.selected.length == 0) {
              alert("You need to select drug for which you want to create promotion.")
            } else {
              this.$root.$emit('bv::show::modal', 'newPromotionModal');
            }        
          },
        addNewPrice(event) {
          event.preventDefault();
          this.inputValuesForNewPrice.drugName = this.selected[0].drug
          this.$http.post("http://localhost:8081/drug-price/", JSON.parse(JSON.stringify(this.inputValuesForNewPrice)))
              .then(() => {
                alert("New price for " + this.selected[0].drug + " is successfully added.");
                this.getDrugStockForDrugstore();
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'newPriceModal');
        },
        createNewPromotion(event) {
          event.preventDefault();
          this.inputValuesForNewPromotion.drugName = this.selected[0].drug
          this.$http.post("http://localhost:8081/drug-price/promotion", JSON.parse(JSON.stringify(this.inputValuesForNewPromotion)))
              .then(() => {
                alert("New promotion for " + this.selected[0].drug + " is successfully added.");
                this.getDrugStockForDrugstore();
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'newPromotionModal');
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'newPriceModal');
            this.$root.$emit('bv::hide::modal', 'newPromotionModal');
        }
        },
    mounted: function(){
        this.initialize();
    }
  }
</script>