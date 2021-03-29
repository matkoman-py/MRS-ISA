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
        <b-button style="margin-left:50px; margin-right:50px" variant="success">Add dermatologist</b-button>
        <b-button style="margin-left:50px" variant="danger">Delete employee</b-button>
    </div>
    <b-container style="margin:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="onSubmit" @reset="onReset">
                <p style="margin-top:20px">Search text:</p>
                <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
                <p style="margin-top:20px">Filter employees by workplace:</p>
                <b-form-select v-model="selected" :options="options"></b-form-select>
                <div>
                <b-button type="reset" style="margin:40px" variant="outline-primary" size="lg">Reset</b-button>
                <b-button type="submit" style="margin:40px" variant="outline-primary" size="lg">Search</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>
  </b-container>
</template>

<script>
  import axios from "axios";

  export default {
    data: function() {
      return {
        employees: [],
        searchText: '',
        selected: 'All_Employees',
        options: [
          { value: 'All_Employees', text: 'All Employees' },
          { value: 'Dermatologists', text: 'Dermatologists' },
          { value: 'Pharmacists', text: 'Pharmacists' }
        ]
      }
    },
    methods: {
        getEmployees : function(){
            axios.get('http://localhost:8081/employees')
            .then(response => {
            this.employees = response.data.map(employee => 
            (
                    {
                        name: employee.name,
                        surname: employee.surname,
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
            alert("Searched text is " + this.searchText + ", and search is filtered by " + this.selected + " workplace.")
        },
        onReset(event) {
            event.preventDefault()
            this.searchText = ''
            this.selected = 'All_Employees'
        },
    },
    mounted: function(){
        this.getEmployees();
    }
  }
</script>