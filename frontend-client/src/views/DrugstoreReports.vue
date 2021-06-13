<template>
  <b-container>
    <h1 style="margin:10px;"> Reports for drugstore </h1>
    <b-card no-body>
    <b-tabs card>

      <b-tab title="Month reports" active>
      
        <div display="flex">
          <div float="left" style="margin-right:50px; text-align:left; display:inline">
            <b-button variant="outline-hub" @click="previousMonthReport">Previous year</b-button>
          </div>
          <div style="marign-left:50px; text-align:right; display:inline;">
            <b-button variant="outline-hub" @click="nextMonthReport">Next year</b-button>
          </div>
        </div>

        <h5 inline v-if="monthReport.emptyData == false" style="margin:10px;"> Profit for period from   <b>{{monthReport.dateFrom}}</b>    to    <b>{{monthReport.dateTo}}</b> </h5>
        <column-chart id="monthChart" v-if="monthReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="monthReport.columnChartData"> </column-chart>
        <h5 v-if="monthReport.emptyData == false" style="margin:10px;"> Profit shares </h5>
        <pie-chart v-if="monthReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="monthReport.pieChartData"> </pie-chart> 
        <h2 style="margin:25px;" v-if="monthReport.emptyData"> There are no data for period from {{this.monthReport.dateFrom}} to {{this.monthReport.dateTo}}. </h2>
      </b-tab>

      <b-tab title="Quartal reports">

        <div display="flex">
          <div float="left" style="margin-right:50px; text-align:left; display:inline">
            <b-button variant="outline-hub" @click="previousQuartalReport">Previous year</b-button>
          </div>
          <div style="marign-left:50px; text-align:right; display:inline;">
            <b-button variant="outline-hub" @click="nextQuartalReport">Next year</b-button>
          </div>
        </div>

        <h5 inline v-if="quartalReport.emptyData == false" style="margin:10px;"> Profit for period from   <b>{{quartalReport.dateFrom}}</b>    to    <b>{{quartalReport.dateTo}}</b> </h5>
        <column-chart v-if="quartalReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="quartalReport.columnChartData"> </column-chart>
        <h5 v-if="quartalReport.emptyData == false" style="margin:10px;"> Profit shares </h5>
        <pie-chart v-if="quartalReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="quartalReport.pieChartData"> </pie-chart> 
        <h2 style="margin:25px;" v-if="quartalReport.emptyData"> There are no data for period from {{this.quartalReport.dateFrom}} to {{this.quartalReport.dateTo}}. </h2>

      </b-tab>

      <b-tab title="Year reports">

        <div display="flex">
          <div float="left" style="margin-right:50px; text-align:left; display:inline">
            <b-button variant="outline-hub" @click="previousYearReport">Previous decade</b-button>
          </div>
          <div style="marign-left:50px; text-align:right; display:inline;">
            <b-button variant="outline-hub" @click="nextYearReport">Next decade</b-button>
          </div>
        </div>

        <h5 inline v-if="yearReport.emptyData == false" style="margin:10px;"> Profit for period from   <b>{{yearReport.dateFrom}}</b>    to    <b>{{yearReport.dateTo}}</b> </h5>
        <column-chart v-if="yearReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="yearReport.columnChartData"> </column-chart>
        <h5 v-if="yearReport.emptyData == false" style="margin:10px;"> Profit shares </h5>
        <pie-chart v-if="yearReport.emptyData == false" style="margin-top:20px; margin-bottom:30px;" :data="yearReport.pieChartData"> </pie-chart> 
        <h2 style="margin:25px;" v-if="yearReport.emptyData"> There are no data for period from {{this.yearReport.dateFrom}} to {{this.yearReport.dateTo}}. </h2>

      </b-tab>

    </b-tabs>
  </b-card>
    
  </b-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    }, components: {
        //Bar
    },
    data: function() {
      return {
            drugstoreId: '',
            counter:0,
            monthReport: {
              currentYear: 2021,
              direction: 0,
              columnChartData:[],
              pieChartData:[],
              dateFrom:'',
              dateTo:'',
              emptyData:false
            },
            quartalReport: {
              currentYear: 2021,
              direction: 0,
              columnChartData:[],
              pieChartData:[],
              dateFrom:'',
              dateTo:'',
              emptyData:false
            },
            yearReport: {
              currentYear: 2021,
              direction: 0,
              columnChartData:[],
              pieChartData:[],
              dateFrom:'',
              dateTo:'',
              emptyData:false
            }
            
      }
    },
    methods: {
        getDrugstore() {
            this.$http.get("https://mrs-isa-usijani.herokuapp.com/employees/drugstoreForId", {
              params: {
                drugstoreAdminId: this.user.id
              }
              })
              .then(response => {
              this.drugstoreId = response.data.id;
              this.getQuartalReport();
              this.getYearReport();
              this.getMonthReport();
              })
              .catch(error => console.log(error));
        },
        getMonthReport() {
            this.$http.get("https://mrs-isa-usijani.herokuapp.com/reports/month", {
              params: {
                drugstoreId: this.drugstoreId,
                currentYear: this.monthReport.currentYear,
                direction: this.monthReport.direction
              }
              })
              .then(response => {
                  if (response.data) {
                    var newArray = [];
                    newArray[0] = ["January", response.data.columnChart[0]];
                    newArray[1] = ["February", response.data.columnChart[1]];
                    newArray[2] = ["March", response.data.columnChart[2]];
                    newArray[3] = ["April", response.data.columnChart[3]];
                    newArray[4] = ["May", response.data.columnChart[4]];
                    newArray[5] = ["June", response.data.columnChart[5]];
                    newArray[6] = ["July", response.data.columnChart[6]];
                    newArray[7] = ["August", response.data.columnChart[7]];
                    newArray[8] = ["September", response.data.columnChart[8]];
                    newArray[9] = ["October", response.data.columnChart[9]];
                    newArray[10] = ["November", response.data.columnChart[10]];
                    newArray[11] = ["December", response.data.columnChart[11]];
                    this.monthReport.columnChartData = newArray;
                    //this.monthReport.columnChartData[0] = ["January", response.data.columnChart[0]];
                    //this.monthReport.columnChartData[1] = ["February", response.data.columnChart[1]];
                    //this.monthReport.columnChartData[2] = ["March", response.data.columnChart[2]];
                    //this.monthReport.columnChartData[3] = ["April", response.data.columnChart[3]];
                    //this.monthReport.columnChartData[4] = ["May", response.data.columnChart[4]];
                    //this.monthReport.columnChartData[5] = ["June", response.data.columnChart[5]];
                    //this.monthReport.columnChartData[6] = ["July", response.data.columnChart[6]];
                    //this.monthReport.columnChartData[7] = ["August", response.data.columnChart[7]];
                    //this.monthReport.columnChartData[8] = ["September", response.data.columnChart[8]];
                    //this.monthReport.columnChartData[9] = ["October", response.data.columnChart[9]];
                    //this.monthReport.columnChartData[10] = ["November", response.data.columnChart[10]];
                    //this.monthReport.columnChartData[11] = ["December", response.data.columnChart[11]];

                    this.monthReport.pieChartData = response.data.pieChart;
                    this.monthReport.dateFrom = response.data.startDate;
                    this.monthReport.dateTo = response.data.endDate;
                    this.monthReport.emptyData = false;
                  } else {
                    this.monthReport.emptyData = true;
                    this.monthReport.dateFrom = (this.monthReport.currentYear + this.monthReport.direction) + this.monthReport.dateFrom.substring(4);
                    this.monthReport.dateTo = (this.monthReport.currentYear + this.monthReport.direction + 1) + this.monthReport.dateTo.substring(4);
                  }
                  
            })
              .catch(error => console.log(error));
        }, previousMonthReport() {
            this.monthReport.direction -= 1;
            this.getMonthReport();
        }, nextMonthReport() {
            this.monthReport.direction += 1;
            this.getMonthReport();
        },
        getQuartalReport() {
            this.$http.get("https://mrs-isa-usijani.herokuapp.com/reports/quartal", {
              params: {
                drugstoreId: this.drugstoreId,
                currentYear: this.quartalReport.currentYear,
                direction: this.quartalReport.direction
              }
              })
              .then(response => {
                  if (response.data) {
                    this.quartalReport.columnChartData = response.data.columnChart;
                    this.quartalReport.pieChartData = response.data.pieChart;
                    this.quartalReport.dateFrom = response.data.startDate;
                    this.quartalReport.dateTo = response.data.endDate;
                    this.quartalReport.emptyData = false;
                  } else {
                    this.quartalReport.emptyData = true;
                    this.quartalReport.dateFrom = (this.quartalReport.currentYear + this.quartalReport.direction) + this.quartalReport.dateFrom.substring(4);
                    this.quartalReport.dateTo = (this.quartalReport.currentYear + this.quartalReport.direction + 1) + this.quartalReport.dateTo.substring(4);
                  }
                  
            })
              .catch(error => console.log(error));
        }, previousQuartalReport() {
            this.quartalReport.direction -= 1;
            this.getQuartalReport();
        }, nextQuartalReport() {
            this.quartalReport.direction += 1;
            this.getQuartalReport();
        },
        getYearReport() {
            this.$http.get("https://mrs-isa-usijani.herokuapp.com/reports/year", {
              params: {
                drugstoreId: this.drugstoreId,
                currentYear: this.yearReport.currentYear,
                direction: this.yearReport.direction
              }
              })
              .then(response => {
                  if (response.data) {
                    this.yearReport.columnChartData = response.data.columnChart;
                    this.yearReport.pieChartData = response.data.pieChart;
                    this.yearReport.dateFrom = response.data.startDate;
                    this.yearReport.dateTo = response.data.endDate;
                    this.yearReport.emptyData = false;
                  } else {
                    this.yearReport.emptyData = true;
                    this.yearReport.dateFrom = (this.yearReport.currentYear + this.yearReport.direction) + this.yearReport.dateFrom.substring(4);
                    this.yearReport.dateTo = (this.yearReport.currentYear + this.yearReport.direction + 10) + this.yearReport.dateTo.substring(4);
                  }
                  
            })
              .catch(error => console.log(error));
        }, previousYearReport() {
            this.yearReport.direction -= 10;
            this.getYearReport();
        }, nextYearReport() {
            this.yearReport.direction += 10;
            this.getYearReport();
        }
    },
    mounted: function(){
            this.getDrugstore();
    }
  }
</script>