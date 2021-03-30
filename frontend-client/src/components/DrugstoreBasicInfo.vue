<template>
  <b-container>

    <b-form @submit="submitHandler">
    <b-row align-h="center">
    <div style="margin-top:40px; margin-bottom:40px; width:75%">
    <b-input-group prepend="Name" class="mt-3">
    <b-form-input v-model="form.name"></b-form-input>
    </b-input-group>

    <b-input-group prepend="Description" class="mt-3">
    <b-form-input v-model="form.description"></b-form-input>
    </b-input-group>
    </div>
    </b-row>

    <div style="float:left; width:48%; height:300px; padding-top:30px; padding-bottom:30px">
    <b-input-group style="margin-bottom:45px;" prepend="Address" class="mt-3">
    <b-form-input v-model="form.location.address"></b-form-input>
    </b-input-group>

    <b-input-group style="margin-bottom:45px; margin-top:45px;" prepend="City" class="mt-3">
    <b-form-input v-model="form.location.city"></b-form-input>
    </b-input-group>

    <b-input-group style="margin-top:45px;" prepend="Country" class="mt-3">
    <b-form-input v-model="form.location.country"></b-form-input>
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
    props: ["form", "submitHandler"],
    name: "DrugstoreBasicInfo",
    data: function() {
      return {};
    }
}
</script>