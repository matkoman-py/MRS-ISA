<template>
  <b-container >
    <h1> List of dermatologists </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="dermatologists"></b-table>
        <p v-if="dermatologists.length == 0"> There are no dermatologists for this criteria.</p>
    </div>
    <b-container style="margin-left:60px">
        <b-row>
            <p><b>Search:</b></p>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">     
            <b-form align="center" style="width:80%" inline>
                <label>Name:</label>
                <b-form-input style="margin:20px;" v-model="searchName" placeholder="Enter name"></b-form-input>
                <label>Surname:</label>
                <b-form-input style="margin:20px;" v-model="searchSurname" placeholder="Enter surname"></b-form-input>
            </b-form>
        </b-row>

        <b-row>
            <p><b>Filters:</b></p>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">         
            <b-form align="center" style="width:80%" inline>
                <label>Average rate:</label>
                <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMin" placeholder="min"></b-form-input>
                <label> - </label>
                <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMax" placeholder="max"></b-form-input>
            </b-form>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">         
            <b-form align="center" style="width:80%" inline>
            <label>Drugstore:</label>
            <b-form-select style="margin:20px;" v-model="selected" :options="drugstores" aria-placeholder="All drugstores"></b-form-select>
            </b-form>
        </b-row>

        <b-row align-h="center">
            <b-button type="reset" style="margin:40px" variant="outline-hub" size="lg" @click="onReset">Reset</b-button>
            <b-button type="submit" style="margin:40px" variant="outline-hub" size="lg" @click="onSubmit">Submit</b-button>
        </b-row>


    </b-container>
  </b-container>
  
</template>

<script>

  export default {
    data: function() {
      return {
        dermatologists: [],
        searchName: '',
        searchSurname: '',
        filterRateMin: 0,
        filterRateMax: 5,
        selected:'',
        drugstores: []
      }
    },
    methods: {
        getDermatologists : function(){
            this.$http.get('http://localhost:8081/employees/dermatologists')
            .then(response => {
            this.dermatologists = response.data.map(dermatologist => 
            (
                    {
                        name: dermatologist.name,
                        surname: dermatologist.surname,
                        averageRate: dermatologist.averageRate,
                        drugstore: dermatologist.drugstoreName
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        getDrugstores : function() {
            this.$http.get('http://localhost:8081/drugstores')
            .then(response => {
            this.drugstores = response.data.map((drugstore) =>
                ({
                value: drugstore,
                text: drugstore.name
                })
            );
        })
            .catch(error => console.log(error));
        },
        dermatologistSearchResult : function() {
            this.$http.get('http://localhost:8081/employees/dermatologists/search', {
              params: {
                name: this.searchName,
                surname: this.searchSurname,
                minRate: this.filterRateMin,
                maxRate: this.filterRateMax,
                drugstore: this.selected.name ? this.selected.name : "",
            }
            })
            .then(response => {
            this.dermatologists = response.data.map(dermatologist => 
            (
                    {
                        name: dermatologist.name,
                        surname: dermatologist.surname,
                        averageRate: dermatologist.averageRate,
                        drugstore: dermatologist.drugstoreName
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        onSubmit() {
            this.dermatologistSearchResult()
        },
        onReset() {
            this.searchName = ''
            this.searchSurname = ''
            this.filterRateMin = 0
            this.filterRateMax = 5
            this.selected = ''
            this.getDermatologists()
        },
    },
    mounted: function(){
        this.getDermatologists();
        this.getDrugstores();
    }
  }
</script>