<template>
    <div>
        <b-container>
            <b-row>
                <b-col cols="4">
                    <b-card>
                        <b-button-group vertical>
                            <b-button
                                size="sm"
                                variant="outline-hub"
                                @click="showAddModal($event.target, 'Supplier')"
                                class="mr-1"
                            >
                                Add Supplier
                            </b-button>
                            <b-button
                                size="sm"
                                variant="outline-hub"
                                @click="
                                    showAddModal(
                                        $event.target,
                                        'DrugstoreAdmin'
                                    )
                                "
                                class="mr-1"
                            >
                                Add Drugstore Admin
                            </b-button>
                            <b-button
                                size="sm"
                                variant="outline-hub"
                                @click="
                                    showAddModal($event.target, 'SystemAdmin')
                                "
                                class="mr-1"
                            >
                                Add System Admin
                            </b-button>
                            <b-button
                                size="sm"
                                variant="outline-hub"
                                @click="
                                    showAddModal($event.target, 'Dermatologist')
                                "
                                class="mr-1"
                            >
                                Add Dermatologist
                            </b-button>
                        </b-button-group>
                    </b-card>
                </b-col>
                <b-col cols="8">
                    <b-card>
                        <b-table
                            id="user-table"
                            striped
                            hover
                            :items="users"
                            :fields="fields"
                        >
                            <template #cell(actions)="row">
                                <b-button
                                    size="sm"
                                    variant="outline-hub"
                                    v-if="row.item.type != 'SystemAdmin'"
                                    @click="
                                        showDeleteModal(row.item, $event.target)
                                    "
                                    class="mr-1"
                                >
                                    Delete
                                </b-button>
                            </template>
                        </b-table>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
        <b-modal
            :id="addModal.id"
            :title="addModal.title"
            ok-only
            v-on:ok="addUser"
            size="lg"
        >
            <add-user-form
                :type="addModal.type"
                v-on:added-user="handleSuccess"
                ref="add-user-form"
            ></add-user-form>
        </b-modal>
        <b-modal
            :id="deleteModal.id"
            :title="deleteModal.title"
            ok-only
            v-on:ok="deleteUser"
            @hide="resetDeleteModal"
            size="lg"
        >
            Are you sure that you want to delete this user?
        </b-modal>
    </div>
</template>

<script>
import AddUserForm from "../forms/user/AddUserForm";

export default {
    name: "AdminuserTable",
    components: {
        AddUserForm,
    },
    data: function() {
        return {
            fields: [
                { key: "name", label: "Name" },
                { key: "surname", label: "Surname" },
                { key: "type", label: "User Type" },
                { key: "actions", label: "Actions" },
            ],
            addModal: {
                id: "add-modal",
                title: "Add user",
                type: "",
            },
            deleteModal: {
                id: "delete-modal",
                title: "",
                user: {},
            },
            users: [],
        };
    },
    methods: {
        handleSuccess: function(user) {
            this.users.push(user);
        },
        showAddModal: function(button, userType) {
            this.addModal.type = userType;
            this.$root.$emit("bv::show::modal", this.addModal.id, button);
        },
        addUser: function(event) {
            this.$refs["add-user-form"].addUser(event);
        },
        showDeleteModal: function(rowItem, button) {
            this.deleteModal.title = `user: ${rowItem.name}`;
            this.deleteModal.user = JSON.parse(JSON.stringify(rowItem));
            console.log(this.deleteModal.user);
            this.$root.$emit("bv::show::modal", this.deleteModal.id, button);
        },
        resetDeleteModal: function() {
            this.deleteModal.title = "";
        },
        deleteUser: function() {
            let userTypes = "";
            if (this.deleteModal.user.type == "Supplier") {
                userTypes = "suppliers";
            } else {
                alert("Not yet supported");
                return;
            }
            console.log(
                `http://localhost:8081/${userTypes}/${this.deleteModal.user.id}`
            );
            this.$http
                .delete(
                    `http://localhost:8081/${userTypes}/${this.deleteModal.user.id}`
                )
                .then((response) => {
                    let index = this.users.findIndex(
                        (user) => user.id == this.deleteModal.user.id
                    );
                    this.users.splice(index, 1);
                    alert("success", response);
                })
                .catch((error) => console.log(error));
        },
        getUsers: function() {
            this.$http
                .get("http://localhost:8081/suppliers-and-admins")
                .then((response) => {
                    this.users = response.data;
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getUsers();
    },
};
</script>
