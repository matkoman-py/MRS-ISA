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
        <b-row align-h="center">
            <b-col>
                
                <template v-for="chunk in getCardDrugstores()">
                    <b-row  cols-lg="4" :key="chunk">
                        <b-col style="padding:20px; text-align:center" v-for="drugstore in chunk" :key="drugstore.id">
                            <drugstore-card :drugstore="drugstore"></drugstore-card>
                        </b-col>
                    </b-row>
                </template>
            
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
                                id: drugstore.id,
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
                let chunkArray=[];
                for(var i=0; i<this.drugstores.length; i+=4){
                    chunkArray.push(this.drugstores.slice(i, i+4));
                }
                return chunkArray
                
            },
        },
        mounted: function () {
            this.getAllDrugstores();
        }
    }
</script>