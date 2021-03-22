<template>
  <ul>
      <li v-for="patient in patients" :key="patient.name">
          <h1>Name: {{patient.name}} Surname: {{patient.surname}}</h1>
      </li>
  </ul>
</template>

<script>
export default {
    data: function(){
        return{
            patients:[]
        }
    },
    methods: {
        getPatients : function(){
            fetch('http://localhost:8081/get_patients')
            .then(response => response.json())
            .then(response => this.patients = response);
        }
    },
    mounted: function(){
        this.getPatients();
    }
}
</script>

<style>

</style>