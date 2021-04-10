<template>
  <b-container style="width: 40%" align-h="center">
    <b-row>
      <b-col>
        <drug-form :form="form"></drug-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>

import axios from "axios"
import DrugForm from './DrugForm'

export default {
  name: 'EditDrugForm',
  components: {
    DrugForm
  },
  props: ["form"],
  data: function(){
    return {
      
    }
  },
  methods: {
    editDrug: function(event){
      event.preventDefault();
      axios.put("http://localhost:8081/drugs", this.form)
      .then(response => {
        alert("drug edited successfully");
        console.log(response);
      })
      .catch(error => console.log(error));
    }
  }
}
</script>