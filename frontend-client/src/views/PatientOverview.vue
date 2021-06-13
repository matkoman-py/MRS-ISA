<template>
    <b-container>
        <b-row align-h="center">
            
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="patients" ></b-table>
                <h3  v-if="patients.length == 0">Sorry, there are no patients with this name/surname!</h3>
            </b-col>
            
            
        </b-row>
        <b-row align-h="center">
            <b-pagination
                        v-if="patients.length != 0"
                        v-model="currentPage"
                        per-page="5"
                        :total-rows="rows"
            ></b-pagination>
        </b-row>
        <b-row align-h="center">
            <b-col sm="3" md="4" lg="4">
                <div>
                    <b-form @submit="search" v-if="show">
                        <b-form-group id="input-group-2" label="Patient Name:" label-for="input-2">
                            <b-form-input id="input-2" v-model="form.name" placeholder="Enter name"></b-form-input>
                        </b-form-group>

                        <b-form-group id="input-group-4" label="Patient Surname:" label-for="input-4">
                            <b-form-input id="input-4" v-model="form.surname" placeholder="Enter surname">
                            </b-form-input>
                        </b-form-group>
                        <b-button type="submit" variant="outline-hub">Search</b-button>
                    </b-form>

                </div>
            </b-col>
        </b-row>

    </b-container>
</template>

<script>
    export default {
        data: function () {
            return {
                substitutions: [],
                patients: [],
                form: {
                    email: '',
                    name: '',
                    surname: '',
                    food: null,
                    substitutions: [],
                },
                foods: [{
                    text: 'Select One',
                    value: null
                }, 'Bronze', 'Silver', 'Gold'],
                show: true,
                rows: 0,
                currentPage: 1,
            }
        },
        watch:{
            currentPage: function() {
                this.search();
            },
        },
        methods: {
            
            search: function () {
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com/patients/search?page=${this
                        .currentPage - 1}&size=5`, {
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
                                adress: patient.location ? patient.location.address : "Null",
                                country: patient.location ? patient.location.country : "Null",
                                city: patient.location ? patient.location.city : "Null",
                                points: patient.points,
                                category: patient.category? patient.category.name : "Null",
                            }));
                        this.getRows()
                        
                    })
                    .catch(error => console.log(error));
            },
            getRows(){
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com/patients/searchLength`, {
                        params: {
                            patientNameParam: this.form.name,
                            patientSurnameParam: this.form.surname 
                        }
                    })
                    .then(response => {
                        
                        this.rows = response.data;
                        
                    })
                    .catch(error => console.log(error));
            }
        },
        mounted: function () {
            this.search();
        }
    }
</script>

<style>
    #nesting_div {
        text-align: center;
    }
</style>