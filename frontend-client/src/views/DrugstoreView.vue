<template>
    <b-container>
        <b-row style="margin:20px">
            <b-col>
                <b-img left
                    src="https://cdn.sanity.io/images/0vv8moc6/mhe/35d3654e121cea13826925b77336d022384fcb78-1000x667.png?auto=format"
                    alt="Left image" fluid></b-img>
            </b-col>
            <b-col style="padding-left:25px;">
                <div align="left" style="margin-top:30px">
                    <p style="margin:20px"><b>Name</b>: {{drugstore.name}}</p>
                    <p style="margin:20px"><b>Address</b>: {{drugstore.location.address}}, {{drugstore.location.city}},
                        {{drugstore.location.country}}</p>
                    <p style="margin:20px"><b>Description</b>: {{drugstore.description}}</p>
                    <p style="margin:20px"><b>Average rating</b>: {{drugstore.averageRating}}</p>
                    <p style="margin:20px"><b>Working hours</b>: {{drugstore.workingHoursFrom}} -
                        {{drugstore.workingHoursTo}}</p>
                    <b-button variant="outline-primary" style="margin:20px">Subscribe</b-button>
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
                <div style="margin-top:20px; margin-right:20px; margin-left:20px; border-style:solid;">
                    <b-table :items="dermatologists">

                    </b-table>
                    <p v-if="dermatologists.length == 0"><b>There are no dermatologists employed in this drugstore.</b>
                    </p>
                </div>
            </b-col>

            <b-col>
                <div style="margin-top:20px; margin-left:20px; margin-right:20px; border-style:solid;">
                    <b-table :items="pharmacists">
                    </b-table>
                    <p v-if="pharmacists.length == 0"><b>There are no pharmacists employed in this drugstore.</b></p>
                </div>
            </b-col>
        </b-row>
        <b-row>
            <b-col style="margin:20px;">
                <router-link :to="'/dermatologist-appointments/'+currentDrugstoreId">
                    <b-button variant="outline-primary" style="margin:30px">Make appointment with dermatologist
                    </b-button>
                </router-link>
            </b-col>
            <b-col style="margin:20px">
                <router-link :to="'/pharmacist-appointments/'+currentDrugstoreId">
                    <b-button variant="outline-primary" style="margin:30px">Make appointment with pharmacist</b-button>
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
                        <DrugInDrugstoreTable />
                    </b-tab>
                </b-tabs>
            </b-card>
        </div>
        <b-modal id="my-modal" title="Drug reservation!" hide-footer>
            <p>Before you finish the reservation process you must select the date to wait for your order</p>
            <b-form @submit="makeReservation">
                <b-form-datepicker id="example-datepicker" v-model="date" class="mb-2"></b-form-datepicker>
                <br>
                <b-button :disabled="date == ''" type="submit" variant="primary">Save</b-button>
            </b-form>
        </b-modal>
    </b-container>
</template>

<script>
    import DrugInDrugstoreTable from "@/components/DrugInDrugstoreTable"

    export default {
        components: {
            DrugInDrugstoreTable,
        },

        data: function () {
            return {
                date:'',
                currentDrugstoreId: '',
                drugstore: {},
                dermatologists: [],
                pharmacists: []
            }
        },
        methods: {
            getDrugstoreId() {
                this.currentDrugstoreId = this.$route.path.slice(12, this.$route.path.length);
            },
            getCurrentDrugstore() {
                this.$http.get('http://localhost:8081/drugstores/' + this.currentDrugstoreId, {})
                    .then(response => {
                        this.drugstore = response.data;
                    })
                    .catch(error => console.log(error));
            },
            getAllDermatologists() {
                this.$http.get("http://localhost:8081/employment/dermatologist-employments", {
                        params: {
                            drugstoreId: this.currentDrugstoreId
                        }
                    })
                    .then(response => {
                        this.dermatologists = response.data.map(employment =>
                            ({
                                name: employment.name,
                                surname: employment.surname,
                                worksFrom: employment.workingHoursFrom,
                                worksTo: employment.workingHoursTo
                            }));
                    })
                    .catch(error => console.log(error));
            },
            getAllPharmacists() {
                this.$http.get("http://localhost:8081/employment/pharmacist-employments", {
                        params: {
                            drugstoreId: this.currentDrugstoreId
                        }
                    })
                    .then(response => {
                        this.pharmacists = response.data.map(employment =>
                            ({
                                name: employment.name,
                                surname: employment.surname,
                                worksFrom: employment.workingHoursFrom,
                                worksTo: employment.workingHoursTo
                            }));
                    })
                    .catch(error => console.log(error));
            },
            getAllEmployees() {
                this.getAllDermatologists();
                this.getAllPharmacists();
            }
        },
        mounted: function () {
            this.getDrugstoreId();
            this.getCurrentDrugstore();
            this.getAllEmployees();
        }
    }
</script>