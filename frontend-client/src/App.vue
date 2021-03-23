<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
      <br>
      <router-link to="/">Hello World</router-link>
      <br>
      <router-link to="/test">Test</router-link>
      <br>
      <router-link to="/patientoverview">Patient overview</router-link>
    
      <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>



