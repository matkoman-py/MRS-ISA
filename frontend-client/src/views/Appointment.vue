<template>
    <div>
        <h1>Drugstore: {{}}</h1>
    </div>
</template>

<script>
export default {
    name: "Appointment",
    
    data: function () {
            return {
                appointment: {},
                drugstore: {},
                patient: {},
                dermatologist: {},
            }
    },
    methods: {
        beginAppointment: function(){
            this.drugstore.id = "";
            this.patient.id = "";
            this.dermatologist.id = "";
        }
    },
    mounted: function() {
        //ovo se treba prepraviti da preko id nabavi appointment i onda popuni sve
        this.beginAppointment();
    },
    
}
</script>

<style>

</style>