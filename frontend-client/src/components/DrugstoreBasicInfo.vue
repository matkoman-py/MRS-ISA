<template>
    <b-container>
        <b-form>
            <b-row align-h="center">
                <div style="margin-top:40px; margin-bottom:40px; width:75%">
                    <b-input-group prepend="Name" class="mt-3">
                        <b-form-input
                            v-model="form.name"
                            :required="mode == 'add'"
                        ></b-form-input>
                    </b-input-group>

                    <b-input-group prepend="Description" class="mt-3">
                        <b-form-input
                            v-model="form.description"
                            :required="mode == 'add'"
                        ></b-form-input>
                    </b-input-group>

                    <b-input-group
                        style="margin-top:45px;"
                        prepend="Working from"
                        class="mt-3"
                    >
                        <b-form-timepicker
                            v-model="form.workingHoursFrom"
                            :required="mode == 'add'"
                        ></b-form-timepicker>
                    </b-input-group>

                    <b-input-group
                        style="margin-top:45px;"
                        prepend="Working to"
                        class="mt-3"
                    >
                        <b-form-timepicker
                            v-model="form.workingHoursTo"
                            :required="mode == 'add'"
                        ></b-form-timepicker>
                    </b-input-group>

                    <b-input-group
                        style="margin-top:45px;"
                        prepend="Price for pharmacist appointment"
                        class="mt-3"
                    >
                        <b-form-input
                            type="number"
                            v-model="form.pharmacistAppointmentPrice"
                            :required="mode == 'add'"
                        ></b-form-input>
                    </b-input-group>
                </div>
            </b-row>

            <div
                style="float:left; width:48%; height:300px; padding-top:30px; padding-bottom:30px"
            >
                <b-input-group
                    style="margin-bottom:45px;"
                    prepend="Address"
                    class="mt-3"
                >
                    <b-form-input
                        v-model="form.location.address"
                        :required="mode == 'add'"
                    ></b-form-input>
                </b-input-group>

                <b-input-group
                    style="margin-bottom:45px; margin-top:45px;"
                    prepend="City"
                    class="mt-3"
                >
                    <b-form-input
                        v-model="form.location.city"
                        :required="mode == 'add'"
                    ></b-form-input>
                </b-input-group>

                <b-input-group
                    style="margin-top:45px;"
                    prepend="Country"
                    class="mt-3"
                >
                    <b-form-input
                        v-model="form.location.country"
                        :required="mode == 'add'"
                    ></b-form-input>
                </b-input-group>
            </div>

            <div style="float:right; width:48%; height:300px; border-style:solid">
                <map-container ref="map-container" :coordinates="[form.point.longitude, form.point.latitude]"></map-container>
            </div>
        </b-form>
    </b-container>
</template>

<script>
import MapContainer from './MapContainer.vue';

export default {
  components: { MapContainer },
    props: ["form", "mode"],
    name: "DrugstoreBasicInfo",
    data: function() {
        return {
            map:null
        };
    }, methods : {

    },
    mounted: function(){
        this.map = this.$refs["map-container"].olMap;
    }
};
</script>
