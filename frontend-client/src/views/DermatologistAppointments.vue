<template>
    <b-container>
        <b-table v-if="appointments.length > 0" :items="appointments" :fields="fields">
            <template #cell(actions)="row">
                <b-button variant="outline-info" v-if="row.item" size="sm" @click="info(row.item, row.index, $event.target)" class="mr-1">
                    Reserve
                </b-button>
            </template>
        </b-table>
        <h1 v-if="appointments.length == 0">No dermatology appointments available</h1>
    </b-container>
</template>


<script>
    import axios from "axios";

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
                axios.get('http://localhost:8081/dermatologist-appointment/search', {
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
                    })
            }
        },
        mounted: function () {
            this.getAllAppointments();
        }
    }
</script>