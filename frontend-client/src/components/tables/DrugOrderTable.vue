<template>
    <div>
        <b-container class="mt-3">
            <b-row>
                <b-col cols="4">
                    <drug-order-search :perPage="perPage" :currentPage="currentPage"
                        v-on:success-search-results="data => drugOrders = data" ref="drugOrdersSearch">
                    </drug-order-search>
                </b-col>
                <b-col>
                    <b-card>
                        <b-table v-if="drugOrders.length != 0" id="stock-table" striped hover :items="drugOrders"
                            :fields="fields">
                            <template #cell(actions)="row">
                                <b-button size="sm" variant="outline-hub"
                                    @click="showDetailsModal(row.item, $event.target)" class="mr-1">
                                    Show details
                                </b-button>
                            </template>
                        </b-table>
                        <div v-else>
                            <h3>No results</h3>
                        </div>
                        <b-pagination v-if="drugOrders.length != 0" v-model="currentPage" :per-page="perPage"
                            :total-rows="rows"></b-pagination>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
        <b-modal :id="detailsModal.id" :title="detailsModal.title" size="xl">
            <b-table :items="detailsModal.order.stock" :fields="stockFields">
            </b-table>
            <div>
                <b-form inline v-if="detailsModal.order.state != 'Expiered'">
                    <b-form-group id="input-group-2" label="Price:" label-for="input-2">
                        <b-form-input class="ml-3" id="input-2" v-model="detailsModal.price" type="number"
                            placeholder="Enter price">
                        </b-form-input>
                    </b-form-group>
                    <b-form-group class="ml-3" invalid-feedback="date is required">
                        <b-form-datepicker id="date-input" v-model="detailsModal.date" required>
                        </b-form-datepicker>
                    </b-form-group>
                    <b-form-group class="ml-3" invalid-feedback="time is required">
                        <b-form-timepicker locale="en" v-model="detailsModal.time">
                        </b-form-timepicker>
                    </b-form-group>
                    <b-button class="ml-3 float-right" size="sm" variant="outline-hub" id="offer-button"
                        v-on:click="makeOffer">
                        Make Offer
                    </b-button>
                </b-form>
            </div>
        </b-modal>
    </div>
</template>

<script>
    import {
        mapState
    } from 'vuex';
    import DrugOrderSearch from "../search/DrugOrderSearch";
    export default {
        name: "DrugOrderTable",
        components: {
            DrugOrderSearch,
        },
        computed: {
            ...mapState({
                supplierId: state => state.userModule.loggedInUser.id,
            }),
            rows: function () {
                return (this.currentPage + 1) * this.perPage;
            }
        },
        data: function () {

            return {
                drugOrders: [],
                perPage: 5,
                currentPage: 1,
                detailsModal: {
                    id: 'detailsModal',
                    order: {},
                    price: 0,
                    date: "",
                    time: "",
                    title: ''
                },
                stockFields: [{
                        key: 'drug.name',
                        label: 'Drug'
                    },
                    {
                        key: 'amount',
                        label: 'Amount'
                    }
                ],
                fields: [{
                        key: 'drugstoreName',
                        label: 'Drugstore'
                    },
                    {
                        key: 'status',
                        label: 'Status'
                    },
                    {
                        key: 'deadlineDate',
                        label: 'Deadline Date'
                    },
                    {
                        key: 'deadlineTime',
                        label: 'Deadline Time'
                    },
                    {
                        key: 'actions',
                        label: ''
                    },
                ],

            };
        },
        methods: {
            showDetailsModal: function (rowItem, button) {
                this.detailsModal.title = `drug: ${rowItem.drugstoreName}`;
                this.detailsModal.order = JSON.parse(JSON.stringify(rowItem));
                this.$root.$emit('bv::show::modal', this.detailsModal.id, button);
            },
            makeOffer: function () {
                const postObject = {
                    drugOrderId: this.detailsModal.order.id,
                    price: this.detailsModal.price,
                    status: "Pending",
                    deliveryDate: this.detailsModal.date,
                    deliveryTime: this.detailsModal.time,
                    supplierId: this.supplierId,
                };
                console.log(this.detailsModal.order);
                this.$http.post("http://localhost:8081/offers/", postObject)
                    .then(response => {
                        console.log(response);
                        this.$toastr.s("success-offer");
                    })
                    .catch(error => this.$toastr.e(error.message));
            }
        }
    }
</script>