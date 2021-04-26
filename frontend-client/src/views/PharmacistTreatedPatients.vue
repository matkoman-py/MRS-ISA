<template>
    <b-container>
        <!-- <b-modal id="my-modal" title="Your profile" hide-footer>
            <b-form @submit="onSubmit">
                <b-form-group label="Name" label-for="name-input" invalid-feedback="Name is required">
                    <b-form-input id="name-input" v-model="selected.name"></b-form-input>
                </b-form-group>

                <b-form-group label="Surame" label-for="surname-input" invalid-feedback="Surname is required">
                    <b-form-input id="surname-input" v-model="selected.surname"></b-form-input>
                </b-form-group>

                <b-form-group label="E-mail" label-for="email-input" invalid-feedback="E-mail is required">
                    <b-form-input id="email-input" type="email" v-model="selected.email"></b-form-input>
                </b-form-group>

                <b-form-group label="Phone number" label-for="phonenumber-input"
                    invalid-feedback="Phone number is required">
                    <b-form-input id="phonenumber-input" v-model="selected.phoneNumber"></b-form-input>
                </b-form-group>
                
               <searchable-tags labelName="Add alergens" :updateValue="(data) => selected.substitutions = data"
                    :data="substitutions" v-model="selected.substitutions">
                </searchable-tags>

                <b-button type="submit" variant="primary">Save</b-button>
                <b-button type="button" variant="danger" @click="handleClose">Cancel</b-button>
            </b-form>
        </b-modal> -->
 
        <b-row>
            <b-col>
                <b-table id="tabela_pacijenata" striped hover :items="patients" :fields="fields"></b-table>
            </b-col>
        </b-row>
        <!-- <b-row align-h="center">
            <b-col sm="3" md="4" lg="4">
                <div>
                    <b-form @submit="search" v-if="show">
                        <b-form-group id="input-group-2" label="Patient Name:" label-for="input-2">
                            <b-form-input id="input-2" v-model="form.name" placeholder="Enter name"></b-form-input>
                        </b-form-group>

                        <b-form-group id="input-group-4" label="Patient Surname:" label-for="input-4">
                            <b-form-input id="input-4" v-model="form.surname" placeholder="Enter surname">
                            </b-form-input>
                        </b-form-group>
                        <b-button type="submit" variant="primary">Search</b-button>
                    </b-form>

                </div>
            </b-col>
        </b-row> -->

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
                //selected: {},
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
                                //drugstore: currentEvent.drugstore.name,
                                start: currentEvent.date.substring(0, 10)+" "+currentEvent.time,
                                duration:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+" "+currentEvent.timeEnd,
                               
                            }));
                    })
                    .then(() => {
                        this.mountedTrue = true;
                        }
                    )

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
            // handleClose() {
            //     this.$root.$emit('bv::hide::modal', 'my-modal');
            // },
            // showModal(item) {
            //     this.$root.$emit('bv::show::modal', 'my-modal');
            //     this.selected = JSON.parse(JSON.stringify(item));
            //     this.modified = item;
            //     this.getDrugs();
            // },
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