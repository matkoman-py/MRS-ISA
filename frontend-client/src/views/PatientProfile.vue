<template>
    <b-card>
        <div class="container emp-profile">
            <form @submit="handleSubmit">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1.png"
                                alt="" />
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file" />
                            </div>
                        </div>
                    </div>
                    <div>
                        <b-card no-body>
                            <b-tabs>
                                <b-tab style="height: 370px;" title="Profile" active>
                                    <div class="profile-head">
                                        <br>
                                        <h5>
                                            {{name}}
                                        </h5>
                                        <h6>
                                            {{employee.type}}
                                        </h6>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Points</label>
                                        </div>
                                        <div class="col-md-6">
                                            <h6>{{employee.points}}</h6>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Category</label>
                                        </div>
                                        <div class="col-md-6">
                                            <h6>{{employee.category}}</h6>
                                            <h6 v-if="employee.category == null">Undefined</h6>
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
                                            <b-form-input :disabled="editEnabled" id="email-input" type="email"
                                                v-model="employee.email" required></b-form-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Phone</label>
                                        </div>
                                        <div class="col-md-6">
                                            <b-form-input :disabled="editEnabled" id="phonenumber-input"
                                                v-model="employee.phoneNumber" required></b-form-input>
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
                                                Save
                                                info</b-button>
                                        </div>
                                    </div>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Pharmacist appointments">
                                    <b-table striped hover :items="appointments"></b-table>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Dermatology appointments">
                                    <b-table striped hover :items="appointments"></b-table>
                                </b-tab>
                                <b-tab style="height: 370px;" title="Drug reservations">
                                    <b-table striped hover :items="appointments"></b-table>
                                </b-tab>
                            </b-tabs>
                        </b-card>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-4">
                        <router-link :to="'/schedule-appointment'">
                            <b-button variant="outline-hub">Schedule pharmacist
                                appointment</b-button>
                        </router-link>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">


                                <!-- <div class="row">
                                    <div class="col-md-6">
                                        <label>Password</label>
                                    </div>
                                    <div class="col-md-6">
                                        <b-form-input :disabled="editEnabled" id="password-input"
                                            v-model="employee.password" type="password" required></b-form-input>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Validate</label>
                                    </div>
                                    <div class="col-md-6">
                                        <b-form-input :disabled="editEnabled" :state="validationState"
                                            id="validatepassword-input" v-model="validationPassword" type="password"
                                            required></b-form-input>
                                    </div>
                                </div> -->
                                <!-- <div class="row">
                                            <div class="col-md-6">
                                                <label>Adress</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled"
                                                id="address-input"
                                                v-model="employee.location.address"
                                                
                                                required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>City</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled"
                                                id="address-input"
                                                v-model="employee.location.city"
                                                
                                                required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Country</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled"
                                                id="address-input"
                                                v-model="employee.location.country"
    
                                                required
                                                ></b-form-input>
                                            </div>
                                        </div>-->
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
    } from 'vuex'

    export default {
        data: function () {
            return {
                employee: {},
                editEnabled: true,
                validationPassword: '',
                name: '',
                inputValues: {
                    date: '',
                    time: '',
                },
                appointments: [],
            }
        },
        methods: {
            handleClose() {
                this.$root.$emit('bv::hide::modal', 'my-modal');
            },
            showForm(event) {
                event.preventDefault()
                this.$root.$emit('bv::show::modal', 'my-modal');
            },
            getEmployee: function () {
                this.employee.id = this.user.id;

                this.$http.get('http://localhost:8081/patients/id', {
                        params: {
                            patientId: this.employee.id
                        }
                    })
                    .then(response => {
                        this.employee = response.data;
                        this.name = this.employee.name;
                        //this.employee.location = {};
                    })
                    .catch(error => console.log(error));

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
                this.$http.put("http://localhost:8081/patients", this.employee)
                    .then(response => {
                        console.log(response);
                        console.log("ovde");
                    })
                    .catch(error => console.log(error));
            },
            validatePassword: function () {
                return this.employee.password == this.validationPassword;
            },
            getAppointments: function () {
                //"664783ca-84a1-4a2b-ae27-a2b820bc3c71"
                this.$http.get("http://localhost:8081/pharmacist-appointment/get-appointments", {
                    params: {
                        patientId: this.user.id
                    }
                }).then(response => {
                    this.appointments = response.data.map(appointment => ({
                        pharmacist: appointment.pharmacist.name,
                        date: appointment.date.slice(0, 10),
                        time: appointment.time.slice(0, 5)
                    }))
                })
            }
        },
        mounted: function () {
            //appointments
            this.getEmployee();
            this.getAppointments();
        },
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
                email: state => state.userModule.loggedInUser.email,
                role: state => state.userModule.loggedInUser.type
            }),
            validationState: function () {
                if (this.editEnabled)
                    return null
                return this.employee.password == this.validationPassword;
            },
        }
    }
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