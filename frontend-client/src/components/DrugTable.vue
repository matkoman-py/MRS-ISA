<template>

  <b-container>
    <b-row class="mt-3">
      <b-col cols="4">
        <b-card>
          <h3>Search</h3>
          <b-form @submit="searchDrugs">
            <b-form-group id="input-group-1" label="Drug name:" label-for="input-1">
              <b-form-input id="input-1" v-model="searchForm.name" type="text" placeholder="Enter drug name">
              </b-form-input>
            </b-form-group>

            <b-form-group id="input-group-3" label="Drug type:" label-for="input-3">
              <b-form-select id="input-3" v-model="searchForm.type" placeholder="Choose a drug type"
                :options="drugTypeOptions">
              </b-form-select>
            </b-form-group>

            <b-form-group id="input-group-1" label="Drug form:" label-for="input-1">
              <b-form-input id="input-1" v-model="searchForm.form" type="text" placeholder="Enter a drug form">
              </b-form-input>
            </b-form-group>

            <b-form-group id="input-group-3" label="Drug manufacturer:" label-for="input-3">
              <b-form-select id="input-3" v-model="searchForm.manufacturerId" :options="manufacturerOptions">
              </b-form-select>
            </b-form-group>

            <b-form-group id="input-group-3" label="Receipt needed:" label-for="input-3">
              <b-form-select id="input-3" v-model="searchForm.receipt" :options="receiptOptions">
              </b-form-select>
            </b-form-group>

            <b-button type="submit" variant="outline-hub">Submit</b-button>
            <br>
            <br>
          </b-form>
        </b-card>
      </b-col>

      <b-col cols="8">
        <b-card>
          <b-table head-variant="outline-hub" striped hover :fields="fields" :items="drugs" sticky-header="400px">
            <template #cell(actions)="row">
              <b-button variant="outline-hub" v-if="row.item" size="sm"
                @click="getDrugstores(row.item, row.index, $event.target)" class="mr-1">
                Reserve
              </b-button>
            </template>
          </b-table>
          <b-pagination v-model="currentPage" per-page=3 :total-rows="rows"></b-pagination>
        </b-card>
        <b-card v-if="reserved" class="mt-3">
          <drug-reservation :selecteddrug="selectedDrug" :reserved="reserved" :drugstores="drugstores" :patientId="patientId">
          </drug-reservation>
          <h1 v-if="drugs.length == 0"> There are no drugs that fit the search parameters</h1>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import {
    mapState
  } from 'vuex'
  import DrugReservation from "../components/DrugReservation"

  export default {
    name: "DrugTable",
    components: {
      DrugReservation
    },
    props:{
      passedDrugstoreId: String,
      passedPatientId: String,
    },
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
        email: state => state.userModule.loggedInUser.email,
        role: state => state.userModule.loggedInUser.type
      }),
      rows() {
        return (this.currentPage + 1) * 3
      }
    },
    watch: {
      currentPage: function () {
        if (!this.suppress) {
          this.getDrugs();
        } else {
          this.suppress = false;
        }
      }
    },
    data: function () {
      return {
        reserved: 0,
        drugstores: [],
        searchForm: {
          name: '',
          type: '',
          form: '',
          manufacturerId: '',
          receipt: '',
        },
        fields: [{
            key: "name"
          },
          {
            key: "form"
          },
          {
            key: "type"
          },
          {
            key: "receipt"
          },
          {
            key: "manufacturer"
          },
          {
            key: 'actions',
            label: ''
          }
        ],
        receiptOptions: [{
            value: null,
            text: "Both"
          },
          {
            value: true,
            text: 'Yes'
          },
          {
            value: false,
            text: 'No'
          },
        ],
        selectedDrug: null,
        drugs: [],
        drugType: [],
        manufacturerOptions: [],
        drugTypeOptions: [],
        ingrediants: [],
        substitutions: [],
        currentPage: 1,
        suppress: false,
        currentSearch: {},
        patientId: '',
      }
    },
    methods: {
      getDrugstores: function (data) {
        if (this.user == null) {
          alert("You must be logged in to reserve a drug!");
          return;
        }
        if(this.passedPatientId == null){
          this.patientId=this.user.id;
        }else{
          this.patientId = this.passedPatientId;
        }
        //alert(data.id);
        this.selectedDrug = {
          id: data.id,
          name: data.name,
          type: data.type
        }
        this.reserved = 1;
        if(this.passedDrugstoreId == null){
        
        this.$http.get('http://localhost:8081/drugstores/reserve', {
            params: {
              drugId: data.id
            }
          })
          .then(response => {
            this.drugstores = response.data.map(stock =>
              ({
                id: stock.drugstore.id,
                name: stock.drugstore.name,
                address: stock.drugstore.location.address,
                city: stock.drugstore.location.city,
                rating: stock.drugstore.averageRating
              }));
          })
          }else{
            this.$http.get('http://localhost:8081/drugstores/reserveEmployee', {
            params: {
              drugId: data.id,
              drugstoreId: this.passedDrugstoreId,
            }
          })
          .then(response => {
            this.drugstores = response.data.map(stock =>
              ({
                id: stock.drugstore.id,
                name: stock.drugstore.name,
                address: stock.drugstore.location.address,
                city: stock.drugstore.location.city,
                rating: stock.drugstore.averageRating
              }));
          })
          }
      },
      searchDrugs: function () {
        this.currentSearch = JSON.parse(JSON.stringify(this.searchForm));
        this.getDrugs();
      },
      getDrugs: function () {
        this.$http.post(`http://localhost:8081/drugs/search?page=${this.currentPage-1}&size=3`, this.currentSearch)
          .then(response => {
            if (response.data.length == 0) {
              this.suppress = true;
              this.currentPage--;
            } else {
              this.suppress = false;
              this.drugs = this.mapDrugs(response);
            }
          })
          .catch(error => console.log(error));
      },
      getManufacturers: function () {
        this.$http.get("http://localhost:8081/manufacturers")
          .then(response => {
            this.manufacturerOptions = response.data.map((manufacturer) =>
              ({
                value: manufacturer.id,
                text: manufacturer.name
              })
            );
          })
          .catch(error => console.log(error));
      },
      getDrugTypes: function () {
        this.$http.get("http://localhost:8081/drug-types")
          .then(response => {
            this.drugTypeOptions = response.data.map((drugType) =>
              ({
                value: drugType.name,
                text: drugType.name
              })
            );
          })
          .catch(error => console.log(error));
      },
      getIngrediants: function () {
        this.$http.get("http://localhost:8081/ingredients")
          .then(response => {
            this.ingrediants = response.data;
          })
          .catch(error => console.log(error));
      },
      getSubstitutionDrugs: function () {
        this.$http.get("http://localhost:8081/drugs")
          .then(response => {
            this.substitutions = response.data;
          })
          .catch(error => console.log(error));
      },
      mapDrugs: function (response) {
        return response.data.map(drug =>
          ({
            id: drug.id,
            name: drug.name,
            form: drug.form,
            type: drug.type.name,
            receipt: drug.receipt ? "Yes" : "No",
            manufacturer: drug.manufacturer.name,
            substitutions: (!drug.substitutions || drug.substitutions.length == 0) ?
              "No substitute" : drug.substitutions.map(sub => sub.name).join(", "),
            ingredients: drug.ingredients.map(ingredient => ingredient.name).join(", "),
          }));
      },
    },

    mounted: function () {
      this.getManufacturers();
      this.getIngrediants();
      this.getSubstitutionDrugs();
      this.getDrugTypes();
      this.searchDrugs();
    }
  }
</script>

<style>

</style>