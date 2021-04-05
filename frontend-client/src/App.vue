<template>
  <div id="app" style="margin: 0px">
    <div id="page-container">
    <navbar></navbar>
    <router-view />
    </div>
    <footer class="bg-light text-center text-lg-start footer">

    <div class="text-center p-3">
      © 2021 Copyright:
      <a class="text-dark"><i>Usijani Momci (Nekromantija)</i></a>
    </div>
  </footer>
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
