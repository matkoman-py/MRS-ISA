<template>
    <b-form @submit="getDrugstores">
        <b-form-group id="input-group-1" label="Drugstore name:" label-for="input-1">
            <b-form-input id="input-1" v-model="name" type="text" placeholder="Enter drugstore name"></b-form-input>
        </b-form-group>

        <b-table head-variant="dark" striped hover :items="drugstores"></b-table>
        <b-button type="submit" variant="primary">Submit</b-button>
        <br>
        <br>
    </b-form>
</template>

<script>
  import axios from "axios";

    export default {
        name: "DrugstoreTable",
        data:function(){
            return{
                name:'',
                drugstores:[],
            }
        },
        methods:{
            getDrugstores: function(){
                axios.get('http://localhost:8081/drugstores/search')
                .then(response =>{
                    this.drugstores = response.data.map(drugstore =>
                    (
                        {
                        name: drugstore.name,
                        adress: drugstore.location.adress,
                        country: drugstore.location.country,
                        city: drugstore.location.city,
                        description: drugstore.description,
                        rating: drugstore.averageRating,
                    }
                    ));
                })
                .catch(error => console.log(error));
            },
            getAllDrugstores: function(){
                axios.get('http://localhost:8081/drugstores')
                .then(response =>{
                    this.drugstores = response.data.map(drugstore =>
                    (
                        {
                        name: drugstore.name,
                        adress: drugstore.location.adress,
                        country: drugstore.location.country,
                        city: drugstore.location.city,
                        description: drugstore.description,
                        rating: drugstore.averageRating,
                    }
                    ));
                })
                .catch(error => console.log(error));
            }
        },
        mounted: function(){
            this.getAllDrugstores();
        }
    }
</script>