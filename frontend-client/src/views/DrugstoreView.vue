<template>
    <b-container>
        <b-row style="margin:20px">
            <b-col> <b-img left src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format" alt="Left image" fluid></b-img> </b-col>
            <b-col style="padding-left:25px;padding-top:40px;">
                <div align="left">
                 <p><b>Name</b>: {{drugstore.name}}</p>
                 <p><b>Address</b>: {{drugstore.location.address}}, {{drugstore.location.city}}, {{drugstore.location.country}}</p>
                 <p><b>Description</b>: {{drugstore.description}}</p>
                 <p><b>Average rating</b>: {{drugstore.averageRating}}</p>
                 <p><b>Working hours</b>: {{drugstore.workingHoursFrom}} - {{drugstore.workingHoursTo}}</p>
                </div>
            </b-col>
        </b-row>
        <b-row style="margin:10px"><b-col><b>Pregled zaposlenih</b></b-col></b-row>
        <b-row>
            <b-col>Dermatolozi:</b-col>
            <b-col>Farmaceuti:</b-col>
        </b-row>
        <b-row>
            <b-col> 
                <div style="margin-top:20px; margin-right:20px; margin-left:20px; border-style:solid;">
                    <b-table :items="dermatologists">

                    </b-table>
                </div>
            </b-col>

            <b-col>
                <div style="margin-top:20px; margin-left:20px; margin-right:20px; border-style:solid;">
                    <b-table>

                    </b-table>
                </div>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
            <b-button variant="outline-primary" style="margin:30px">Zakazi sastanak</b-button>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import axios from "axios";

export default {
    
    data: function() {
      return {
        path: '',
        drugstore: {},
        dermatologists: [],
        pharmacists: []
      }
    },
    methods: {
        getDrugstoreId() {
        this.path =  this.$route.path.slice(12, this.$route.path.length);
    },
        getCurrentDrugstore() {
            axios.get('http://localhost:8081/drugstores/' + this.path, {
                    })
                    .then(response => {
                        this.drugstore = response.data;
                    })
                    .catch(error => console.log(error));
        }, getAllDermatologists() {
        axios.get("http://localhost:8081/employment/dermatologist-employments", {
                params: {
                            drugstoreId: this.path
                        }})
            .then(response => {
            this.dermatologists = response.data.map(employment => 
            (
                    {
                        name: employment.name,
                        surname: employment.surname,
                        worksFrom: employment.workingHoursFrom,
                        worksTo: employment.workingHoursTo
                    }
                ));
            })
            .catch(error => console.log(error));
    }, getAllPharmacists() {
        axios.get("http://localhost:8081/employment/pharmacist-employments", {
                params: {
                            drugstoreId: this.path
                        }})
            .then(response => {
            this.pharmacists = response.data.map(employment => 
            (
                    {
                        name: employment.name,
                        surname: employment.surname,
                        worksFrom: employment.workingHoursFrom,
                        worksTo: employment.workingHoursTo
                    }
                ));
            })
            .catch(error => console.log(error));
    }, getAllEmployees() {
            this.getAllDermatologists();
            this.getAllPharmacists();
    }
    },
    mounted: function(){
        this.getDrugstoreId();
        this.getCurrentDrugstore();
        this.getAllEmployees();
    }
  }
</script>
