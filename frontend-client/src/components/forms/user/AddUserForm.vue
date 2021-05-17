<template>
    <b-form>
        <b-form-row>
            <b-col>
                <b-form-group label="Name:" label-for="name-input">
                    <b-form-input
                        id="name-input"
                        v-model="form.name"
                        placeholder="Enter name"
                        required
                    ></b-form-input>
                </b-form-group>
            </b-col>

            <b-col>
                <b-form-group label="Surname:" label-for="surname-input">
                    <b-form-input
                        id="surname-input"
                        v-model="form.surname"
                        placeholder="Enter surname"
                        required
                    ></b-form-input>
                </b-form-group>
            </b-col>
        </b-form-row>

        <b-form-row>
            <b-col cols="6">
                <b-form-group label="Email:" label-for="email-input">
                    <b-form-input
                        id="email-input"
                        v-model="form.email"
                        placeholder="Enter email address"
                        type="email"
                        required
                    ></b-form-input>
                </b-form-group>
            </b-col>
            <b-col cols="6">
                <b-form-group
                    label="Phone Number:"
                    label-for="phoneNumber-input"
                >
                    <b-form-input
                        id="phoneNumber-input"
                        v-model="form.phoneNumber"
                        placeholder="Enter phone number"
                        required
                    ></b-form-input>
                </b-form-group>
            </b-col>
        </b-form-row>

        <b-form-row>
            <b-col>
                <b-form-group label="Address:" label-for="address-input">
                    <b-form-input
                        id="address-input"
                        v-model="form.location.address"
                        placeholder="Enter address"
                    ></b-form-input>
                </b-form-group>
            </b-col>
            <b-col>
                <b-form-group label="City:" label-for="city-input">
                    <b-form-input
                        id="city-input"
                        v-model="form.location.city"
                        placeholder="Enter city"
                    ></b-form-input>
                </b-form-group>
            </b-col>

            <b-col>
                <b-form-group label="Country:" label-for="country-input">
                    <b-form-input
                        id="country-input"
                        v-model="form.location.country"
                        placeholder="Enter country"
                    ></b-form-input>
                </b-form-group>
            </b-col>
        </b-form-row>

        <b-form-group
            v-if="type == 'DrugstoreAdmin'"
            label="Drugstore:"
            label-for="drugstore-input"
        >
            <b-form-select
                id="drugstore-select"
                v-model="form.drugstoreId"
                :options="drugstoreOptions"
            ></b-form-select>
        </b-form-group>
    </b-form>
</template>

<script>
export default {
    name: "AddUserForm",
    props: ["type"],
    components: {},
    data: function() {
        return {
            form: {
                name: "",
                surname: "",
                email: "",
                phoneNumber: "",
                location: {
                    address: "",
                    city: "",
                    country: "",
                },
                drugstoreId: "",
            },
            drugstoreOptions: [],
        };
    },
    methods: {
        addUser(event) {
            event.preventDefault();

            let user = JSON.parse(JSON.stringify(this.form));
            user.type = this.type;

            this.$http
                .post("http://localhost:8081/register/", user)
                .then((response) => {
                    console.log(response);
                    alert("New user successfully added.");
                    this.$emit("added-user", user);
                })
                .catch((error) => console.log(error));
        },
        getDrugstores() {
            this.$http
                .get("http://localhost:8081/drugstores/")
                .then((response) => {
                    console.log(response.data);
                    this.drugstoreOptions = response.data.map((drugstore) => ({
                        value: drugstore.id,
                        text: drugstore.name,
                    }));
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getDrugstores();
    },
};
</script>
