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
                <textarea disabled id="diagnosis" v-model="selected.appointment_report" name="diagnosis" rows="10" cols="50" style="resize: none">

                </textarea>
                </b-form-group>
                
                
                
                <b-button type="button" variant="danger" @click="handleClose">Cancel</b-button>
            </b-form>
        </b-modal>
 
        <b-row>
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="patients" :fields="fields" @row-clicked="showModal"></b-table>
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
                //substitutions: [],
                selected: {},
                //modified: {},
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
                ],
                // form: {
                //     email: '',
                //     name: '',
                //     surname: '',
                //     food: null,
                //     substitutions: [],
                // },
                // foods: [{
                //     text: 'Select One',
                //     value: null
                // }, 'Bronze', 'Silver', 'Gold'],
                // show: true
            }
        },
        
        methods: {
            // search: function () {
            //     this.$http.get('http://localhost:8081/dermatologist/search', {
            //             params: {
            //                 patientNameParam: this.form.name,
            //                 patientSurnameParam: this.form.surname
            //             }
            //         })
            //         .then(response => {
            //             this.patients = response.data.map(patient =>
            //                 ({
            //                     name: patient.name,
            //                     surname: patient.surname,
            //                     email: patient.email,
            //                     phoneNumber: patient.phoneNumber,
            //                     adress: patient.location ? patient.location.address : "Null",
            //                     country: patient.location ? patient.location.country : "Null",
            //                     city: patient.location ? patient.location.city : "Null",
            //                     points: patient.points,
            //                     category: patient.category,
            //                 }));
            //         })
            //         .catch(error => console.log(error));
            // },
            getAllPatients: function () {
                if(this.user.type == "Dermatologist"){
                this.$http.get('http://localhost:8081/dermatologist-appointment/all-derm-done', {
                        params: {
                            dermatologistId:  this.user.id// 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                        this.patients = response.data.map(currentEvent =>
                            ({
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
                            pharmacistId:  this.user.id// 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                        this.patients = response.data.map(currentEvent =>
                            ({
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
            // onSubmit(event) {
            //     event.preventDefault();
            //     this.modified.name = this.selected.name;
            //     this.modified.surname = this.selected.surname;
            //     this.modified.email = this.selected.email;
            //     this.modified.phoneNumber = this.selected.phoneNumber;
            //     this.modified.allergens = this.selected.substitutions;
            //     this.$root.$emit('bv::hide::modal', 'my-modal');
            //     console.log(this.modified);
            //     this.$http.put("http://localhost:8081/patients", this.modified)
            //         .then(response => {
            //             console.log(response);
            //             console.log("ovde");
            //         })
            //         .catch(error => console.log(error));
            // },
            handleClose() {
                this.$root.$emit('bv::hide::modal', 'my-modal');
            },
            showModal(item) {
                this.$root.$emit('bv::show::modal', 'my-modal');
                this.selected = JSON.parse(JSON.stringify(item));
                console.log(this.selected);
                //this.modified = item;
                //this.getDrugs();
            },
            // getDrugs: function () {
            //     this.$http.get("http://localhost:8081/ingredients")
            //         .then(response => {
            //             this.substitutions = response.data;
            //         })
            //         .catch(error => console.log(error));
            // },
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