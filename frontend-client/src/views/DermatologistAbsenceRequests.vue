<template>
  <b-container>
    <h1> Dermatologist absence requests overview </h1>
    <div style="margin:40px; border-style:solid;">
        <b-table striped hover :items="requests" :fields="fields" selectable select-mode='single' @row-selected="onRowSelected"></b-table>
        <p v-if="requests.length == 0"> There are no absence requests at the moment. </p>
    </div>

    <div v-if="requests.length != 0 && selectedRequest.length != 0" style="margin:40px;">
        <p><b>Reason of request</b>:</p>
        <b-form-input id="input-large" placeholder="Reason.." disabled v-model="selectedRequest[0].reason"></b-form-input>
    </div>

    <div v-if="requests.length != 0">
        <b-button style="margin-right:50px" variant="outline-hub" @click="acceptRequest" size="lg"> Accept </b-button>
        <b-button style="margin-left:50px" variant="outline-hub" @click="rejectRequest" size="lg"> Reject </b-button>
    </div>

    <b-modal id="rejectConfirmation" title="Complete rejection" align="center" hide-footer>
      <b-form>
        <b-row style="padding:20px;">
        <b-label style="margin-bottom:10px;"> Reason of rejection: </b-label>
        <b-form-input id="input-default" placeholder="Type the reason of rejection.." v-model="reasonOfRejection"></b-form-input>
        </b-row>
        <b-row align-h="center">
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="reject">Finish</b-button>
        <b-button style="margin:20px" type="button" variant="outline-hub" @click="cancel" >Cancel</b-button>
        </b-row>
      </b-form>
    </b-modal>
  </b-container>
</template>

<script>
  import { mapState } from 'vuex'
  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
      }),
    },
    data: function() {
      return {
        selectedRequest:[],
        requests: [],
        reasonOfRejection: '',        
        fields: [
                  
                  {
                      key: 'name',
                  },
                  {
                      key: 'surname',
                  },
                  {
                      key: 'absence_start_date',
                  },
                  {
                      key: 'absence_end_date',
                  },
                  {
                      key: 'drugstores',
                  }
              ]
      }
    },
    methods: {
        getRequests : function(){
            this.$http.get('http://localhost:8081/employment/dermatologistRequests')
            .then(response => {
              this.requests = response.data.map(request => 
              (
                      {
                          name: request.pharmacistName,
                          surname: request.pharmacistSurname,
                          absence_start_date: request.startDate.slice(0,10),
                          absence_end_date: request.endDate.slice(0,10),
                          reason: request.reason,
                          requestId: request.requestId,
                          drugstores: request.drugstores
                      }
                  ));
            })
            .catch(error => console.log(error));
        }, acceptRequest() {
            if (this.selectedRequest.length == 0) {
                alert("You need to select absence request that you want to accept.")
          } else {
                this.$http.post('http://localhost:8081/absence-request/approveDermatologist/' + this.selectedRequest[0].requestId)
                  .then(response => {
                    alert(response.data); // zasto se ne prikaze ovo??
                    this.getRequests();
                  })
                  .catch(error => console.log(error));
          }
        }, rejectRequest(event) {
          event.preventDefault()
          if (this.selectedRequest.length == 0) {
                alert("You need to select absence request that you want to reject.")
          } else {
                this.$root.$emit('bv::show::modal', 'rejectConfirmation');
          }
        }, reject() {
            this.$http.post('http://localhost:8081/absence-request/reject', {
                    requestId: this.selectedRequest[0].requestId,
                    reasonOfRejection: this.reasonOfRejection
            })
                  .then(response => {
                    alert(response.data); // zasto se ne prikaze ovo??
                    this.getRequests();
                    this.$root.$emit('bv::hide::modal', 'rejectConfirmation');
                  })
                  .catch(error => console.log(error));
        }, cancel() {
            this.$root.$emit('bv::hide::modal', 'rejectConfirmation');
        }, onRowSelected(item) {
            this.selectedRequest = item
        }
    },
    mounted: function(){
        this.getRequests();
    }
  }
</script>