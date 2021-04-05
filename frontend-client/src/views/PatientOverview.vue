<template>
    <b-container>
        <b-row>
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="patients"></b-table>
            </b-col>
        </b-row>
        <b-row align-h="center">
            <b-col sm="3" md="4" lg="4">
                <div>
                    <b-form @submit="search" v-if="show">
                    <!-- <b-form-group
                        id="input-group-1"
                        label="Email address:"
                        label-for="input-1"
                    >
                        <b-form-input
                        id="input-1"
                        v-model="form.email"
                        type="email"
                        placeholder="Enter email"
                        required
                        ></b-form-input>
                    </b-form-group> -->

                    <b-form-group id="input-group-2" label="Patient Name:" label-for="input-2">
                        <b-form-input
                        id="input-2"
                        v-model="form.name"
                        placeholder="Enter name"
                        ></b-form-input>
                    </b-form-group>
                    
                    <b-form-group id="input-group-4" label="Patient Surname:" label-for="input-4">
                        <b-form-input
                        id="input-4"
                        v-model="form.surname"
                        placeholder="Enter surname"
                        ></b-form-input>
                    </b-form-group>

                    <!-- <b-form-group id="input-group-3" label="Category:" label-for="input-3">
                        <b-form-select
                        id="input-3"
                        v-model="form.food"
                        :options="foods"
                        required
                        ></b-form-select>
                    </b-form-group> -->


                    <b-button type="submit" variant="primary">Search</b-button>
                    </b-form>
                    
                </div>
            </b-col>
        </b-row>
        
    </b-container>
</template>

<script>
import axios from "axios";
export default {
    
    data: function() {
        return{
        patients:[],
        form: {
          email: '',
          name: '',
          surname: '',
          food: null
        },
        foods: [{ text: 'Select One', value: null }, 'Bronze', 'Silver', 'Gold'],
        show: true
        }
    },
    methods: {
      search: function() {
                axios.get('http://localhost:8081/patients/search', {
                        params: {
                            patientNameParam: this.form.name,
                            patientSurnameParam: this.form.surname
                        }
                    })
                    .then(response => {
                        this.patients = response.data.map(patient =>
                            ({
                                name: patient.name,
                                surname: patient.surname,
                                email: patient.email,
                                phoneNumber: patient.phoneNumber,
                                adress:  patient.location? patient.location.address : "Null",
                                country: patient.location? patient.location.country:"Null",
                                city: patient.location? patient.location.city:"Null",
                                points: patient.points,
                                category: patient.category,
                            }));
                    })
                    .catch(error => console.log(error));
        },
        getAllPatients: function(){
            axios.get('http://localhost:8081/patients')
                    .then(response => {
                        this.patients = response.data.map(patient =>
                            ({
                                name: patient.name,
                                surname: patient.surname,
                                email: patient.email,
                                phoneNumber: patient.phoneNumber,
                                adress:  patient.location? patient.location.address : "Null",
                                country: patient.location? patient.location.country:"Null",
                                city: patient.location? patient.location.city:"Null",
                                points: patient.points,
                                category: patient.category,
                            }));
                    })
                    .catch(error => console.log(error));
            
        }
    },
    mounted: function (){
        this.getAllPatients();
    }
}
</script>

<style>
#nesting_div{
    text-align: center;
}
</style>