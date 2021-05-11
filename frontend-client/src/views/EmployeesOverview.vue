<template>
  <b-container>
    <h1> Employees in drugstore </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="employees" selectable select-mode='single' @row-selected="onRowSelected"></b-table>
        <p v-if="employees.length == 0"> There are no employees for this criteria.</p>
    </div>
    <div>
        <router-link to="/addPharmacistForm">
           <b-button style="margin-right:50px" variant="outline-hub">Add pharmacist</b-button>
        </router-link>
        <router-link to="/addDermatologistForm">
                <b-button style="margin-left:50px; margin-right:50px" variant="outline-hub">Add dermatologist</b-button>
        </router-link>
        <b-button style="margin-left:50px" variant="outline-hub" @click="showModal">Delete employee</b-button>
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
                <b-button type="reset" style="margin:40px" variant="outline-hub" size="lg">Reset</b-button>
                <b-button type="submit" style="margin:40px" variant="outline-hub" size="lg">Submit</b-button>
                </div>
            </b-form>
        </b-row>
    </b-container>



    <b-modal id="deleteConfirmation" title="Warning! This emoloyee is going to be removed from this drugstore!" align="center" hide-footer>
      <b-form>
        <b-row align-h="center">
        <b-label> Are you sure that you want to delete this employee?</b-label>
        </b-row>
        <b-row align-h="center">
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="deleteEmployee">Continue</b-button>
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="cancel" >Cancel</b-button>
        </b-row>
      </b-form>
    </b-modal>
  </b-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    },
    data: function() {
      return {
        drugstoreId: '',
        selectedEmployee:{},
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
        getDrugstore() {
            this.$http.get("http://localhost:8081/employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.drugstoreId = response.data.id;
              })
              .catch(error => console.log(error));
        },
        getEmployees : function(){
            this.$http.get('http://localhost:8081/employees', {
              params: {
                drugstoreAdminId: this.user.id
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
                drugstoreAdminId: this.user.id,
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
        showModal(event) {
          event.preventDefault()
          if (this.selectedEmployee.length == 0) {
            alert("You need to select employee that you want to delete.")
          } else {
            this.$root.$emit('bv::show::modal', 'deleteConfirmation');
          }
        },
        onRowSelected(item) {
            this.selectedEmployee = item
        }, cancel() {
            this.$root.$emit('bv::hide::modal', 'deleteConfirmation');
        }, deleteEmployee(event) {
            event.preventDefault();
            if (this.selectedEmployee[0].type == 'Pharmacist') {
              this.$http.delete("http://localhost:8081/employees/delete/", {params :{ pharmacistEmail: this.selectedEmployee[0].email}})
              .then(() => {
                alert("Pharmacist is succesfully removed from drugstore!");
                this.getEmployees();
              })
              .catch(error => console.log(error));
            } else {
              this.$http.delete("http://localhost:8081/employment/delete", {data : { dermatologistEmail: this.selectedEmployee[0].email, drugstoreId: this.drugstoreId }})
              .then(() => {
                alert("Dermatologist is succesfully removed from drugstore!");
                this.getEmployees();
              })
              .catch(error => console.log(error));
            }
            this.$root.$emit('bv::hide::modal', 'deleteConfirmation');
        }
    },
    mounted: function(){
        this.getDrugstore();
        this.getEmployees();
    }
  }
</script>