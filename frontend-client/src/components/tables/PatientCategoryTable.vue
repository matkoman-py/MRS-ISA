<template>
    <div>
        <b-container>
            <b-row class="mt-3">
                <b-col cols="4">
                    <b-card>
                        <edit-configuration-form></edit-configuration-form>
                    </b-card>
                </b-col>
                <b-col cols="8">
                    <b-card>
                        <b-table
                            id="categories-table"
                            striped
                            hover
                            :items="categories"
                            :fields="fields"
                        >
                            <template #cell(actions)="row">
                                <b-button
                                    size="sm"
                                    variant="outline-hub"
                                    @click="
                                        showEditModal(row.item, $event.target)
                                    "
                                    class="mr-1"
                                >
                                    Edit
                                </b-button>
                            </template>
                        </b-table>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
        <b-modal
            :id="editModal.id"
            :title="editModal.title"
            ok-only
            v-on:ok="edit"
            @hide="resetEditModal"
            size="xl"
        >
            <edit-patient-category
                :form="editModal.category"
                v-on:update-category-success="getCategories"
            ></edit-patient-category>
        </b-modal>
    </div>
</template>

<script>
import EditConfigurationForm from "../forms/patient-category/EditConfigurationForm.vue";
import EditPatientCategory from "../forms/patient-category/EditPatientCategory.vue";
export default {
    name: "PatientCategoryTable",
    components: {
        EditConfigurationForm,
        EditPatientCategory,
    },
    data: function() {
        return {
            fields: [
                { key: "name", label: "Full Name" },
                { key: "requieredPoints", label: "Requiered points" },
                { key: "discount", label: "Discount" },
                { key: "actions", label: "" },
            ],
            editModal: {
                id: "edit-modal",
                title: "",
                category: {},
            },
            categories: [],
        };
    },
    methods: {
        showEditModal: function(rowItem, button) {
            this.editModal.title = `Patient category: ${rowItem.name}`;
            this.editModal.category = JSON.parse(JSON.stringify(rowItem));
            console.log(this.editModal.category);
            this.$root.$emit("bv::show::modal", this.editModal.id, button);
        },
        resetEditModal: function() {
            this.editModal.title = "";
        },
        edit: function() {
            this.$http
                .put(
                    "http://localhost:8081/patient-categories",
                    this.editModal.category
                )
                .then((response) => {
                    let index = this.drugstores.findIndex(
                        (drugstore) => drugstore.id == response.data.id
                    );
                    this.drugstores.splice(index, 1, response.data);
                    this.$toastr.s("Success!");
                })
                .catch((error) => console.log(error));
        },
        getCategories: function() {
            this.$http
                .get("http://localhost:8081/patient-categories")
                .then((response) => {
                    this.categories = response.data;
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getCategories();
    },
};
</script>
