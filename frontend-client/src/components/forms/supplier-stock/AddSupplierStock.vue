<template>
    <b-card class="mt-3">
        <b-form class="text-left" @submit="add">
            <b-form-group id="input-group-1" label="Drug:" label-for="input-1">
                <multiselect v-model="form.drug" track-by="id" label="name"
                    placeholder="Select one" :options="drugs" :searchable="true" :allow-empty="false" select-label="">
                </multiselect>
            </b-form-group>
            <b-form-group id="input-group-2" label="Amount:" label-for="input-2">
                <b-form-input id="input-2" v-model="form.amount" type="number" placeholder="Enter drug amount">
                </b-form-input>
            </b-form-group>
            <div class="text-center">
                <b-button type="submit" size="sm" variant="outline-hub">
                    Add To Stock
                </b-button>
            </div>
        </b-form>
    </b-card>
</template>
<script>
    import Multiselect from "vue-multiselect";
import { mapState } from 'vuex';
    export default {
        name: "SupplierStockTable",
        components: {
            Multiselect
        },
        computed: {
            ...mapState({
                supplierId: state => state.userModule.loggedInUser.id,
            })
        },
        data: function () {
            return {
                form: {
                    drug: "",
                    amount: 0,
                },
                drugs: [],
            }
        },
        methods: {
            getDrugs: function () {
                this.$http.get("http://localhost:8081/drugs/")
                    .then(response => {
                        this.drugs = response.data;
                    })
                    .catch(error => this.$toastr.e(error));
            },
            add: function(){
                this.$http.post("http://localhost:8081/supplier-stocks/", 
                {
                    supplierId: this.supplierId,
                    drugId: this.form.drug.id,
                    drugName: this.form.drug.name,
                    id: "",
                    amount: this.form.amount,
                })
                .then(response => {
                    this.$emit("add-stock-success", response.data);
                })
                .catch(error => this.$toastr.e(error.message));
            }
        },
        mounted: function () {
            this.getDrugs();
        }
    }
</script>