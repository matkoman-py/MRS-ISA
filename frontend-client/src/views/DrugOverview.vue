<template>
  <b-container>
    <h1> Drugs </h1>
    <div>
        <b-table striped hover :items="drugs"></b-table>
    </div>
  </b-container>
</template>

<script>
  import axios from "axios";
  export default {
    name: "DrugOverview",
    data: function() {
      return {
        drugs: [],
      }
    },
    methods: {
        getDrugs : function(){
            axios.get('http://localhost:8081/drugs')
            .then(response => {
		console.log(response);
                this.drugs = response.data.map(drug => 
                (
                    {
                        name: drug.name,
                        form: drug.form, 
                        type: drug.type.name,
                        receipt: drug.receipt ? "Yes" : "No",
                        manufacturer: drug.manufacturer.name,
                        substitutions: 
                            (!drug.substitutions || drug.substitutions.length == 0) 
                            ? "No substitute"
                            : drug.substitutions.map(sub => sub.name).join(", "),
                        ingredients: drug.ingredients.map(ingredient => ingredient.name).join(", "),
                    }
                ));
            })
            .catch(error => console.log(error));
        }
    },
    mounted: function(){
        console.log("koj ")
        this.getDrugs();
    }
  }
</script>
