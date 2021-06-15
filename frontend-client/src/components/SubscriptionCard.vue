<template>
    <b-card border-variant="hub">
        <template #header>
            <a class="x-icon" href="#" v-on:click="unsubscribe">
                <b-icon style="float: right;" icon="x" scale="1.5" variant="hub"></b-icon>
            </a>
            <h4 class="mr-3">{{subscription.name}}</h4>
        </template>

        <b-card-body>
            <img style="max-width: 10rem; border-radius: 70%;"
                src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format">
        </b-card-body>

        <div style="text-align:center;padding:0.5rem;">
            <router-link :to="'drugstores/' + subscription.id">
                <b-button variant="outline-hub" style="width:50%;">Visit</b-button>
            </router-link>
        </div>

    </b-card>
</template>

<script>
    import {
        mapState
    } from 'vuex';

    export default {

        props: {
            subscription: {}
        },
        computed: {
            ...mapState({
                patientId: state => state.userModule.loggedInUser.id,
            }),
        },
        methods: {
            unsubscribe: function (event) {
                event.preventDefault();
                this.$http.post(`https://mrs-isa-usijani.herokuapp.com/subscription/unsubscribe/`, {
                        patientId: this.patientId,
                        drugstoreId: this.subscription.id
                    })
                    .then( () => this.$emit("unsubscribed", this.subscription.id)
                    )
                    .catch(error => this.$toastr.e(error));
            }
        }
    }
</script>

<style>

</style>