<template>
    <b-container style="padding:80px;">
        <b-row style="margin:20px">
            <b-col> <b-img left src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format" alt="Left image" fluid></b-img> </b-col>
            <b-col style="padding-left:25px;">
                <div align="left" style="margin-top:20px">
                    <p style="margin:20px"><b>Name</b>: {{drugstore.name}}</p>
                    <p style="margin:20px"><b>Address</b>: {{drugstore.location.address}}, {{drugstore.location.city}}, {{drugstore.location.country}}</p>
                    <p style="margin:20px"><b>Description</b>: {{drugstore.description}}</p>
                    <p  style="margin:20px"><b>Average rating</b>: {{ drugstore.rating.toFixed(2) }}</p>
                    
                    <p style="margin:20px"><b>Working hours</b>: {{drugstore.workingHoursFrom}} - {{drugstore.workingHoursTo}}</p>
                    <p style="margin:20px"><b>Pharmacist appointment price</b>: {{drugstore.pharmacistAppointmentPrice}}</p>
                </div>
            </b-col>
        </b-row>
        <b-row>
            <b-col> 
                <router-link to="/drugstore-reports">
                    <b-button variant="outline-hub" style="margin:20px"> Drugstore reports </b-button> 
                </router-link>
            </b-col>
            <b-col> 
                <router-link to="/pharmacist-absence-requests">
                    <b-button variant="outline-hub" style="margin:20px"> Absence requests </b-button>
                </router-link>
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
        drugstore: {
                name: "",
                location: {
                    address: "",
                    city: "",
                    country: ""
                },
                employements: "",
                drugStock: "",
                rating: 0,
                workingHoursFrom: "",

                workingHoursTo: "",
                pharmacistappointmentPrice: ""
            },
       
      }
    },
    methods: {
        getMyDrugstore() {
            this.$http.get('https://mrs-isa-usijani.herokuapp.com/drugstores/adminsDrugstore', {
                    params : {
                        adminId : this.user.id
                    }
                    })
                    .then(response => {
                        this.drugstore = response.data;
                        console.log(response.data)
                        this.drugstore.workingHoursFrom? this.drugstore.workingHoursFrom.slice(0,5) : this.drugstore.workingHoursFrom;
                        this.drugstore.workingHoursTo? this.drugstore.workingHoursTo.slice(0,5) : this.drugstore.workingHoursTo;
                    })
                    .catch(error => console.log(error));
        },
    },
    mounted: function(){
        this.getMyDrugstore();
    }
  }
</script>
