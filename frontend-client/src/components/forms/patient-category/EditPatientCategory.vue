<template>
    <b-form @submit="editConfiguration">
        <b-form-group
            label="Dermatologists appointment:"
            label-for="dermatologist-points-gained-input"
        >
            <b-form-input
                id="dermatologist-points-gained-input"
                v-model="form.requieredPoints"
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
                v-model="form.discount"
                type="number"
                placeholder="Enter discount (%)"
                required
            ></b-form-input>
        </b-form-group>
        <b-button variant="outline-hub" type="submit">Save</b-button>
    </b-form>
</template>

<script>
export default {
    name: "EditPatientCategoryForm",
    props: ["form"],
    data: function() {
        return {};
    },
    methods: {
        editConfiguration: function(event) {
            event.preventDefault();
            this.$http
                .put("https://mrs-isa-usijani.herokuapp.com//patient-categories/", this.form)
                .then((response) => {
                    this.$emit("update-category-success", response.data);
                    console.log(response);
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {},
};
</script>
