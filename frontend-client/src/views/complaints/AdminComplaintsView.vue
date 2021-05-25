<template>
    <b-container>
        <b-row>
            <b-col
                cols="4"
                style="height: 90vh; direction:ltr;"
                class="overflow-auto"
            >
                <b-list-group>
                    <b-list-group-item
                        href="#"
                        v-for="complaint in complaints"
                        :key="complaint.id"
                        v-on:click="showComplaint(complaint)"
                    >
                        <b-row>
                            <b-col cols="6" style="text-align: left;">
                                <b>{{ complaint.patientEmail }}</b
                                ><br />
                                {{ complaint.type }}
                            </b-col>

                            <b-col cols="6" style="text-align: right;">
                                Not anwsered<br />
                                {{ complaint.employeName }}
                                {{ complaint.employeSurname }}
                            </b-col>
                        </b-row>
                        <b-row>
                            <b-card class="mt-3" style="text-align: left;">
                                {{ complaint.text }}
                            </b-card>
                        </b-row>
                    </b-list-group-item>
                </b-list-group>
            </b-col>
            <b-col v-if="!$helpers.isObjectEmpty(selectedComplaint)" cols="8">
                <div style="text-align: left;">
                    <b-card-header>Complaint</b-card-header>
                    <p class="m-2" style="text-align: justify;">
                        {{ selectedComplaint.text }}
                    </p>
                </div>
                <div class="mt-3" style="text-align: left;">
                    <b-card-header>Reply</b-card-header>
                    <p class="m-2" style="text-align: justify;">
                        {{ selectedComplaintReply.text }}
                    </p>
                </div>
                <b-card class="mt-3">
                    <b-form>
                        <b-form-row>
                            <b-col cols="10">
                                <b-form-textarea
                                    id="textarea"
                                    placeholder="Enter something..."
                                    rows="3"
                                    max-rows="6"
                                    v-model="replyForm.text"
                                    :disabled="
                                        !$helpers.isObjectEmpty(
                                            selectedComplaintReply
                                        )
                                    "
                                ></b-form-textarea>
                            </b-col>
                            <b-col>
                                <b-button
                                    variant="outline-hub"
                                    :disabled="
                                        !$helpers.isObjectEmpty(
                                            selectedComplaintReply
                                        )
                                    "
                                    v-on:click="postReplyForComplaint"
                                    >Reply</b-button
                                >
                            </b-col>
                        </b-form-row>
                    </b-form>
                </b-card>
            </b-col>
            <b-col v-else class="mt-3">
                <h3>Select a complaint</h3>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: "AdminComplaintsView",
    components: {},
    data: function() {
        return {
            complaints: [],
            selectedComplaint: {},
            selectedComplaintReply: {},
            replyForm: {
                text: "",
                complaintId: "",
                adminId: "",
            },
        };
    },
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
        }),
    },
    methods: {
        getComplaints: function() {
            this.$http
                .get("http://localhost:8081/complaints")
                .then((response) => {
                    this.complaints = response.data;
                });
        },
        getReplyForComplaint: function(complaint) {
            this.$http
                .get("http://localhost:8081/complaints/reply/" + complaint.id)
                .then((response) => {
                    this.selectedComplaintReply = response.data;
                });
        },
        showComplaint: function(complaint) {
            this.selectedComplaint = complaint;
            this.getReplyForComplaint(this.selectedComplaint);
            this.replyForm.complaintId = this.selectedComplaint.id;
            this.replyForm.adminId = this.user.id;
        },
        postReplyForComplaint: function() {
            this.$http
                .post(
                    "http://localhost:8081/complaints/reply/" +
                        this.selectedComplaint.id,
                    this.replyForm
                )
                .then((response) => {
                    this.selectedComplaintReply = response.data;
                    this.replyForm.text = "";
                });
        },
    },
    mounted: function() {
        this.getComplaints();
    },
};
</script>
