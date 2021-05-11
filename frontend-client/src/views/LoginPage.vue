<template>
    <b-container>
        <b-row class="vh-100" align-v='center' align-h='center'>
            <b-col cols="5" class="text-left">
                <b-card>
                <b-form>
                    <b-form-group
                        id="input-group-1"
                        label="Email address:"
                        label-for="input-1"
                        description="We'll never share your email with anyone else."
                    >
                        <b-form-input
                        id="input-1"
                        v-model="form.email"
                        type="email"
                        placeholder="Enter email"
                        required
                        ></b-form-input>
                    </b-form-group>
                    
                    <b-form-group
                        label="Password:"
                        label-for="input-2"
                        >
                    <b-form-input
                        id="input-2"
                        v-model="form.password"
                        type="password"
                        placeholder="Enter password"
                        required
                        ></b-form-input>
                    </b-form-group>
            
                    <b-button class="float-right" type="login" variant="outline-hub" v-on:click="loginUser">Login</b-button>
                </b-form>
                </b-card>
            </b-col>
        </b-row>
     
    </b-container>
</template>

<script>
import { mapState } from 'vuex'

export default {
        name: 'LoginPage',
        computed: {
        ...mapState({
            loginError: state => state.userModule.error,
        }),
        },
        components: {
        },
        data: function () {
            return {
                form: {
                    email: '',
                    password: '',
                }
            }
        },
        
        methods: {
            loginUser: function (event){
                event.preventDefault();
                this.$store.dispatch('userModule/login', this.form);
                setTimeout(() =>  
                {
                    if (this.loginError){
                    this.$toastr.e(this.loginError);
                }
                }, 1000);
               
            }
        }

    }
</script>