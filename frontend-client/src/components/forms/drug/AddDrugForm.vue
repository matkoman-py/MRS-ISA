<template>
    <b-container class="form-container">
        <b-row>
            <b-col>
                <drug-form :form="form"></drug-form>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import DrugForm from "./DrugForm";

export default {
    name: "AddDrugForm",
    components: {
        DrugForm,
    },
    data: function() {
        return {
            form: {
                name: "",
                form: "",
                receipt: false,
                description: "",
                substitutions: [],
                ingredients: [],
                manufacturer: null,
                type: null,
                point: 0,
                code: "",
                weight: null,
                dailyDose: null,
            },
        };
    },
    methods: {
        addDrug: function(event) {
            event.preventDefault();
            this.$http
                .post("http://localhost:8081/drugs", this.form)
                .then((response) => {
                    this.$toastr.e("drug added successfully");
                    console.log(response);
                })
                .catch((error) => console.log(error));
        },
    },
};
</script>
