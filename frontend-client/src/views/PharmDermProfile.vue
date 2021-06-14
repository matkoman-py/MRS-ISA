<template>

<div class="container emp-profile"  v-if="employee && Object.keys(employee).length !== 0">
            <form @submit="handleSubmit">
                <div class="row">
                    <!-- <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1.png" alt=""/>
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file"/>
                            </div>
                        </div>
                    </div> -->
                    <div class="col-md-12">
                        <div class="profile-head">
                                    <h5>
                                        {{name}}
                                    </h5>
                                    <h6>
                                        {{employee.type}}
                                    </h6>
                                    <p class="proile-rating">RATING : <span>{{employee.rating}}</span></p>
                            <!-- <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#about" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#schedule" role="tab" aria-controls="profile" aria-selected="false">Work schedule</a>
                                </li>
                            </ul> -->
                        </div>
                    </div>
                    <!-- <div class="col-md-2">
                        
                    </div> -->
                </div>
                <div class="row">
                    <!-- <div class="col-md-4">
                        <div class="profile-work">
                            
                        </div>
                    </div> -->
                    <div class="col-md-12">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="about" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <b-form-input :disabled="editEnabled"
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
                                                <b-form-input :disabled="editEnabled"
                                                id="name-input"
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
                                                <b-form-input disabled
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
                                                <b-form-input :disabled="editEnabled"
                                                id="phonenumber-input"
                                                v-model="employee.phoneNumber"
                                                
                                                required
                                                ></b-form-input>
                                            </div>
                                        </div>
                                        
                                            <div class="row">
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
                                                    id="city-input"
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
                                                    id="country-input"
                                                    v-model="employee.location.country"
        
                                                    required
                                                    ></b-form-input>
                                                </div>
                                            </div>
                                        
                                        <div class="row">
                                            <div class="col-md-4">
                                                <b-button class="dugme" type="button" variant="outline-hub" :disabled="!editEnabled" @click="handleEdit">Edit info</b-button>
                                            </div>
                                            <div class="col-md-4">
                                                
                                                <b-button class="dugme" type="submit" variant="outline-hub" :disabled="editEnabled">Save info</b-button>
                                            </div>
                                            <div class="col-md-4">
                                                
                                                <b-button class="dugme" variant="outline-hub" @click="handlePassword" >Change password</b-button>
                                            </div>
                                        </div> 
                            </div>
                            <div class="tab-pane fade" id="schedule" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Experience</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Hourly Rate</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>10$/hr</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Total Projects</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>230</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>English Level</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Availability</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>6 months</p>
                                            </div>
                                        </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                <b-button type="submit" variant="outline-hub"  >Save</b-button>
            </b-form>
            </b-modal>        
        </div>

</template>

<script>
import { mapState } from 'vuex'
export default {
    computed:{
        validationState: function(){
            return this.newPasswordInput == this.newPasswordValidateInput;
        },
        ...mapState({
        user: state => state.userModule.loggedInUser,
        }),
    },
    data: function(){
        return{
            employee: {},
            editEnabled: true,
            validationPassword:'',
            name:'',
            oldPasswordInput: '',
            newPasswordInput: '',
            newPasswordValidateInput: '',
        }
    },
    methods: {
        getEmployee : function(){
            
            this.$http.get('https://mrs-isa-usijani.herokuapp.com//employees/id', {
                        params: {
                            employeeId: this.user.id
                        }
                    })
                    .then(response => {
                        this.employee = response.data;
                        console.log(response.data)
                        this.name = this.employee.name;
                        //this.employee.location = {};
                    })
                    .catch(error => console.log(error));
            
        },
        handleEdit: function(){
            this.editEnabled = false;
        },
        handlePassword: function(){
            this.$root.$emit('bv::show::modal', 'my-modal');
        },
        handleSubmit: function(event){
            event.preventDefault();
            this.name = this.employee.name;
            this.editEnabled = true;
            this.$http.put("https://mrs-isa-usijani.herokuapp.com//employees", this.employee)
                .then(response => {
                console.log(response);
                console.log("ovde");
                })
                .catch(error => console.log(error));
        },
        validatePassword: function(){
            return this.newPasswordInput == this.newPasswordValidateInput;
        },
        oldPasswordValidate: function(event){
            event.preventDefault();
            var valid = false;
            this.$http.get('https://mrs-isa-usijani.herokuapp.com//employees/password', {
                        params: {
                            employeeId: this.user.id,
                            passwordInput: this.oldPasswordInput
                        }
                    })
                    .then(response => {
                        valid = response.data;
                        if(valid){
                            this.changePassword();
                        }else{
                            alert("Old password does not match!");

                        }
                    })
                    .catch(error => console.log(error));
        },
        changePassword: function(){
            
            
            if(this.validatePassword()){
                this.employee.password = this.newPasswordInput;
                this.$http.put("https://mrs-isa-usijani.herokuapp.com//employees/updatepassword", this.employee)
                .then(response => {
                console.log(response);
                console.log("ovde");
                })
                .catch(error => console.log(error));

                this.$root.$emit('bv::hide::modal', 'my-modal');
                this.oldPasswordInput= '';
                this.newPasswordInput= '';
                this.newPasswordValidateInput= '';
            }else{
                alert("New passwords not ok!");

            }   
        },
    },
    created: function(){
        console.log(this.employee);
        this.getEmployee();
    },
    
}
</script>

<style>

.emp-profile{
    padding: 3%;
    margin-top: 3%;
    margin-bottom: 3%;
    border-radius: 0.5rem;
    border-style: solid;
}
.profile-img{
    text-align: center;
}
.profile-img img{
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
.profile-head h5{
    color: #333;
}
.profile-head h6{
    color: #0062cc;
}
.profile-edit-btn{
    border: none;
    border-radius: 1.5rem;
    width: 70%;
    padding: 2%;
    font-weight: 600;
    color: #6c757d;
    cursor: pointer;
}
.proile-rating{
    font-size: 12px;
    color: #818182;
    margin-top: 5%;
}
.proile-rating span{
    color: #495057;
    font-size: 15px;
    font-weight: 600;
}
.profile-head .nav-tabs{
    margin-bottom:5%;
}
.profile-head .nav-tabs .nav-link{
    font-weight:600;
    border: none;
}
.profile-head .nav-tabs .nav-link.active{
    border: none;
    border-bottom:2px solid #0062cc;
}
.profile-work{
    padding: 14%;
    margin-top: -15%;
}
.profile-work p{
    font-size: 12px;
    color: #818182;
    font-weight: 600;
    margin-top: 10%;
}
.profile-work a{
    text-decoration: none;
    color: #495057;
    font-weight: 600;
    font-size: 14px;
}
.profile-work ul{
    list-style: none;
}
.profile-tab label{
    font-weight: 600;
}
.profile-tab p{
    font-weight: 600;
    color: #0062cc;
}
.dugme{
    margin-top: 20px;
}
</style>