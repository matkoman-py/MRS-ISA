<template>
    <b-card>
        <div style = "width:750px; padding:20px;" class="container emp-profile">
            <form @submit="handleSubmit">
                <div style="padding=20px" align="center" class="row">
                    <div style="padding:20px" align="center" class="col-md-12">
                        <b-card style="padding:20px" no-body>
                            <h1> Profile overview </h1>
                                    <div align="center" class="profile-head">
                                    
                                        <br>
                                        <h5>
                                            {{user.name}}  

                                        </h5>
                                        <h6>
                                        </h6>
                                    </div>
                                    <div align-h="center">
                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Name</label>
                                        </div>
                                        <div class="col-md-6">
                                            <b-form-input :disabled="editEnabled" id="name-input"
                                                v-model="profile.name" required></b-form-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Surname</label>
                                        </div>
                                        <div class="col-md-6">
                                            <b-form-input :disabled="editEnabled" id="surname-input"
                                                v-model="profile.surname" required></b-form-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Email</label>
                                        </div>
                                        <div class="col-md-6">
                                            <b-form-input disabled id="email-input" type="email"
                                                v-model="profile.email" required></b-form-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label>Phone</label>
                                        </div>
                                        <div class="col-md-6">
                                            <b-form-input :disabled="editEnabled" id="phonenumber-input"
                                                v-model="profile.phoneNumber" required></b-form-input>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <b-button class="dugme" type="button" variant="outline-hub"
                                                :disabled="!editEnabled" @click="handleEdit">Edit info
                                            </b-button>
                                        </div>
                                        <div class="col-md-4">

                                            <b-button class="dugme" type="submit" variant="outline-hub"
                                                :disabled="editEnabled">
                                                Save
                                                info</b-button>
                                        </div>
                                        <div class="col-md-4">
                                                
                                                <b-button class="dugme" variant="outline-hub" @click="handlePassword" >Change password</b-button>
                                            </div>
                                    </div>
                                    </div>
                                    </b-card> 
                        
                    </div>
                </div>
            </form>
            <b-modal id="my-modal" title="Change password" hide-footer>
            <b-form  @submit="oldPasswordValidate">
                <b-form-input id="password-input1"
                              v-model="oldPasswordInput"
                              type="password"
                              required
                              placeholder="Old password"
                ></b-form-input>
                <br>
                <b-form-input id="password-input2"
                              v-model="newPasswordInput"
                              type="password"
                              required
                              placeholder="New password"
                ></b-form-input>
                <br>
                <b-form-input id="password-input3"
                              v-model="newPasswordValidateInput"
                              type="password"
                              required
                              :state="validationState"
                              placeholder="Repeat new password"
                ></b-form-input>
                <br>
                <b-button type="submit" variant="outline-hub">Save</b-button>
            </b-form>
            </b-modal> 
        </div>
    </b-card>
</template>

<script>
    import {
        mapState
    } from 'vuex'

    export default {
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
            }),
            validationState: function () {
                if (this.editEnabled)
                    return null
                return this.profile.password == this.validationPassword;
            },
        },
        data: function () {
            return {
                profile: {},
                editEnabled: true,
                validationPassword: '',
                oldPasswordInput: '',
                newPasswordInput: '',
                newPasswordValidateInput: ''
            }
        },
        methods: {
            getProfile: function () {
                this.$http.get('http://localhost:8081/drugstoreAdmin', {
                        params: {
                            drugstoreAdminId: this.user.id
                        }
                    })
                    .then(response => {
                        this.profile = response.data;
                    })
                    .catch(error => console.log(error));

            },
            handleEdit: function () {
                this.editEnabled = false;
            },
            handleSubmit: function (event) {
                event.preventDefault();
                this.editEnabled = true;
                this.$http.put("http://localhost:8081/drugstoreAdminUpdate", this.profile)
                    .then(() => {
                        this.$toastr.s("You succesfully updated your profile information!")
                    })
                    .catch(error => console.log(error));
            },
            handlePassword: function(){
            this.$root.$emit('bv::show::modal', 'my-modal');
            },
            validatePassword: function(){
                return this.newPasswordInput == this.newPasswordValidateInput;
            },
            oldPasswordValidate: function(event){
                event.preventDefault();
                var valid = false;
                this.$http.get('http://localhost:8081/drugstoreAdmin/password', {
                            params: {
                                drugstoreAdminId: this.user.id,
                                passwordInput: this.oldPasswordInput
                            }
                        })
                        .then(response => {
                            valid = response.data;
                            if(valid){
                                this.changePassword();
                            }else{
                                this.$toastr.e("Old password does not match!");
                            }
                        })
                        .catch(error => console.log(error));
            },
            changePassword: function(){
                if(this.validatePassword()){
                    this.profile.password = this.newPasswordInput;
                    this.$http.put("http://localhost:8081/drugstoreAdmin/updatepassword", this.profile)
                    .then(() => {
                        this.$toastr.s("You succesfully updated your password!");
                        this.$root.$emit('bv::hide::modal', 'my-modal');
                    })
                    .catch(error => console.log(error));

                    this.oldPasswordInput= '';
                    this.newPasswordInput= '';
                    this.newPasswordValidateInput= '';
                }else{
                    this.$toastr.s("New passwords not ok!");
                }   
            },
        },
        mounted: function () {
            this.getProfile();
        },
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