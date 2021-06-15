<template>
    <b-card>
        <div class="container emp-profile">
            <form @submit="handleSubmit">
                <div class="row">
                    <!-- <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1.png"
                                alt="" />
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file" />
                            </div>
                        </div>
                    </div> -->
                    <div class="col-md-12">
                        <b-card no-body>
                            <b-tabs>
                                <b-tab style="height: 370px;" title="Profile" active>
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
                                                <label>Penalties</label>
                                            </div>
                                            <div class="col-md-6">
                                                <h6>{{ employee.penaltyCounter }}</h6>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Category</label>
                                            </div>
                                            <div class="col-md-6">
                                                <h6 v-if="
                                                        employee.category ==
                                                            null ||
                                                            employee.category ==
                                                                undefined
                                                    ">
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
                                                <b-form-input :disabled="editEnabled" id="name-input"
                                                    v-model="employee.name" required></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Surname</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled" id="surname-input"
                                                    v-model="employee.surname" required></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input disabled id="email-input" type="email"
                                                    v-model="employee.email" required></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled" id="phonenumber-input" v-model="
                                                        employee.phoneNumber
                                                    " required></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <b-button class="dugme" type="button" variant="outline-hub"
                                                    :disabled="!editEnabled" @click="handleEdit">Edit info
                                                </b-button>
                                            </div>
                                            <div class="col-md-6">
                                                <b-button class="dugme" type="submit" variant="outline-hub"
                                                    :disabled="editEnabled">
                                                    Save info</b-button>
                                            </div>
                                        </div>
                                    </b-card>
                                </b-tab>
                                <!--@click="cancelDrugReservation(row.item, row.index, $event.target)"-->

                                <b-tab style="height: 370px;" title="Pharmacist appointments">
                                    <b-card style="border: none;">
                                        <b-table v-if="
                                                pharmacistappointments.length !=
                                                    0
                                            " striped hover :fields="
                                                pharmacistappointmentfields
                                            " :items="pharmacistappointments">
                                            <template #cell(actions)="row">
                                                <b-button variant="outline-danger" v-if="row.item.processed == 'No'"
                                                    size="sm" @click="
                                                        cancelPharmacistAppointment(
                                                            row.item,
                                                            row.index,
                                                            $event.target
                                                        )
                                                    " class="mr-1">
                                                    Cancel appointment
                                                </b-button>
                                                <b-button variant="outline-danger" v-if="row.item.processed == 'Yes'"
                                                    size="sm" class="mr-1" disabled>
                                                    Cancel appointment
                                                </b-button>
                                            </template>
                                        </b-table>
                                        <br />
                                        <h3 v-if="
                                                pharmacistappointments.length ==
                                                    0
                                            ">
                                            You have no pharmacist appointments
                                            scheduled
                                        </h3>
                                        <b-pagination v-if="rowsPharmacist > 3" v-model="currentPagePharmacist"
                                            per-page="3" :total-rows="rowsPharmacist">
                                        </b-pagination>
                                        <router-link :to="'/schedule-appointment'">
                                            <b-button :disabled="employee.penaltyCounter >= 3" variant="outline-hub">
                                                Schedule pharmacist appointment</b-button>
                                        </router-link>
                                    </b-card>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Dermatology appointments">
                                    <b-card style="border: none;">
                                        <b-table v-if="
                                                dermatologistappointments.length !=
                                                    0
                                            " striped hover :items="dermatologistappointments" :fields="dermAppFields">
                                            <template #cell(actions)="row">
                                                <b-button variant="outline-danger" v-if="row.item.processed == 'No'"
                                                    size="sm" @click="
                                                        cancelDermatologyAppointment(
                                                            row.item,
                                                            row.index,
                                                            $event.target
                                                        )
                                                    " class="mr-1">
                                                    Cancel appointment
                                                </b-button>
                                                <b-button variant="outline-danger" v-if="row.item.processed == 'Yes'"
                                                    size="sm" class="mr-1" disabled>
                                                    Cancel appointment
                                                </b-button>
                                            </template>
                                        </b-table>
                                        <br />
                                        <h3 v-if="
                                                dermatologistappointments.length ==
                                                    0
                                            ">
                                            You have no dermatologist
                                            appointments scheduled
                                        </h3>
                                        <b-pagination v-if="
                                                rowsDermatologist > 3" v-model="currentPageDermatologist" per-page="3"
                                            :total-rows="rowsDermatologist">
                                        </b-pagination>
                                    </b-card>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Drug reservations">
                                    <b-card style="border: none;">
                                        <b-table v-if="drugReservations.length != 0" striped hover
                                            :fields="drugReservationFields" :items="drugReservations">
                                            <template #cell(actions)="row">
                                                <b-button variant="outline-danger" v-if="row.item" size="sm" @click="
                                                        cancelDrugReservation(
                                                            row.item,
                                                            row.index,
                                                            $event.target
                                                        )
                                                    " class="mr-1">
                                                    Cancel reservation
                                                </b-button>
                                            </template>
                                        </b-table>
                                        <br />
                                        <h3 v-if="rows == 0">
                                            You have no drug reservations
                                        </h3>
                                        <b-pagination v-if="rows > 3" v-model="currentPage" per-page="3"
                                            :total-rows="rows">
                                        </b-pagination>
                                    </b-card>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Add alergens">
                                    <b-form @submit="AddAlergens" label-for="tags-component-select">
                                        <b-card>
                                            <h3>Choose an ingrediant from the list and add it to your list of allergens
                                            </h3>
                                            <br>
                                            <b-form-select v-model="alergenToAdd" :options="ingrediants"
                                                value-field="id" text-field="name">
                                            </b-form-select>
                                            <br>
                                            <br>
                                            <b-button :disabled="alergenToAdd == null" type="submit"
                                                variant="outline-hub">Add alergen</b-button>
                                        </b-card>
                                    </b-form>
                                </b-tab>
                            </b-tabs>
                        </b-card>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-12">

                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </b-card>
</template>

<script>
    import {
        mapState
    } from "vuex";

    export default {
        watch: {
            currentPage: function () {
                this.getDrugReservations();
            },
            currentPagePharmacist: function () {
                this.getAppointments();
            },
            currentPageDermatologist: function () {
                this.getDermatologyAppointments();
            },
        },
        data: function () {
            return {
                alergenToAdd: null,
                today: '',
                rowsPharmacist: 0,
                rows: 0,
                rowsDermatologist: 0,
                suppress: true,
                dermatologistappointments: [],
                drugReservations: [],
                eReceiptReservations: [],
                employee: {},
                editEnabled: true,
                validationPassword: "",
                name: "",
                inputValues: {
                    date: "",
                    time: "",
                },
                pharmacistappointmentfields: [{
                        key: "pharmacist",
                    },
                    {
                        key: "date",
                    },
                    {
                        key: "time",
                    },
                    {
                        key: 'processed',
                        label: "Done"
                    },
                    {
                        key: "actions",
                        label: "Cancel appointment",
                    },
                ],
                dermAppFields: [{
                        key: "dermatologist",
                    },
                    {
                        key: "date",
                    },
                    {
                        key: "time",
                    },
                    {
                        key: 'processed',
                        label: "Done"
                    },
                    {
                        key: "actions",
                        label: "Cancel appointment",
                    },
                ],
                drugReservationFields: [{
                        key: "drug",
                    },
                    {
                        key: "drugstore",
                    },
                    {
                        key: "date",
                    },
                    {
                        key: 'receipt',
                        label: "eReceipt"
                    },
                    {
                        key: "actions",
                        label: "",
                    },
                ],
                ingrediants: [],
                pharmacistappointments: [],
                itemm: {},
                currentPage: 1,
                currentPagePharmacist: 1,
                currentPageDermatologist: 1,
            };
        },

        methods: {
            AddAlergens: function () {
                this.$http.get('https://mrs-isa-usijani.herokuapp.com/patients/add-alergen', {
                        params: {
                            patientId: this.user.id,
                            alergenId: this.alergenToAdd,
                        }
                    })
                    .then(response => {
                        alert(response.data);
                    })
                    .catch(error => console.log(error));
            },
            getIngrediants: function () {
                this.$http.get("https://mrs-isa-usijani.herokuapp.com/ingredients")
                    .then(response => {
                        this.ingrediants = response.data;
                    })
                    .catch(error => console.log(error));
            },
            cancelPharmacistAppointment(item) {
                const date = new Date()
                var d = date.getDate();
                var m = date.getMonth() + 1; //Month from 0 to 11
                var y = date.getFullYear();
                var dateOfReservation = item.date;
                var dateNow = '' + y + '-' + (m <= 9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
                if (dateOfReservation <= dateNow) {
                    alert("You can't cancel appointments one or more days before they are due")
                    return;
                }
                this.$http
                    .get(
                        "https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/cancelAppointment", {
                            params: {
                                pharmacistAppointmentId: item.id,
                            },
                        }
                    )
                    .then(() => {
                        if (this.currentPagePharmacist > 1)
                            this.currentPagePharmacist--;
                        else
                            this.getAppointments();
                        /*this.pharmacistappointments = response.data.map(
                            (appointment) => ({
                                id: appointment.id,
                                pharmacist: appointment.pharmacist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                            })
                        );*/
                    })
                    .then(alert("Pharmacist appointment succesfully canceled!"))
                    .catch((error) => console.log(error));
            },
            cancelDermatologyAppointment(item) {
                const date = new Date()
                var d = date.getDate();
                var m = date.getMonth() + 1; //Month from 0 to 11
                var y = date.getFullYear();
                var dateOfReservation = item.date;
                var dateNow = '' + y + '-' + (m <= 9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
                if (dateOfReservation <= dateNow) {
                    alert("You can't cancel appointments one or more days before they are due")
                    return;
                }
                this.$http
                    .get(
                        "https://mrs-isa-usijani.herokuapp.com/dermatologist-appointment/cancelAppointment", {
                            params: {
                                dermatologistAppointmentId: item.id,
                            },
                        }
                    )
                    .then((response) => {
                        if (this.currentPageDermatologist > 1)
                            this.currentPageDermatologist--;
                        else
                            this.getDermatologyAppointments();

                        this.dermatologistappointments = response.data.map(
                            (appointment) => ({
                                id: appointment.id,
                                dermatologist: appointment.dermatologist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                            })
                        );
                    })
                    .then(alert("Dermatology appointment succesfully canceled!"))
                    .catch((error) => console.log(error));
            },
            reservationNotPickedUp() {
                for (var drug in this.drugReservations) {
                    //alert(this.drugReservations[drug].date);
                    //alert(this.drugReservations[drug].date);

                    if (this.drugReservations[drug].date == this.today) {
                        //alert("You recieved a penalty");
                        this.$http.get('https://mrs-isa-usijani.herokuapp.com/patients/penalty', {
                                params: {
                                    patientId: this.user.id, //'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                                    reservationId: this.drugReservations[drug].id,
                                    type: "Drug reservation"
                                }
                            })
                            .then(response => {
                                console.log(response);
                                //alert("Patient received a penalty.");
                                //prvo = true;
                            })
                            .catch(error => console.log(error));
                    }
                }
            },
            cancelDrugReservation(item) {
                const date = new Date()
                var d = date.getDate();
                var m = date.getMonth() + 1; //Month from 0 to 11
                var y = date.getFullYear();
                var dateOfReservation = item.date;
                var dateNow = '' + y + '-' + (m <= 9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
                if (dateOfReservation <= dateNow) {
                    alert("You can't cancel reservations one or more days before they are due")
                    return;
                }
                this.$http
                    .put(
                        "https://mrs-isa-usijani.herokuapp.com/drugReservation/cancelReservation", {
                            drugReservationId: item.id,
                            patientId: this.user.id,
                        }
                    )
                    .then(() => {
                        if (this.currentPage > 1)
                            this.currentPage--;
                        else
                            this.getDrugReservations();
                        /*
                        this.drugReservations = response.data.map(
                            (drugReservation) => ({
                                id: drugReservation.id,
                                drug: drugReservation.drug.name,
                                drugstore: drugReservation.drugstore.name,
                                date: drugReservation.date,
                            })
                        );
                        */
                    })
                    .then(alert("Drug reservation succesfully canceled!"))
                    .catch((error) => console.log(error));
            },
            handleClose() {
                this.$root.$emit("bv::hide::modal", "my-modal");
            },
            showForm(event) {
                event.preventDefault();
                this.$root.$emit("bv::show::modal", "my-modal");
            },
            getEmployee: function () {
                var today = new Date();
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                var yyyy = today.getFullYear();

                today = yyyy + '-' + mm + '-' + dd;
                this.today = today;
                this.employee.id = this.user.id;

                this.$http
                    .get("https://mrs-isa-usijani.herokuapp.com/patients/id", {
                        params: {
                            patientId: this.employee.id,
                        },
                    })
                    .then((response) => {
                        this.employee = response.data;
                        this.name = this.employee.name;
                        //this.employee.location = {};
                    })
                    .catch((error) => console.log(error));
            },
            handleEdit: function () {
                this.editEnabled = false;
            },
            handleSubmit: function (event) {
                event.preventDefault();
                // if (!this.validatePassword()) {
                //     return;
                // }
                this.name = this.employee.name;
                this.editEnabled = true;
                this.$http
                    .put("https://mrs-isa-usijani.herokuapp.com/patients", this.employee)
                    .then((response) => {
                        console.log(response);
                        console.log("ovde");
                    })
                    .catch((error) => console.log(error));
            },
            validatePassword: function () {
                return this.employee.password == this.validationPassword;
            },
            getDrugReservationsLength: function () {
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/drugReservation/getPatientReservationsLength`, {
                            params: {
                                patientId: this.user.id
                            }
                        }
                    )
                    .then((response) => {
                        this.rows = response.data;
                    })
                    .catch((error) => console.log(error));
            },
            getPharmacistAppointmentLength: function () {
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/reservations-length`, {
                            params: {
                                patientId: this.user.id
                            }
                        }
                    )
                    .then((response) => {
                        this.rowsPharmacist = response.data;
                    })
                    .catch((error) => console.log(error));
            },
            getDermatologistAppointmentLength: function () {
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/dermatologist-appointment/reservations-length`, {
                            params: {
                                patientId: this.user.id
                            }
                        }
                    )
                    .then((response) => {
                        this.rowsDermatologist = response.data;
                    })
                    .catch((error) => console.log(error));
            },
            getDrugReservations: function () {
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/drugReservation/getPatientReservations?page=${this.currentPage - 1}&size=3`, {
                            params: {
                                patientId: this.user.id
                            }
                        }
                    )
                    .then((response) => {
                        this.getDrugReservationsLength()
                        this.drugReservations = response.data.map(
                            (drugReservation) => ({
                                id: drugReservation.id,
                                drug: drugReservation.drug.name,
                                drugstore: drugReservation.drugstore.name,
                                date: drugReservation.date,
                                receipt: drugReservation.eReceipt ? "Yes" : "No"
                            })
                        );
                        //this.reservationNotPickedUp()
                    })
                    .catch((error) => console.log(error));
            },
            getAppointments: function () {
                //PHARMACIST
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/pharmacist-appointment/get-appointments?page=${this.currentPagePharmacist - 1}&size=3`, {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.getPharmacistAppointmentLength();
                        this.pharmacistappointments = response.data.map(
                            (appointment) => ({
                                id: appointment.id,
                                pharmacist: appointment.pharmacist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                                processed: appointment.processed ? "Yes" : "No"
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            },
            getDermatologyAppointments: function () {
                //"664783ca-84a1-4a2b-ae27-a2b820bc3c71"
                this.$http
                    .get(
                        `https://mrs-isa-usijani.herokuapp.com/dermatologist-appointment/returnAppointments?page=${this.currentPageDermatologist - 1}&size=3`, {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.getDermatologistAppointmentLength();
                        this.dermatologistappointments = response.data.map(
                            (appointment) => ({
                                id: appointment.id,
                                dermatologist: appointment.dermatologist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                                processed: appointment.processed ? "Yes" : "No"
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            },
        },
        mounted: function () {
            //appointments
            this.getIngrediants();
            this.getDrugReservations();
            this.getEmployee();
            this.getAppointments();
            this.getDermatologyAppointments();

        },
        computed: {
            ...mapState({
                user: (state) => state.userModule.loggedInUser,
                email: (state) => state.userModule.loggedInUser.email,
                role: (state) => state.userModule.loggedInUser.type,
            }),
            validationState: function () {
                if (this.editEnabled) return null;
                return this.employee.password == this.validationPassword;
            },
        },
    };
</script>

<style>
    .emp-profile {
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        border-style: solid;
    }

    .profile-img {
        text-align: center;
    }

    .profile-img img {
        width: 70%;
        height: 100%;
    }

    .profile-img .file {
        position: relative;
        overflow: hidden;
        margin-top: -20%;
        width: 70%;
        border: none;
        border-radius: 0;
        font-size: 15px;
        background: #212529b8;
    }

    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }

    .profile-head h5 {
        color: #333;
    }

    .profile-head h6 {
        color: #0062cc;
    }

    .profile-edit-btn {
        border: none;
        border-radius: 1.5rem;
        width: 70%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }

    .proile-rating {
        font-size: 12px;
        color: #818182;
        margin-top: 5%;
    }

    .proile-rating span {
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs {
        margin-bottom: 5%;
    }

    .profile-head .nav-tabs .nav-link {
        font-weight: 600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active {
        border: none;
        border-bottom: 2px solid #0062cc;
    }

    .profile-work {
        padding: 14%;
        margin-top: -15%;
    }

    .profile-work p {
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }

    .profile-work a {
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-work ul {
        list-style: none;
    }

    .profile-tab label {
        font-weight: 600;
    }

    .profile-tab p {
        font-weight: 600;
        color: #0062cc;
    }

    .dugme {
        margin-top: 20px;
    }
</style>