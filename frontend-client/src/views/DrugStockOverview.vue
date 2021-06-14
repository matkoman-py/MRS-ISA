<template>
  <b-container>
    <h1> Drugs on stock </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="drugStocks" selectable select-mode='single' @row-selected="onRowSelected"></b-table>
        <p v-if="drugStocks.length == 0">There are no certain drugs in this drugstore at the moment.</p>
    </div>
    <div>
        <router-link to="/drug-stock-create-order">
          <b-button style="margin-right:50px" @click="newDrug" variant="outline-hub">Add new drug to stock</b-button>
        </router-link>
        <router-link to="/drug-stock-check-offers">
          <b-button style="margin-left:50px; margin-right:50px" @click="deleteDrug" variant="outline-hub">Delete drug from stock</b-button>
        </router-link>
          <b-button style="margin-left:50px; margin-right:50px" @click="newPriceAssign" variant="outline-hub">Assign new price</b-button>
        <router-link to="#">
          <b-button style="margin-left:50px" @click="newPromotion" variant="outline-hub">Create promotion</b-button>
        </router-link>
    </div>
    <b-container style="margin:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="search">
                <p style="margin-top:20px">Search text:</p>
                <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
                <div>
                <b-button type="button" style="margin:40px" variant="outline-hub" size="lg" @click="search">Search</b-button>
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

        <b-button type="button" variant="outline-hub" @click="addNewPrice">Save</b-button>
        <b-button type="button" variant="outline-hub" @click="handleClose" >Cancel</b-button>

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

        <b-button type="button" variant="outline-hub" @click="createNewPromotion">Save</b-button>
        <b-button type="button" variant="outline-hub" @click="handleClose">Cancel</b-button>

      </b-form>
    </b-modal>

    <b-modal id="addNewDrugModal" title="Add new drug to stock" hide-footer>
      <b-form>
        <b-form-group id="input-group-1" label="Drug" label-for="input-1">
          <multiselect v-model="inputValuesForNewDrug.selectedDrug" track-by="id" label="name"
              placeholder="Select one" :options="drugs" :searchable="true" :allow-empty="false" select-label="">
          </multiselect>
        </b-form-group>

        <b-form-group
          label="Initial price"
          label-for="price-input"
          invalid-feedback="Price is required">
          <b-form-input
            id="price-input"
            v-model="inputValuesForNewDrug.price" 
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
            v-model="inputValuesForNewDrug.endDate"
            :min="minDate"
            required >
          </b-form-datepicker>
        </b-form-group>

        <b-button type="button" variant="outline-hub" @click="addNewDrugToDrugstore">Add</b-button>
        <b-button type="button" variant="outline-hub" @click="handleClose" >Cancel</b-button>

      </b-form>
    </b-modal>

    <b-modal id="deleteConfirmation" title="Warning! This drug is going to be removed from drugstore stock!" align="center" hide-footer>
      <b-form>
        <b-row align-h="center">
        <b-label> Are you sure that you want to remove this drug?</b-label>
        </b-row>
        <b-row align-h="center">
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="deleteDrugFromStock">Continue</b-button>
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="cancel" >Cancel</b-button>
        </b-row>
      </b-form>
    </b-modal>

  </b-container>
  
</template>

<script>
  import Multiselect from "vue-multiselect";
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    },
    components: {
      Multiselect
    },
    data: function() {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return {
        drugs: [],
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
        },
        inputValuesForNewDrug: {
          drugStoreId: '',
          selectedDrug: '',
          price: '',
          startDate: minDate,
          endDate: ''
        }
      }
    },
    methods: {
        initialize() {
            this.$http.get("https://mrs-isa-usijani.herokuapp.com//employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.inputValuesForNewPrice.drugStoreId = response.data.id;
              this.inputValuesForNewPromotion.drugStoreId = response.data.id;
              this.inputValuesForNewDrug.drugStoreId = response.data.id;
              this.drugstoreId = response.data.id;
              this.getDrugStockForDrugstore();
              this.getDrugsNotOnStock();
              })
              .catch(error => console.log(error));
        },
        getDrugStockForDrugstore : function(){
            this.$http.get('https://mrs-isa-usijani.herokuapp.com//drug-stock', {
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
        getDrugsNotOnStock: function () {
          this.$http.get("https://mrs-isa-usijani.herokuapp.com//drugs/notOnStock", {
            params: {
              drugstoreId: this.drugstoreId
            }
          })
              .then(response => {
                  this.drugs = response.data;
              })
              .catch(error => this.$toastr.e(error));
        },
        onRowSelected(item) {
            this.selected = item
        },
        search(event) {
            event.preventDefault()
            this.$http.get('https://mrs-isa-usijani.herokuapp.com//drug-stock/search', {
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
        newDrug(event) {
          event.preventDefault();
          this.$root.$emit('bv::show::modal', 'addNewDrugModal');
        }, deleteDrug(event) {
          event.preventDefault();
          if (this.selected.length == 0) {
            alert("You need to select the drug that you want to delete.")
          } else {
            this.$root.$emit('bv::show::modal', 'deleteConfirmation');
          }
        },
        addNewPrice(event) {
          event.preventDefault();
          this.inputValuesForNewPrice.drugName = this.selected[0].drug
          this.$http.post("https://mrs-isa-usijani.herokuapp.com//drug-price/", JSON.parse(JSON.stringify(this.inputValuesForNewPrice)))
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
          this.$http.post("https://mrs-isa-usijani.herokuapp.com//drug-price/promotion", JSON.parse(JSON.stringify(this.inputValuesForNewPromotion)))
              .then(() => {
                alert("New promotion for " + this.selected[0].drug + " is successfully added.");
                this.getDrugStockForDrugstore();
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'newPromotionModal');
        },
        addNewDrugToDrugstore(event) {
          event.preventDefault();
          this.$http.post("https://mrs-isa-usijani.herokuapp.com//drug-stock", JSON.parse(JSON.stringify(this.inputValuesForNewDrug)))
              .then(() => {
                alert("New drug '" + this.inputValuesForNewDrug.selectedDrug.drugName + "' is successfully added to stock.");
                this.getDrugStockForDrugstore();
                this.getDrugsNotOnStock();
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'addNewDrugModal');
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'newPriceModal');
            this.$root.$emit('bv::hide::modal', 'newPromotionModal');
            this.$root.$emit('bv::hide::modal', 'addNewDrugModal');
        },
         cancel() {
            this.$root.$emit('bv::hide::modal', 'deleteConfirmation');
        }, deleteDrugFromStock(event) {
            event.preventDefault();
              this.$http.delete("https://mrs-isa-usijani.herokuapp.com//drug-stock/delete/", {data :{ drugName: this.selected[0].drug, drugStoreId: this.drugstoreId}})
              .then((response) => {
                alert(response.data)
                this.getDrugStockForDrugstore();
                this.getDrugsNotOnStock();
              }).catch(error => console.log(error));
            this.$root.$emit('bv::hide::modal', 'deleteConfirmation');
        }
  },
    mounted: function(){
        this.initialize();
    }
  }
</script>