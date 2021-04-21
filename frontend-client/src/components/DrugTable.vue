<template>

  <b-container>
    <b-row>
      <b-col>
        <b-form @submit="getDrugs">
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

          <b-button type="submit" variant="primary">Submit</b-button>
          <br>
          <br>
        </b-form>
      </b-col>

      <b-col>
        <b-table head-variant="dark" striped hover :items="drugs" sticky-header="400px"></b-table>

        <b-pagination v-model="currentPage" per-page=3 :total-rows="rows"></b-pagination>

        <h1 v-if="drugs.length == 0"> There are no drugs that fit the search parameters</h1>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  export default {
    name: "DrugTable",
    components: {},
    computed: {
      rows() {
        return this.drugs.length * 3
      }
    },
    watch: {
      currentPage: function () {
        if(!this.suppress){
          this.getDrugs();
        }else{
          this.suppress = false;
        }
      }
    },
    data: function () {
      return {
        searchForm: {
          name: '',
          type: '',
          form: '',
          manufacturerId: '',
          receipt: '',
        },
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
        drugs: [],
        drugType: [],
        manufacturerOptions: [],
        drugTypeOptions: [],
        ingrediants: [],
        substitutions: [],
        currentPage: 1,
        suppress: false
      }
    },
    methods: {
      getDrugs: function () {
        this.$http.post(`http://localhost:8081/drugs/search?page=${this.currentPage-1}&size=3`, this.searchForm)
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
        this.$http.get("http://localhost:8081/drugs?page=0&size=3")
          .then(response => {
            this.substitutions = response.data;
          })
          .catch(error => console.log(error));
      },
      mapDrugs: function (response) {
        return response.data.map(drug =>
          ({
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
      this.getDrugs();
    }
  }
</script>

<style>

</style>