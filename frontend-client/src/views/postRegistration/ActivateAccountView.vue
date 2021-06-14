<template>
    <div>
        <b-container style="width:30%" fluid>
            <b-row class="vh-100" align-v="center" align-h="center">
                <b-card v-if="passed">
                    You have successfully activated your account <br />
                    Now you can <router-link to="/login"> login </router-link>
                </b-card>
                <b-card v-else>
                    The code you have provided has already been activated or
                    isn't present in the system.
                </b-card>
            </b-row>
        </b-container>
    </div>
</template>

<script>
export default {
    // computed: {
    //     activationCode: () =>,
    // },
    data() {
        return {
            passed: false,
        };
    },
    methods: {},
    mounted: function() {
        this.$http
            .get(
                "http://localhost:8081/register/activate/" +
                    this.$route.params.activationCode
            )
            .then((response) => {
                console.log(response);
                this.passed = response.data;
            })
            .catch((error) => console.log(error));
    },
};
</script>

<style></style>
