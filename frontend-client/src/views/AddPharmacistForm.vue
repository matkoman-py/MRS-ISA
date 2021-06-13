<template>
  <b-container style="width: 40%" align-h="center">
      <h1> Add pharmacist form </h1>
    <b-row>
      <b-col>
        <b-form @submit="onSubmit">

          <b-form-group label="Name:" label-for="name-input">
            <b-form-input
              id="name-input"
              v-model="form.name"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Surname:" label-for="surname-input">
            <b-form-input
              id="surname-input"
              v-model="form.surname"
              placeholder="Enter surname"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Email:" label-for="email-input">
            <b-form-input
              id="email-input"
              v-model="form.email"
              placeholder="Enter email address"
              type="email"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Phone Number:" label-for="phoneNumber-input">
            <b-form-input
              id="phoneNumber-input"
              v-model="form.phoneNumber"
              placeholder="Enter phone number"
              required
            ></b-form-input>
          </b-form-group>

            <div style="margin-top:50px; margin-bottom:50px">
          <b-form-group label="Address:" label-for="address-input">
            <b-form-input
              id="address-input"
              v-model="form.location.address"
              placeholder="Enter address"
            ></b-form-input>
          </b-form-group>

          <b-form-group label="City:" label-for="city-input">
            <b-form-input
              id="city-input"
              v-model="form.location.city"
              placeholder="Enter city"
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Country:" label-for="country-input">
            <b-form-input
              id="country-input"
              v-model="form.location.country"
              placeholder="Enter country"
            ></b-form-input>
          </b-form-group>
            </div>
          <b-form-group style="display:inline-block;" label="Working hours:" label-for="workingHours-input">
            <div style="float:left;margin:20px">
              <b style="clear: right;">From:</b>
            <b-form-timepicker v-model="form.workingHoursFrom" locale="en"></b-form-timepicker>
            </div>
            <div style="float:left;margin:20px">
              <b>To:</b>
            <b-form-timepicker v-model="form.workingHoursTo" locale="en"></b-form-timepicker>
            </div>
          </b-form-group>
          <div style="margin:25px">
          <b-button type="submit" variant="outline-hub">Submit</b-button>
          <b-button type="reset" variant="outline-hub">Reset</b-button>
          </div>  
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
  name: 'AddPharmacistForm',
  components: {
  },
  computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
  },
  data: function(){
    return {
      form : {
        name: "",
        surname: "",
        email: "",
        phoneNumber: "",
        location: {
          address: "",
          city: "",
          country: ""
        },
        workingHoursFrom: "",
        workingHoursTo: "",
        type: "Pharmacist",
        drugstore: {}
      }
    }
  },
  methods : {
    onSubmit(event){
          event.preventDefault();
          console.log(this.form);
          this.$http.post("https://mrs-isa-usijani.herokuapp.com/employees/pharmacist", JSON.parse(JSON.stringify(this.form)))
              .then(response => {
              console.log(response);
              alert("New pharmacist successfully added.");
              this.$router.push('employeesOverview');
              })
              .catch(error => console.log(error));
      },
    getDrugstore() {
       this.$http.get("https://mrs-isa-usijani.herokuapp.com/employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.form.drugstore = response.data;
              })
              .catch(error => console.log(error));
    }
  }, mounted() {
        this.getDrugstore();
    }
}
</script>