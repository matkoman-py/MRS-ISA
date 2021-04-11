<template>
  <b-card
    no-body
    img-alt="Image"
    img-top
    img-src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format"
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"

  >
  <template #header>
      <h4 class="mb-0">{{drugstore.name}}</h4>
  </template>
  
    <b-card-body>
      <b-card-sub-title class="mb-2">Description:</b-card-sub-title>
      <b-card-text>
        {{drugstore.description}}
      
      </b-card-text>
    </b-card-body>
   
   <b-list-group flush>
     <b-list-group-item>Address: {{drugstore.id}}</b-list-group-item>
      <b-list-group-item>Address: {{drugstore.adress}}</b-list-group-item>
      <b-list-group-item>City: {{drugstore.city}}</b-list-group-item>
      <b-list-group-item>Country: {{drugstore.country}}</b-list-group-item>
      <b-list-group-item>Rating: {{drugstore.rating}}</b-list-group-item>
    </b-list-group>
    <div style="text-align:center;padding:0.5rem;">
      <router-link :to="'drugstores/'+drugstore.id">
        <b-button variant="primary" style="width:50%;">Visit</b-button>
      </router-link>
    </div>
    
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