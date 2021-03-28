<template>
  <b-container>

    <h1> Drugstore basic informations update </h1>

    <b-form @submit="onSubmit">
    <b-row align-h="center">
    <div style="margin-top:40px; margin-bottom:40px; width:75%">
    <b-input-group prepend="Name" class="mt-3">
    <b-form-input v-model="name"></b-form-input>
    </b-input-group>

    <b-input-group prepend="Description" class="mt-3">
    <b-form-input v-model="description"></b-form-input>
    </b-input-group>
    </div>
    </b-row>

    <div style="float:left; width:48%; height:300px; padding-top:30px; padding-bottom:30px">
    <b-input-group style="margin-bottom:45px;" prepend="Address" class="mt-3">
    <b-form-input v-model="address"></b-form-input>
    </b-input-group>

    <b-input-group style="margin-bottom:45px; margin-top:45px;" prepend="City" class="mt-3">
    <b-form-input v-model="city"></b-form-input>
    </b-input-group>

    <b-input-group style="margin-top:45px;" prepend="Country" class="mt-3">
    <b-form-input v-model="country"></b-form-input>
    </b-input-group>
    </div>

    <div style="float:right; width:48%; height:300px; border-style:solid">
    </div>

    <b-container>
    <b-button type="submit" style="margin:30px; width:25%" variant="outline-primary" size="lg">Save</b-button>
    </b-container>

    </b-form>

  </b-container>
</template>

<script>
  export default {
    data: function() {
      return {
        form: {
          name: "",
          description: "",
          address: "",
          city: "",
          country: ""
      }
      }
    },
    methods: {
        getSelectedDrugstoreInfo : function() {

        },
         onSubmit(event) {
            event.preventDefault()
            alert("Searched text is " + this.description)
        }
    },
    mounted: function(){
        this.getSelectedDrugstoreInfo();
    }
  }
</script>