<template>
    <div>
        <b-container style="width:30%" fluid>
            <b-row class="vh-100" align-v="center" align-h="center">
                <b-card>
                    <b-col>
                        <h1>Registration form</h1>
                    </b-col>
                    <b-col class="text-left mt-3">
                        <b-form @submit="onSubmit" v-if="show">
                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Email address:"
                                        label-for="input1"
                                    >
                                        <b-form-input
                                            id="input1"
                                            v-model="form.email"
                                            type="email"
                                            placeholder="Enter email"
                                            required
                                        ></b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>
                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Name:"
                                        label-for="input2"
                                    >
                                        <b-form-input
                                            id="input2"
                                            v-model="form.name"
                                            placeholder="Enter name"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>
                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Surname:"
                                        label-for="input3"
                                    >
                                        <b-form-input
                                            id="input3"
                                            v-model="form.surname"
                                            placeholder="Enter surname"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>
                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Password:"
                                        label-for="input4"
                                    >
                                        <b-form-input
                                            id="input4"
                                            v-model="form.password"
                                            placeholder="Enter password"
                                            type="password"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Repeat password:"
                                        label-for="input5"
                                    >
                                        <b-form-input
                                            id="input5"
                                            v-model="form.repeatedPassword"
                                            placeholder="Repeat password"
                                            type="password"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>
                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Phone Number:"
                                        label-for="input6"
                                    >
                                        <b-form-input
                                            id="input6"
                                            v-model="form.phoneNumber"
                                            placeholder="Enter Phone Number"
                                            type="number"
                                            required
                                        ></b-form-input>

                                        <b-popover
                                            target="input6"
                                            id="pop"
                                            triggers="a"
                                            :placement="placement"
                                        >
                                            <p>
                                                <b-icon
                                                    style="position: relative; top:8px"
                                                    class="h4"
                                                    name="icon"
                                                    icon="exclamation-square-fill"
                                                    variant="outline-hub"
                                                ></b-icon>
                                                Phone number must have at least
                                                6 digits.
                                            </p>
                                        </b-popover>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>

                            <b-form-row>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Country:"
                                        label-for="input6"
                                    >
                                        <b-form-input
                                            v-model="form.country"
                                            required
                                            placeholder="Enter country"
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="City:"
                                        label-for="input7"
                                    >
                                        <b-form-input
                                            id="input7"
                                            v-model="form.city"
                                            placeholder="Enter city"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col>
                                    <b-form-group
                                        id="inputg1"
                                        label="Address:"
                                        label-for="input8"
                                    >
                                        <b-form-input
                                            id="input8"
                                            v-model="form.address"
                                            placeholder="Enter address"
                                            required
                                        >
                                        </b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-form-row>

                            <b-button type="submit" variant="outline-hub"
                                >Submit</b-button
                            >
                        </b-form>
                    </b-col>
                </b-card>
            </b-row>
        </b-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                email: "",
                password: "",
                repeatedPassword: "",
                name: "",
                surname: "",
                phoneNumber: "",
                location: {
                    country: "",
                    city: "",
                    address: "",
                },
                type: "Patient",
                drugstore: {},
            },
            placement: "bottomright",
            show: true,
        };
    },
    methods: {
        onSubmit(event) {
            this.$root.$emit("bv::hide::popover", "pop");
            if (!this.validatePhoneNumber()) {
                this.$root.$emit("bv::show::popover", "pop");
                return;
            }
            if (!this.validatePassword()) {
                this.$toastr.e("Passwords aren't matching!")
                return;
            }
            event.preventDefault();
            this.registerUser();
        },
        registerUser: function() {
            this.$http
                .post("http://localhost:8081/register", this.form)
                .then((response) => {
                    console.log(response);
                    this.$toastr.s("Check your email for further instructions!")
                })
                .catch((error) => console.log(error));
        },
        validatePhoneNumber: function() {
            return this.form.phoneNumber.length > 6;
        },
        validatePassword: function() {
            return this.form.password == this.form.repeatedPassword;
        },
    },
};
</script>

<style></style>
