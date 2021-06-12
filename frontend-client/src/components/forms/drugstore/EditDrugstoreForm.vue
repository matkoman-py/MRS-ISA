<template>
    <div>
        <drugstore-basic-info ref="info" :form="drugstore"></drugstore-basic-info>
    </div>
</template>

<script>
import DrugstoreBasicInfo from "../../DrugstoreBasicInfo.vue";
export default {
    props: ["drugstore"],
    components: { DrugstoreBasicInfo },
    data: function() {
        return {
            map:null
        };
    },
    methods: {
        getSelectedDrugstoreInfo: function() {},
        submitHandler(event) {
            event.preventDefault();
            alert("Update saved.");
        },
    },
    mounted: function() {
        this.getSelectedDrugstoreInfo();
        this.map = this.$refs["info"].map;
    },
};
</script>
