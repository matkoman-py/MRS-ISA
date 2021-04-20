<template>
  <b-form>

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
                        v-model="form.type"
                        :options="drugTypeOptions"
                        :required="form.type == null"></b-form-select>
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
        v-model="form.point"
        placeholder="Number of points"
        type="number"
        required
      ></b-form-input>
    </b-form-group>
  
    <searchable-tags labelName="Ingredients"
                      :updateValue="(data) => form.ingredients = data"
                      :data="ingredients"
                      ref="ingrediants-tags"
                      >
    </searchable-tags>

    <searchable-tags labelName="Substitutions"
                      :updateValue="(data) => form.substitutions = data"
                      :data="substitutions">
    </searchable-tags>
  </b-form>
</template>

<script>

import SearchableTags from "../../SearchableTags"

export default {
  name: 'DrugForm',
  components: {
    SearchableTags
  },
  props: ["form"],
  data: function(){
    return {
      receiptOptions: [
        { value: false, text: "Doesn't need receipt"},
        { value: true, text: "Needs receipt"}
      ],
      manufacturerOptions: [],
      drugTypeOptions: [],
      ingredients: [],
      substitutions: [],
    }
  },
  methods: {
    getManufacturers: function(){
      this.$http.get("http://localhost:8081/manufacturers")
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
      this.$http.get("http://localhost:8081/drug-types")
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
    getIngredients: function(){
      this.$http.get("http://localhost:8081/ingredients")
      .then(response => {
        this.ingredients = response.data;
      })
      .catch(error => console.log(error));
    },
    getSubstitutionDrugs: function(){
      this.$http.get("http://localhost:8081/drugs")
      .then(response => {
        this.substitutions = response.data;
      })
      .catch(error => console.log(error));
    }
  },
  mounted: function(){
    this.getManufacturers();
    this.getIngredients();
    this.getSubstitutionDrugs();
    this.getDrugTypes();
  }
}
</script>