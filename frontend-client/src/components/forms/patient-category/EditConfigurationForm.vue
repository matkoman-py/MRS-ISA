<template>
    <b-form @submit="editConfiguration">
        <b-form-group
            label="Dermatologists appointment:"
            label-for="dermatologist-points-gained-input"
        >
            <b-form-input
                id="dermatologist-points-gained-input"
                v-model="form.dermatologistPointsGained"
                type="number"
                placeholder="Enter points"
                required
            ></b-form-input>
        </b-form-group>
        <b-form-group
            label="Pharmacist appointment:"
            label-for="pharamcist-points-gained-input"
        >
            <b-form-input
                id="name-input"
                v-model="form.pharmacistPointsGained"
                type="number"
                placeholder="Enter points"
                required
            ></b-form-input>
        </b-form-group>
        <b-button variant="outline-hub" type="submit">Save</b-button>
    </b-form>
</template>

<script>
export default {
    name: "EditConfigurationForm",
    data: function() {
        return {
            form: {
                id: 1,
                dermatologistPointsGained: "",
                pharmacistPointsGained: "",
            },
        };
    },
    methods: {
        editConfiguration: function(event) {
            event.preventDefault();
            this.$http
                .put(
                    "http://localhost:8081/patient-categories/configuration",
                    this.form
                )
                .then((response) => {
                    this.$toastr.e("Configuration updated successfully")
                    console.log(response);
                })
                .catch((error) => console.log(error));
        },
        getConfiguration: function() {
            this.$http
                .get("http://localhost:8081/patient-categories/configuration")
                .then((response) => {
                    this.form = response.data;
                    console.log(response);
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getConfiguration();
    },
};
</script>
