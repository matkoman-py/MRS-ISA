<template>
    <b-container>
        <template v-if="patientId == user.id">
            <h1 v-if="reserved == 1">Drug: {{ selecteddrug.name }}</h1>
            <h1 v-if="(drugstores.length == 0) & (reserved == 1)">
                Sorry, there are no pharmacies with that drug in stock :(
            </h1>

            <b-table
                striped
                hover
                :fields="drugstoreFields"
                v-if="drugstores.length != 0"
                :items="drugstores"
            >
                <template #cell(actions)="row">
                    <b-button
                        variant="outline-hub"
                        v-if="row.item"
                        size="sm"
                        @click="showModal(row.item, row.index, $event.target)"
                        class="mr-1"
                    >
                        Choose pharmacy
                    </b-button>
                </template>
            </b-table>

            <b-modal id="my-modal" title="Almost done!" hide-footer>
                <p>
                    Before you finish the reservation process you must select
                    the date to wait for your order
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
                        min="1"
                        v-model="amount"
                        type="number"
                    ></b-form-input>
                    <br />
                    <p>Price: {{ price * amount }}</p>
                    <b-button
                        :disabled="date == '' || amount == ''"
                        type="submit"
                        variant="outline-hub"
                        >Save</b-button
                    >
                </b-form>
            </b-modal>
        </template>
        <template v-else>
            <h1 v-if="(drugstores.length == 0) & (reserved == 1)">
                Sorry, the selected drug is not on stock in this pharmacy :(
                Substitutes:
            </h1>

            <b-table
                striped
                hover
                :fields="drugFields"
                v-if="drugstores.length == 0"
                :items="drugSubstitutions"
            >
                <template #cell(actions)="row">
                    <b-button
                        variant="outline-hub"
                        v-if="row.item"
                        size="sm"
                        @click="
                            showSubstituteModal(
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
            </b-table>

            <b-modal id="my-modal" title="Almost done!" hide-footer>
                <p>
                    Before you finish the reservation process you must select
                    the date to wait for your order
                </p>
                <b-form @submit="makeReservationEmployee">
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
                    <p>Duration of therapy (in days):</p>
                    <b-form-input
                        :value="1"
                        :min="1"
                        v-model="duration"
                        type="number"
                    ></b-form-input>
                    <br />
                    <p>Price: {{ price * amount }}</p>
                    <b-button
                        :disabled="date == '' || amount == ''"
                        type="submit"
                        variant="outline-hub"
                        >Save</b-button
                    >
                </b-form>
            </b-modal>
        </template>
    </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
            email: (state) =>
                state.userModule.loggedInUser
                    ? state.userModule.loggedInUser.email
                    : "",
            role: (state) => state.userModule.loggedInUser.type,
        }),
    },
    props: [
        "drugstores",
        "reserved",
        "selecteddrug",
        "patientId",
        "passedDrugstoreId",
        "appointmentId",
        "check",
    ],
    data: function() {
        const now = new Date();
        const today = new Date(
            now.getFullYear(),
            now.getMonth(),
            now.getDate() + 1
        );
        const minDate = new Date(today);
        return {
            amount: "",
            minDate: minDate,
            drugstoreId: "",
            date: "",
            price: 0,
            drugSubstitutions: [],
            drugstoreFields: [
                {
                    key: "name",
                },
                {
                    key: "address",
                },
                {
                    key: "city",
                },
                {
                    key: "rating",
                },
                {
                    key: "actions",
                    label: "",
                },
            ],
            drugFields: [
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
            ],
            duration: "",
        };
    }, //drug-price
    methods: {
        getPrice() {
            this.$http
                .get("http://localhost:8081/drug-price", {
                    params: {
                        drugId: this.selecteddrug.id,
                        drugstoreId: this.drugstoreId,
                    },
                })
                .then((response) => {
                    this.price = response.data;
                })
                .catch((error) => console.log(error));
        },
        showModal(item) {
            this.drugstoreId = item.id;
            this.getPrice();
            this.$root.$emit("bv::show::modal", "my-modal");
            //this.modified = item;
        },
        showSubstituteModal(item) {
            this.selecteddrug.id = item.id;
            this.drugstoreId = this.passedDrugstoreId;
            this.getPrice();
            this.$root.$emit("bv::show::modal", "my-modal");
        },
        makeReservation() {
            this.$http
                .post("http://localhost:8081/drugReservation/saveReservation", {
                    patientId: this.patientId,
                    drugstoreId: this.drugstoreId,
                    drugId: this.selecteddrug.id,
                    date: this.date,
                    amount: this.amount,
                })
                .then((response) => {
                    this.$toastr.s(response.data);
                    this.$root.$emit("bv::hide::modal", "my-modal");
                })
                .catch((error) => console.log(error));
        },
        makeReservationEmployee: function() {
            //alert(this.check)
            this.$http
                .post(
                    "http://localhost:8081/drugReservation/saveReservationEmployee",
                    {
                        patientId: this.patientId,
                        drugstoreId: this.drugstoreId,
                        drugId: this.selecteddrug.id,
                        date: this.date,
                        amount: this.amount,
                        duration: this.duration,
                        appointmentId: this.appointmentId,
                        check: this.check,
                    }
                )
                .then((response) => {
                    this.$toastr.s(response.data);
                    this.$root.$emit("bv::hide::modal", "my-modal1");
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        //alert(this.passedDrugstoreId);
        if (this.passedDrugstoreId != null) {
            this.$http
                .get("http://localhost:8081/drugs/substitutionsDrugstore", {
                    params: {
                        drugId: this.selecteddrug.id,
                        drugstoreId: this.passedDrugstoreId,
                        patientId: this.patientId,
                    },
                })
                .then((response) => {
                    this.drugSubstitutions = response.data.map((drug) => ({
                        id: drug.id,
                        name: drug.name,
                        form: drug.form,
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                    }));
                })
                .catch((error) => console.log(error));
        }
    },
};
</script>
