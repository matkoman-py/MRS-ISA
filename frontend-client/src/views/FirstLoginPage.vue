<template>
    <b-container style="width:40%" align-h="center">
        <b-card style="margin:50px;">
            <div>
            <b-form @submit="changePassword">

                <b-form-input id="password-input2"
                                v-model="dto.password"
                                type="password"
                                required
                                placeholder="New password"
                ></b-form-input>
                <br>
                <b-form-input id="password-input3"
                                v-model="newPasswordValidateInput"
                                type="password"
                                required
                                placeholder="Repeat new password"
                ></b-form-input>
                <br>
                <b-button type="submit" variant="outline-hub"  >Save</b-button>
            </b-form>
            </div>
        </b-card>
    </b-container>
</template>

<script>
import { mapState } from 'vuex'
import router from "../router";

    export default {
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
            }),
        },
        data: function () {
            return {
                dto: {
                    id: '',
                    password: '',
                },
                newPasswordValidateInput: ''
            }
        },
        methods: {
            validatePassword: function(){
                return this.dto.password == this.newPasswordValidateInput;
            },
            changePassword: function(){
                if(this.validatePassword()){
                    this.dto.id = this.user.id;
                    this.$http.put("https://mrs-isa-usijani.herokuapp.com/firstLoginPasswordChange", this.dto)
                    .then(() => {
                        alert("You succesfully updated your password!");
                        this.$root.$emit('bv::hide::modal', 'my-modal');
                        router.push("/");
                    })
                    .catch(error => console.log(error));

                    this.dto.password= '';
                    this.newPasswordValidateInput= '';
                }else{
                    alert("New passwords not ok!");
                }   
            },
        }
    }
</script>
