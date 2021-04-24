<template>
  <b-container class="form-container">
    <b-row>
      <b-col>
        <drug-form :form="form"></drug-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>

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
      this.$http.put("http://localhost:8081/drugs", this.form)
      .then(response => {
        alert("drug edited successfully");
        console.log(response);
      })
      .catch(error => console.log(error));
    }
  }
}
</script>