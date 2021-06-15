<template>
  <b-container >
    <h1> List of pharmacists </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="pharmacists"></b-table>
        <p v-if="pharmacists.length == 0"> There are no pharmacists for this criteria.</p>
    </div>
    <b-container style="margin-left:60px">
        <b-row>
            <p><b>Search:</b></p>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">     
            <b-form align="center" style="width:80%" inline>
                <b-label>Name:</b-label>
                <b-form-input style="margin:20px;" v-model="searchName" placeholder="Enter name"></b-form-input>
                <b-label>Surname:</b-label>
                <b-form-input style="margin:20px;" v-model="searchSurname" placeholder="Enter surname"></b-form-input>
            </b-form>
        </b-row>

        <b-row>
            <p><b>Filters:</b></p>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">         
            <b-form align="center" style="width:80%" inline>
                <b-label>Average rate:</b-label>
                <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMin" placeholder="min"></b-form-input>
                <b-label> - </b-label>
                <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMax" placeholder="max"></b-form-input>
            </b-form>
        </b-row>
        <b-row style="margin-left:20px" align-h="left">         
            <b-form align="center" style="width:80%" inline>
            <b-label>Drugstore:</b-label>
            <b-form-select style="margin:20px;" v-model="selected" :options="drugstores" ></b-form-select>
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
        pharmacists: [],
        searchName: '',
        searchSurname: '',
        filterRateMin: 0,
        filterRateMax: 5,
        selected:'',
        drugstores: []
      }
    },
    methods: {
        getPharmacists : function(){
            this.$http.get('https://mrs-isa-usijani.herokuapp.com/employees/pharmacists')
            .then(response => {
            this.pharmacists = response.data.map(pharmacist => 
            (
                    {
                        name: pharmacist.name,
                        surname: pharmacist.surname,
                        averageRate: pharmacist.averageRate,
                        drugstore: pharmacist.drugstoreName
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        getDrugstores : function() {
            this.$http.get('https://mrs-isa-usijani.herokuapp.com/drugstores')
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
        pharmacistSearchResult : function() {
            this.$http.get('https://mrs-isa-usijani.herokuapp.com/employees/pharmacists/search', {
              params: {
                name: this.searchName,
                surname: this.searchSurname,
                minRate: this.filterRateMin,
                maxRate: this.filterRateMax,
                drugstore: this.selected.name ? this.selected.name : "",
            }
            })
            .then(response => {
            this.pharmacists = response.data.map(pharmacist => 
            (
                    {
                        name: pharmacist.name,
                        surname: pharmacist.surname,
                        averageRate: pharmacist.averageRate,
                        drugstore: pharmacist.drugstoreName
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        onSubmit() {
            this.pharmacistSearchResult()
        },
        onReset(event) {
            event.preventDefault()
            this.searchName = ''
            this.searchSurname = ''
            this.filterRateMin = 0
            this.filterRateMax = 5
            this.selected = ''
            this.getPharmacists()
        },
    },
    mounted: function(){
        this.getPharmacists();
        this.getDrugstores();
    }
  }
</script>