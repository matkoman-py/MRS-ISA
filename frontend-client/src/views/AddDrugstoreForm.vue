<template>
    <b-container>
        <drugstore-basic-info :form="drugstore" mode="add"></drugstore-basic-info>
        <b-button type="submit" style="margin:30px; width:25%" variant="outline-hub" size="lg" v-on:click="submitHandler">Save</b-button>
    </b-container>
</template>


<script>
import DrugstoreBasicInfo from '../components/DrugstoreBasicInfo.vue'
  export default {
  components: { DrugstoreBasicInfo },
    data: function() {
      return {
        drugstore: {
          name: "",
          description: "",
          location: {
            address: "",
            city: "",
            country: ""
          },
          workingHoursFrom: "",
          workingHoursTo: "",
          pharmacistAppointmentPrice: null,
          point: {
            longitude: null,
            latitude: null
          }
        }
      };
    },

    methods:{
        submitHandler: function(){
            this.$http.post("http://localhost:8081/drugstores", this.drugstore)
            .then(response => {
                console.log(response);
                this.$toastr.s("Succesfully added drugstore");
            })
            .catch(error => console.log(error));
        }
    }

  }
</script>