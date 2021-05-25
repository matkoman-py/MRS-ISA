<template>
  <b-container>
    <template v-if="patientId == user.id">
    <h1 v-if="reserved == 1">Drug: {{selecteddrug.name}}</h1>
    <h1 v-if="drugstores.length == 0 & reserved == 1">Sorry, there are no pharmacies with that drug in stock :(</h1>

    <b-table striped hover :fields="drugstoreFields" v-if="drugstores.length != 0"
      :items="drugstores">
      <template #cell(actions)="row">
        <b-button variant="outline-hub" v-if="row.item" size="sm"
          @click="showModal(row.item, row.index, $event.target)" class="mr-1">
          Choose pharmacy
        </b-button>
      </template>
    </b-table>

    <b-modal id="my-modal" title="Almost done!" hide-footer>
      <p>Before you finish the reservation process you must select the date to wait for your order</p>
      <b-form @submit="makeReservation">
        <b-form-datepicker :min="minDate" id="example-datepicker" v-model="date" class="mb-2"></b-form-datepicker>
        <br>
        <b-button :disabled="date == ''" type="submit" variant="outline-hub">Save</b-button>
      </b-form>
    </b-modal>
    </template>
    <template v-else>
        <h1 v-if="drugstores.length == 0 & reserved == 1">Sorry, the selected drug is not on stock in this pharmacy :( Substitutes: </h1>
        <b-table head-variant="dark" striped hover :fields="drugstoreFields" v-if="drugstores.length != 0"
          :items="drugstores">
          <template #cell(actions)="row">
            <b-button variant="outline-hub" v-if="row.item" size="sm"
              @click="showModal(row.item, row.index, $event.target)" class="mr-1">
              Reserve
            </b-button>
          </template>
        </b-table>

        <b-table head-variant="dark" striped hover :fields="drugFields" v-if="drugstores.length == 0"
          :items="drugSubstitutions">
          <template #cell(actions)="row">
            <b-button variant="outline-hub" v-if="row.item" size="sm"
              @click="showSubstituteModal(row.item, row.index, $event.target)" class="mr-1">
              Reserve
            </b-button>
          </template>
        </b-table>

        <b-modal id="my-modal" title="Almost done!" hide-footer>
          <p>Before you finish the reservation process you must select the date to wait for your order</p>
          <b-form @submit="makeReservation">
            <b-form-datepicker :min="minDate" id="example-datepicker" v-model="date" class="mb-2"></b-form-datepicker>
            <br>
            <b-button :disabled="date == ''" type="submit" variant="outline-hub">Save</b-button>
          </b-form>
        </b-modal>
    </template>
  </b-container>
  
</template>

<script>
  import {
    mapState
  } from 'vuex'

  export default {
    computed: {
      ...mapState({
        user: state => state.userModule.loggedInUser,
        email: state => state.userModule.loggedInUser.email,
        role: state => state.userModule.loggedInUser.type
      }),
    },
    props: ['drugstores', 'reserved', 'selecteddrug', 'patientId', 'passedDrugstoreId'],
    data: function () {
      const now = new Date()
      const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
      const minDate = new Date(today)
      return {
        minDate : minDate,
        drugstoreId: '',
        date: '',
        drugSubstitutions: [],
        drugstoreFields: [{
            key: "name"
          },
          {
            key: "address"
          },
          {
            key: "city"
          },
          {
            key: "rating"
          },
          {
            key: 'actions',
            label: ''
          }
        ],
        drugFields: [{
            key: "name"
          },
          {
            key: "form"
          },
          {
            key: "type"
          },
          {
            key: "receipt"
          },
          {
            key: "manufacturer"
          },
          {
            key: 'actions',
            label: ''
          }
        ],
      }
    },
    methods: {
      showModal(item) {
        this.drugstoreId = item.id;
        this.$root.$emit('bv::show::modal', 'my-modal');
        //this.modified = item;
      },
      showSubstituteModal(item){
        
        this.selecteddrug.id = item.id;
        this.drugstoreId = this.passedDrugstoreId;
        this.$root.$emit('bv::show::modal', 'my-modal');
      },
      makeReservation() {
        //alert(this.selecteddrug.id);
        //alert(this.drugstoreId);
        //alert(this.user.id);
        //alert(this.date);
        this.$http.post('http://localhost:8081/drugReservation/saveReservation', {
            patientId: this.patientId,
            drugstoreId: this.drugstoreId,
            drugId: this.selecteddrug.id,
            date: this.date
          })
          .then(response => {
            alert(response.data);
            this.$root.$emit('bv::hide::modal', 'my-modal');
          })
          .catch(error => console.log(error));
      }
    },
    mounted: function(){
        //alert(this.passedDrugstoreId);
        if(this.passedDrugstoreId != null){
        this.$http.get("http://localhost:8081/drugs/substitutionsDrugstore", {
            params:
            {
            drugId: this.selecteddrug.id,
            drugstoreId: this.passedDrugstoreId,
            }
          })
          .then(response => {
            this.drugSubstitutions = response.data.map(drug =>
                            ({
                                  id: drug.id,
                                  name: drug.name,
                                  form: drug.form,
                                  type: drug.type.name,
                                  receipt: drug.receipt ? "Yes" : "No",
                                  manufacturer: drug.manufacturer.name,
                            }));
          })
          .catch(error => console.log(error));
        }
    }
  }
</script>