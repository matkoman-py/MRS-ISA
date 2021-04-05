<template>

    <b-container>
        <b-row >
            <b-col>
                <h1>Drugstore search: </h1>
                <b-form @submit="getDrugstores">
                    <b-form-group id="input-group-1" label="Drugstore name:" label-for="input-1">
                        <b-form-input id="input-1" v-model="name" type="text" placeholder="Enter drugstore name">
                        </b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-2" label="City:" label-for="input-2">
                        <b-form-input id="input-2" v-model="city" type="text" placeholder="Enter city">
                        </b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-3" label="Country:" label-for="input-3">
                        <b-form-input id="input-3" v-model="country" type="text" placeholder="Enter country">
                        </b-form-input>
                    </b-form-group>


                    <b-button type="submit" variant="primary">Submit</b-button>
                    <br><br><br>
                </b-form>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <!-- <b-card-group v-for="n in getNumberOfRows()" :key="n">
                                                                                                                                             
                    <drugstore-card  v-for="drugstore in getCardDrugstores()" :key="drugstore.id" :drugstore="drugstore"></drugstore-card>
                
                </b-card-group> -->
                <b-card-group columns>
                                                                                                                                             
                    <drugstore-card  v-for="drugstore in drugstores" :key="drugstore.id" :drugstore="drugstore"></drugstore-card>
                
                </b-card-group>
                <!-- <b-table head-variant="dark" striped hover :items="drugstores" sticky-header="400px"></b-table> -->
                <h1 v-if="drugstores.length == 0"> There are no drugstores that fit the search parameters</h1>

            </b-col>

        </b-row>
    </b-container>
</template>

<script>
    import axios from "axios";
    import DrugstoreCard from './DrugstoreCard.vue';

    export default {
  components: { DrugstoreCard },
        name: "DrugstoreTable",
        data: function () {
            return {
                name: '',
                city: '',
                country: '',
                drugstores: [],
                threeDrugstores: [],
                cardCounter: 0,
            }
        },
        methods: {
            getDrugstores: function () {
                axios.get('http://localhost:8081/drugstores/search', {
                        params: {
                            drugStoreNameParam: this.name,
                            drugStoreCityParam: this.city,
                            drugStoreCountryParam: this.country,
                        }
                    })
                    .then(response => {
                        this.drugstores = response.data.map(drugstore =>
                            ({
                                name: drugstore.name,
                                adress: drugstore.location.address,
                                country: drugstore.location.country,
                                city: drugstore.location.city,
                                description: drugstore.decription,
                                rating: drugstore.averageRating,
                            }));
                    })
                    .catch(error => console.log(error));
            },
            getAllDrugstores: function () {
                axios.get('http://localhost:8081/drugstores')
                    .then(response => {
                        this.drugstores = response.data.map(drugstore =>
                            ({
                                name: drugstore.name,
                                adress: drugstore.location.address,
                                country: drugstore.location.country,
                                city: drugstore.location.city,
                                description: drugstore.decription,
                                rating: drugstore.averageRating,
                            }));
                    })
                    .catch(error => console.log(error));
            },
            getCardDrugstores: function(){
                let nesto = this.cardCounter+3;
               
                if(this.cardCounter+3>this.drugstores.length){
                    nesto = this.drugstores.length
                }
                this.threeDrugstores = this.drugstores.slice(this.cardCounter,nesto)
                
                if(this.cardCounter+3<this.drugstores.length){
                    this.cardCounter+=3
                }
                return this.threeDrugstores
                
            },
            getNumberOfRows: function(){
                
                return Math.ceil(this.drugstores.length/3)
            }
        },
        mounted: function () {
            this.getAllDrugstores();
        }
    }
</script>