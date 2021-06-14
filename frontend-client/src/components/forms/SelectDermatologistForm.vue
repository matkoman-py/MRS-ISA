<template>
  <b-container align-h="center">
    <b-row>
      <b-col>
        <search-dermatologist-form v-on:dermatologist-search="updateDermatologists"></search-dermatologist-form>
      </b-col>
      <b-col cols-lg="8">
        <b-form>
            <b-table id="dermatologist-table" striped hover :items="dermatologists" select-mode="single" @row-clicked="onRowClicked"></b-table>
            <select v-model="form.drugstoreId">
                <option v-for="drugstore in drugstores" :value="drugstore.id" :key="drugstore.id">{{drugstore.name}}</option>
            </select>
            <button v-on:click="addDermatologist">Add Dermatologist</button>
        </b-form>
      </b-col>
    </b-row>
    
  </b-container>
</template>

<script>
import SearchDermatologistForm from './SearchDermatologistForm.vue';

export default {
  name: 'SelectDermatologistForm',
  components: {
    SearchDermatologistForm
  },
  data: function(){
    return {
      form : {
        dermatologistEmail: "",
        drugstoreId: "",
      },
      dermatologists: [],
      drugstores: []
    }
  },
  methods : {
    updateDermatologists: function(data){
        this.dermatologists = data;
        this.dermatologists.forEach(dermatologist => this.mapSingleDermatologist(dermatologist));
    },
    mapSingleDermatologist: function(dermatologist){
        let drugstoresArray = [];
        for(let drugstore of dermatologist.drugstores){
          drugstoresArray.push(drugstore.drugstore.name);
        }
        if (drugstoresArray.length > 1){
          dermatologist.drugstores = drugstoresArray.join(', ');
        } else {
          dermatologist.drugstores = drugstoresArray[0];
        }
        return dermatologist;
    },
    onRowClicked: function(row){
        console.log(row)
        this.form.dermatologistEmail = row.email; 
    },
    addDermatologist: function(event){
        event.preventDefault();
        this.$http.post("https://mrs-isa-usijani.herokuapp.com//employees/employement", this.form)
          .then(response => {
              let updatedDermatologist = response.data;
              let index = this.dermatologists.findIndex(dermatologist => dermatologist.email == updatedDermatologist.email);
              console.log(index);
              this.dermatologists.splice(index, 1, this.mapSingleDermatologist(updatedDermatologist));
              alert("Success");
              console.log(this.dermatologists);
          })
          .catch(error => console.log(error));
    },
    getDrugstores: function(){
        this.$http.get("https://mrs-isa-usijani.herokuapp.com//drugstores")
          .then(response => {
              this.drugstores = response.data;
          })
          .catch(error => console.log(error));
    },
    getDermatologist: function(){
      this.$http.post("https://mrs-isa-usijani.herokuapp.com//employees/search/dermatologist", this.form)
          .then(response => {
              this.updateDermatologists(response.data);
          })
          .catch(error => console.log(error));
    }

  },
  mounted: function(){
      this.getDrugstores();
      this.getDermatologist();
  }
}
</script>