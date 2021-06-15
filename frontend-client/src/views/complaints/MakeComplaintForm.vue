<template>
    <b-form>
        <b-form-row>
            <b-col cols="10">
                <b-form-textarea
                    id="textarea"
                    placeholder="Enter something..."
                    rows="3"
                    max-rows="6"
                    v-model="complaintForm.text"
                ></b-form-textarea>
            </b-col>
            <b-col>
                <b-button
                    variant="outline-hub"
                    v-on:click="postReplyForComplaint"
                    >Complain</b-button
                >
            </b-col>
        </b-form-row>
    </b-form>
</template>

<script>
export default {
    name: "MakeComplaintForm",

    props: ["complaintForm"],
    data: function() {
        return {};
    },
    methods: {
        postReplyForComplaint: function() {
            console.log(this.complaintForm);
            this.$http
                .post("http://localhost:8081/complaints/", this.complaintForm)
                .then((response) => {
                    this.$toastr.e("Succes!")
                    this.$emit("complaint-success");
                    console.log(response);
                })
                .catch(() => this.$toastr.e("You can't complain about this!"));
        },
    },
    mounted: function() {},
};
</script>
