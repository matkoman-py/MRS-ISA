<template>

    <b-container>
        <b-row class="mt-3">
            <b-col cols="4">
                <b-card>
                    <h3>Search</h3>
                    <b-form @submit="searchDrugs">
                        <b-form-group id="input-group-1" label="Drug name:" label-for="input-1">
                            <b-form-input id="input-1" v-model="searchForm.name" type="text"
                                placeholder="Enter drug name">
                            </b-form-input>
                        </b-form-group>

                        <b-form-group id="input-group-3" label="Drug type:" label-for="input-3">
                            <b-form-select id="input-3" v-model="searchForm.type" placeholder="Choose a drug type"
                                :options="drugTypeOptions">
                            </b-form-select>
                        </b-form-group>

                        <b-form-group id="input-group-1" label="Drug form:" label-for="input-1">
                            <b-form-input id="input-1" v-model="searchForm.form" type="text"
                                placeholder="Enter a drug form">
                            </b-form-input>
                        </b-form-group>

                        <b-form-group id="input-group-3" label="Drug manufacturer:" label-for="input-3">
                            <b-form-select id="input-3" v-model="searchForm.manufacturerId"
                                :options="manufacturerOptions">
                            </b-form-select>
                        </b-form-group>

                        <b-form-group id="input-group-3" label="Receipt needed:" label-for="input-3">
                            <b-form-select id="input-3" v-model="searchForm.receipt" :options="receiptOptions">
                            </b-form-select>
                        </b-form-group>

                        <b-button type="submit" variant="outline-hub">Submit</b-button>
                        <br>
                        <br>
                    </b-form>
                </b-card>
            </b-col>
            <b-col cols="8">
                <b-card>
                    <b-table head-variant="outline-hub" striped hover :fields="fields" :items="drugs"
                        sticky-header="400px">
                        <template #cell(actions)="row">
                            <b-button variant="outline-hub" v-if="row.item" size="sm"
                                @click="addToReceipt(row.item, row.index, $event.target)" class="mr-1">
                                Add to Receipt
                            </b-button>
                        </template>
                    </b-table>
                    <b-pagination v-model="currentPage" per-page=3 :total-rows="rows"></b-pagination>
                </b-card>
                <b-card class="mt-3">
                    <qrcode-vue :value="JSON.stringify(eReceipt)" :size="300" level="M" ref="canvas"></qrcode-vue>
                    <b-button variant="outline-hub" size="sm"
                                @click="downloadQrCode"
                                class="mr-1">
                                Download as image
                    </b-button>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import { mapState } from 'vuex';
    import QrcodeVue from 'qrcode.vue';

    export default {
        name: "ReceiptGeneratorView",
        components: {
            QrcodeVue
        },
        computed: {
            ...mapState({
                user: state => state.userModule.loggedInUser,
                email: state => state.userModule.loggedInUser.email,
                role: state => state.userModule.loggedInUser.type
            }),
            rows() {
                return (this.currentPage + 1) * 3
            }
        },
        watch: {
            currentPage: function () {
                if (!this.suppress) {
                    this.getDrugs();
                } else {
                    this.suppress = false;
                }
            }
        },
        data: function () {
            return {
                drugstores: [],
                searchForm: {
                    name: '',
                    type: '',
                    form: '',
                    manufacturerId: '',
                    receipt: "",
                },
                fields: [{
                        key: "name"
                    },
                    {
                        key: "form"
                    },
                    {
                        key: "type"
                    },
                    {
                        key: "receipt"
                    },
                    {
                        key: "manufacturer"
                    },
                    {
                        key: 'actions',
                        label: ''
                    }
                ],
                receiptOptions: [{
                        value: null,
                        text: "Both"
                    },
                    {
                        value: true,
                        text: 'Yes'
                    },
                    {
                        value: false,
                        text: 'No'
                    },
                ],
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
                eReceipt: "",
            }
        },
        methods: {
            downloadQrCode: function(event){
                event.preventDefault();
                var link = document.createElement('a');
                link.download = 'qrcode.png';
                link.href = document.getElementsByTagName("canvas")[0].toDataURL()
                link.click();
                link.delete;
            },
            addToReceipt: function (drug) {
                if (this.eReceipt.includes(drug.id)) {
                    return;
                }
                this.eReceipt += `${drug.id},1;`;
            },
            searchDrugs: function () {
                this.currentSearch = JSON.parse(JSON.stringify(this.searchForm));
                this.getDrugs();
            },
            getDrugs: function () {
                this.$http.post(`http://localhost:8081/drugs/search?page=${this.currentPage-1}&size=3`, this
                        .currentSearch)
                    .then(response => {
                        if (response.data.length == 0 && this.currentPage != 1) {
                            this.suppress = true;
                            this.currentPage--;
                        } else {
                            this.suppress = false;
                            this.drugs = this.mapDrugs(response);
                        }
                    })
                    .catch(error => console.log(error));
            },
            getManufacturers: function () {
                this.$http.get("http://localhost:8081/manufacturers")
                    .then(response => {
                        this.manufacturerOptions = response.data.map((manufacturer) =>
                            ({
                                value: manufacturer.id,
                                text: manufacturer.name
                            })
                        );
                    })
                    .catch(error => console.log(error));
            },
            getDrugTypes: function () {
                this.$http.get("http://localhost:8081/drug-types")
                    .then(response => {
                        this.drugTypeOptions = response.data.map((drugType) =>
                            ({
                                value: drugType.name,
                                text: drugType.name
                            })
                        );
                    })
                    .catch(error => console.log(error));
            },
            getIngrediants: function () {
                this.$http.get("http://localhost:8081/ingredients")
                    .then(response => {
                        this.ingrediants = response.data;
                    })
                    .catch(error => console.log(error));
            },
            getSubstitutionDrugs: function () {
                this.$http.get("http://localhost:8081/drugs")
                    .then(response => {
                        this.substitutions = response.data;
                    })
                    .catch(error => console.log(error));
            },
            mapDrugs: function (response) {
                return response.data.map(drug =>
                    ({
                        id: drug.id,
                        name: drug.name,
                        form: drug.form,
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                        substitutions: (!drug.substitutions || drug.substitutions.length == 0) ?
                            "No substitute" : drug.substitutions.map(sub => sub.name).join(", "),
                        ingredients: drug.ingredients.map(ingredient => ingredient.name).join(", "),
                    }));
            },
        },

        mounted: function () {
            this.getManufacturers();
            this.getIngrediants();
            this.getSubstitutionDrugs();
            this.getDrugTypes();
            this.searchDrugs();

        }
    }
</script>

<style>

</style>