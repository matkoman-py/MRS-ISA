<template>
    <b-container>
        <b-modal id="my-modal" title="Appointment" hide-footer>
            <b-form >
                <b-form-group label="Name" label-for="name-input">
                    <b-form-input id="name-input"  v-model="selected.first_name" disabled></b-form-input>
                </b-form-group>

                <b-form-group label="Surname" label-for="surname-input">
                    <b-form-input id="surname-input"  v-model="selected.last_name" disabled></b-form-input>
                </b-form-group>

                <b-form-group label="Drugstore" label-for="email-input">
                    <b-form-input id="email-input" type="email" v-model="selected.drugstore" disabled></b-form-input>
                </b-form-group>

                <b-form-group label="Start" label-for="phonenumber-input">
                    <b-form-input id="phonenumber-input"  v-model="selected.start" disabled></b-form-input>
                </b-form-group>
                
                <b-form-group label="Duration" label-for="duration-input">
                    <b-form-input id="duration-input"  v-model="selected.duration" disabled></b-form-input>
                </b-form-group>

                <b-form-group label="Appointment report" label-for="duration-input">
                <textarea disabled id="diagnosis" v-model="selected.appointment_report" name="diagnosis" rows="10" cols="47" style="resize: none">

                </textarea>
                </b-form-group>
                
                
                
                <b-button type="button" variant="outline-hub" @click="handleClose">Cancel</b-button>
            </b-form>
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
        <b-row style="margin: 20px;" align-h="center">
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="patients" :fields="fields" @row-clicked="showModal">
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
                <h1 v-if="patients.length == 0"> You have no history of appointments!</h1>
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
                selected: {},
                patients: [],
                fields: [
                    {
                        key: 'last_name'
                    },
                    {
                        key: 'first_name'
                        
                    },
                    {
                        key: 'drugstore',
                        
                    },
                    {
                        key: 'start',
                        
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
                this.getAllPatients();
            },
        },
        methods: {
            getAllPatients: function () {
                if(this.user.type == "Dermatologist"){
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com//dermatologist-appointment/all-derm-done?page=${this
                        .currentPage - 1}&size=5`, {
                        params: {
                            dermatologistId:  this.user.id,
                            sortBy: this.selectedOption
                        }
                    })
                    .then(response => {
                        this.getRows()
                        this.patients = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.patient.id,
                                first_name: currentEvent.patient.name,
                                last_name:  currentEvent.patient.surname,
                                drugstore: currentEvent.drugstore.name,
                                start: currentEvent.date.substring(0, 10)+" "+currentEvent.time,
                                duration:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+" "+currentEvent.timeEnd,
                                appointment_report: currentEvent.appointmentReport,
                            }));
                    })
                     
                }else{
                
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com//pharmacist-appointment/all-pharm-done?page=${this
                        .currentPage - 1}&size=5`, {
                        params: {
                            pharmacistId:  this.user.id,
                            sortBy: this.selectedOption
                        }
                    })
                    .then(response => {
                        this.getRows()
                        this.patients = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.patient.id,
                                first_name: currentEvent.patient.name,
                                last_name:  currentEvent.patient.surname,
                                drugstore: currentEvent.pharmacist.drugstore.name,
                                start: currentEvent.date.substring(0, 10)+" "+currentEvent.time,
                                duration:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+" "+currentEvent.timeEnd,
                                appointment_report: currentEvent.appointmentReport,
                            }));
                    })
                    
                }

            },
            handleClose() {
                this.$root.$emit('bv::hide::modal', 'my-modal');
            },
            showModal(item) {
                this.$root.$emit('bv::show::modal', 'my-modal');
                this.selected = JSON.parse(JSON.stringify(item));
                console.log(this.selected);
            },
            patientProfileView(item){
                if(this.user.type == "Dermatologist"){
                this.$router.push({ name: 'PatientProfileView', params: { passedId: item.id, check: "derm" } });
                }
                else{
                    this.$router.push({ name: 'PatientProfileView', params: { passedId: item.id, check: "pharm" } });
                }
            },
            getRows(){
                if(this.user.type == "Dermatologist"){
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com//dermatologist-appointment/all-derm-done-length`, {
                        params: {
                            dermatologistId:  this.user.id
                        }
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                       
                }else{
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com//pharmacist-appointment/all-pharm-done-length`, {
                        params: {
                            pharmacistId:  this.user.id
                        }
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                    
                }
            },
            sort: function(){
                this.getAllPatients()
            }
        },
        mounted: function () {
            this.getAllPatients();
        }
    }
</script>

<style>
    #nesting_div {
        text-align: center;
    }
</style>