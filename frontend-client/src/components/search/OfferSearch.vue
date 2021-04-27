<template>
    <div>
        <b-card title="Search">
            <b-form class="text-left" @submit="submitSearch()">
                <b-form-group id="input-group-1" label="Drug name:" label-for="input-1">
                    <b-form-select v-model="selected" :options="options"></b-form-select>
                </b-form-group>
                <div class="text-center">
                    <b-button type="submit" variant="outline-hub">Submit</b-button>
                </div>
            </b-form>
        </b-card>
    </div>
</template>

<script>
    import {
        mapState
    } from 'vuex';

    export default {
        name: "OfferSearch",
        props: ["currentPage", "perPage"],
        watch: {
            currentPage: function () {
                this.search();
            }
        },
        components: {},
        computed: {
            ...mapState({
                supplierId: state => state.userModule.loggedInUser.id,
            })
        },
        data: function () {
            return {
                selected: '',
                currentSearchTerm: '',
                currentPageData: [],
                nextPageData: [],
                options: [
                    //Active, Pending, Rejected
                    { value: "Active", text: "Active"},
                    { value: "Pending", text: "Pending"},
                    { value: "Rejected", text: "Rejected"},
                ],
            };
        },
        methods: {
            submitSearch: function(){
                this.currentSearchTerm = this.selected;
                this.search();
            },
            search: function () {
                if (this.currentPage != 1) {
                    this.currentPageData = JSON.parse(JSON.stringify(this.nextPageData));
                    this.$emit("success-search-results", this.currentPageData);
                    this.getNextPage(this.currentPage);
                } else {
                    this.$http.post("http://localhost:8081/offers/search", 
                        {
                            offerStatus: this.currentSearchTerm,
                            supplierId: this.supplierId,
                        }, 
                        {
                            params: {
                                page: this.currentPage - 1,
                                size: this.perPage
                            }
                        })
                        .then(response => {
                            console.log(response.data);
                            this.currentPageData = response.data;
                            this.$emit("success-search-results", this.currentPageData);
                            this.getNextPage(this.currentPage);
                        })
                        .catch(error => this.$toastr.e(error.message));
                }

            },
            getNextPage: function (page) {
                this.$http.post("http://localhost:8081/offers/search", 
                    {
                        offerStatus: this.currentSearchTerm,
                        supplierId: this.supplierId,
                    }, 
                    {
                        params: {
                            page: page,
                            size: this.perPage
                        }
                    })
                    .then(response => {
                        this.nextPageData = response.data;
                    })
                    .catch(error => this.$toastr.e(error));
            }
        },
        mounted: function () {
            this.submitSearch();
        }
    }
</script>