<template>

    <b-container>
        <b-row class="mt-3">
            <b-col>
                <b-card>
                    <qr-code-scanner v-on:qr-code-scanned="getReceiptDrugstores"></qr-code-scanner>
                </b-card>
                <b-card v-if="drugstores.length != 0" class="mt-3">
                    <b-table head-variant="outline-hub" striped hover :fields="fields" :items="drugstores" sticky-header="400px">
                    </b-table>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import { mapState } from 'vuex';
    import QrCodeScanner from "../components/QrCodeScanner";

    export default {
        name: "ReceiptGeneratorView",
        components: {
            QrCodeScanner
        },
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
                email: state => state.userModule.loggedInUser.email,
                role: state => state.userModule.loggedInUser.type
            }),
        },

        data: function () {
            return {
                drugstores: [],
                fields: [
                    {
                        key: 'name',
                    },
                    {
                        key: 'description',
                    },
                    {
                        key: 'averageRating',
                    },
                    {
                        key: 'workingHoursFrom',
                    },
                    {
                        key: 'workingHoursTo',
                    },
                ],
            }
        },
        methods: {
            getReceiptDrugstores: function(receipt){
                this.$http.post(`http://localhost:8081/drugstores/search-receipt`, {receiptData: receipt})
                    .then(response => {
                        this.drugstores = response.data;
                        console.log(response.data);
                    })
                    .catch(error => console.log(error));
            },
        },
    }
</script>