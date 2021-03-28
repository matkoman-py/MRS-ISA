<template>

  <b-container>
    <b-row>
      <b-col>
        <b-form @submit="getDrugs">
          <b-form-group id="input-group-1" label="Drug name:" label-for="input-1">
            <b-form-input id="input-1" v-model="name" type="text" placeholder="Enter drug name"></b-form-input>
          </b-form-group>
      
        <b-form-group id="input-group-3" label="Drug type:" label-for="input-3">
          <b-form-select id="input-3" v-model="type" placeholder="Choose a drug type" :options="drugTypeOptions">
          </b-form-select>
        </b-form-group>
      
        <b-form-group id="input-group-1" label="Drug form:" label-for="input-1">
          <b-form-input id="input-1" v-model="form" type="text" placeholder="Enter a drug form"></b-form-input>
        </b-form-group>
      
        <b-form-group id="input-group-3" label="Drug manufacturer:" label-for="input-3">
          <b-form-select id="input-3" v-model="manufacturer" :options="manufacturerOptions">
          </b-form-select>
        </b-form-group>
      
        <b-form-group id="input-group-3" label="Receipt needed:" label-for="input-3">
          <b-form-select id="input-3" v-model="receipt" :options="receiptOptions">
          </b-form-select>
        </b-form-group>
      
        <b-button type="submit" variant="primary">Submit</b-button>
        <br>
        <br>
      </b-form>
      </b-col>

      <b-col>
        <b-table head-variant="dark" striped hover :items="drugs"></b-table>
        <h1 v-if="drugs.length == 0"> There are no drugs that fit the search parameters</h1>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import axios from "axios";

  export default {
    name: "DrugTable",
    data: function () {
      return {
        name: '',
        type: '',
        form: '',
        manufacturer: '',
        receipt: '',
        receiptOptions: ['Yes', 'No'],
        drugs: [],
        drugType: [],
        manufacturerOptions: [],
        drugTypeOptions: [],
        ingrediants: [],
        substitutions: [],
      }
    },
    methods: {
      getDrugs: function () {
        axios.get('http://localhost:8081/drugs/search', {
            params: {
              drugNameParam: this.name,
              drugTypeParam: this.type.name,
              drugFormParam: this.form,
              drugManufacturerParam: this.manufacturer.name,
              drugReceiptParam: this.receipt,
            }
          })
          .then(response => {
            this.drugs = response.data.map(drug =>
              ({
                name: drug.name,
                form: drug.form,
                type: drug.type.name,
                receipt: drug.receipt ? "Yes" : "No",
                manufacturer: drug.manufacturer.name,
                substitutions: (!drug.substitutions || drug.substitutions.length == 0) ?
                  "No substitute" : drug.substitutions.map(sub => sub.name).join(", "),
                ingrediants: drug.ingrediants.map(ingrediant => ingrediant.name).join(", "),
              }));
          })
          .catch(error => console.log(error));
      },
      getManufacturers: function () {
        axios.get("http://localhost:8081/manufacturers")
          .then(response => {
            this.manufacturerOptions = response.data.map((manufacturer) =>
              ({
                value: manufacturer,
                text: manufacturer.name
              })
            );
          })
          .catch(error => console.log(error));
      },
      getDrugTypes: function () {
        axios.get("http://localhost:8081/drug-types")
          .then(response => {
            this.drugTypeOptions = response.data.map((drugType) =>
              ({
                value: drugType,
                text: drugType.name
              })
            );
          })
          .catch(error => console.log(error));
      },
      getIngrediants: function () {
        axios.get("http://localhost:8081/ingrediants")
          .then(response => {
            this.ingrediants = response.data;
          })
          .catch(error => console.log(error));
      },
      getSubstitutionDrugs: function () {
        axios.get("http://localhost:8081/drugs")
          .then(response => {
            this.substitutions = response.data;
          })
          .catch(error => console.log(error));
      },
      getAllDrugs : function(){
            axios.get('http://localhost:8081/drugs')
            .then(response => {
                this.drugs = response.data.map(drug => 
                (
                    {
                        name: drug.name,
                        form: drug.form, 
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                        substitutions: 
                            (!drug.substitutions || drug.substitutions.length == 0) 
                            ? "No substitute"
                            : drug.substitutions.map(sub => sub.name).join(", "),
                        ingrediants: drug.ingrediants.map(ingrediant => ingrediant.name).join(", "),
                    }
                ));
            })
            .catch(error => console.log(error));
        }
    },
    mounted: function () {
      this.getManufacturers();
      this.getIngrediants();
      this.getSubstitutionDrugs();
      this.getDrugTypes();
      this.getAllDrugs();
    }
  }
</script>

<style>

</style>