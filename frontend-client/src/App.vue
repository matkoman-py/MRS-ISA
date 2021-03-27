<template>
  <div id="app">
      <b-navbar type="dark" variant="dark" fixed="top">
      <b-navbar-brand tag="h3"><router-link to="/test">Test</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/patientoverview">Patient overview</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/employeesOverview">Employees overview</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/register">Registration page</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/add-drug-form">Add drug form</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/drugs-overview">Drugs overview</router-link></b-navbar-brand>
      <b-navbar-brand tag="h3"><router-link to="/drugSearch">Drug search</router-link></b-navbar-brand>
      </b-navbar>
      <img alt="Vue logo" src="./assets/logo.png">
      <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
.router-link-active{
  color: #41b883;
}


</style>


