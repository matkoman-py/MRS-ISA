<template>
    <div>
        <drugstore-basic-info :form="form"></drugstore-basic-info>
    </div>
</template>

<script>
import DrugstoreBasicInfo from "../../DrugstoreBasicInfo.vue";
export default {
    props: ["form"],
    components: { DrugstoreBasicInfo },
    data: function() {
        return {};
    },
    methods: {
        getSelectedDrugstoreInfo: function() {},
        submitHandler(event) {
            event.preventDefault();
            console.log(this.form);
            alert("Update saved.");
        },
    },
    mounted: function() {
        this.getSelectedDrugstoreInfo();
    },
};
</script>
