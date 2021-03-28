<template>
  <b-container style="width: 40%" align-h="center">
    <b-row>
      <b-col>
        <b-form @submit="addPharmacist">

          <b-form-group label="Name:" label-for="name-input">
            <b-form-input
              id="name-input"
              v-model="name"
              placeholder="Enter name"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Surname:" label-for="surname-input">
            <b-form-input
              id="surname-input"
              v-model="surname"
              placeholder="Enter surname"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Email:" label-for="email-input">
            <b-form-input
              id="email-input"
              v-model="email"
              placeholder="Enter email address"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Password:" label-for="password-input">
            <b-form-input
              id="password-input"
              v-model="password"
              placeholder="Enter password"
              type="password"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Phone Number:" label-for="phoneNumber-input">
            <b-form-input
              id="phoneNumber-input"
              v-model="phoneNumber"
              placeholder="Enter phone number"
              required
            ></b-form-input>
          </b-form-group>

            <div style="margin-top:50px; margin-bottom:50px">
          <b-form-group label="Address:" label-for="address-input">
            <b-form-input
              id="address-input"
              v-model="address"
              placeholder="Enter address"
            ></b-form-input>
          </b-form-group>

          <b-form-group label="City:" label-for="city-input">
            <b-form-input
              id="city-input"
              v-model="city"
              placeholder="Enter city"
            ></b-form-input>
          </b-form-group>

          <b-form-group label="Country:" label-for="country-input">
            <b-form-input
              id="country-input"
              v-model="country"
              placeholder="Enter country"
            ></b-form-input>
          </b-form-group>
            </div>

          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
        <b-card class="mt-3" header="Form Data Result">
          <pre class="m-0">{{ form }}</pre>
        </b-card>
      </b-col>
    </b-row>
    
  </b-container>
</template>

<script>

export default {
  name: 'AddPharmacistForm',
  components: {
  },
  data: function(){
    return {
        name: "",
        surname: "",
        email: "",
        password: "",
        phoneNumber: "",
        address: "",
        city: "",
        country: ""
    }
  }
}
</script>