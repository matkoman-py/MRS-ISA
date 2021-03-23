<template>
  <b-container style="width: 40%" align-h="center">
    <b-row>
      <b-col>
        <b-form @submit="addDrug">

          <b-form-group label="Name:" label-for="name-input">
            <b-form-input
              id="name-input"
              v-model="form.name"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Drug Type:" label-for="drug-type-select">
              <b-form-select id="drug-type-select" 
                             v-model="form.drugType"
                             :options="drugTypeOptions"
                             :required="form.drugType == null"></b-form-select>
          </b-form-group>

          <b-form-group label="Form:" label-for="form-input">
            <b-form-input
              id="form-input"
              v-model="form.form"
              placeholder="Form name"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Manufacturer:" label-for="manufacturer-select">
              <b-form-select id="manufacturer-select" 
                             v-model="form.manufacturer"
                             :options="manufacturerOptions"
                             :required="form.manufacturer == null"></b-form-select>
          </b-form-group>

          <b-form-group label="Description:" label-for="description-textarea">
            <b-form-textarea
              id="description-textarea"
              v-model="form.description"
              placeholder="Enter something..."
              rows="3"
              max-rows="6"
              required
            ></b-form-textarea>
          </b-form-group>

          <b-form-group label="Receipt:" label-for="receipt-select">
              <b-form-select id="receipt-select"
                             v-model="form.receipt"
                             :options="receiptOptions"
                             ></b-form-select>
          </b-form-group>

          <b-form-group label="Points:" label-for="point-input">
            <b-form-input
              id="point-input"
              v-model="form.points"
              placeholder="Number of points"
              type="number"
              required
            ></b-form-input>
          </b-form-group>
        

          <searchable-tags labelName="Ingrediants"
                            :updateValue="(data) => form.ingrediants = data"
                            :data="ingrediants"
                            >
          </searchable-tags>

          <searchable-tags labelName="Substitutions"
                            :updateValue="(data) => form.substitutions = data"
                            :data="substitutions">
          </searchable-tags>

          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
        <b-card class="mt-3" header="Form Data Result">
          <pre class="m-0">{{ form }}</pre>
        </b-card>
      </b-col>
    </b-row>
    
  </b-container>
</template>

<script>

import axios from "axios"
import SearchableTags from "../components/SearchableTags"

export default {
  name: 'AddDrugForm',
  components: {
    SearchableTags
  },
  data: function(){
    return {
      form: {
        name: "",
        form: "",
        receipt: false,
        description: "",
        substitutions: [],
        ingrediants: [],
        manufacturer: null,
        drugType: null,
        points: 0,
      },
      receiptOptions: [
        { value: false, text: "Doesn't need receipt"},
        { value: true, text: "Needs receipt"}
      ],
      manufacturerOptions: [],
      drugTypeOptions: [],
      ingrediants: [],
      substitutions: [],
    }
  },
  methods: {
    getManufacturers: function(){
      axios.get("http://localhost:8081/manufacturers")
      .then(response => {
        this.manufacturerOptions = response.data.map((manufacturer) => 
        ({
          value: manufacturer,
          text: manufacturer.name
        })
        );
        this.manufacturerOptions.unshift({value: null, text: "Choose one"})
      })
      .catch(error => console.log(error));
    },
    getDrugTypes: function(){
      axios.get("http://localhost:8081/drug-types")
      .then(response => {
        this.drugTypeOptions = response.data.map((drugType) => 
        ({
          value: drugType,
          text: drugType.name
        })
        );
        this.drugTypeOptions.unshift({value: null, text: "Choose one"})
      })
      .catch(error => console.log(error));
    },
    getIngrediants: function(){
      axios.get("http://localhost:8081/ingrediants")
      .then(response => {
        this.ingrediants = response.data;
      })
      .catch(error => console.log(error));
    },
    getSubstitutionDrugs: function(){
      axios.get("http://localhost:8081/drugs")
      .then(response => {
        this.substitutions = response.data;
      })
      .catch(error => console.log(error));
    },
    addDrug: function(event){
      event.preventDefault();
      axios.post("http://localhost:8081/drugs", this.form)
      .then(response => {
        alert("drug added successfully");
        console.log(response);
      })
      .catch(error => console.log(error));
    }
  },
  mounted: function(){
    this.getManufacturers();
    this.getIngrediants();
    this.getSubstitutionDrugs();
    this.getDrugTypes();
  }
}
</script>