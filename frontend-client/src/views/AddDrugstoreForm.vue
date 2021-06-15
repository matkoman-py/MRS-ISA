<template>
    <b-container>
        <drugstore-basic-info
            ref="info"
            :form="drugstore"
            mode="add"
        ></drugstore-basic-info>
        <b-button
            v-if="!hideButton"
            type="submit"
            style="margin:30px; width:25%"
            variant="outline-hub"
            size="lg"
            v-on:click="submitHandler"
            >Save</b-button
        >
    </b-container>
</template>

<script>
import DrugstoreBasicInfo from "../components/DrugstoreBasicInfo.vue";
export default {
    components: { DrugstoreBasicInfo },
    props: ["hideButton"],
    data: function() {
        return {
            map: null,
            drugstore: {
                name: "",
                description: "",
                location: {
                    address: "",
                    city: "",
                    country: "",
                },
                workingHoursFrom: "",
                workingHoursTo: "",
                pharmacistAppointmentPrice: null,
                point: {
                    longitude: null,
                    latitude: null,
                },
            },
        };
    },

    methods: {
        submitHandler: function() {
            this.$http
                .post("http://localhost:8081/drugstores", this.drugstore)
                .then((response) => {
                    console.log(response);
                    alert("Successfully added drugstore");
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.map = this.$refs["info"].map;
    },
};
</script>
