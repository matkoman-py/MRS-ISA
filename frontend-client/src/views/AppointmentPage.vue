<template>
  <b-container>


      
      
      <b-modal id="my-modal" hide-footer title="Start appointment">
        <div class="d-block text-center">
          <p>Patient: {{selected.patient.name}} {{selected.patient.surname}}</p>
          
          
          <p>Drugstore: {{selected.drugstore.name}}</p>
          <p>Start: {{selected.start}}</p>
          <p>Duration: {{selected.duration}} minutes</p>
        </div>
        <b-button class="mt-3" variant="outline-hub" :disabled="!timeValid" block @click="patientNotShowup"  >Patient did not show up!</b-button>
        <b-button class="mt-2" variant="outline-hub" :disabled="!timeValid" block @click="startApp">Start appointment!</b-button>
      </b-modal>
     
      <b-row align-h="center" style="margin: 20px;" >
            <b-form-select style="width:30%;margin: 2px;" v-model="selectedOption" :options="options" ></b-form-select>
            <b-button                                  
                                                    style="margin: 2px;"
                                                    variant="outline-hub"
                                                    size="sm"
                                                    class="mr-1"
                                                    @click="sort"
                                                >
                                                    Sort
                                                </b-button>
      </b-row>
      <b-row>
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="appointments" :fields="fields" @row-clicked="showModal">
                    <template #cell(actions)="row">
                                                <b-button
                                                    variant="outline-hub"
                                                    size="sm"
                                                    class="mr-1"
                                                    @click="patientProfileView(row.item,
                                                            row.index,
                                                            $event.target)"
                                                >
                                                    Visit patient profile
                                                </b-button>
                                               
                                            </template>
                </b-table>
                <h1 v-if="appointments.length == 0"> You have no scheduled appointments!</h1>
            </b-col>
    </b-row>
    
    
    <b-row align-h="center">
            <b-pagination
                        v-model="currentPage"
                        per-page="5"
                        :total-rows="rows"
            ></b-pagination>
        </b-row>
    
  </b-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
    computed:{
        ...mapState({
        user: state => state.userModule.loggedInUser,
        //email: state => state.userModule.loggedInUser.email,
        //role: state => state.userModule.loggedInUser.type
        }),
    },
    data: function () {
            return {
                selectedOption: '',
                options:[{text: "First Name", value:"patient.name"},
                        {text: "Last Name", value:"patient.surname"},
                        {text: "Drugstore", value:"drugstore.name"},
                        {text: "Start", value:"date"},
                        {text: "Duration", value:"duration"}],
                selected: {
                    patient: {},
                    drugstore: {},
                },
                appointments: [],
                fields: [
                    {
                        key: 'last_name'
                    },
                    {
                        key: 'first_name'
                    },
                    {
                        key: 'drugstore'
                    },
                    {
                        key: 'start'
                    },
                    {
                        key: 'duration'
                    },
                    {
                    key: "actions",
                    label: "Visit",
                    },
                ],
                rows: 0,
                currentPage: 1,
                
            }
    },
    watch:{
            currentPage: function() {
                this.getAllAppointments();
            },
        },
    methods:{
        patientProfileView(item){
                if(this.user.type == "Dermatologist"){
                this.$router.push({ name: 'PatientProfileView', params: { passedId: item.patient.id, check: "derm" } });
                }
                else{
                    this.$router.push({ name: 'PatientProfileView', params: { passedId: item.patient.id, check: "pharm" } });
                }
        },
        getAllAppointments: function () {
                if(this.user.type == "Dermatologist"){
                this.$http.get(`http://localhost:8081/dermatologist-appointment/all-derm-todo?page=${this
                        .currentPage - 1}&size=5`, {
                        params: {
                            dermatologistId:  this.user.id,
                            sortBy: this.selectedOption
                        }
                    })
                    .then(response => {
                        this.getRows()
                        this.appointments = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.id,
                                first_name: currentEvent.patient.name,
                                last_name:  currentEvent.patient.surname,
                                drugstore: currentEvent.drugstore.name,
                                start: currentEvent.date.substring(0, 10)+" "+currentEvent.time,
                                duration:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+" "+currentEvent.timeEnd,
                                patient: currentEvent.patient,
                            }));
                            
                    })
                    
                     
                }else{
                
                this.$http.get(`http://localhost:8081/pharmacist-appointment/all-pharm-todo?page=${this
                        .currentPage - 1}&size=5`, {
                        params: {
                            pharmacistId:  this.user.id,
                            sortBy: this.selectedOption
                        }
                    })
                    .then(response => {
                        this.getRows()
                        this.appointments = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.id,
                                first_name: currentEvent.patient.name,
                                last_name:  currentEvent.patient.surname,
                                drugstore: currentEvent.pharmacist.drugstore.name,
                                start: currentEvent.date.substring(0, 10)+" "+currentEvent.time,
                                duration:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+" "+currentEvent.timeEnd,
                                patient: currentEvent.patient,
                            }));
                    })
                    
                    
                }

            },
        getRows(){
                if(this.user.type == "Dermatologist"){
                this.$http.get(`http://localhost:8081/dermatologist-appointment/all-derm-todo-length`, {
                        params: {
                            dermatologistId:  this.user.id
                        }
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                      
                }else{
                this.$http.get(`http://localhost:8081/pharmacist-appointment/all-pharm-todo-length`, {
                        params: {
                            pharmacistId:  this.user.id
                        }
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                    
                }
        },
        showModal: function(item){
            this.selected = JSON.parse(JSON.stringify(item));
            
           
            var d = new Date();
            var d1 = new Date(this.selected.start);
            d1.setMinutes(d1.getMinutes()-10);
            var d2 = new Date(this.selected.start);
            d2.setMinutes(d2.getMinutes()+10);
            if(d>d1 && d<d2){
                this.timeValid = true;
            }else{
                this.timeValid = false;
            }
            this.$root.$emit('bv::show::modal', 'my-modal');
            //this.$root.$emit('bv::show::modal', 'my-modal');
        },
        patientNotShowup: function(){
                alert("Patient did not show up");
                this.$root.$emit('bv::hide::modal', 'my-modal');

                this.$http.get('http://localhost:8081/patients/penalty', {
                                    params: {
                                        patientId: this.selected.patient.id//'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                                    }
                                })
                                .then(response => {
                                console.log(response);
                                alert("Patient received a penalty.");
                                //prvo = true;
                                })
                                .catch(error => console.log(error));
                if(this.user.type == "Dermatologist"){
                this.$http.get('http://localhost:8081/dermatologist-appointment/end-appointment', {
                                    params: {
                                        dermatologistAppointmentId: this.selected.id,
                                        appointmentReport: "Patient did not show up!",
                                    }
                                })
                                .then(response => {
                                    console.log(response.data)
                                    this.getAllAppointments()
                                
                                })
                }else{
                    alert("Patient did not show up");
                    console.log(this.selected.extendedProps.patient.id);
                    this.$root.$emit('bv::hide::modal', 'my-modal');
                    this.$http.get('http://localhost:8081/patients/penalty', {
                                        params: {
                                            patientId: this.selected.patient.id//'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                                        }
                                    })
                                    .then(response => {
                                    console.log(response);
                                    alert("Patient received a penalty.");
                                    //prvo = true;
                                    })
                                    .catch(error => console.log(error));
                    
                    this.$http.get('http://localhost:8081/pharmacist-appointment/end-appointment', {
                                        params: {
                                            pharmacistAppointmentId: this.selected.id,
                                            appointmentReport: "Patient did not show up!",
                                        }
                                    })
                                    .then(response => {
                                        console.log(response.data)
                                        this.getAllAppointments()
                                    })
                }
      
        },
        startApp: function(){
            if(this.user.type == "Dermatologist"){
            alert("Appointment started"+ this.selected.id);
            this.$root.$emit('bv::hide::modal', 'my-modal');
            this.$router.push({ name: 'AppointmentDermatologist', params: { passedId: this.selected.id } })
            }else{
                alert("Appointment started"+this.selected.id);
                this.$root.$emit('bv::hide::modal', 'my-modal');
                this.$router.push({ name: 'AppointmentPharmacist', params: { passedId: this.selected.id } })
            }
        },
        sort: function(){
                this.getAllAppointments()
            }   
    },
    mounted: function(){
        this.getAllAppointments()
    }
}
</script>

<style>

</style>