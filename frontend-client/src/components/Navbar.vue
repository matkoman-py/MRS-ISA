<template>
          <b-navbar toggleable="sm" type="light" variant="light">
            <b-navbar-nav>
                <b-navbar-brand>
                    <router-link to="/">
                        <img src="static/full_logo.png" height="80%" class="d-inline-block align-top">
                    </router-link>
                </b-navbar-brand>

                <b-navbar-brand v-for="route in routes" :key="route.name" tag="h3" class="nav-link">
                        <router-link :to="route.path" class="link-font">{{route.name}}</router-link>
                </b-navbar-brand>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <b-navbar-brand tag="h3" class="nav-link">
                        <router-link to="/login" class="link-font">Login</router-link>
                </b-navbar-brand> 
                <b-navbar-brand tag="h3" class="nav-link">
                        <router-link to="/register" class="link-font">Register</router-link>
                </b-navbar-brand> 
            </b-navbar-nav>
        </b-navbar>
</template>
<script>

export default {
    name:'Navbar',
    data() {
      return {
        routes: [
            {name: "Drugstores", path: "/drugstoreSearch"},
            {name: "Drugs", path: "/drugSearch"}
        ]
      }
    },
  }
</script>

