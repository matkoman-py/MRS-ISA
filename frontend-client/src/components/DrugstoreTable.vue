<template>
    <b-container>
        <b-row>
            <b-col>
                <b-form @submit="searchDrugstores">
                    <b-form-group id="input-group-1" label="Drugstore name:" label-for="input-1">
                        <b-form-input id="input-1" v-model="searchForm.name" type="text" placeholder="Enter drugstore name">
                        </b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-2" label="City:" label-for="input-2">
                        <b-form-input id="input-2" v-model="searchForm.location.city" type="text" placeholder="Enter city">
                        </b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-3" label="Country:" label-for="input-3">
                        <b-form-input id="input-3" v-model="searchForm.location.country" type="text" placeholder="Enter country">
                        </b-form-input>
                    </b-form-group>
                    <b-button type="submit" variant="outline-hub">Submit</b-button>
                    <br><br><br>
                </b-form>
            </b-col>
        </b-row>
        <b-row align-h="center">
            <b-col>
                
                <template v-for="(chunk, index) in getCardDrugstores()">
                    <b-row cols-lg="4" :key="index">
                        <b-col style="padding:20px; text-align:center" v-for="drugstore in chunk" :key="drugstore.id">
                            <drugstore-card :drugstore="drugstore"></drugstore-card>
                        </b-col>
                    </b-row>
                </template>

                <h1 v-if="drugstores.length == 0"> There are no drugstores that fit the search parameters</h1>
                <b-pagination v-model="currentPage" per-page=4 :total-rows="rows"></b-pagination>

            </b-col>

        </b-row>
    </b-container>
</template>

<script>
    import DrugstoreCard from './DrugstoreCard.vue';

    export default {
        computed: {
            //rows() {
              //  return (this.currentPage+1)*4
            //}
        },
        watch: {
            currentPage: function () {
                if (!this.suppress) {
                    this.getDrugstores();
                } else {
                    this.suppress = false;
                }
            }
        },
        components: {
            DrugstoreCard
        },
        name: "DrugstoreTable",
        data: function () {
            return {
                searchForm: {
                    name: '',
                    location: {
                        city: '',
                        country: '',
                        id: '',
                        address: '',
                    },
                },
                id: '',
                drugstores: [],
                currentPage: 1,
                suppress: false,
                currentSearch : {},
                rows:0
            }
        },
        methods: {
            getDrugstoresLength: function () {
                this.$http.post(`https://mrs-isa-usijani.herokuapp.com/drugstores/searchLength`, this.currentSearch)
                    .then(response => {
                        this.rows = response.data;
                    })
                    .catch(error => console.log(error));
            },
            searchDrugstores: function () {
                this.currentSearch = JSON.parse(JSON.stringify(this.searchForm));
                this.getDrugstores();
            },
            getDrugstores: function () {
                this.$http.post(`https://mrs-isa-usijani.herokuapp.com/drugstores/search?page=${this.currentPage-1}&size=4`, this.currentSearch)
                    .then(response => {
                        this.getDrugstoresLength()
                        if (response.data.length == 0) {
                            //this.suppress = true;
                            this.drugstores = this.mapDrugstores(response);
                            //this.currentPage--;
                        } else {
                            //this.suppress = false;
                            this.drugstores = this.mapDrugstores(response);
                        }
                    })
                    .catch(error => console.log(error));
            },
            getAllDrugstores: function () {
                this.$http.get('https://mrs-isa-usijani.herokuapp.com/drugstores')
                    .then(response => {
                        this.drugstores = this.mapDrugstores(response);
                    })
                    .catch(error => console.log(error));
            },
            mapDrugstores: function (response) {
                return response.data.map(drugstore =>
                    ({
                        id: drugstore.id,
                        name: drugstore.name,
                        adress: drugstore.location.address,
                        country: drugstore.location.country,
                        city: drugstore.location.city,
                        description: drugstore.decription,
                        rating: drugstore.rating,
                    }));
            },
            getCardDrugstores: function () {
                let chunkArray = [];
                for (var i = 0; i < this.drugstores.length; i += 4) {
                    chunkArray.push(this.drugstores.slice(i, i + 4));
                }
                return chunkArray

            },
        },
        mounted: function () {
            this.searchDrugstores();
        }
    }
</script>