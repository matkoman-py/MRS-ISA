<template>
  <b-card>
        <div class="container emp-profile">
            <form>
                <div class="row">
                    <div class="col-md-12">
                        <b-card no-body>
                            <b-tabs>
                                <b-tab
                                    style="height: 370px;"
                                    title="Profile"
                                    active
                                >
                                    <b-card style="border: none;">
                                        <div class="profile-head">
                                            <br />
                                            <h5>
                                                {{ name }}
                                            </h5>
                                            <h6>
                                                {{ employee.type }}
                                            </h6>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Points</label>
                                            </div>
                                            <div class="col-md-6">
                                                <h6>{{ employee.points }}</h6>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Category</label>
                                            </div>
                                            <div class="col-md-6">
                                                <h6
                                                    v-if="
                                                        employee.category ==
                                                            null ||
                                                            employee.category ==
                                                                undefined
                                                    "
                                                >
                                                    Basic
                                                </h6>
                                                <h6 v-else>
                                                    {{ employee.category.name }}
                                                </h6>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input
                                                    :disabled="editEnabled"
                                                    id="name-input"
                                                    v-model="employee.name"
                                                    required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Surname</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input
                                                    :disabled="editEnabled"
                                                    id="surname-input"
                                                    v-model="employee.surname"
                                                    required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input
                                                    :disabled="editEnabled"
                                                    id="email-input"
                                                    type="email"
                                                    v-model="employee.email"
                                                    required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input
                                                    :disabled="editEnabled"
                                                    id="phonenumber-input"
                                                    v-model="
                                                        employee.phoneNumber
                                                    "
                                                    required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        
                                    </b-card>
                                </b-tab>
                                

                                <b-tab
                                    v-if="checkType == 0"
                                    style="height: 370px;"
                                    title="Pharmacist appointments"
                                >
                                    
                                    <b-card style="border: none;">
                                        <b-table
                                            v-if="
                                                pharmacistappointments.length !=
                                                    0
                                            "
                                            striped
                                            hover
                                            :fields="
                                                pharmacistappointmentfields
                                            "
                                            :items="pharmacistappointments"
                                            @row-clicked="showModal"
                                        >
                                        </b-table>
                                        <br />
                                        <h3
                                            v-if="
                                                pharmacistappointments.length ==
                                                    0
                                            "
                                        >
                                            Patient has no history of pharmacist appointments!
                                        </h3>
                                        <b-pagination align="center"
                                                    v-model="currentPage"
                                                    per-page="4"
                                                    :total-rows="rows"
                                        ></b-pagination>
                                    </b-card>
                                </b-tab>
                                <b-tab
                                    v-if="checkType == 1"
                                    style="height: 370px;"
                                    title="Dermatology appointments"
                                >
                                    <b-card style="border: none;">
                                        <b-table
                                            v-if="
                                                dermatologistappointments.length !=
                                                    0
                                            "
                                            striped
                                            hover
                                            :items="dermatologistappointments"
                                            :fields="dermAppFields"
                                            @row-clicked="showModal"
                                        >
                                        </b-table>
                                        <br />
                                        <h3
                                            v-if="
                                                dermatologistappointments.length ==
                                                    0
                                            "
                                        >
                                            Patient has no history of dermatology appointments!
                                        </h3>
                                        <b-pagination align="center"
                                                    v-model="currentPage"
                                                    per-page="4"
                                                    :total-rows="rows"
                                        ></b-pagination>
                                    </b-card>
                                </b-tab>
                            </b-tabs>
                        </b-card>
                    </div>
                </div>
                <br />
            </form>
        </div>

        <b-modal id="my-modal" title="Appointment" hide-footer>
            <b-form >
                <b-form-group v-if="checkType==1" label="Dermatologist" label-for="name-input">
                    <b-form-input id="name-input"  v-model="selected.dermatologist" disabled></b-form-input>
                </b-form-group>
                <b-form-group v-if="checkType==0" label="Pharmacist" label-for="name-input">
                    <b-form-input id="name-input"  v-model="selected.pharmacist" disabled></b-form-input>
                </b-form-group>

                

                <b-form-group label="Drugstore" label-for="email-input">
                    <b-form-input id="email-input" type="email" v-model="selected.drugstore" disabled></b-form-input>
                </b-form-group>

                <b-form-group label="Date" label-for="phonenumber-input">
                    <b-form-input id="phonenumber-input"  v-model="selected.date" disabled></b-form-input>
                </b-form-group>
                
                <b-form-group label="Time" label-for="phonenumber-input">
                    <b-form-input id="phonenumber-input"  v-model="selected.time" disabled></b-form-input>
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
    </b-card>
</template>

<script>
export default {
    props:{
        passedId: String,
        check: String,
    },
    data: function() {
        return {
            dermatologistappointments: [],
            employee: {},
            editEnabled: true,
            name: "",
            checkType: this.check=="derm"? 1 : 0,
            pharmacistappointmentfields: [
                {
                    key: "pharmacist",
                    sortable: true,
                },
                {
                    key: "date",
                    sortable: true,
                },
                {
                    key: "time",
                },
                {
                    key: 'processed',
                    label: "Done"
                },
            ],
            dermAppFields: [
                {
                    key: "dermatologist",
                    sortable: true,
                },
                {
                    key: "date",
                    sortable: true,
                },
                {
                    key: "time",
                },
                {
                    key: 'processed',
                    label: "Done"
                },
            ],
            pharmacistappointments: [],
            selected: {},
            rows: 0,
            currentPage: 1,
        };
    },
    watch:{
            currentPage: function() {
                this.getAppointments();
            },
    },
    methods:{
        getEmployee: function() {
            this.employee.id = this.passedId;

            this.$http
                .get("https://mrs-isa-usijani.herokuapp.com/patients/id", {
                    params: {
                        patientId: this.employee.id,
                    },
                })
                .then((response) => {
                    this.employee = response.data;
                    this.name = this.employee.name;
                })
                .catch((error) => console.log(error));
        },
        getAppointments: function() {
            if(this.check == "pharm"){
            
            this.$http
                .get(
                    `https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/get-appointments?page=${this
                        .currentPage - 1}&size=4`,
                    {
                        params: {
                            patientId: this.passedId,
                        },
                    }
                )
                .then((response) => {
                    this.getRows()
                    this.pharmacistappointments = response.data.map(
                        (appointment) => ({
                            id: appointment.id,
                            pharmacist: appointment.pharmacist.name,
                            date: appointment.date.slice(0, 10),
                            time: appointment.time.slice(0, 5),
                            processed: appointment.processed ? "Yes" : "No",
                            drugstore: appointment.pharmacist.drugstore.name,
                            duration: appointment.duration,
                            appointment_eport: appointment.appointmentReport,
                        })
                    );
                    
                })
                .catch((error) => console.log(error));
            }
            else{
                this.$http
                .get(
                    `https://mrs-isa-usijani.herokuapp.com/dermatologist-appointment/returnAppointments?page=${this
                        .currentPage - 1}&size=4`,
                    {
                        params: {
                            patientId: this.passedId,
                        },
                    }
                )
                .then((response) => {
                    this.getRows()
                    this.dermatologistappointments = response.data.map(
                        (appointment) => ({
                            id: appointment.id,
                            dermatologist: appointment.dermatologist.name,
                            date: appointment.date.slice(0, 10),
                            time: appointment.time.slice(0, 5),
                            processed: appointment.processed ? "Yes" : "No",
                            drugstore: appointment.drugstore.name,
                            duration: appointment.duration,
                            appointment_report: appointment.appointmentReport,
                        })
                    );
                })
                .catch((error) => console.log(error));
            }
        },
        showModal(item) {
                this.$root.$emit('bv::show::modal', 'my-modal');
                this.selected = JSON.parse(JSON.stringify(item));
                console.log(this.selected);
        },
        handleClose() {
                this.$root.$emit('bv::hide::modal', 'my-modal');
        },
        getRows(){
            if(this.check == "derm"){
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com/dermatologist-appointment/returnAppointments-length`, {
                        params: {
                            patientId: this.passedId,
                        },
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                       
                }else{
                this.$http.get(`https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/get-appointments-length`, {
                        params: {
                            patientId: this.passedId,
                        },
                    })
                    .then(response => {
                        this.rows = response.data;
                    })
                    
                }
        }
    },
    mounted: function() {
        //appointments
        
        this.getEmployee();
        this.getAppointments();
        //this.getDermatologyAppointments();
    },
}
</script>

<style>

</style>