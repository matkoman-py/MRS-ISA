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
 
        <b-row>
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
            </b-col>
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
                selected: {},
                patients: [],
                fields: [
                    {
                        key: 'last_name',
                        sortable: true
                    },
                    {
                        key: 'first_name',
                        sortable: true
                    },
                    {
                        key: 'drugstore',
                        sortable: true
                    },
                    {
                        key: 'start',
                        sortable: true
                    },
                    {
                        key: 'duration',
                        sortable: true
                    },
                    {
                    key: "actions",
                    label: "Visit",
                    },
                ],
            }
        },
        
        methods: {
            getAllPatients: function () {
                if(this.user.type == "Dermatologist"){
                this.$http.get('http://localhost:8081/dermatologist-appointment/all-derm-done', {
                        params: {
                            dermatologistId:  this.user.id
                        }
                    })
                    .then(response => {
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
                    .then(() => {
                        this.mountedTrue = true;
                        }
                    )    
                }else{
                this.$http.get('http://localhost:8081/pharmacist-appointment/all-pharm-done', {
                        params: {
                            pharmacistId:  this.user.id
                        }
                    })
                    .then(response => {
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
                    .then(() => {
                        this.mountedTrue = true;
                        }
                    )
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