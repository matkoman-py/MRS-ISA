<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
      <br>
      <router-link to="/">Hello World</router-link>
      <br>
      <router-link to="/test">Test</router-link>
      <br>
      <router-link to="/patientoverview">Patient overview</router-link>
      <br>
      <router-link to="/employeesOverview">Employees overview</router-link>
      <br>
      <router-link to="/register">Registration page</router-link>
      <br>
      <router-link to="/add-drug-form">Add drug form</router-link>
      <br>
      <router-link to="/drugs-overview">Drugs overview</router-link>
      <br>
      <router-link to="/employeeprofiles">Employee profiles</router-link>
      <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>



