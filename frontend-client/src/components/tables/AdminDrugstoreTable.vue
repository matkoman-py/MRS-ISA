<template>
  <div>
    <b-container>
        <b-table id="drugstore-table" striped hover :items="drugstores" :fields="fields">
            <template #cell(actions)="row">
                <b-button size="sm" @click="showEditModal(row.item, $event.target)" class="mr-1">
                    Edit
                </b-button>
            </template>
        </b-table>
    </b-container>
    <b-modal :id="editModal.id" :title="editModal.title" ok-only v-on:ok='edit' @hide="resetEditModal" size="xl">
      <pre><drugstore-basic-info-update :form="editModal.drugstore" submitHandle="null"></drugstore-basic-info-update></pre>
    </b-modal>
  </div>
</template>

<script>
import DrugstoreBasicInfoUpdate from '../../views/DrugstoreBasicInfoUpdate.vue';
import axios from 'axios'

export default {
  components: { DrugstoreBasicInfoUpdate },
    name: "AdminDrugstoreTable",
    data: function() {
      return {
          fields:[
              { key: 'name', label: 'Full Name' },
              { key: 'description', label: 'Description' },
              { key: 'actions', label: 'Actions'}
        ], 
        editModal: {
            id: 'edit-modal',
            title: '',
            drugstore: {}
        },
        drugstores: [],
      };
    },
    methods:{
      showEditModal: function(rowItem, button){
            this.editModal.title = `Drugstore: ${rowItem.name}`;
            this.editModal.drugstore = JSON.parse(JSON.stringify(rowItem));
            console.log(this.editModal.drugstore);
            this.$root.$emit('bv::show::modal', this.editModal.id, button);
      },
      resetEditModal: function() {
            this.editModal.title = '';
      },
      edit: function(){
          axios.put('http://localhost:8081/drugstores', this.editModal.drugstore)
            .then(response => {
                let index = this.drugstores.findIndex(drugstore => drugstore.id == response.data.id);
                this.drugstores.splice(index, 1, response.data);
                alert("success");
            })
            .catch(error => console.log(error));
      },
      getDrugstores: function () {
            axios.get('http://localhost:8081/drugstores')
            .then(response => {
                this.drugstores = response.data;
            })
            .catch(error => console.log(error));
       },
    },
    mounted: function(){
        this.getDrugstores();
    }
}
</script>