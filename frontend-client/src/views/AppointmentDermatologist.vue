<template>
    <b-container  style="margin-top:3rem;" id="appinfo">
        <b-row>
            <b-col style="text-align:left">
                <h1>Appointment info</h1>
                <h2>Drugstore: {{currentAppointment.drugstore.name}}</h2>
                <h2>Patient: {{currentAppointment.patient.name}}  {{currentAppointment.patient.surname}}</h2>
                <h2>Dermatologist: {{currentAppointment.dermatologist.name}}</h2>
                <h2>Duration: {{currentAppointment.duration}} minutes</h2>
            </b-col>
            <b-col style="text-align:center">
                <label for="diagnosis"> <h3>Diagnosis:</h3></label>
                <br>
                <textarea id="diagnosis" v-model="currentAppointment.appointmentReport" name="diagnosis" rows="10" cols="50" style="resize: none">

                </textarea>
            </b-col>
        </b-row>
        <b-row style="margin-top:140px">
            <!-- <div style="margin:0px;">
                <b-button variant="info">Reserve drug</b-button>
                <b-button variant="info" @click="showModal">New appointment</b-button>
                <b-button variant="success">End appointment</b-button>
                <b-button variant="danger">Absent patient</b-button>
            </div> -->
            
            <b-col>
                <b-button variant="outline-hub">Reserve drug</b-button>
            </b-col>
            <b-col>
                <b-button variant="outline-hub" @click="showAppointmentModal">New appointment</b-button>
            </b-col>
            <b-col>
                <b-button variant="outline-hub" @click="endAppointment">End appointment</b-button>
            </b-col>
        </b-row>
        
        <b-modal id="appointmentmodal" size="xl" title="New appointment" hide-footer>
            <h1>Available appointments</h1>
            <b-table v-if="appointments.length > 0" :items="appointments" :fields="fields">
            <template #cell(actions)="row">
                <b-button variant="outline-hub" v-if="row.item" size="sm" @click="createReservation(row.item, row.index, $event.target)" class="mr-1">
                    Reserve
                </b-button>
            </template>
            </b-table>
            <h1 v-if="appointments.length == 0">No dermatology appointments available</h1>
            <hr>
            <h1>Create appointment</h1>
            <b-form>

                <b-form-group
                label="Appointment date"
                label-for="date-picker"
                invalid-feedback="Appointment date is required">
                    <b-form-datepicker
                    id="date-input"
                    v-model="inputValues.date"
                    :min="minDate"
                    required >
                    </b-form-datepicker>
                </b-form-group>

                <b-form-group
                label="Appointment time"
                label-for="time-picker"
                invalid-feedback="Appointment time is required">
                    <b-form-timepicker
                    v-model="inputValues.time"
                    locale="en">
                    </b-form-timepicker>
                </b-form-group>

                <b-form-group
                label="Appointment duration(in minutes)"
                label-for="duration-picker"
                invalid-feedback="Appointment duration is required">
                    <b-form-input
                    id="duration-input"
                    v-model="inputValues.duration"
                    type="number"
                    required >
                    </b-form-input>
                </b-form-group>

                <b-form-group
                label="Appointment price(in RSD)"
                label-for="price-picker"
                invalid-feedback="Appointment price is required">
                    <b-form-input
                    id="price-input"
                    v-model="inputValues.price"
                    type="number"
                    required >
                    </b-form-input>
                </b-form-group>

                <b-button type="button" variant="outline-hub" @click="addNewApointment">Save</b-button>
                <b-button type="button" variant="outline-hub" @click="handleClose" >Cancel</b-button>

            </b-form>
        </b-modal>
        
    </b-container>
</template>

<script>

export default {
    name: "AppointmentDermatologist",
    props:{
        passedId: String,
    },
    data: function () {
            const now = new Date()
            const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
            const minDate = new Date(today)
            return {
                currentAppointment: {},
                minDate:minDate,
                appointments: [],
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
                ],
                inputValues: {
                    dermatologistId: '',
                    drugstoreId: '',
                    date: '',
                    time: '',
                    duration: '',
                    patientId: '',
                    price: 0,
                }
            }
    },
    methods: {
        endAppointment: function(){
            this.currentAppointment.id = this.passedId? this.passedId:"4a73ae19-2001-450a-a050-85f51717ab76";
            this.$http.get('http://localhost:8081/dermatologist-appointment/end-appointment', {
                        params: {
                            dermatologistAppointmentId: this.currentAppointment.id,
                            appointmentReport: this.currentAppointment.appointmentReport,
                        }
                    })
                    .then(response => {
                        this.currentAppointment = response.data; 
                        this.$router.push({ name: 'Schedule' }) 
                    })
            
        },
        beginAppointment: function(){
            this.currentAppointment.id = this.passedId? this.passedId:"4a73ae19-2001-450a-a050-85f51717ab76";
            this.$http.get('http://localhost:8081/dermatologist-appointment/begin-appointment', {
                        params: {
                            dermatologistAppointmentId: this.currentAppointment.id
                        }
                    })
                    .then(response => {
                        this.currentAppointment = response.data;  
                    })
                    
        },
        showAppointmentModal(){
            this.getAllAppointments();
            this.$root.$emit('bv::show::modal', 'appointmentmodal');
            
        },
        getAllAppointments: function () {
                this.$http.get('http://localhost:8081/dermatologist-appointment/available', {
                        params: {
                            drugstoreId: this.currentAppointment.drugstore.id,
                            dermatologistId: this.currentAppointment.dermatologist.id
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
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'appointmentmodal');
        },
        addNewApointment(event) {
          event.preventDefault();
          this.inputValues.drugstoreId = this.currentAppointment.drugstore.id;
          this.inputValues.patientId = this.currentAppointment.patient.id;
          this.inputValues.dermatologistId = this.currentAppointment.dermatologist.id;
          this.$http.post("http://localhost:8081/dermatologist-appointment/with-patient", JSON.parse(JSON.stringify(this.inputValues)))
              .then(response => {
              console.log(response);
              alert("New appointment is successfully created.");
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'appointmentmodal');
        },
        createReservation :function(item){
                this.$http.get('http://localhost:8081/dermatologist-appointment/reserveAppointment', {
                        params: {
                            drugstoreId : this.currentAppointment.drugstore.id,
                            patientId: this.currentAppointment.patient.id,
                            appointmentId: item.appointmentId,
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
                    });
                this.$root.$emit('bv::hide::modal', 'appointmentmodal');

        },
    },
    mounted: function() {
        //ovo se treba prepraviti da preko id nabavi appointment i onda popuni sve
        this.beginAppointment();
        
    },
    
}
</script>
