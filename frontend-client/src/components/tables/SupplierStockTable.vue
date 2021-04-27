<template>
  <div>
    <b-container class="mt-3">
        <b-row>
            <b-col cols="4">

                <supplier-stock-search 
                    :perPage="perPage"
                    :currentPage="currentPage"
                    v-on:success-search-results="data => supplierStocks = data"
                    ref="supplierStockSearch">
                </supplier-stock-search>
                <add-supplier-stock
                    v-on:add-stock-success="() => $refs['supplierStockSearch'].search()">
                </add-supplier-stock>
            </b-col>
            <b-col>
                <b-card>
                    <b-table v-if="supplierStocks.length != 0" id="stock-table" striped hover :items="supplierStocks" :fields="fields">
                        <!-- <template #cell(actions)="row">
                            <b-button size="sm" @click="showDeleteModal(row.item, $event.target)" class="mr-1">
                                Delete
                            </b-button>
                        </template> -->
                    </b-table>
                    <div v-else>
                        <h3>No results</h3>
                    </div>
                    <b-pagination v-if="supplierStocks.length != 0" v-model="currentPage" :per-page="perPage" :total-rows="rows"></b-pagination>
                </b-card>
            </b-col>
        </b-row>
        
        
        <!-- <b-button size="sm" @click="showAddModal($event.target, 'Supplier')" class="mr-1">
                    Add Supplier
        </b-button>
        <b-button size="sm" @click="showAddModal($event.target, 'DrugstoreAdmin')" class="mr-1">
                    Add Drugstore Admin
        </b-button> -->
    </b-container>

    <!-- <b-modal :id="addModal.id" :title="addModal.title" ok-only v-on:ok='addUser' size="xl">
        <add-user-form :type="addModal.type" v-on:added-user="handleSuccess" ref='add-user-form'></add-user-form>
    </b-modal>
    <b-modal :id="deleteModal.id" :title="deleteModal.title" ok-only v-on:ok='deleteUser' @hide="resetDeleteModal" size="xl">
        Are you sure that you want to delete this user?
    </b-modal> -->
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
    computed:{
        rows : function(){
            return (this.currentPage+1)*this.perPage;
        }, 
    },
    data: function() {
        return {
            supplierStocks: [],
            perPage: 5,
            currentPage: 1,
            fields: [
                { key: 'drugName', label: 'Drug' },
                { key: 'amount', label: 'Amount' },
            ],
        }
    },
}
</script>