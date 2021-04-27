 <template>
      <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="2000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="150"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        img-src="static/nekaSlika2.jpg" img-height="150">
        <h1>Pharmacy hub - 25 godina sa vama!</h1>
      </b-carousel-slide>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="static/slider2.jpg">
        <h1></h1>
      </b-carousel-slide>

      <!-- Slides with image only -->
      <b-carousel-slide img-src="static/nekaSlika.jpg">
      <h1></h1>
      </b-carousel-slide>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="100"
            src="static/slider3.jpg"
            alt="image slot"
          >
        </template>
      </b-carousel-slide>

      <!-- Slide with blank fluid image to maintain slide aspect ratio -->
      
    </b-carousel>
 </template>

 <script>
  export default {
    name:'Slider',
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
