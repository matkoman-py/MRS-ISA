<template>
    <div>
        <b-container class="mt-3">
            <b-row>
                <b-col cols="4">
                    <offer-search :perPage="perPage" :currentPage="currentPage"
                        v-on:success-search-results="data => offers = data" ref="offersSearch">
                    </offer-search>
                </b-col>
                <b-col>
                    <b-card>
                        <b-table v-if="offers.length != 0" id="stock-table" striped hover :items="offers"
                            :fields="fields">
                        </b-table>
                        <div v-else>
                            <h3>No results</h3>
                        </div>
                        <b-pagination v-if="offers.length != 0" v-model="currentPage" :per-page="perPage"
                            :total-rows="rows"></b-pagination>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import {
        mapState
    } from 'vuex';
    import OfferSearch from "../search/OfferSearch";
    export default {
        name: "OfferTable",
        components: {
            OfferSearch,
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
                offers: [],
                perPage: 5,
                currentPage: 1,
               
                fields: [
                    {
                        key: 'price',
                        label: 'Price'
                    },
                    {
                        key: 'status',
                        label: 'Status'
                    },
                    {
                        key: 'deliveryDate',
                        label: 'Delivery date'
                    },
                    {
                        key: 'deliveryTime',
                        label: 'Delivery time'
                    },
                ],

            };
        },
        methods: {
        }
    }
</script>