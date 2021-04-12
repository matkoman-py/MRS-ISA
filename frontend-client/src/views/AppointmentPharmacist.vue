<template>
    <b-container  style="margin-top:3rem;" id="appinfo">
        <b-row>
            <b-col style="text-align:left">
                <h1>Appointment info</h1>
                <!-- kako odraditi ovo ovde?, farmaceut jos nije vezan na drugstore                -->
                <!-- <h2>Drugstore: {{currentAppointment.pharmacist.drugstore.name}} </h2> -->
                <h2>Patient: {{currentAppointment.patient.name}} </h2>
                <h2>Pharmacist: {{currentAppointment.pharmacist.name}} </h2>
            </b-col>
            <b-col style="text-align:center">
                <label for="diagnosis"> <h3>Diagnosis:</h3></label>
                <br>
                <textarea id="diagnosis" name="diagnosis" rows="10" cols="50" style="resize: none">

                </textarea>
            </b-col>
        </b-row>
        <b-row style="margin-top:140px">
            <b-col>
                <b-button variant="info">Reserve drug</b-button>
            </b-col>
            <b-col>
                <b-button variant="info" @click="showAppointmentModal">New appointment</b-button>
            </b-col>
            <b-col>
                <b-button variant="success">End appointment</b-button>
            </b-col>
            <b-col>
                <b-button variant="danger">Absent patient</b-button>
            </b-col>
        </b-row>
        
        <b-modal id="appointmentmodal" size="xl" title="New appointment" hide-footer>
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

                <b-button type="button" variant="primary" @click="addNewApointment">Save</b-button>
                <b-button type="button" variant="danger" @click="handleClose" >Cancel</b-button>

            </b-form>
        </b-modal>
        
    </b-container>
</template>

<script>
import axios from "axios";
export default {
    name: "AppointmentPharmacist",
    
    data: function () {
            return {
                currentAppointment: {},
                inputValues: {
                    pharmacistId: '',
                    date: '',
                    time: '',
                    duration: '',
                    patientId: '',
                }
            }
    },
    methods: {
        beginAppointment: function(){
            this.currentAppointment.id = "2229dbcb-7f36-4e4e-81f5-656ce14a553a";
            axios.get('http://localhost:8081/pharmacist-appointment/begin-appointment', {
                        params: {
                            pharmacistAppointmentId: this.currentAppointment.id
                        }
                    })
                    .then(response => {
                        this.currentAppointment = response.data;  
                    })
                    
        },
        showAppointmentModal(){
            this.$root.$emit('bv::show::modal', 'appointmentmodal');
            
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'appointmentmodal');
        },
        addNewApointment(event) {
          event.preventDefault();
          this.inputValues.patientId = this.currentAppointment.patient.id;
          this.inputValues.pharmacistId = this.currentAppointment.pharmacist.id;
          //this.inputValues.pharmacistId = "ccb953a7-d244-48bb-8627-4b2437491dc1";
          //this.inputValues.patientId = "8128d806-c29b-4086-aae6-877d17eeb6fa";
          axios.post("http://localhost:8081/pharmacist-appointment/with-patient", JSON.parse(JSON.stringify(this.inputValues)))
              .then(response => {
              console.log(response);
              alert("New appointment is successfully created.");
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'appointmentmodal');
        },
    },
    mounted: function() {
        //ovo se treba prepraviti da preko id nabavi appointment i onda popuni sve
        this.beginAppointment();
        
    },
    
}
</script>

<style>

</style>