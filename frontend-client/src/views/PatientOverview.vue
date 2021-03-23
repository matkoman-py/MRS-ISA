<template>
    <b-container>
        <b-row>
            <b-col>
                <PatientTable/>
            </b-col>
        </b-row>
        <b-row align-h="center">
            <b-col sm="3" md="4" lg="4">
                <SearchPatient/>
            </b-col>
        </b-row>
        
    </b-container>
</template>

<script>
import PatientTable from "@/components/PatientTable"
import SearchPatient from "@/components/SearchPatient"
export default {
    components:{
        PatientTable,
        SearchPatient
    }
}
</script>

<style>
#nesting_div{
    text-align: center;
}
</style>