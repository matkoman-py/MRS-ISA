<template>
    <b-container>
        <drugstore-basic-info :form="form" :submitHandler="submitHandler" mode="add"></drugstore-basic-info>
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