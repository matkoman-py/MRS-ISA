<template>
  <b-container>
    <h1> Employees in drugstore </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="employees"></b-table>
    </div>
    <div>
        <router-link to="/addPharmacistForm">
           <b-button style="margin-right:50px" variant="success">Add pharmacist</b-button>
        </router-link>
        <router-link to="/addDermatologistForm">
                <b-button style="margin-left:50px; margin-right:50px" variant="success">Add dermatologist</b-button>
        </router-link>
        <b-button style="margin-left:50px" variant="danger">Delete employee</b-button>
    </div>
    <b-container style="margin:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="onSubmit" @reset="onReset">
                <p style="margin-top:20px">Search text:</p>
                <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
                <p style="margin-top:20px">Average rate:</p>
                <b-row align-h="center">   
                  <b-form align="center" inline>
                    <b-form-input type="number" min="0" max="10" style="margin:20px;" v-model="filterRateMin" placeholder="min"></b-form-input>
                      <b-label> - </b-label>
                    <b-form-input type="number" min="0" max="10" style="margin:20px;" v-model="filterRateMax" placeholder="max"></b-form-input>
                  </b-form>
              </b-row>
                <p style="margin-top:20px">Filter employees by workplace:</p>
                <b-form-select v-model="selected" :options="options"></b-form-select>
                <div>
                <b-button type="reset" style="margin:40px" variant="outline-primary" size="lg">Reset</b-button>
                <b-button type="submit" style="margin:40px" variant="outline-primary" size="lg">Submit</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>
    {{this.selected}}
  </b-container>
</template>

<script>

  export default {
    data: function() {
      return {
        employees: [],
        searchText: '',
        selected: 'All_Employees',
        filterRateMin: 0,
        filterRateMax: 10,
        options: [
          { value: 'All_Employees', text: 'All Employees' },
          { value: 'Dermatologists', text: 'Dermatologists' },
          { value: 'Pharmacists', text: 'Pharmacists' }
        ]
      }
    },
    methods: {
        getEmployees : function(){
            this.$http.get('http://localhost:8081/employees', {
              params: {
                drugstoreId: "2b7933e9-6as3-463a-974b-ded43ad63843"
            }
            })
            .then(response => {
            this.employees = response.data.map(employee => 
            (
                    {
                        name: employee.name,
                        surname: employee.surname,
                        averageRate: employee.averageRate,
                        email: employee.email, 
                        phoneNumber: employee.phoneNumber,
                        address: employee.location ? employee.location.address : null,
                        city: employee.location ? employee.location.city : null,
                        country: employee.location ? employee.location.country : null,
                        type: employee.type,
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        employeesSearchResult : function() {
            this.$http.get('http://localhost:8081/employees/search', {
              params: {
                drugstoreId: "2b7933e9-6as3-463a-974b-ded43ad63843",
                searchText: this.searchText,
                minRate: this.filterRateMin,
                maxRate: this.filterRateMax,
                type: this.selected,
            }
            })
            .then(response => {
            this.employees = response.data.map(employee => 
            (
                    {
                        name: employee.name,
                        surname: employee.surname,
                        averageRate: employee.averageRate,
                        email: employee.email, 
                        phoneNumber: employee.phoneNumber,
                        address: employee.location ? employee.location.address : null,
                        city: employee.location ? employee.location.city : null,
                        country: employee.location ? employee.location.country : null,
                        type: employee.type,
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        onSubmit(event) {
            event.preventDefault()
            this.employeesSearchResult()
        },
        onReset(event) {
            event.preventDefault()
            this.searchText = ''
            this.selected = 'All_Employees'
            this.filterRateMin = 0
            this.filterRateMax = 10
        },
    },
    mounted: function(){
        this.getEmployees();
    }
  }
</script>