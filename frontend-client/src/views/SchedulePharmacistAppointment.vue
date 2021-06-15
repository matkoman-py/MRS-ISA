<template>
    <b-container>
        <b-card>
            <h1 v-if="employees.length == 0 & drugstores.length == 0">Choose a date and time for your appointment</h1>
            <h1 v-if="employees.length == 0 & drugstores.length != 0">Choose a drugstore for your appointment</h1>
            <h1 v-if="employees.length != 0 & drugstores.length != 0">Choose a pharmacist for your appointment</h1>

            <b-form v-if="employees.length == 0 & drugstores.length == 0" @submit="showDrugstores">
                <b-form-group label="Pharmacist appointment date" label-for="date-picker"
                    invalid-feedback="Appointment date is required">
                    <b-form-datepicker id="date-input" v-model="inputValues.date" :min="min" required>
                    </b-form-datepicker>
                </b-form-group>

                <b-form-group label="Pharmacist appointment time" label-for="time-picker"
                    invalid-feedback="Appointment time is required">
                    <b-form-timepicker v-model="inputValues.time" locale="en" required>
                    </b-form-timepicker>
                </b-form-group>
                <b-button type="submit" variant="outline-hub">Search</b-button>
            </b-form>
            <br>
            <h1 v-if="searched == 1 & drugstores.length == 0">Sorry, there are no available pharmacists at this time.
            </h1>

            <b-table id="my-table1" striped hover v-if="employees.length == 0 & drugstores.length != 0" :current-page="currentPage1" :per-page="4" :items="drugstores"
                :fields="fieldsDrugstores" @row-clicked="getPharmacists">
            </b-table>
            <b-pagination v-model="currentPage" v-if="employees.length == 0 & drugstores.length != 0" :total-rows="drugstores.length" :per-page="4" aria-controls="my-table1">
            </b-pagination>

            <b-table id="my-table" striped hover v-if="employees.length != 0" :current-page="currentPage" :items="employees" :per-page="4" :fields="fieldsPharmacists"
                @row-clicked="saveAppointment">
            </b-table>
            <b-pagination v-model="currentPage" v-if="employees.length != 0" :total-rows="employees.length" :per-page="4" aria-controls="my-table">
            </b-pagination>
        </b-card>
    </b-container>
</template>

<script>
    import {
        mapState
    } from 'vuex'
    export default {
        data: function () {
            const now = new Date()
            const today = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1)
            const minDate = new Date(today)
            return {
                currentPage1: 1,
                currentPage: 1,
                searched: 0,
                drugstores: [],
                employees: [],
                min: minDate,
                inputValues: {
                    time: '',
                    date: ''
                },
                saveData: {
                    pharmacistId: '',
                    date: '',
                    time: '',
                    duration: 30,
                    patientId: '',
                },
                fieldsDrugstores: [{
                        key: 'name'
                    },
                    {
                        key: 'adress'
                    },
                    {
                        key: 'city'
                    },
                    {
                        key: 'pharmacistAppointmentPrice',
                        label: "Appointment price",
                        sortable: true,
                    },
                    {
                        key: 'rating',
                        sortable: true
                    }
                ],
                fieldsPharmacists: [{
                        key: 'name'
                    },
                    {
                        key: 'surname'
                    },
                    {
                        key: 'rating',
                        sortable: true,
                    }
                ]
            }
        },
        methods: {
            saveAppointment: function (data) {
                this.saveData.pharmacistId = data.id;
                this.saveData.date = this.inputValues.date;
                this.saveData.time = this.inputValues.time;
                this.saveData.patientId = this.user.id;
                this.$http.post("https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/with-patient", JSON.parse(JSON
                        .stringify(
                            this.saveData)))
                    .then(response => {
                        console.log(response);
                        this.$toastr.s("New appointment is successfully created.")
                        this.$router.push({
                            path: 'patient'
                        });
                    })
                    .catch(error => {
                        this.$toastr.e("You already have an appointment at that time.")
                        console.log(error);
                    })
            },
            getPharmacists: function (data) {
                this.$http.get('https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/get-pharmacists', {
                        params: {
                            drugstoreId: data.id,
                            pharmacistAppointmentDate: this.inputValues.date,
                            pharmacistAppointmentTime: this.inputValues.time,
                        }
                    })
                    .then(response => {
                        this.employees = response.data.map(employee =>
                            ({
                                id: employee.id,
                                name: employee.name,
                                surname: employee.surname,
                                rating: employee.rating
                            }));
                    })
                    .catch(error => console.log(error));
            },
            showDrugstores: function (event) {
                event.preventDefault();
                if (this.inputValues.date == "" || this.inputValues.time == "") return;
                this.searched = 1;
                this.$http.get('https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/get-drugstores', {
                        params: {
                            pharmacistAppointmentDate: this.inputValues.date,
                            pharmacistAppointmentTime: this.inputValues.time,
                        }
                    })
                    .then(response => {
                        this.drugstores = response.data.map(drugstoreDto =>
                            ({
                                id: drugstoreDto.id,
                                name: drugstoreDto.name,
                                adress: drugstoreDto.location.address,
                                country: drugstoreDto.location.country,
                                city: drugstoreDto.location.city,
                                description: drugstoreDto.decription,
                                rating: drugstoreDto.rating,
                                pharmacistAppointmentPrice: drugstoreDto.pharmacistAppointmentPrice
                            }));
                    })
                    .catch(error => console.log(error));
            },

        },
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
                email: state => state.userModule.loggedInUser.email,
                role: state => state.userModule.loggedInUser.type
            }),
        }
    }
</script>