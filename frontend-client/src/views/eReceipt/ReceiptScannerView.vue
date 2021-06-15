<template>
    <b-container>
        <b-row class="mt-3">
            <b-col>
                <b-card>
                    <qr-code-scanner
                        ref="qrScanner"
                        v-on:qr-code-scanned="getReceiptData"
                    ></qr-code-scanner>
                </b-card>
                <b-card class="mt-3">
                    <b-button v-b-toggle.collapse-1 variant="outline-hub"
                        >Show Drugs</b-button
                    >
                    <b-collapse id="collapse-1" class="mt-3">
                        <b-table
                            v-if="drugstores.length != 0"
                            head-variant="outline-hub"
                            striped
                            hover
                            :fields="drugFields"
                            :items="drugs"
                            sticky-header="400px"
                        >
                        </b-table>
                    </b-collapse>
                </b-card>

                <b-card class="mt-3">
                    <b-table
                        v-if="drugstores.length != 0"
                        head-variant="outline-hub"
                        striped
                        hover
                        :fields="drugstoreFields"
                        :items="drugstores"
                        sticky-header="400px"
                    >
                        <template #cell(actions)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item"
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
                                Choose pharmacy
                            </b-button>
                        </template></b-table
                    >
                    <div v-else>
                        There are no drugstores which contain all those drugs.
                    </div>
                </b-card>
            </b-col>
        </b-row>
        <b-modal id="my-modal" title="Almost done!" hide-footer>
            <p>
                Before you finish the reservation process you must select the
                date to wait for your order
            </p>
            <b-form @submit="makeReservation">
                <b-form-datepicker
                    id="example-datepicker"
                    v-model="reservationDate"
                    class="mb-2"
                ></b-form-datepicker>
                <br />
                <b-button
                    :disabled="reservationDate == ''"
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
import QrCodeScanner from "../../components/QrCodeScanner";

export default {
    name: "ReceiptGeneratorView",
    components: {
        QrCodeScanner,
    },
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
            email: (state) => state.userModule.loggedInUser.email,
            role: (state) => state.userModule.loggedInUser.type,
        }),
    },

    data: function() {
        return {
            drugstores: [],
            drugs: [],
            reservationDate: "",
            receipt: {},
            drugstoreId: "",
            drugstoreFields: [
                {
                    label: "Name",
                    key: "drugstore.name",
                },
                {
                    label: "Address",
                    key: "drugstore.location.address",
                },
                {
                    label: "City",
                    key: "drugstore.location.city",
                },
                {
                    label: "Rating",
                    key: "drugstore.averageRating",
                },
                {
                    label: "Total Price",
                    key: "totalPrice",
                },
                {
                    key: "actions",
                },
            ],
            drugFields: [
                {
                    label: "Name",
                    key: "name",
                },
                {
                    label: "Form",
                    key: "form",
                },
                {
                    label: "Type",
                    key: "type.name",
                },
                {
                    label: "Manufacturer",
                    key: "manufacturer.name",
                },
            ],
        };
    },
    methods: {
        showModal(item) {
            this.drugstoreId = item.drugstore.id;
            this.$root.$emit("bv::show::modal", "my-modal");
            this.modified = item;
        },
        handleClose() {
            this.$root.$emit("bv::hide::modal", "my-modal");
        },
        getReceiptData: function(receipt, searchData) {
            this.receipt = receipt;
            this.$http
                .post(`https://mrs-isa-usijani.herokuapp.com/drugstores/search-receipt`, {
                    receiptData: receipt,
                    ...searchData,
                })
                .then((response) => {
                    this.drugstores = response.data.drugstores;
                    this.drugs = response.data.drugs;
                    console.log(response.data);
                })
                .catch((error) => console.log(error));
        },
        makeReservation: function(event) {
            event.preventDefault();
            this.$http
                .post(
                    `https://mrs-isa-usijani.herokuapp.com/drugReservation/saveMultipleReservations`,
                    this.makeReservationRequestBody()
                )
                .then((response) => {
                    this.$toastr.s("Success!");
                    console.log(response.data);
                })
                .catch((error) => console.log(error));
        },
        makeReservationRequestBody: function() {
            const requestBodyData = [];
            let counter = 0;
            for (let drug of Object.keys(this.receipt)) {
                requestBodyData.push({
                    patientId: this.user.id,
                    drugstoreId: this.drugstoreId,
                    drugId: drug,
                    amount: this.receipt[drug],
                    date: this.reservationDate,
                });
                counter += 1;
                console.log(counter);
            }

            return requestBodyData;
        },
    },
};
</script>
