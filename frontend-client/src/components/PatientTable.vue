<template>
  <!-- <b-list-group>
    <b-list-group-item v-for="patient in patients" :key="patient.name">Name: {{patient.name}} Surname: {{patient.surname}}</b-list-group-item>
  </b-list-group> -->
  <b-table id="tabela_pacijenata" striped hover :items="patients"></b-table>
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