<template>
    <b-container>
        <b-form @submit="onSubmit">
            <b-button type="submit" variant="primary">Submit</b-button>
            <br>
            <br>
        </b-form>
    </b-container>
</template>

<script>
import {eventBus} from "../main";

export default {
    name: "DrugSearch",
    data: function(){
        return{
            message: 'WTF',
        }
    },
    methods:{
        onSubmit(event) {
            event.preventDefault()
            eventBus.$emit('poruka', 'EUREKA ???')
        },
    }
}
</script>