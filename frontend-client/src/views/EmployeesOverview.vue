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
        <b-button style="margin-left:50px; margin-right:50px" variant="outline-hub" @click="showHireForm">Hire dermatologist</b-button>
        <b-button style="margin-left:50px; margin-right:50px" variant="outline-hub" @click="showModal">Delete employee</b-button>
        <b-button style="margin-left:50px" variant="outline-hub" @click="showForm">Create appointment for deramtologist</b-button>
    </div>
    <b-container style="margin:15px">
        <b-row align-h="center">
            <b-form style="width:40%" @submit="onSubmit" @reset="onReset">
                <p style="margin-top:20px">Search text:</p>
                <b-form-input v-model="searchText" placeholder="Enter text for search"></b-form-input>
                <p style="margin-top:20px">Average rate:</p>
                <b-row align-h="center">   
                  <b-form align="center" inline>
                    <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMin" placeholder="min"></b-form-input>
                      <b-label> - </b-label>
                    <b-form-input type="number" min="0" max="5" style="margin:20px;" v-model="filterRateMax" placeholder="max"></b-form-input>
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



    <b-modal id="deleteConfirmation" title="Warning! This employee is going to be removed from this drugstore!" align="center" hide-footer>
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

    <b-modal id="hireDermatologist" title="Hire dermatologist" hide-footer>
      <b-form>
        <b-form-group
        label="Dermatologists that are not employed in your drugstore"
        label-for="dermatologist-picker"
        invalid-feedback="Dermatologist is required">
            <b-form-select
                v-model="hireDermatologistValues.dermatologistId"
                :options="notEmployedDermatologists">
            </b-form-select>
        </b-form-group>

        <b-form-group
        label="Shift start time"
        label-for="time-picker"
        invalid-feedback="Shift start time is required">
            <b-form-timepicker
            v-model="hireDermatologistValues.workingHoursFrom"
            locale="en">
            </b-form-timepicker>
        </b-form-group>

        <b-form-group
        label="Shift end time"
        label-for="time-picker"
        invalid-feedback="Shift start time is required">
            <b-form-timepicker
            v-model="hireDermatologistValues.workingHoursTo"
            locale="en">
            </b-form-timepicker>
        </b-form-group>

        <b-button type="button" variant="outline-hub" @click="hireDermatologistFunction">Hire dermatologist</b-button>
        <b-button type="button" variant="outline-hub" @click="closeHireModal" >Cancel</b-button>

      </b-form>
    </b-modal>

    <b-modal id="my-modal" title="Create new appointment" hide-footer>
      <b-form>
        <b-form-group
        label="Choose dermatologist"
        label-for="dermatologist-picker"
        invalid-feedback="Dermatologist is required">
            <b-form-select
                v-model="inputValues.dermatologist"
                :options="availableDermatologists">
            </b-form-select>
        </b-form-group>

        <b-form-group
        label="Appointment date"
        label-for="date-picker"
        invalid-feedback="Appointment date is required">
            <b-form-datepicker
            id="date-input"
            v-model="inputValues.date"
            :min="minDate"
            required >
            </b-form-datepicker>
        </b-form-group>

        <b-form-group
        label="Appointment time"
        label-for="time-picker"
        invalid-feedback="Appointment time is required">
            <b-form-timepicker
            v-model="inputValues.time"
            locale="en">
            </b-form-timepicker>
        </b-form-group>

        <b-form-group
        label="Appointment duration(in minutes)"
        label-for="duration-picker"
        invalid-feedback="Appointment duration is required">
            <b-form-input
            id="duration-input"
            v-model="inputValues.duration"
            type="number"
            min="0"
            required >
            </b-form-input>
        </b-form-group>

        <b-form-group
        label="Appointment price(in RSD)"
        label-for="price-picker"
        invalid-feedback="Appointment price is required">
            <b-form-input
            id="price-input"
            v-model="inputValues.price"
            type="number"
            min="0"
            required >
            </b-form-input>
        </b-form-group>

        <b-button type="button" variant="outline-hub" @click="addNewApointment">Save</b-button>
        <b-button type="button" variant="outline-hub" @click="handleClose" >Cancel</b-button>

      </b-form>
    </b-modal>
    {{hireDermatologistValues.dermatologist}}
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
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return {
        drugstoreId: '',
        selectedEmployee:{},
        employees: [],
        searchText: '',
        selected: 'All_Employees',
        filterRateMin: 0,
        filterRateMax: 5,
        options: [
          { value: 'All_Employees', text: 'All Employees' },
          { value: 'Dermatologists', text: 'Dermatologists' },
          { value: 'Pharmacists', text: 'Pharmacists' }
        ],

        notEmployedDermatologists: [],
        hireDermatologistValues: {
          drugstoreId: '',
          dermatologistId: '',
          workingHoursFrom: '',
          workingHoursTo: ''
        },

        appointments: [],
        minDate: minDate,
        availableDermatologists: [],
        inputValues: {
          dermatologist: '',
          drugstoreId: '',
          date: '',
          time: '',
          duration: '',
          price: ''
        },
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
              this.inputValues.drugstoreId = response.data.id;
              this.hireDermatologistValues.drugstoreId = response.data.id;
              this.getAllDermatologistForThisDrugstore();
              this.getAllNotEmployedDermatologists();
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
                        address: employee.location ? employee.location.address : null,//+ ", " + employee.location.city + ", " + employee.location.country: null,
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
            this.filterRateMax = 5
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
              .then(response => {
                if (response.data == "Denied") {
                  alert("Pharmacist have scheduled appointments in future so you can't delete him!");
                } else {
                  alert("Pharmacist is succesfully removed from drugstore!");
                }
                this.getEmployees();
              })
              .catch(error => console.log(error));
            } else {
              this.$http.delete("http://localhost:8081/employment/delete", {data : { dermatologistEmail: this.selectedEmployee[0].email, drugstoreId: this.drugstoreId }})
              .then(response => {
                if (response.data == "Denied") {
                  alert("Dermatologist have scheduled appointments in future so you can't delete him!");
                } else {
                  alert("Dermatologist is succesfully removed from drugstore!");
                  this.getAllNotEmployedDermatologists();
                  this.getAllDermatologistForThisDrugstore();
                }
                this.getEmployees();
              })
              .catch(error => console.log(error));
            }
            this.$root.$emit('bv::hide::modal', 'deleteConfirmation');
        },
        getAllNotEmployedDermatologists : function() {
            this.$http.get("http://localhost:8081/employment/dermatologists/notEmployed", {
                params: {
                            drugstoreId: this.inputValues.drugstoreId
                        }})
            .then(response => {
            this.notEmployedDermatologists = response.data.map((dermatologist) =>
                ({
                value: dermatologist.id,
                text: dermatologist.name + " " + dermatologist.surname
                })
            );
            })
            .catch(error => console.log(error));
        },
        showHireForm(event) {
          event.preventDefault()
          this.$root.$emit('bv::show::modal', 'hireDermatologist');
        },
        hireDermatologistFunction(event) {
          event.preventDefault();
          this.$http.post("http://localhost:8081/employment/dermatologist/hire", JSON.parse(JSON.stringify(this.hireDermatologistValues)))
              .then(response => {
              console.log(response);
              alert("Dermatologist is successfully hired!");
              this.getEmployees();
              this.getAllNotEmployedDermatologists();
              this.getAllDermatologistForThisDrugstore();
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'hireDermatologist');
        },
        closeHireModal(){
            this.$root.$emit('bv::hide::modal', 'hireDermatologist');
        },
        getAllDermatologistForThisDrugstore : function() {
            this.$http.get("http://localhost:8081/employment/dermatologists", {
                params: {
                            drugstoreId: this.inputValues.drugstoreId
                        }})
            .then(response => {
            this.availableDermatologists = response.data.map((dermatologistDto) =>
                ({
                value: dermatologistDto.dermatologist,
                text: dermatologistDto.dermatologist.name + " (" + dermatologistDto.workingHoursFrom.slice(0,5) + " - " + dermatologistDto.workingHoursTo.slice(0,5) + ")"
                })
            );
            })
            .catch(error => console.log(error));
        },
        showForm(event) {
          event.preventDefault()
          this.$root.$emit('bv::show::modal', 'my-modal');
        },
        addNewApointment(event) {
          event.preventDefault();
          this.$http.post("http://localhost:8081/dermatologist-appointment/", JSON.parse(JSON.stringify(this.inputValues)))
              .then(response => {
              console.log(response);
              alert("New appointment is successfully created.");
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'my-modal');
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'my-modal');
        }
    },
    mounted: function(){
        this.getDrugstore();
        this.getEmployees();
    }
  }
</script>