<template>
  <div id="app" style="margin-top: 0px">
    
    <navbar></navbar>
    <router-view />

  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
  export default {
  components: { Navbar },
    name:'App',
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
  }
</script>
