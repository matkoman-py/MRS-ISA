<template>
          <b-navbar toggleable="sm" type="light" variant="light">
            <b-navbar-nav>
                <b-navbar-brand>
                    <router-link to="/">
                        <img src="static/full_logo.png" height="80%" class="d-inline-block align-top">
                    </router-link>
                </b-navbar-brand>

                <b-navbar-brand v-for="route in commonRoutes" :key="route.name" tag="h3" class="nav-link">
                        <router-link :to="route.path" class="link-font">{{route.name}}</router-link>
                </b-navbar-brand>

                <template v-if="!$helpers.isObjectEmpty(user) && role == 'Dermatologist'">
                  <b-navbar-brand v-for="route in userSpecificRoutes['Dermatologist']" :key="route.name" tag="h3" class="nav-link">
                        <router-link :to="route.path" class="link-font">{{route.name}}</router-link>
                  </b-navbar-brand>
                </template>

                <template v-if="!$helpers.isObjectEmpty(user) && role == 'Pharmacist'">
                  <b-navbar-brand v-for="route in userSpecificRoutes['Pharmacist']" :key="route.name" tag="h3" class="nav-link">
                        <router-link :to="route.path" class="link-font">{{route.name}}</router-link>
                  </b-navbar-brand>
                </template>
                
                 <b-nav-item-dropdown text="CRUD" right v-if="!$helpers.isObjectEmpty(user) && role == 'SystemAdmin'" class="nav-dropdown link-font">
                  <b-dropdown-item  v-for="route in userSpecificRoutes['SystemAdmin']" :key="route.name">
                    <router-link :to="route.path" class="link-font">{{route.name}}</router-link>
                  </b-dropdown-item>
                </b-nav-item-dropdown>

            </b-navbar-nav>
            <b-navbar-nav class="ml-auto" >
              <template v-if="$helpers.isObjectEmpty(user)">
                <b-navbar-brand tag="h3" class="nav-link">
                        <router-link to="/login" class="link-font">Login</router-link>
                </b-navbar-brand> 
                <b-navbar-brand tag="h3" class="nav-link">
                        <router-link to="/register" class="link-font">Register</router-link>
                </b-navbar-brand> 
              </template>
              <template v-else>
                <b-navbar-brand tag="h3" class="nav-link">
                  <router-link to="/">{{email}}</router-link>
                </b-navbar-brand> 
                <b-navbar-brand tag="h3" class="nav-link" v-on:click="logout">
                        <router-link to="/" class="link-font">Logout</router-link>
                </b-navbar-brand> 
              </template>
                
            </b-navbar-nav>
        </b-navbar>
</template>
<script>
import { mapState } from 'vuex'
export default {
  
    name:'Navbar',
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
        email: state => state.userModule.loggedInUser.email,
        role: state => state.userModule.loggedInUser.type
      }),
      // routes: function(){
        // return (this.$helpers.isObjectEmpty(this.user)) ? this.commonRoutes : [...this.commonRoutes, ...this.userSpecificRoutes[this.role]];
      // }
    },
    data() {
      return {
        userSpecificRoutes: {
          "SystemAdmin" : [
            {name: "Drugstore CRUD", path: "/admin-drugstore-table"},
            {name: "Drug CRUD", path: "/admin-drug-table"},
            {name: "User CRUD", path: "/admin-user-table"},
          ],
          "DrugstoreAdmin":[],
          "Pharmacist":[
            {name: "Schedule", path: "/schedule-pharm"},
            {name: "Patients", path: "/patientoverview"},
            {name: "Treated patients", path: "/treated"},
          ],
          "Dermatologist":[
            {name: "Schedule", path: "/schedule"},
            {name: "Patients", path: "/patientoverview"},
            {name: "Treated patients", path: "/treated"},
          ],
          "Patient":[],
        },
        commonRoutes: [
            {name: "Drugstores", path: "/drugstoreSearch"},
            {name: "Drugs", path: "/drugSearch"}
        ]
      }
    },
    methods:{
      logout: function(event){
        event.preventDefault();
        this.$store.dispatch('userModule/logout');
      }
    }
  }
</script>

