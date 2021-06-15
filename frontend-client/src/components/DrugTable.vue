<template>
    <b-container>
        <b-row class="mt-3">
            <b-col cols="4">
                <b-card>
                    <b-card style="border: none;">
                        <img
                            class="center img-fluid w-10"
                            width="100"
                            height="100"
                            src="static/pharmacy.png"
                            alt="image slot"
                        />
                    </b-card>
                    <b-form @submit="searchDrugs">
                        <b-form-group
                            id="input-group-1"
                            label="Drug name:"
                            label-for="input-1"
                        >
                            <b-form-input
                                id="input-1"
                                v-model="searchForm.name"
                                type="text"
                                placeholder="Enter drug name"
                            >
                            </b-form-input>
                        </b-form-group>

                        <b-form-group
                            id="input-group-3"
                            label="Drug type:"
                            label-for="input-3"
                        >
                            <b-form-select
                                id="input-3"
                                v-model="searchForm.type"
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
                                v-model="searchForm.form"
                                type="text"
                                placeholder="Enter a drug form"
                            >
                            </b-form-input>
                        </b-form-group>

                        <b-form-group
                            id="input-group-3"
                            label="Drug manufacturer:"
                            label-for="input-3"
                        >
                            <b-form-select
                                id="input-3"
                                v-model="searchForm.manufacturerId"
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
                                v-model="searchForm.receipt"
                                :options="receiptOptions"
                            >
                            </b-form-select>
                        </b-form-group>

                        <b-form-group
                            id="input-group-3"
                            label="Sort by:"
                            label-for="input-3"
                        >
                            <b-form-select
                                id="input-3"
                                v-model="searchForm.sortByField"
                                :options="sortOptions"
                            >
                            </b-form-select>
                        </b-form-group>

                        <b-form-group
                            id="input-group-3"
                            label="Order by:"
                            label-for="input-3"
                        >
                            <b-form-select
                                id="input-3"
                                v-model="searchForm.ascending"
                                :options="sortOrderOptions"
                            >
                            </b-form-select>
                        </b-form-group>

                        <b-button type="submit" variant="outline-hub"
                            >Submit</b-button
                        >
                        <br />
                        <br />
                    </b-form>
                </b-card>
            </b-col>

            <b-col cols="8">
                <b-card>
                    <b-table
                        head-variant="outline-hub"
                        striped
                        hover
                        :fields="fields"
                        :items="drugs"
                        sticky-header="400px"
                    >
                        <template #cell(actions)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item && passedPatientId == null"
                                size="sm"
                                @click="
                                    getDrugstores(
                                        row.item,
                                        row.index,
                                        $event.target
                                    )
                                "
                                class="mr-1"
                                :disabled="employee.penaltyCounter >= 3"
                            >
                                Reserve
                            </b-button>

                            <b-button
                                variant="outline-hub"
                                v-if="row.item && passedPatientId != null"
                                size="sm"
                                @click="
                                    getDrugstores(
                                        row.item,
                                        row.index,
                                        $event.target
                                    )
                                "
                                class="mr-1"
                            >
                                Reserve
                            </b-button>
                        </template>
                        <template #cell(rateAction)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item && passedPatientId == null"
                                size="sm"
                                @click="
                                    showModal(
                                        row.item,
                                        row.index,
                                        $event.target
                                    )
                                "
                                class="mr-1"
                            >
                                Rate drug
                            </b-button>
                        </template>
                    </b-table>
                    <b-pagination
                        v-model="currentPage"
                        per-page="3"
                        :total-rows="rows"
                    ></b-pagination>
                </b-card>
                <b-card v-if="reserved" class="mt-3">
                    <drug-reservation
                        :selecteddrug="selectedDrug"
                        :reserved="reserved"
                        :drugstores="drugstores"
                        :patientId="patientId"
                        :passedDrugstoreId="passedDrugstoreId"
                        :drugSubstitutions="drugSubstitutions"
                        :appointmentId="passedAppointmentId"
                        :check="passedCheck"
                    >
                    </drug-reservation>
                    <h1 v-if="drugs.length == 0">
                        There are no drugs that fit the search parameters
                    </h1>
                </b-card>
            </b-col>
        </b-row>
        <b-modal id="my-modalR" title="Rate drug" hide-footer>
            <b-form @submit="saveRating">
                <label v-if="canRate" style="margin:20px"
                    ><b>Rate us: </b></label
                >
                <b-form-rating
                    v-if="canRate"
                    id="rate"
                    inline
                    value="userRating"
                    v-model="userRating"
                ></b-form-rating>
                <label v-if="canRate == false" style="margin:20px"
                    ><b>Rate us: </b></label
                >
                <b-form-rating
                    v-if="canRate == false"
                    id="cant-rate"
                    inline
                    disabled
                    value="2.75"
                ></b-form-rating>
                <br />
                <b-button type="submit" variant="outline-hub">Save</b-button>
            </b-form>
        </b-modal>

        <b-modal id="my-modal1" title="Almost done!" hide-footer>
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
                <br />
                <p>Now choose how much you want</p>
                <b-form-input
                    :value="1"
                    :min="1"
                    v-model="amount"
                    type="number"
                ></b-form-input>
                <br />
                <p>Duration of therapy (in days):</p>
                <b-form-input
                    :value="1"
                    :min="1"
                    v-model="duration"
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
            =======
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
                <br />
                <b-button
                    :disabled="date == ''"
                    type="submit"
                    variant="outline-hub"
                    >Save</b-button
                >
            </b-form>
            >>>>>>> refactor_student4
        </b-modal>

        <b-modal
            :id="drugDetailsModal.id"
            :title="drugDetailsModal.title"
            size="lg"
        >
            Are you sure that you want to delete this drug?
        </b-modal>
    </b-container>
</template>

<script>
import { mapState } from "vuex";
import DrugReservation from "../components/DrugReservation";

export default {
    name: "DrugTable",
    components: {
        DrugReservation,
    },
    props: {
        passedDrugstoreId: String,
        passedPatientId: String,
        passedAppointmentId: String,
        passedCheck: String,
    },
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
            email: (state) => state.userModule.loggedInUser.email,
            role: (state) => state.userModule.loggedInUser.type,
        }),
        //rows() {
        //return (this.currentPage + 1) * 3;
        //},
    },
    watch: {
        currentPage: function() {
            if (!this.suppress) {
                this.getDrugs();
            } else {
                this.suppress = false;
            }
        },
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
            amount: "",
            minDate: minDate,
            userRating: "1",
            canRate: false,
            reserved: 0,
            drugstores: [],
            drugSubstitutions: [],
            date: "",
            searchForm: {
                name: "",
                type: "",
                form: "",
                manufacturerId: "",
                receipt: "",
                sortByField: "",
                ascending: true,
            },
            fields: [
                {
                    key: "name",
                },
                {
                    key: "form",
                },
                {
                    key: "type",
                },
                {
                    key: "receipt",
                },
                {
                    key: "manufacturer",
                },
                {
                    key: "actions",
                    label: "",
                },
                {
                    key: "rateAction",
                    label: "",
                },
            ],
            receiptOptions: [
                {
                    value: null,
                    text: "Both",
                },
                {
                    value: true,
                    text: "Yes",
                },
                {
                    value: false,
                    text: "No",
                },
            ],
            sortOptions: [
                {
                    value: "name",
                    text: "Name",
                },
                {
                    value: "type",
                    text: "Type",
                },
                {
                    value: "form",
                    text: "Form",
                },
                {
                    value: "manufacturer.name",
                    text: "Manufacturer",
                },
                {
                    value: "receipt",
                    text: "Receipt",
                },
            ],
            sortOrderOptions: [
                {
                    value: true,
                    text: "Ascending",
                },
                {
                    value: false,
                    text: "Descending",
                },
            ],
            drugReservations: [],
            selectedDrug: null,
            drugs: [],
            drugType: [],
            manufacturerOptions: [],
            drugTypeOptions: [],
            ingrediants: [],
            substitutions: [],
            currentPage: 1,
            suppress: false,
            currentSearch: {},
            patientId: "",
            selectedDrugId: "",
            rows: 0,
            appointmentId: "",
            check: "",
            duration: "",
            drugDetailsModal: {
                id: "",
                title: "",
                drug: "",
            },
        };
    },
    methods: {
        saveRating() {
            return true;
        },
        showModal(item) {
            if (this.user == null) {
                alert("You must be logged in to rate a drug!");
                return;
            }
            //alert(item.name);
            this.getDrugReservations();
            this.selecteddrug = item.id;
            var i;
            var x = 1;
            for (i = 0; i < this.drugReservations.length; i++) {
                //alert(this.drugReservations[i].drug);
                // alert(item.name);
                if (this.drugReservations[i].drug == item.name) {
                    //alert("JUPI");
                    this.canRate = true;
                    x = 0;
                }
            }
            if (x == 1) this.canRate = false;
            this.$root.$emit("bv::show::modal", "my-modalR");
        },

        makeReservation: function() {
            //alert(this.check)
            this.$http
                .post(
                    "https://mrs-isa-usijani.herokuapp.com/drugReservation/saveReservationEmployee",
                    {
                        patientId: this.patientId,
                        drugstoreId: this.drugstoreId,
                        drugId: this.selectedDrugId,
                        date: this.date,
                        amount: this.amount,
                        duration: this.duration,
                        appointmentId: this.appointmentId,
                        check: this.check,
                    }
                )
                .then((response) => {
                    alert(response.data);
                    this.$root.$emit("bv::hide::modal", "my-modal1");
                })
                .catch((error) => console.log(error));
        },
        getDrugReservations: function() {
            if (this.user != null) {
                this.$http
                    .get(
                        "https://mrs-isa-usijani.herokuapp.com/drugReservation/getPatientReservations",
                        {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.drugReservations = response.data.map(
                            (drugReservation) => ({
                                id: drugReservation.id,
                                drug: drugReservation.drug.name,
                                drugstore: drugReservation.drugstore.name,
                                date: drugReservation.date,
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            }
        },
        getDrugstores: function(data) {
            if (this.user == null) {
                alert("You must be logged in to reserve a drug!");
                return;
            }
            if (this.passedPatientId == null) {
                this.patientId = this.user.id;
            } else {
                this.patientId = this.passedPatientId;
            }
            //alert(data.id);
            this.selectedDrug = {
                id: data.id,
                name: data.name,
                type: data.type,
            };
            this.reserved = 1;
            if (this.passedDrugstoreId == null) {
                this.$http
                    .get("https://mrs-isa-usijani.herokuapp.com/drugstores/reserve", {
                        params: {
                            drugId: data.id,
                        },
                    })
                    .then((response) => {
                        this.drugstores = response.data.map((stock) => ({
                            id: stock.drugstore.id,
                            name: stock.drugstore.name,
                            address: stock.drugstore.location.address,
                            city: stock.drugstore.location.city,
                            rating: stock.drugstore.averageRating,
                        }));
                    });
            } else {
                this.$http
                    .get("https://mrs-isa-usijani.herokuapp.com/drugstores/reserveEmployee", {
                        params: {
                            drugId: data.id,
                            drugstoreId: this.passedDrugstoreId,
                        },
                    })
                    .then((response) => {
                        this.drugstores = response.data.map((stock) => ({
                            id: stock.drugstore.id,
                            name: stock.drugstore.name,
                            address: stock.drugstore.location.address,
                            city: stock.drugstore.location.city,
                            rating: stock.drugstore.averageRating,
                        }));
                        if (this.drugstores.length != 0) {
                            this.selectedDrugId = data.id;
                            this.drugstoreId = this.passedDrugstoreId;
                            this.patientId = this.passedPatientId;
                            this.check = this.passedCheck;
                            this.appointmentId = this.passedAppointmentId;
                            this.$root.$emit("bv::show::modal", "my-modal1");
                        }
                    });
            }
        },
        getLength: function() {
            this.$http
                .post(
                    `https://mrs-isa-usijani.herokuapp.com/drugs/searchLength`,
                    this.currentSearch
                )
                .then((response) => {
                    this.rows = response.data;
                })
                .catch((error) => console.log(error));
        },
        searchDrugs: function() {
            this.currentSearch = JSON.parse(JSON.stringify(this.searchForm));
            this.getDrugs();
        },
        getDrugs: function() {
            this.$http
                .post(
                    `https://mrs-isa-usijani.herokuapp.com/drugs/search?page=${this
                        .currentPage - 1}&size=3`,
                    this.currentSearch
                )
                .then((response) => {
                    this.getLength();
                    if (response.data.length == 0) {
                        //this.suppress = true;
                        //this.currentPage--;
                        this.drugs = this.mapDrugs(response);
                        //alert(this.currentPage)
                    } else {
                        //this.suppress = false;
                        this.drugs = this.mapDrugs(response);
                    }
                })
                .catch((error) => console.log(error));
        },
        getManufacturers: function() {
            this.$http
                .get("https://mrs-isa-usijani.herokuapp.com/manufacturers")
                .then((response) => {
                    this.manufacturerOptions = response.data.map(
                        (manufacturer) => ({
                            value: manufacturer.id,
                            text: manufacturer.name,
                        })
                    );
                })
                .catch((error) => console.log(error));
        },
        getDrugTypes: function() {
            this.$http
                .get("https://mrs-isa-usijani.herokuapp.com/drug-types")
                .then((response) => {
                    this.drugTypeOptions = response.data.map((drugType) => ({
                        value: drugType.name,
                        text: drugType.name,
                    }));
                })
                .catch((error) => console.log(error));
        },
        getIngrediants: function() {
            this.$http
                .get("https://mrs-isa-usijani.herokuapp.com/ingredients")
                .then((response) => {
                    this.ingrediants = response.data;
                })
                .catch((error) => console.log(error));
        },
        getSubstitutionDrugs: function() {
            this.$http
                .get("https://mrs-isa-usijani.herokuapp.com/drugs")
                .then((response) => {
                    this.substitutions = response.data;
                })
                .catch((error) => console.log(error));
        },
        mapDrugs: function(response) {
            return response.data.map((drug) => ({
                id: drug.id,
                name: drug.name,
                form: drug.form,
                type: drug.type.name,
                receipt: drug.receipt ? "Yes" : "No",
                manufacturer: drug.manufacturer.name,
                substitutions:
                    !drug.substitutions || drug.substitutions.length == 0
                        ? "No substitute"
                        : drug.substitutions.map((sub) => sub.name).join(", "),
                ingredients: drug.ingredients
                    .map((ingredient) => ingredient.name)
                    .join(", "),
            }));
        },
        getEmployee: function() {
            if (this.user != null) {
                this.$http
                    .get("https://mrs-isa-usijani.herokuapp.com/patients/id", {
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
                this.employee = "not logged in";
            }
        },
    },

    mounted: function() {
        this.getEmployee();
        this.getDrugReservations();
        this.getManufacturers();
        this.getIngrediants();
        this.getSubstitutionDrugs();
        this.getDrugTypes();
        this.searchDrugs();
    },
};
</script>

<style></style>
