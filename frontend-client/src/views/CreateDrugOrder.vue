<template>
    <b-container>
    <h1> Drug list </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table sticky-header striped hover :items="drugs" selectable select-mode='multi' @row-selected="onRowSelected"></b-table>
    </div>
    <div>
        <b-button variant="outline-hub" @click="showModal">Create new drug order</b-button>
    </div>
    <b-container style="margin-top:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="onSubmit">
                <p style="margin-top:20px">Filter drugs:</p>
                <b-form-select v-model="selected" :options="options" @select="onSubmit"></b-form-select>
                <div>
                <b-button type="submit" style="margin-top:40px; margin-bottom:40px" variant="outline-hub" size="lg">Submit</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>

    <b-modal id="createOrderModal" title="Finish order" hide-footer>
      <b-form>
        <div style="margin:5px" v-for="drug in orderAtributtes.selectedDrugs" :key="drug.name">
            <b-form-group
                :label= "drug.name"
                label-for="amount-input"
                invalid-feedback="Amount is required">
                <b-form-input
                    required
                    :min=0
                    type="number"
                    v-model="drug.amount"
                    placeholder="Insert amount.."
                ></b-form-input>
            </b-form-group>
        </div>

        <b-form-group
        label="Deadline date for offerss"
        label-for="date-picker"
        invalid-feedback="Deadline date is required">
        <b-form-datepicker 
            style="margin:5px"
            :min="minDate"
            v-model="orderAtributtes.date"
            required >
        </b-form-datepicker>
        </b-form-group>

        <b-form-group
        label="Deadline time for offerss"
        label-for="time-picker"
        invalid-feedback="Deadline time is required">
            <b-form-timepicker
            style="margin:5px"
            v-model="orderAtributtes.time"
            locale="en">
            </b-form-timepicker>
        </b-form-group>
        
        <div style="margin:20px">
        <b-button type="button" variant="outline-hub" @click="createOrder">Create</b-button>
        <b-button type="button" variant="outline-hub" @click="cancel" >Cancel</b-button>
        </div>

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
        drugs: [],
        selected: 'All_Drugs',
        options: [
          { value: 'All_Drugs', text: 'All drugs' },
          { value: 'Requested_Drugs', text: 'Requested drugs' },
        ],
        orderAtributtes: {
            selectedDrugs: [],
            date: '',
            time: '',
            drugstoreId: ''
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
              this.drugstoreId = response.data.id;
              this.getDrugs();
              })
              .catch(error => console.log(error));
        },
        getDrugs() {
            this.$http.get("http://localhost:8081/drugs/createOrderView", {
              params: {
                drugstoreId: this.drugstoreId,
                drugsToBeShown: this.selected
              }
              })
              .then(response => {
                this.drugs = response.data.map(drug => 
                (
                    {
                        name: drug.drugName,
                        available_in_this_drugstore: drug.available ? "Yes" : "No",
                        amount_available : drug.available ? drug.availableAmount : "/",
                        requested : drug.requested ? "Yes" : "No"
                    }
                ));
            })
              .catch(error => console.log(error));
        },
        onSubmit(event) {
            event.preventDefault()
            this.getDrugs()
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
            this.orderAtributtes.selectedDrugs = item
        },
        createOrder(event) {
          event.preventDefault();
          this.orderAtributtes.drugstoreId = this.drugstoreId;
          this.$http.post("http://localhost:8081/drug-orders/create", JSON.parse(JSON.stringify(this.orderAtributtes)))
              .then(() => {
                this.$root.$emit('bv::hide::modal', 'createOrderModal');
                alert("Order successfully created!");
                // ovde ces trebati da osvezis vrv
              })
              .catch(error => console.log(error));
        }, cancel() {
            this.$root.$emit('bv::hide::modal', 'createOrderModal');
        }
    },
    mounted: function(){
        this.initialize();
    }
  }
</script>