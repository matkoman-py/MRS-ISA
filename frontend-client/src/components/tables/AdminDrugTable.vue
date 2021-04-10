<template>
  <div>
    <b-container>
        <b-table id="drug-table" striped hover :items="drugs" :fields="fields">
            <template #cell(actions)="row">
                <b-button size="sm" @click="showEditModal(row.item, $event.target)" class="mr-1">
                    Edit
                </b-button>
                <b-button size="sm" @click="showDeleteModal(row.item, $event.target)" class="mr-1">
                    Delete
                </b-button>
            </template>
        </b-table>
    </b-container>
    <b-modal :id="editModal.id" :title="editModal.title" ok-only v-on:ok='edit' @hide="resetEditModal" size="xl">
    </b-modal>
    <b-modal :id="deleteModal.id" :title="deleteModal.title" ok-only v-on:ok='deleteDrug' @hide="resetDeleteModal" size="xl">
        Are you sure that you want to delete this drug?
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "AdmindrugTable",
    data: function() {
      return {
          fields:[
              { key: 'name', label: 'Name' },
              { key: 'form', label: 'Form' },
              { key: 'type.name', label: 'Type' },
              { key: 'manufacturer.name', label: 'Manufacturer' },
              { key: 'description', label: 'Description' },
              { key: 'actions', label: 'Actions'}
        ], 
        editModal: {
            id: 'edit-modal',
            title: '',
            drug: {}
        }, 
        deleteModal: {
            id: 'delete-modal',
            title: '',
            drug: {}
        },
        drugs: [],
      };
    },
    methods:{
      showEditModal: function(rowItem, button){
            this.editModal.title = `drug: ${rowItem.name}`;
            this.editModal.drug = JSON.parse(JSON.stringify(rowItem));
            console.log(this.editModal.drug);
            this.$root.$emit('bv::show::modal', this.editModal.id, button);
      },
      resetEditModal: function() {
            this.editModal.title = '';
      },
      edit: function(){
          axios.put('http://localhost:8081/drugs', this.editModal.drug)
            .then(response => {
                let index = this.drugs.findIndex(drug => drug.id == response.data.id);
                this.drugs.splice(index, 1, response.data);
                alert("success");
            })
            .catch(error => console.log(error));
      },
      showDeleteModal: function(rowItem, button){
            this.deleteModal.title = `drug: ${rowItem.name}`;
            this.deleteModal.drug = JSON.parse(JSON.stringify(rowItem));
            console.log(this.deleteModal.drug);
            this.$root.$emit('bv::show::modal', this.deleteModal.id, button);
      },
      resetDeleteModal: function() {
            this.deleteModal.title = '';
      },
      deleteDrug: function(){
          axios.delete(`http://localhost:8081/drugs/${this.deleteModal.drug.id}`)
            .then(response => {
                let index = this.drugs.findIndex(drug => drug.id == this.deleteModal.drug.id);
                this.drugs.splice(index, 1);
                alert("success", response);
            })
            .catch(error => console.log(error));
      },
      getDrugs: function () {
            axios.get('http://localhost:8081/drugs')
            .then(response => {
                this.drugs = response.data;
            })
            .catch(error => console.log(error));
       },
    },
    mounted: function(){
        this.getDrugs();
    }
}
</script>