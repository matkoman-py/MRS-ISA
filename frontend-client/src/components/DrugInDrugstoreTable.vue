<template>
    <b-container>
        <b-row>
            <b-col>
                <b-form @submit="getDrugs">
                    <b-form-group
                        id="input-group-1"
                        label="Drug name:"
                        label-for="input-1"
                    >
                        <b-form-input
                            id="input-1"
                            v-model="name"
                            type="text"
                            placeholder="Enter drug name"
                        ></b-form-input>
                    </b-form-group>

                    <b-form-group
                        id="input-group-3"
                        label="Drug type:"
                        label-for="input-3"
                    >
                        <b-form-select
                            id="input-3"
                            v-model="type"
                            placeholder="Choose a drug type"
                            :options="drugTypeOptions"
                        >
                        </b-form-select>
                    </b-form-group>

                    <b-form-group
                        id="input-group-1"
                        label="Drug form:"
                        label-for="input-1"
                    >
                        <b-form-input
                            id="input-1"
                            v-model="form"
                            type="text"
                            placeholder="Enter a drug form"
                        ></b-form-input>
                    </b-form-group>

                    <b-form-group
                        id="input-group-3"
                        label="Drug manufacturer:"
                        label-for="input-3"
                    >
                        <b-form-select
                            id="input-3"
                            v-model="manufacturer"
                            :options="manufacturerOptions"
                        >
                        </b-form-select>
                    </b-form-group>

                    <b-form-group
                        id="input-group-3"
                        label="Receipt needed:"
                        label-for="input-3"
                    >
                        <b-form-select
                            id="input-3"
                            v-model="receipt"
                            :options="receiptOptions"
                        >
                        </b-form-select>
                    </b-form-group>

                    <b-button type="submit" variant="outline-hub"
                        >Submit</b-button
                    >
                    <br />
                    <br />
                </b-form>
            </b-col>

            <b-col>
                <b-table
                    head-variant="outline-hub"
                    striped
                    hover
                    :items="drugs"
                    :fields="fields"
                    sticky-header="400px"
                >
                    <template #cell(actions)="row">
                        <b-button
                            variant="outline-hub"
                            v-if="row.item"
                            :disabled="
                                employee.penaltyCounter >= 3 ||
                                    role != 'Patient'
                            "
                            size="sm"
                            @click="
                                showModal(row.item, row.index, $event.target)
                            "
                            class="mr-1"
                        >
                            Reserve
                        </b-button>
                    </template>
                </b-table>
                <h1 v-if="drugs.length == 0">
                    There are no drugs that fit the search parameters
                </h1>
            </b-col>
        </b-row>
        <!-- -->
        <b-modal id="my-modal" title="Almost done!" hide-footer>
            <p>
                Before you finish the reservation process you must select the
                date to wait for your order
            </p>
            <b-form @submit="makeReservation">
                <b-form-datepicker
                    :min="minDate"
                    id="example-datepicker"
                    v-model="date"
                    class="mb-2"
                ></b-form-datepicker>
                <p>Now choose how much you want</p>
                <b-form-input
                    :value="1"
                    :min="1"
                    v-model="amount"
                    type="number"
                ></b-form-input>
                <br />
                <b-button
                    :disabled="date == '' || amount == ''"
                    type="submit"
                    variant="outline-hub"
                    >Save</b-button
                >
            </b-form>
        </b-modal>
    </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: "DrugTable",
    props: {
        passedDrugstoreId: String,
        passedPatientId: String,
    },
    data: function() {
        const now = new Date();
        const today = new Date(
            now.getFullYear(),
            now.getMonth(),
            now.getDate()
        );
        const minDate = new Date(today);
        return {
            employee: 0,
            amount: "",
            selecteddrug: "",
            minDate: minDate,
            date: "",
            currentDrugstoreId: "",
            name: "",
            type: "",
            form: "",
            manufacturer: "",
            receipt: "",
            receiptOptions: ["Yes", "No"],
            drugs: [],
            drugType: [],
            manufacturerOptions: [],
            drugTypeOptions: [],
            ingrediants: [],
            substitutions: [],
            fields: [
                {
                    key: "name",
                },
                {
                    key: "type",
                },
                {
                    key: "form",
                },
                {
                    key: "manufacturer",
                },
                {
                    key: "receipt",
                },
                {
                    key: "amountAvailable",
                },
                {
                    key: "actions",
                    label: "",
                },
            ],
        };
    },
    methods: {
        makeReservation() {
            if (this.passedDrugstoreId != null) {
                this.$http
                    .post(
                        "http://localhost:8081/drugReservation/saveReservation",
                        {
                            patientId: this.passedPatientId,
                            drugstoreId: this.currentDrugstoreId,
                            drugId: this.selecteddrug,
                            date: this.date,
                            amount: this.amount,
                        }
                    )
                    .then((response) => {
                        this.$toastr.s(response.data);
                        this.getAllDrugsOfDrugstore();
                        this.$root.$emit("bv::hide::modal", "my-modal");
                    })
                    .catch((error) => console.log(error));
            } else {
                this.$http
                    .post(
                        "http://localhost:8081/drugReservation/saveReservation",
                        {
                            patientId: this.user.id,
                            drugstoreId: this.currentDrugstoreId,
                            drugId: this.selecteddrug,
                            date: this.date,
                            amount: this.amount,
                        }
                    )
                    .then((response) => {
                        this.$toastr.s(response.data);
                        this.getAllDrugsOfDrugstore();
                        this.$root.$emit("bv::hide::modal", "my-modal");
                    })
                    .catch((error) => console.log(error));
            }
        },
        showModal(item) {
            if (this.user == null) {
                this.$toastr.e("You must be logged in to reserve a drug.");
                return;
            }
            //alert(item.name);
            this.selecteddrug = item.id;
            this.$root.$emit("bv::show::modal", "my-modal");
        },
        getDrugstoreId() {
            if (this.passedDrugstoreId != null) {
                this.currentDrugstoreId = this.passedDrugstoreId;
            } else {
                this.currentDrugstoreId = this.$route.path.slice(
                    12,
                    this.$route.path.length
                );
            }
        },
        getDrugs: function() {
            console.log({
                name: this.name,
                type: this.type.name,
                form: this.form,
                manufacturerId: this.manufacturer.id,
                receipt: this.receipt,
            });
            this.$http
                .post("http://localhost:8081/drugs/search", {
                    name: this.name,
                    type: this.type.name,
                    form: this.form,
                    manufacturerId: this.manufacturer.id,
                    receipt: this.receipt,
                })
                .then((response) => {
                    this.drugs = response.data.map((drug) => ({
                        id: drug.id,
                        name: drug.name,
                        form: drug.form,
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                        amountAvailable: drug.amount,
                    }));
                })
                .catch((error) => console.log(error));
        },
        getManufacturers: function() {
            this.$http
                .get("http://localhost:8081/manufacturers")
                .then((response) => {
                    this.manufacturerOptions = response.data.map(
                        (manufacturer) => ({
                            value: manufacturer,
                            text: manufacturer.name,
                        })
                    );
                })
                .catch((error) => console.log(error));
        },
        getDrugTypes: function() {
            this.$http
                .get("http://localhost:8081/drug-types")
                .then((response) => {
                    this.drugTypeOptions = response.data.map((drugType) => ({
                        value: drugType,
                        text: drugType.name,
                    }));
                })
                .catch((error) => console.log(error));
        },
        getIngrediants: function() {
            this.$http
                .get("http://localhost:8081/ingredients")
                .then((response) => {
                    this.ingrediants = response.data;
                })
                .catch((error) => console.log(error));
        },
        getSubstitutionDrugs: function() {
            this.$http
                .get("http://localhost:8081/drugs")
                .then((response) => {
                    this.substitutions = response.data;
                })
                .catch((error) => console.log(error));
        },
        getAllDrugsOfDrugstore: function() {
            this.$http
                .get(
                    "http://localhost:8081/drugs/in-drugstore/" +
                        this.currentDrugstoreId,
                    {
                        params: {
                            size: 5,
                            page: 0,
                        },
                    }
                )
                .then((response) => {
                    this.drugs = response.data.map((drug) => ({
                        id: drug.id,
                        name: drug.name,
                        form: drug.form,
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                        amountAvailable: drug.amount,
                    }));
                })
                .catch((error) => console.log(error));
        },
        getEmployee: function() {
            if (this.user != null) {
                this.$http
                    .get("http://localhost:8081/patients/id", {
                        params: {
                            patientId: this.user.id,
                        },
                    })
                    .then((response) => {
                        this.employee = response.data;
                        console.log(this.employee.penaltyCounter);
                    })
                    .catch((error) => console.log(error));
            } else {
                //this.user = "not logged in";
            }
        },
    },
    mounted: function() {
        this.getEmployee();
        this.getDrugstoreId();
        this.getManufacturers();
        this.getIngrediants();
        this.getSubstitutionDrugs();
        this.getDrugTypes();
        this.getAllDrugsOfDrugstore();
    },
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
            email: (state) =>
                state.userModule.loggedInUser
                    ? state.userModule.loggedInUser.email
                    : "",
            role: (state) =>
                state.userModule.loggedInUser
                    ? state.userModule.loggedInUser.type
                    : "",
        }),
    },
};
</script>
