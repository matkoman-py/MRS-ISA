<template>
    <b-container>
        <b-row>
            <b-col>
                <h1>Drugstore Table</h1>
                <DrugstoreTable/>
            </b-col>
        </b-row>    
    </b-container>    
</template>

<script>

import DrugstoreTable from "@/components/DrugstoreTable"

export default {
    components:{
        DrugstoreTable,
    }
}

</script>