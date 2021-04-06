<template>
    <b-container>
        <drugstore-basic-info :form="form" mode="add"></drugstore-basic-info>
        <b-button type="submit" style="margin:30px; width:25%" variant="outline-primary" size="lg" v-on:click="submitHandler">Save</b-button>
    </b-container>
</template>


<script>
import axios from 'axios';
import DrugstoreBasicInfo from '../components/DrugstoreBasicInfo.vue'
  export default {
  components: { DrugstoreBasicInfo },
    data: function() {
      return {
        form: {
          name: "",
          description: "",
          location: {
            address: "",
            city: "",
            country: ""
          }
        }
      };
    },

    methods:{
        submitHandler: function(){
            axios.post("http://localhost:8081/drugstores", this.form)
            .then(response => {
                console.log(response);
                alert("Successfully added drugstore");
            })
            .catch(error => console.log(error));
        }
    }

  }
</script>