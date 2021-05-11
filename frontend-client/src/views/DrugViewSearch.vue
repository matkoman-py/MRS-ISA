<template>
    <b-container>
        <b-row>
            <b-col>
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