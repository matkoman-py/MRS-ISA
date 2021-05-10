<template>

    <b-container>
        <b-row align-h="center">
            <b-col>

                <template v-for="(chunk, index) in getCardDrugstores()">
                    <b-row cols-lg="4" :key="index">
                        <b-col style="padding:20px; text-align:center" v-for="drugstore in chunk" :key="drugstore.id">
                            <subscription-card :subscription="drugstore" v-on:unsubscribed="handleUnsubscribe"></subscription-card>
                        </b-col>
                    </b-row>
                </template>

                <h1 v-if="drugstores.length == 0"> You have no subscriptions</h1>
            </b-col>

        </b-row>
    </b-container>
</template>

<script>
import { mapState } from 'vuex';
    import SubscriptionCard from '../components/SubscriptionCard';

    export default {
        computed: {
            ...mapState({
                patientId: state => state.userModule.loggedInUser.id,
            }),
        },
        components: {
            SubscriptionCard
        },
        name: "SubscriptionView",
        data: function () {
            return {
                drugstores: [],
            }
        },
        methods: {
            getDrugstores: function () {
                this.$http.get(`http://localhost:8081/subscription/patient/${this.patientId}`)
                    .then(response => {
                            this.drugstores = this.mapDrugstores(response);
                    })
                    .catch(error => console.log(error));
            },
            mapDrugstores: function (response) {
                return response.data.map(sub =>
                    ({
                        subId: sub.id,
                        id: sub.drugstoreId,
                        name: sub.name,
                        adress: sub.address,
                        country: sub.country,
                        city: sub.city,
                        description: sub.decription,
                        rating: sub.averageRating,
                    }));
            },
            getCardDrugstores: function () {
                let chunkArray = [];
                for (var i = 0; i < this.drugstores.length; i += 4) {
                    chunkArray.push(this.drugstores.slice(i, i + 4));
                }
                return chunkArray

            },
            handleUnsubscribe: function() {
                this.getDrugstores();
            }
        },
        mounted: function () {
            this.getDrugstores();
        }
    }
</script>