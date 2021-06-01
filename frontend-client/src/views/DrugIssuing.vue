<template>
    <b-container>
        <b-modal id="my-modal" title="Drug issuing" hide-footer>
            <b-form @submit="onSubmit">
                
                <b-form-group label="Reservation ID" label-for="reservationid-input" invalid-feedback="Reservation id is requred">
                            <b-form-input id="reservationid-input" type="text" v-model="confirmationCode" placeholder="Enter reservation id" required></b-form-input>
                </b-form-group>

                <b-button type="submit" variant="outline-hub" >Issue drug</b-button>
                <b-button type="button" variant="outline-hub" @click="handleClose">Cancel</b-button>
            </b-form>
        </b-modal>
 
        <b-row>
            <b-col>
                <b-table v-if="showTable" id="tabela_rezervacija" striped hover :fields="fields" :items="reservations" @row-clicked="showModal"></b-table>
                <h1 v-if="noResults">There are no reservations of patient in this drugstore!</h1>
            </b-col>
        </b-row>
        <b-row align-h="center">
            <b-col sm="3" md="4" lg="4">
                <div>
                    <b-form @submit="search">

                        <b-form-group label="E-mail" label-for="email1-input" invalid-feedback="E-mail is required">
                            <b-form-input id="email1-input" type="email" v-model="form.email" placeholder="Enter email"></b-form-input>
                        </b-form-group>

                        
                        <b-button type="submit" variant="outline-hub">Search</b-button>
                    </b-form>

                </div>
            </b-col>
        </b-row>

    </b-container>
</template>

<script>
    //import SearchableTags from "../components/SearchableTags"
    import {
    mapState
  } from 'vuex'
    export default {
        components: {
            //SearchableTags
        },
        computed: {
        ...mapState({
            user: state => state.userModule.loggedInUser,
            email: state => state.userModule.loggedInUser.email,
            role: state => state.userModule.loggedInUser.type
            }),
            // routes: function(){
            // return (this.$helpers.isObjectEmpty(this.user)) ? this.commonRoutes : [...this.commonRoutes, ...this.userSpecificRoutes[this.role]];
            // }
        },
        data: function () {
            return {
                form: {
                    email: '',
                    name: '',
                    surname: '',
                    food: null,
                    substitutions: [],
                },
                fields: [
                    {
                        key: 'name'
                    },
                    {
                        key: 'surname'
                    },
                    {
                        key: 'email'
                    },
                    {
                        key: 'drug'
                    },
                    {
                        key: 'date',
                        sortable: true
                    },
                ],
                reservations: [],
                noResults: false,
                showTable: false,
                reservationId: '',
                confirmationCode: '',
            }
        },
        
        methods: {
            search: function () {
                this.$http.get('http://localhost:8081/drugReservation/getPatientReservationsInDrugstore', {
                        params: {
                            patientEmail: this.form.email,
                            pharmacistId: this.user.id 
                        }
                    })
                    .then(response => {
                        this.reservations = response.data.map(reservation =>
                            ({
                                name: reservation.patient.name,
                                surname: reservation.patient.surname,
                                email: reservation.patient.email,
                                drug: reservation.drug.name,
                                date: reservation.date,
                                id: reservation.id,
                            }));

                        if(this.reservations.length == 0){
                            this.noResults = true;
                            this.showTable = false;
                        }else{
                            this.noResults = false;
                            this.showTable = true;
                        }
                    })
                    .catch(error => console.log(error));
            },
            onSubmit(event) {
                event.preventDefault();
                this.$http.get('http://localhost:8081/drugReservation/issueReservation', {
                        params: {
                            reservationId: this.reservationId,
                            confirmationCode: this.confirmationCode
                        }
                    })
                    .then(response => {
                        alert(response.data);
                        this.search();
                    })
                    .catch(error => console.log(error));
            },
            handleClose() {
                this.$root.$emit('bv::hide::modal', 'my-modal');
            },
            showModal(item) {
                this.reservationId = item.id;
                this.$root.$emit('bv::show::modal', 'my-modal');
            },
           
        },
        
    }
</script>

<style>
    #nesting_div {
        text-align: center;
    }
</style>