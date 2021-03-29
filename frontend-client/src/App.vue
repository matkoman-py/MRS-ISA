<template>
  <div id="app">

    <b-navbar type="dark" variant="dark" fixed="top">
      <b-navbar-brand tag="h3">
        <router-link to="/test">Test</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/patientoverview">Patient overview</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/employeesOverview">Employees overview</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/register">Registration page</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/add-drug-form">Add drug form</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/drugs-overview">Drugs overview</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/drugSearch">Drug search</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/drugstoreSearch">Drug search</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/employeeprofiles">Employee profiles</router-link>
      </b-navbar-brand>
      <b-navbar-brand tag="h3">
        <router-link to="/drugstoreUpdate">Drugstore update</router-link>
      </b-navbar-brand>
    </b-navbar>

    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="2000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="200"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        img-src="https://picsum.photos/1024/200/?image=1">
        <h1>Najjaci lekovi bez recepta!</h1>
      </b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://picsum.photos/1024/200/?image=54">
        <h1>Hello world!</h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="https://picsum.photos/1024/200/?image=58">
      <h1>Opis</h1>
      </b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="200"
            src="https://picsum.photos/1024/200/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      
    </b-carousel>
    <router-view />
  </div>
</template>

<script>
  export default {
    name:'App',
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    methods: {
      onSlideStart() {
        this.sliding = true
      },
      onSlideEnd() {
        this.sliding = false
      }
    }
  }
</script>

<style>
  .router-link-active {
    color: #41b883;
  }
</style>