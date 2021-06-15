<template>
    <b-container>
        <div>
            <drugstore-basic-info :form="drugstore"></drugstore-basic-info>
        </div>
        <div>
            <router-link to="/my-drugstore">
                <b-button
                    style="margin-top:40px; margin-bottom:40px; margin-right:100px;"
                    type="button"
                    variant="outline-hub"
                    size="lg"
                    >Cancel</b-button
                >
            </router-link>
                <b-button
                    style="margin-top:40px; margin-bottom:40px; margin-left:100px;"
                    type="button"
                    variant="outline-hub"
                    @click="saveChanges"
                    size="lg"
                    >Save changes</b-button
                >
        </div>
    </b-container>
</template>

<script>
import DrugstoreBasicInfo from "../components/DrugstoreBasicInfo.vue";
import { mapState } from "vuex";
export default {
    components: { DrugstoreBasicInfo },
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
        }),
    },
    data: function() {
        return {
            drugstore: {},
        };
    },
    methods: {
        getSelectedDrugstoreInfo: function() {
            this.$http
                .get("http://localhost:8081/drugstores/adminsDrugstore", {
                    params: {
                        adminId: this.user.id,
                    },
                })
                .then((response) => {
                    this.drugstore = response.data;
                })
                .catch((error) => console.log(error));
        },
        saveChanges: function() {
            this.$http
                .put("http://localhost:8081/drugstores/update", this.drugstore)
                .then(() => {
                    this.$router.push('my-drugstore');
                    this.$toastr.s("You succesfully updated drugstore informations!")
                })
                .catch((error) => console.log(error));
        },
    },
    mounted: function() {
        this.getSelectedDrugstoreInfo();
    },
};
</script>
