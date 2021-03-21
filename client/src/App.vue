<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld :msg="testData"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data: function(){
    return {
      testData: {},
    }
  },
  methods: {
    testController : function(){
      fetch('http://localhost:8081/test')
      .then(response => response.json())
      .then(response => this.testData = response);
    }
  },
  created: function(){
    this.testController();
  }
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
