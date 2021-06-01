<template>
    <div>
        <b-container>
            <b-table
                id="drugstore-table"
                striped
                hover
                :items="drugstores"
                :fields="fields"
            >
                <template #cell(actions)="row">
                    <b-button
                        size="sm"
                        variant="outline-hub"
                        @click="showEditModal(row.item, $event.target)"
                        class="mr-1"
                    >
                        Edit
                    </b-button>
                    <b-button
                        size="sm"
                        variant="outline-hub"
                        @click="showDeleteModal(row.item, $event.target)"
                        class="mr-1"
                    >
                        Delete
                    </b-button>
                </template>
            </b-table>
        </b-container>
        <b-modal
            :id="editModal.id"
            :title="editModal.title"
            ok-only
            v-on:ok="edit"
            @hide="resetEditModal"
            size="xl"
        >
            <pre><edit-drugstore-form ref="edit-form" :drugstore="editModal.drugstore" submitHandle="null"></edit-drugstore-form></pre>
        </b-modal>
        <b-modal
            :id="deleteModal.id"
            :title="deleteModal.title"
            v-on:ok="deleteDrugstore"
            @hide="resetDeleteModal"
            size="xl"
        >
            <pre>Are you sure you want to delete this Drugstore?</pre>
            <template #modal-footer="{ ok, hide }">
                <b-button size="sm" @click="ok()">
                    Yes
                </b-button>
                <b-button size="sm" @click="hide()">
                    Cancel
                </b-button>
            </template>
        </b-modal>
    </div>
</template>

<script>
import EditDrugstoreForm from "../forms/drugstore/EditDrugstoreForm";

export default {
    components: { EditDrugstoreForm },
    name: "AdminDrugstoreTable",
    data: function() {
        return {
            fields: [
                { key: "name", label: "Full Name" },
                { key: "description", label: "Description" },
                { key: "actions", label: "Actions" },
            ],
            editModal: {
                id: "edit-modal",
                title: "",
                drugstore: {},
            },
            deleteModal: {
                id: "delete-modal",
                title: "",
                drugstore: {},
            },
            drugstores: [],
        };
    },
    methods: {
        showEditModal: function(rowItem, button) {
            this.editModal.title = `Drugstore: ${rowItem.name}`;
            this.editModal.drugstore = JSON.parse(JSON.stringify(rowItem));
            console.log(this.editModal.drugstore);
            this.$root.$emit("bv::show::modal", this.editModal.id, button);
            setTimeout(() =>
            {
                 this.$refs["edit-form"].map.updateSize();
                 this.$refs["edit-form"].map.getView().setZoom(6);
            }
            , 300);
        },
        resetEditModal: function() {
            this.editModal.title = "";
        },
        edit: function() {
            this.$http
                .put(
                    "http://localhost:8081/drugstores",
                    this.editModal.drugstore
                )
                .then((response) => {
                    let index = this.drugstores.findIndex(
                        (drugstore) => drugstore.id == response.data.id
                    );
                    this.drugstores.splice(index, 1, response.data);
                    alert("success");
                })
                .catch((error) => console.log(error));
        },
        showDeleteModal: function(rowItem, button) {
            this.deleteModal.title = `drug: ${rowItem.name}`;
            this.deleteModal.drugstore = JSON.parse(JSON.stringify(rowItem));
            console.log(this.deleteModal.drugstore);
            this.$root.$emit("bv::show::modal", this.deleteModal.id, button);
        },
        resetDeleteModal: function() {
            this.deleteModal.title = "";
        },
        deleteDrugstore: function() {
            this.$http
                .delete(
                    `http://localhost:8081/drugstores/${this.deleteModal.drugstore.id}`
                )
                .then((response) => {
                    let index = this.drugstores.findIndex(
                        (drugstore) =>
                            drugstore.id == this.deleteModal.drugstore.id
                    );
                    this.drugstores.splice(index, 1);
                    alert("success", response);
                })
                .catch((error) => console.log(error));
        },
        getDrugstores: function() {
            this.$http
                .get("http://localhost:8081/drugstores")
                .then((response) => {
                    this.drugstores = response.data;
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getDrugstores();
    },
};
</script>
