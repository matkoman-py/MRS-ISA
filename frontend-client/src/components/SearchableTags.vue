<template>
  <div>
    <b-form-group :label="labelName + ':'" label-for="tags-with-dropdown">
      <multiselect
       id="tags-with-dropdown"
       v-model="form[type]"
       tag-placeholder=""
       placeholder="Search for ingrediants"
       label="name"
       select-label=""
       track-by="id" 
       :options="options" 
       :multiple="true" 
       :taggable="true" 
       ></multiselect>
    </b-form-group>
  </div>
</template>

<script>
 import Multiselect from 'vue-multiselect'

export default {
  components: {
    Multiselect
  },
  props: ["form", "options", "labelName", "type"],
  data () {
    return {

    }
  },
}

</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>