<template>
    <b-container>
        <b-table v-if="appointments.length > 0" :items="appointments" :fields="fields">
            <template #cell(actions)="row">
                <b-button variant="outline-hub" v-if="row.item" size="sm" @click="createReservation(row.item, row.index, $event.target)" class="mr-1">
                    Reserve
                </b-button>
            </template>
        </b-table>
        <h1 v-if="appointments.length == 0">No dermatology appointments available</h1>
    </b-container>
</template>


<script>

    export default {

        data: function () {
            return {
                appointments: [],
                drugstoreId: this.$route.params.id,
                fields: [
                   
                    {
                        key: 'dermatologist',
                    },
                    {
                        key: 'drugstore',
                    },
                    {
                        key: 'date',
                    },
                    {
                        key: 'time',
                    },
                    {
                        key: 'duration',
                    },
                    {
                        key: 'actions',
                        label: ''
                    }
                ]
            }
        },
        methods: {
            info(item) {
                console.log(item)
            },
            getAllAppointments: function () {
                this.$http.get('http://localhost:8081/dermatologist-appointment/search', {
                        params: {
                            drugstoreId: this.drugstoreId,
                        }
                    })
                    .then(response => {
                        this.appointments = response.data.map(appointment =>
                            ({
                                appointmentId: appointment.id,
                                dermatologist: appointment.dermatologist.name,
                                drugstore: appointment.drugstore.name,
                                date: appointment.date.substring(0, 10),
                                time: appointment.time,
                                duration: appointment.duration,
                                patient: appointment.patient ? appointment.patient.name : "Available",
                            }));
                    }).catch(error => console.log(error));
            },
            createReservation :function(item){
                this.$http.get('http://localhost:8081/dermatologist-appointment/createReserrvation', {
                        params: {
                            appointmentId : item.appointmentId,
                            patientId: '8128d806-c29b-4086-aae6-877d17eeb6fa',
                            drugstoreId: this.drugstoreId,
                        }
                    })
                    .then(response => {
                        this.appointments = response.data.map(appointment =>
                            ({
                                appointmentId: appointment.id,
                                dermatologist: appointment.dermatologist.name,
                                drugstore: appointment.drugstore.name,
                                date: appointment.date.substring(0, 10),
                                time: appointment.time,
                                duration: appointment.duration,
                                patient: appointment.patient ? appointment.patient.name : "Available",
                            }));
                            //this.getAllAppointments();
                    }).then(this.$toastr.s("You have succesfully reserved a dermatologist appointment"))
                    .catch(error => console.log(error));
            },
        },
        mounted: function () {
            this.getAllAppointments();
        }
    }
</script>