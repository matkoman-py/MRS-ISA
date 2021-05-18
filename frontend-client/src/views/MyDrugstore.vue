<template>
    <b-container style="padding:80px;">
        <b-row style="margin:20px">
            <b-col> <b-img left src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format" alt="Left image" fluid></b-img> </b-col>
            <b-col style="padding-left:25px;">
                <div align="left" style="margin-top:30px">
                    <p style="margin:20px"><b>Name</b>: {{drugstore.name}}</p>
                    <p style="margin:20px"><b>Address</b>: {{drugstore.location.address}}, {{drugstore.location.city}}, {{drugstore.location.country}}</p>
                    <p style="margin:20px"><b>Description</b>: {{drugstore.description}}</p>
                    <p style="margin:20px"><b>Average rating</b>: {{drugstore.averageRating}}</p>
                    <p style="margin:20px"><b>Working hours</b>: {{drugstore.workingHoursFrom}} - {{drugstore.workingHoursTo}}</p>
                    <p style="margin:20px"><b>Pharmacist appointment price</b>: {{drugstore.pharmacistAppointmentPrice}}</p>
                </div>
            </b-col>
        </b-row>
        <b-row>
            <b-col> 
                <b-button variant="outline-hub" style="margin:20px"> Drugstore reports </b-button> 
            </b-col>
            <b-col> 
                <router-link to="/drugstoreUpdate">
                    <b-button variant="outline-hub" style="margin:20px"> Edit drugstore informations </b-button>
                </router-link>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    },
    data: function() {
      return {
        drugstore: {}
      }
    },
    methods: {
        getMyDrugstore() {
            this.$http.get('http://localhost:8081/drugstores/adminsDrugstore', {
                    params : {
                        adminId : this.user.id
                    }
                    })
                    .then(response => {
                        this.drugstore = response.data;
                    })
                    .catch(error => console.log(error));
        }
    },
    mounted: function(){
        this.getMyDrugstore();
    }
  }
</script>
