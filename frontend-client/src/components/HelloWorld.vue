<template>
  <div class="hello">
    <h1>{{ testData }}</h1>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
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
  mounted: function(){
    this.testController();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
