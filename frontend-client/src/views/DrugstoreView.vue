<template>
    <b-container>
        <b-row style="margin:20px">
            <b-col>
                <div
                    style="width:100%; height:300px; border-style:solid"
                    left
                    alt="Left image"
                    fluid
                >
                    <map-for-drugstore-view
                        ref="map-container"
                        :coordinates="[
                            drugstore.point.longitude,
                            drugstore.point.latitude,
                        ]"
                    ></map-for-drugstore-view>
                </div>
                <!--b-img
                    left
                    src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format"
                    alt="Left image"
                    fluid
                ></b-img-->
            </b-col>
            <b-col style="padding-left:25px;">
                <div align="left" style="margin-top:30px">
                    <p style="margin:20px"><b>Name</b>: {{ drugstore.name }}</p>
                    <p style="margin:20px">
                        <b>Address</b>: {{ drugstore.location.address }},
                        {{ drugstore.location.city }},
                        {{ drugstore.location.country }}
                    </p>
                    <p style="margin:20px">
                        <b>Description</b>: {{ drugstore.description }}
                    </p>
                    <p style="margin:20px">
                        <b>Average rating</b>:
                        {{ drugstore.rating.toFixed(2) }}
                    </p>

                    <p style="margin:20px">
                        <b>Working hours</b>: {{ drugstore.workingHoursFrom }} -
                        {{ drugstore.workingHoursTo }}
                    </p>
                    <b-button
                        variant="outline-hub"
                        style="margin:20px"
                        v-if="subscribed == false"
                        @click="subscribe"
                        :disabled="role != 'Patient'"
                    >
                        Subscribe</b-button
                    >
                    <b-button
                        variant="outline-hub"
                        style="margin:20px"
                        v-if="subscribed == true"
                        @click="unsubscribe"
                        :disabled="role != 'Patient'"
                    >
                        Unsubscribe</b-button
                    >
                    <b-button
                        style="margin:20px"
                        variant="outline-hub"
                        @click="showModal()"
                        class="mr-1"
                        :disabled="role != 'Patient'"
                    >
                        Rate drugstore
                    </b-button>
                    <b-button
                        style="margin:20px"
                        variant="outline-hub"
                        @click="showComplaintModal('Drugstore')"
                        class="mr-1"
                        :disabled="role != 'Patient'"
                    >
                        Make complaint
                    </b-button>
                </div>
            </b-col>
        </b-row>
        <b-row style="margin:10px">
            <b-col><b>Employees overview</b></b-col>
        </b-row>
        <b-row>
            <b-col>Dermatologists:</b-col>
            <b-col>Pharmacists:</b-col>
        </b-row>
        <b-row>
            <b-col>
                <div
                    style="margin-top:20px; margin-right:10px; margin-left:10px; border-style:solid;"
                >
                    <b-table :items="dermatologists" :fields="dTableFields">
                        <template #cell(rateActionD)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item"
                                :disabled="role != 'Patient'"
                                size="sm"
                                @click="
                                    showModalD(
                                        row.item,
                                        row.index,
                                        $event.target
                                    )
                                "
                                class="mr-1"
                            >
                                Rate
                            </b-button>
                        </template>
                        <template #cell(complain)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item"
                                size="sm"
                                :disabled="role != 'Patient'"
                                @click="
                                    showComplaintModal(
                                        'Dermatologist',
                                        row.item
                                    )
                                "
                                class="mr-1"
                            >
                                Complain
                            </b-button>
                        </template>
                    </b-table>

                    <p v-if="dermatologists.length == 0">
                        There are no dermatologists employed in this drugstore.
                    </p>
                </div>
            </b-col>

            <b-col>
                <div
                    style="margin-top:20px; margin-left:10px; margin-right:10px; border-style:solid;"
                >
                    <b-table :items="pharmacists" :fields="pTableFields">
                        <template #cell(rateActionP)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item"
                                size="sm"
                                @click="
                                    showModalP(
                                        row.item,
                                        row.index,
                                        $event.target
                                    )
                                "
                                class="mr-1"
                                :disabled="role != 'Patient'"
                            >
                                Rate
                            </b-button>
                        </template>
                        <template #cell(complain)="row">
                            <b-button
                                variant="outline-hub"
                                v-if="row.item"
                                size="sm"
                                @click="
                                    showComplaintModal('Pharmacist', row.item)
                                "
                                class="mr-1"
                                :disabled="role != 'Patient'"
                            >
                                Complain
                            </b-button>
                        </template>
                    </b-table>
                    <p v-if="pharmacists.length == 0">
                        There are no pharmacists employed in this drugstore.
                    </p>
                </div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="margin:20px;">
                <router-link
                    :to="'/dermatologist-appointments/' + currentDrugstoreId"
                >
                    <b-button
                        :disabled="this.user == null || role != 'Patient'"
                        variant="outline-hub"
                        style="margin:30px"
                        >Make appointment with dermatologist</b-button
                    >
                </router-link>
            </b-col>
            <b-col style="margin:20px">
                <router-link
                    :to="
                        '/schedule-pharm-app-drugstore/' +
                            this.currentDrugstoreId
                    "
                >
                    <b-button
                        :disabled="this.user == null || role != 'Patient'"
                        variant="outline-hub"
                        style="margin:30px"
                        >Make appointment with pharmacist</b-button
                    >
                </router-link>
            </b-col>
        </b-row>
        <b-row style="margin:10px">
            <b-col><b>Drug stock overview</b></b-col>
        </b-row>
        <div style="margin-top:30px">
            <b-card no-body>
                <b-tabs card>
                    <b-tab title="All available drugs" active>
                        <DrugInDrugstoreTable />
                    </b-tab>
                    <b-tab title="From my e-receipt only">
                        <qr-code-scanner
                            mode="true"
                            v-on:qr-code-scanned="getSingleReceipt"
                        />
                        <div v-if="priceOfERecipt <= 0">
                            <b-card class="mt-3">
                                This drugstore doesn't have the needed drugs.
                            </b-card>
                        </div>
                        <div v-else>
                            <b-card class="mt-3">
                                <b-form>
                                    <b-label>
                                        Price: {{ priceOfERecipt }} <br />
                                    </b-label>
                                    <b-button
                                        class="mt-3"
                                        @click="showSaveMultipleModal"
                                        variant="outline-hub"
                                        >Reserve eReceipt</b-button
                                    >
                                </b-form>
                            </b-card>
                        </div>
                    </b-tab>
                </b-tabs>
            </b-card>
        </div>
        <b-modal id="my-modal1" title="Rate drugstore" hide-footer>
            <b-form @submit="saveRatingDrugstore(item)">
                <label v-if="canRate" style="margin:20px"
                    ><b>Rate us: </b></label
                >
                <b-form-rating
                    v-if="canRate"
                    id="rate"
                    inline
                    value="userRating"
                    v-model="userRating"
                ></b-form-rating>
                <label v-if="canRate == false" style="margin:20px"
                    ><b>Rate us: </b></label
                >
                <b-form-rating
                    v-if="canRate == false"
                    id="cant-rate"
                    inline
                    disabled
                    value="2.75"
                ></b-form-rating>
                <br />
                <b-button type="submit" variant="outline-hub">Save</b-button>
            </b-form>
        </b-modal>

        <b-modal id="my-modalP" title="Rate pharmacist" hide-footer>
            <b-form @submit="saveRatingPharmacist(item)">
                <b-form-rating
                    v-if="canRate"
                    id="rate"
                    inline
                    value="userRating"
                    v-model="userRating"
                ></b-form-rating>
                <b-form-rating
                    v-if="canRate == false"
                    id="cant-rate"
                    inline
                    disabled
                    value="2.75"
                ></b-form-rating>
                <br />
                <br />
                <b-button type="submit" variant="outline-hub">Save</b-button>
            </b-form>
        </b-modal>

        <b-modal id="my-modalD" title="Rate dermatologist" hide-footer>
            <b-form @submit="saveRatingDermatologist(item)">
                <b-form-rating
                    v-if="canRate"
                    id="rate"
                    inline
                    value="userRating"
                    v-model="userRating"
                ></b-form-rating>
                <b-form-rating
                    v-if="canRate == false"
                    id="cant-rate"
                    inline
                    disabled
                    value="2.75"
                ></b-form-rating>
                <br />
                <br />
                <b-button type="submit" variant="outline-hub">Save</b-button>
            </b-form>
        </b-modal>

        <b-modal id="save-multiple" title="Almost done!" hide-footer>
            <p>
                Before you finish the reservation process you must select the
                date to wait for your order
            </p>
            <b-form @submit="makeReservation">
                <b-form-datepicker
                    id="example-datepicker"
                    v-model="reservationDate"
                    class="mb-2"
                ></b-form-datepicker>
                <br />
                <b-button
                    :disabled="reservationDate == ''"
                    type="submit"
                    variant="outline-hub"
                    >Save</b-button
                >
            </b-form>
        </b-modal>

        <b-modal
            id="complaintModal"
            title="Make complaint"
            size="lg"
            hide-footer
        >
            <b-form>
                <make-complaint-form
                    v-on:complaint-success="handleComplaintSuccess"
                    :complaintForm="complaintForm"
                ></make-complaint-form>
            </b-form>
        </b-modal>
    </b-container>
</template>

<script>
//Name Surname Works From Works To
import { fromLonLat } from "ol/proj";
import DrugInDrugstoreTable from "@/components/DrugInDrugstoreTable";
import { mapState } from "vuex";
import MakeComplaintForm from "./complaints/MakeComplaintForm.vue";
import MapForDrugstoreView from "../components/MapForDrugstoreView.vue";
import QrCodeScanner from "../components/QrCodeScanner.vue";
export default {
    computed: {
        ...mapState({
            user: (state) => state.userModule.loggedInUser,
        }),
    },
    components: {
        DrugInDrugstoreTable,
        MakeComplaintForm,
        MapForDrugstoreView,
        QrCodeScanner,
    },
    data: function() {
        return {
            reservationDate: "",
            priceOfERecipt: 0,
            saveRatingPharmacistId: "",
            saveRatingDermatologistId: "",
            dermatologistappointments: [],
            pharmacistappointments: [],
            drugReservations: [],
            canRate: false,
            avgRating: "",
            userRating: "3",
            currentDrugstoreId: "",
            subscribed: false,
            drugstore: {
                name: "",
                location: {
                    address: "",
                    city: "",
                    country: "",
                },
                employements: "",
                drugStock: "",
                workingHoursFrom: "",
                workingHoursTo: "",
                pharmacistappointmentPrice: "",
                point: {
                    longitude: "",
                    latitude: "",
                },
            },
            dermatologists: [],
            pharmacists: [],
            date: "",
            averageRate: 0,
            numberOfRates: 0,
            pTableFields: [
                {
                    key: "name",
                },
                {
                    key: "surname",
                },
                {
                    key: "worksFrom",
                },
                {
                    key: "worksTo",
                },
                {
                    key: "rateActionP",
                    label: "",
                },
                {
                    key: "complain",
                    label: "",
                },
            ],
            dTableFields: [
                {
                    key: "name",
                },
                {
                    key: "surname",
                },
                {
                    key: "worksFrom",
                },
                {
                    key: "worksTo",
                },
                {
                    key: "rateActionD",
                    label: "",
                },
                {
                    key: "complain",
                    label: "",
                },
            ],
            complaintForm: {
                drugstoreId: "",
                employeeId: "",
                type: "",
                patientId: "",
                text: "",
            },
        };
    },
    methods: {
        showSaveMultipleModal() {
            this.drugstoreId = this.drugstore.id;
            this.$root.$emit("bv::show::modal", "save-multiple");
        },
        handleClose() {
            this.$root.$emit("bv::hide::modal", "save-multiple");
        },
        getSingleReceipt: function(someData) {
            this.receipt = someData;
            let requestData = {
                drugstoreId: this.drugstore.id,
                receiptData: this.receipt,
            };
            this.$http
                .post(
                    "http://localhost:8081/drugstores/single-receipt",
                    requestData
                )
                .then((response) => {
                    this.priceOfERecipt = response.data[0].totalPrice;
                })
                .catch((error) => console.log(error));
        },
        makeReservation: function(event) {
            event.preventDefault();
            this.$http
                .post(
                    `http://localhost:8081/drugReservation/saveMultipleReservations`,
                    this.makeReservationRequestBody()
                )
                .then((response) => {
                    this.$toastr.s("Success!");
                    console.log(response.data);
                })
                .catch((error) => console.log(error));
        },
        makeReservationRequestBody: function() {
            const requestBodyData = [];
            let counter = 0;
            for (let drug of Object.keys(this.receipt)) {
                requestBodyData.push({
                    patientId: this.user.id,
                    drugstoreId: this.drugstoreId,
                    drugId: drug,
                    amount: this.receipt[drug],
                    date: this.reservationDate,
                });
                counter += 1;
                console.log(counter);
            }

            return requestBodyData;
        },
        saveRatingPharmacist() {
            this.$http
                .get("http://localhost:8081/rating-pharmacist/saveRating", {
                    params: {
                        patientId: this.user.id,
                        pharmacistId: this.saveRatingPharmacistId,
                        rating: this.userRating,
                    },
                })
                .then(() => {
                    this.$toastr.s("You successfully rated a pharmacist");
                    this.getCurrentDrugstore();
                    this.$root.$emit("bv::hide::modal", "my-modalP");
                })
                .catch((error) => console.log(error));
        },
        saveRatingDermatologist() {
            this.$http
                .get("http://localhost:8081/rating-dermatologist/saveRating", {
                    params: {
                        patientId: this.user.id,
                        dermatologistId: this.saveRatingDermatologistId,
                        rating: this.userRating,
                    },
                })
                .then(() => {
                    this.$toastr.s("You successfully rated a dermatologist");
                    this.getCurrentDrugstore();
                    this.$root.$emit("bv::hide::modal", "my-modalD");
                })
                .catch((error) => console.log(error));
        },
        saveRatingDrugstore() {
            this.$http
                .get("http://localhost:8081/rating-drugsore/saveRating", {
                    params: {
                        patientId: this.user.id,
                        drugstoreId: this.currentDrugstoreId,
                        rating: this.userRating,
                    },
                })
                .then(() => {
                    this.$toastr.s("You successfully rated a drugstore");
                    this.getCurrentDrugstore();
                    this.$root.$emit("bv::hide::modal", "my-modal1");
                })
                .catch((error) => console.log(error));
        },
        showModal() {
            if (this.user == null) {
                this.$toastr.e("You must be logged in to rate a drugstore!");
                return;
            }
            //alert(item.name);
            this.getDrugReservations();
            var i;
            var x = 1;
            for (i = 0; i < this.drugReservations.length; i++) {
                //alert(this.drugReservations[i].drugstore);
                //alert(this.drugstore.name);
                if (this.drugReservations[i].drugstore == this.drugstore.name) {
                    //alert("JUPI");
                    this.canRate = true;
                    x = 0;
                }
            }
            if (x == 1) this.canRate = false;
            this.$root.$emit("bv::show::modal", "my-modal1");
        },
        showModalP(item) {
            this.saveRatingPharmacistId = item.employeeId;
            if (this.user == null) {
                this.$toastr.e("You must be logged in to rate a pharmacist!");
                //alert("You must be logged in to rate a pharmacist!");
                return;
            }
            this.getAppointments();
            var i;
            var x = 1;
            for (i = 0; i < this.pharmacistappointments.length; i++) {
                //alert(this.pharmacistappointments[i].processed);
                //alert(item.surname);
                if (
                    this.pharmacistappointments[i].pharmacist == item.name &&
                    this.pharmacistappointments[i].surname == item.surname &&
                    this.pharmacistappointments[i].processed
                ) {
                    //alert("JUPI");
                    x = 0;
                    this.canRate = true;
                }
            }
            if (x == 1) this.canRate = false;
            this.$root.$emit("bv::show::modal", "my-modalP");
        },
        showModalD(item) {
            this.saveRatingDermatologistId = item.employeeId;
            if (this.user == null) {
                this.$toastr.e(
                    "You must be logged in to rate a dermatologist!"
                );
                //alert("You must be logged in to rate a pharmacist!");
                return;
            }
            this.getDermatologyAppointments();
            var i;
            var x = 1;
            for (i = 0; i < this.dermatologistappointments.length; i++) {
                //alert(this.dermatologistappointments[i].dermatologist);
                //alert(item.name);
                if (
                    this.dermatologistappointments[i].dermatologist ==
                        item.name &&
                    this.dermatologistappointments[i].surname == item.surname &&
                    this.dermatologistappointments[i].processed
                ) {
                    //alert("JUPI");
                    x = 0;
                    this.canRate = true;
                }
            }
            if (x == 1) this.canRate = false;
            this.$root.$emit("bv::show::modal", "my-modalD");
        },
        showComplaintModal(complaintType, employee) {
            if (this.user == null) {
                this.$toastr.e("You must be logged in to make a complaint!");
                return;
            }
            this.complaintForm.type = complaintType;
            this.complaintForm.drugstoreId = this.drugstore.id;
            this.complaintForm.patientId = this.user.id;

            if (complaintType != "Drugstore") {
                this.complaintForm.employeeId = employee.employeeId;
            }

            console.log(employee);
            this.$root.$emit("bv::show::modal", "complaintModal");
        },
        handleComplaintSuccess: function() {
            console.log("neeeeeeeee");
            this.$root.$emit("bv::hide::modal", "complaintModal");
        },
        getAppointments: function() {
            //"664783ca-84a1-4a2b-ae27-a2b820bc3c71"
            if (this.user != null) {
                this.$http
                    .get(
                        "http://localhost:8081/pharmacist-appointment/get-appointments",
                        {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.pharmacistappointments = response.data.map(
                            (appointment) => ({
                                processed: appointment.processed,
                                surname: appointment.pharmacist.surname,
                                pharmacist: appointment.pharmacist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            }
        },
        getDermatologyAppointments: function() {
            //"664783ca-84a1-4a2b-ae27-a2b820bc3c71"
            if (this.user != null) {
                this.$http
                    .get(
                        "http://localhost:8081/dermatologist-appointment/returnAppointments",
                        {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.dermatologistappointments = response.data.map(
                            (appointment) => ({
                                processed: appointment.processed,
                                surname: appointment.dermatologist.surname,
                                dermatologist: appointment.dermatologist.name,
                                date: appointment.date.slice(0, 10),
                                time: appointment.time.slice(0, 5),
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            }
        },
        getDrugReservations: function() {
            if (this.user != null) {
                this.$http
                    .get(
                        "http://localhost:8081/drugReservation/getPatientReservations",
                        {
                            params: {
                                patientId: this.user.id,
                            },
                        }
                    )
                    .then((response) => {
                        this.drugReservations = response.data.map(
                            (drugReservation) => ({
                                id: drugReservation.id,
                                drug: drugReservation.drug.name,
                                drugstore: drugReservation.drugstore.name,
                                date: drugReservation.date,
                            })
                        );
                    })
                    .catch((error) => console.log(error));
            }
        },
        getDrugstoreId() {
            this.currentDrugstoreId = this.$route.path.slice(
                12,
                this.$route.path.length
            );
        },
        checkSubscription() {
            this.$http
                .get("http://localhost:8081/subscription/check", {
                    params: {
                        patientId: this.user.id,
                        drugstoreId: this.drugstore.id,
                    },
                })
                .then((response) => {
                    this.subscribed = response.data;
                });
        },
        getCurrentDrugstore() {
            this.$http
                .get(
                    "http://localhost:8081/drugstores/" +
                        this.currentDrugstoreId,
                    {}
                )
                .then((response) => {
                    this.drugstore = response.data;
                    this.drugstore.workingHoursFrom
                        ? this.drugstore.workingHoursFrom.slice(0, 5)
                        : this.drugstore.workingHoursFrom;
                    this.drugstore.workingHoursTo
                        ? this.drugstore.workingHoursTo.slice(0, 5)
                        : this.drugstore.workingHoursTo;
                    this.checkSubscription();
                    this.$refs["map-container"].addMarker(
                        fromLonLat([
                            this.drugstore.point.longitude,
                            this.drugstore.point.latitude,
                        ])
                    );
                })
                .catch((error) => console.log(error));
        },

        getAllDermatologists() {
            this.$http
                .get(
                    "http://localhost:8081/employment/dermatologist-employments",
                    {
                        params: {
                            drugstoreId: this.currentDrugstoreId,
                        },
                    }
                )
                .then((response) => {
                    this.dermatologists = response.data.map((employment) => ({
                        employeeId: employment.employeeId,
                        name: employment.name,
                        surname: employment.surname,
                        worksFrom: employment.workingHoursFrom.slice(0, 5),
                        worksTo: employment.workingHoursTo.slice(0, 5),
                    }));
                })
                .catch((error) => console.log(error));
        },
        getAllPharmacists() {
            this.$http
                .get(
                    "http://localhost:8081/employment/pharmacist-employments",
                    {
                        params: {
                            drugstoreId: this.currentDrugstoreId,
                        },
                    }
                )
                .then((response) => {
                    this.pharmacists = response.data.map((employment) => ({
                        employeeId: employment.employeeId,
                        name: employment.name,
                        surname: employment.surname,
                        worksFrom: employment.workingHoursFrom.slice(0, 5),
                        worksTo: employment.workingHoursTo.slice(0, 5),
                    }));
                })
                .catch((error) => console.log(error));
        },
        getAllEmployees() {
            this.getAllDermatologists();
            this.getAllPharmacists();
        },
        subscribe() {
            if (this.user == null) {
                this.$toastr.e(
                    "You must be logged in to subscribe to a drugstore!"
                );
                return;
            }
            this.$http
                .post("http://localhost:8081/subscription/subscribe", {
                    patientId: this.user.id,
                    drugstoreId: this.drugstore.id,
                })
                .then((response) => {
                    this.subscribed = response.data;
                });
        },
        unsubscribe() {
            if (this.user == null) {
                this.$toastr.e(
                    "You must be logged in to unsubscribe to a drugstore!"
                );
                return;
            }
            this.$http
                .post("http://localhost:8081/subscription/unsubscribe", {
                    patientId: this.user.id,
                    drugstoreId: this.drugstore.id,
                })
                .then((response) => {
                    this.subscribed = response.data;
                });
        },
    },
    mounted: function() {
        this.getDrugstoreId();
        this.getCurrentDrugstore();
        this.getAllEmployees();
        this.getDrugReservations();
        this.getDermatologyAppointments();
        this.getAppointments();
    },
};
</script>
