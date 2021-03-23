<template>
  <b-container>
    <h1> Employees in drugstore </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="employees"></b-table>
    </div>
    <div>
        <b-button style="margin-right:50px" variant="success">Add pharmacist</b-button>
        <b-button style="margin-left:50px; margin-right:50px" variant="success">Add dermatologist</b-button>
        <b-button style="margin-left:50px" variant="danger">Delete employee</b-button>
    </div>
    <div style="margin:25px">
        <b-form @submit="onSubmit" @reset="onReset">
            <p>Search text:</p>
            <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
            <p>Filter employees by workplace:</p>
            <b-form-select v-model="selected" :options="options"></b-form-select>
            <b-button type="reset" style="margin:40px" variant="outline-primary" size="lg">Reset</b-button>
            <b-button type="submit" style="margin:40px" variant="outline-primary" size="lg">Search</b-button>
        </b-form>
    </div>
  </b-container>
</template>

<script>
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
            fetch('http://localhost:8081/employees')
            .then(response => response.json())
            .then(response => this.employees = response);
        },
        onSubmit(event) {
            event.preventDefault()
            alert("Searched text is " + this.searchText + ", and search is filtered by " + this.selected + " workplace.")
        },
        onReset(event) {
            event.preventDefault()
            this.searchText = ''
            this.selected = 'All_Employees'
        }
    },
    mounted: function(){
        this.getEmployees();
    }
  }
</script>