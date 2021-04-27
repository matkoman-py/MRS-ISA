<template>
  <b-container>
    <h1 v-if="reserved == 1">Drug: {{selecteddrug.name}}</h1>
    <h1 v-if="drugstores.length == 0 & reserved == 1">Sorry, there are no pharmacies with that drug in stock :(</h1>
    <b-table head-variant="dark" striped hover :fields="drugstoreFields" v-if="drugstores.length != 0"
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
        <b-form-datepicker id="example-datepicker" v-model="date" class="mb-2"></b-form-datepicker>
        <br>
        <b-button :disabled="date == ''" type="submit" variant="outline-hub">Save</b-button>
      </b-form>
    </b-modal>
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
    props: ['drugstores', 'reserved', 'selecteddrug'],
    data: function () {
      return {
        drugstoreId: '',
        date: '',
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
      }
    },
    methods: {
      showModal(item) {
        this.drugstoreId = item.id;
        this.$root.$emit('bv::show::modal', 'my-modal');
        //this.selected = JSON.parse(JSON.stringify(item));
        //this.selected.repeatPassword = "";
        this.modified = item;
      },
      handleClose() {
        this.$root.$emit('bv::hide::modal', 'my-modal');
      },
      makeReservation() {
        //alert(this.selecteddrug.id);
        //alert(this.drugstoreId);
        //alert(this.user.id);
        //alert(this.date);
        this.$http.post('http://localhost:8081/drugReservation/saveReservation', {
            patientId: this.user.id,
            drugstoreId: this.drugstoreId,
            drugId: this.selecteddrug.id,
            date: this.date
          })
          .then(response => {
            alert(response.data);
          })
          .catch(error => console.log(error));
      }
    }
  }
</script>