<template>
    <b-container>
        <b-row class="mt-3">
            <b-col>
                <b-card>
                    <qr-code-scanner
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
                        ></b-table>
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
                    ></b-table>
                    <div v-else>
                        There are no drugstores which contain all those drugs.
                    </div>
                </b-card>
            </b-col>
        </b-row>
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
            drugstoreFields: [
                {
                    label: "Name",
                    key: "drugstore.name",
                },
                {
                    label: "Description",
                    key: "drugstore.description",
                },
                {
                    label: "Average Rating",
                    key: "drugstore.averageRating",
                },
                {
                    label: "From",
                    key: "drugstore.workingHoursFrom",
                },
                {
                    label: "To",
                    key: "drugstore.workingHoursTo",
                },
                {
                    label: "Total Price",
                    key: "totalPrice",
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
        getReceiptData: function(receipt, searchData) {
            console.log(searchData);
            this.$http
                .post(`http://localhost:8081/drugstores/search-receipt`, {
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
    },
};
</script>
