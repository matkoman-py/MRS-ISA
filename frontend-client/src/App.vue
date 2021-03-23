<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <div class="nav">
      <router-link to="/">Hello World</router-link>
      <br>
      <router-link to="/test">Test</router-link>
      <br>
      <router-link to="/register">Registration page</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
