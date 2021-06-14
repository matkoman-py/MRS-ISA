<template>
  <b-container align-h="left">
        <b-form>
          <b-form-group label="Name:" label-for="name-input">
            <b-form-input
              id="name-input"
              v-model="form.name"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group label="Surname:" label-for="surname-input">
            <b-form-input
              id="surname-input"
              v-model="form.surname"
              placeholder="Enter surname"
              required
            ></b-form-input>
          </b-form-group>
        <b-form-group>
            <button id="search" v-on:click="onSubmit">Search</button>
        </b-form-group>
        </b-form>
  </b-container>
</template>

<script>

export default {
  name: 'SearchDermatologistForm',
  components: {
  },
  data: function(){
    return {
      form : {
        name: "",
        surname: "",
      },
      dermatologists: [],
    }
  },
  methods : {
    onSubmit(event){
          event.preventDefault();
          this.$http.post("https://mrs-isa-usijani.herokuapp.com//employees/search/dermatologist", this.form)
          .then(response => {
              this.dermatologists = response.data;
              this.$emit("dermatologist-search", this.dermatologists);
          })
          .catch(error => console.log(error))
      }
  }
}
</script>