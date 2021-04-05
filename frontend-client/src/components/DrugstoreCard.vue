<template>
  <b-card
    
    
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  >
  <template #header>
      <h4 class="mb-0">{{drugstore.name}}</h4>
  </template>
  <b-card-sub-title class="mb-2">Description:</b-card-sub-title>
    <b-card-text>
      {{drugstore.description}}
      
    </b-card-text>
   <b-list-group flush>
      <b-list-group-item>Address: {{drugstore.adress}}</b-list-group-item>
      <b-list-group-item>City: {{drugstore.city}}</b-list-group-item>
      <b-list-group-item>Country: {{drugstore.country}}</b-list-group-item>
      <b-list-group-item>Rating: {{drugstore.rating}}</b-list-group-item>
    </b-list-group>
    <b-button href="#" variant="primary">Go somewhere</b-button>
  </b-card>
</template>

<script>
export default {
    props:{
        drugstore: {}
    }
}
</script>

<style>

</style>