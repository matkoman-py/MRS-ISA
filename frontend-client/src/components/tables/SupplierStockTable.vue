<template>
    <div>
        <b-container class="mt-3">
            <b-row>
                <b-col cols="4">
                    <supplier-stock-search :perPage="perPage" :currentPage="currentPage"
                        v-on:success-search-results="data => supplierStocks = data" ref="supplierStockSearch">
                    </supplier-stock-search>
                    <add-supplier-stock v-on:add-stock-success="() => $refs['supplierStockSearch'].search()">
                    </add-supplier-stock>
                </b-col>
                <b-col>
                    <b-card>
                        <b-table v-if="supplierStocks.length != 0" id="stock-table" striped hover
                            :items="supplierStocks" :fields="fields">
                            <template #cell(actions)="row">
                                <b-button size="sm" variant="outline-hub"
                                    @click="showEditModal(row.item, $event.target)" class="mr-1">
                                    Edit
                                </b-button>
                                <b-button size="sm" variant="outline-hub"
                                    @click="showDeleteModal(row.item, $event.target)" class="mr-1">
                                    Delete
                                </b-button>
                            </template>
                        </b-table>
                        <div v-else>
                            <h3>No results</h3>
                        </div>
                        <b-pagination v-if="supplierStocks.length != 0" v-model="currentPage" :per-page="perPage"
                            :total-rows="rows"></b-pagination>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
        <b-modal :id="editModal.id" :title="editModal.title" ok-only v-on:ok='editStock' size="lg">
            <pre>
            <b-form>
                <b-form-group id="input-group-2" label="Amount:" label-for="input-2">
                    <b-form-input id="input-2" v-model="editModal.stock.amount" type="number" placeholder="Enter drug amount">
                    </b-form-input>
                </b-form-group>
            </b-form>
            </pre>
        </b-modal>
        <b-modal :id="deleteModal.id" :title="deleteModal.title" ok-only v-on:ok='deleteStock' size="xl">
            Are you sure that you want to delete this stock?
        </b-modal>
    </div>
</template>

<script>
    import AddSupplierStock from '../forms/supplier-stock/AddSupplierStock.vue'
    import SupplierStockSearch from '../search/SupplierStockSearch.vue'

    export default {
        name: "SupplierStockTable",
        components: {
            SupplierStockSearch,
            AddSupplierStock
        },
        computed: {
            rows: function () {
                return (this.currentPage + 1) * this.perPage;
            },
        },
        data: function () {
            return {
                supplierStocks: [],
                perPage: 5,
                currentPage: 1,
                fields: [{
                        key: 'drugName',
                        label: 'Drug'
                    },
                    {
                        key: 'amount',
                        label: 'Amount'
                    },
                    {
                        key: 'actions',
                        label: ''
                    },
                ],
                deleteModal: {
                    id: "deleteModal",
                    title: "",
                    stock: {},
                },
                editModal: {
                    id: "editModal",
                    title: "",
                    stock: {},
                }
            };
        },
        methods: {
            showEditModal: function (rowItem, button) {
                this.editModal.title = `drug: ${rowItem.drugName}`;
                this.editModal.stock = JSON.parse(JSON.stringify(rowItem));
                this.$root.$emit('bv::show::modal', this.editModal.id, button);
            },
            editStock: function () {
                this.$http.put(`https://mrs-isa-usijani.herokuapp.com//supplier-stocks/`, this.editModal.stock)
                    .then(() => {
                        this.$refs['supplierStockSearch'].search();
                    })
                    .catch(error => this.$toastr.e(error));
            },
            showDeleteModal: function (rowItem, button) {
                this.deleteModal.title = `Drug stock: ${rowItem.drugName}`;
                this.deleteModal.stock = JSON.parse(JSON.stringify(rowItem));
                this.$root.$emit('bv::show::modal', this.deleteModal.id, button);
            },
            deleteStock: function () {
                this.$http.delete(`https://mrs-isa-usijani.herokuapp.com//supplier-stocks/${this.deleteModal.stock.id}`)
                    .then(() => {
                        this.$refs['supplierStockSearch'].search();
                    })
                    .catch(error => this.$toastr.e(error));
            }
        }
    }
</script>