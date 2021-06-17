<template>
  <b-container>
    <h1> Create appointment for deramotologist </h1>
    <!--div align="center">
    <b-form-group style="width:400px;"
        label="Choose dermatologist"
        label-for="dermatologist-picker"
        invalid-feedback="Dermatologist is required">
            <b-form-select
                v-model="inputValues.dermatologist"
                :options="availableDermatologists">
            </b-form-select>
    </b-form-group>
    </div>
    <div style="margin:40px; height:500px; border-style:solid;">
        <FullCalendar class='demo-app-calendar'> </FullCalendar>
    </div-->
    <div>
        <b-button style="margin-bottom:40px;" variant="outline-hub" @click="showForm">Create new appointment</b-button>
    </div>

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

  </b-container>
  
</template>

<script>
  import FullCalendar from '@fullcalendar/vue'
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    components: {
      FullCalendar // make the <FullCalendar> tag available
    },
    },
    data: function() {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return {
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
        getAllApointments : function(){
        },
        initialize() {
            this.$http.get("http://localhost:8081/employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.inputValues.drugstoreId = response.data.id;
              this.getAllApointments();
              this.getAllDermatologistForThisDrugstore();
              })
              .catch(error => console.log(error));
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
                text: dermatologistDto.dermatologist.name + " (" + dermatologistDto.workingHoursFrom + " - " + dermatologistDto.workingHoursTo + ")"
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
              this.$toastr.s("New appointment is successfully created.")
              })
              .catch(error => console.log(error));
          this.$root.$emit('bv::hide::modal', 'my-modal');
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'my-modal');
        }
        },
    mounted: function(){
        this.initialize();
    }
  }
</script>