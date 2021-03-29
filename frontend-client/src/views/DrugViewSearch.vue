<template>
    <b-container>
        <b-row>
            <b-col>
                <h1>Drug Table</h1>
                <DrugTable/>
            </b-col>
        </b-row>    
    </b-container>    
</template>

<script>

import DrugTable from "@/components/DrugTable"

export default {
    components:{
        DrugTable,
    }
}

</script>