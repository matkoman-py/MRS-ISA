<template>
    <b-container>
        <b-card>            
            <h1 v-if="employees.length == 0">Choose a date and time for your appointment</h1>
            <h1 v-if="employees.length != 0">Choose a pharmacist for your appointment</h1>

            <b-form v-if="employees.length == 0" @submit="getPharmacists">
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
                <h1 v-if="searched == 1 && employees.length == 0">Sorry, there are no available pharmacists at this time.</h1>
            <b-table striped hover v-if="employees.length != 0" :items="employees" :fields="fieldsPharmacists"
                @row-clicked="saveAppointment"></b-table>
        </b-card>
    </b-container>
</template>

<script>
    import {
        mapState
    } from 'vuex'
    export default {
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
                email: state => state.userModule.loggedInUser.email,
                role: state => state.userModule.loggedInUser.type
            }),
        },
        data: function () {
            const now = new Date()
            const today = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1)
            const minDate = new Date(today)
            return {
                searched : 0,
                drugstoreId: '',
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
            getDrugstoreId() {
            this.drugstoreId = this.$route.path.slice(
                30,
                this.$route.path.length
            );
        },
            saveAppointment: function (data) {
                this.saveData.pharmacistId = data.id;
                this.saveData.date = this.inputValues.date;
                this.saveData.time = this.inputValues.time;
                this.saveData.patientId = this.user.id;
                this.$http.post("http://localhost:8081/pharmacist-appointment/with-patient", JSON.parse(JSON
                        .stringify(
                            this.saveData)))
                    .then(response => {
                        console.log(response);
                        this.$toastr.s("New appointment is successfully created.")
                        this.$router.push({
                            path: '/drugstores/' + this.drugstoreId
                        });
                    })
                    .catch(error => {
                        this.$toastr.e("You already have an appointment at that time.")
                        this.$router.push({
                            path: '/drugstores/' + this.drugstoreId
                        });
                        console.log(error);
                    })
            },
            getPharmacists: function () {
                this.searched = 1;
                this.$http.get('http://localhost:8081/pharmacist-appointment/get-pharmacists', {
                        params: {
                            drugstoreId: this.drugstoreId,
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
                                email: employee.email,
                                phoneNumber: employee.phoneNumber,
                                address: employee.location ? employee.location.address : null,
                                city: employee.location ? employee.location.city : null,
                                country: employee.location ? employee.location.country : null,
                                type: employee.type,
                            }));
                    })
                    .catch(error => console.log(error));
            },
        }, mounted : function() {
            this.getDrugstoreId();
        }
    }
</script>