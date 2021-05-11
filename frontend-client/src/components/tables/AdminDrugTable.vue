<template>
  <div>
    <b-container>
        <b-table id="drug-table" striped hover :items="drugs" :fields="fields">
            <template #cell(actions)="row">
                <b-button size="sm"  @click="showEditModal(row.item, $event.target)" class="mr-1" variant="outline-hub">
                    Edit
                </b-button>
                <b-button size="sm" @click="showDeleteModal(row.item, $event.target)" class="mr-1" variant="outline-hub">
                    Delete
                </b-button>
            </template>
        </b-table>
        <b-button size="sm" @click="showAddModal($event.target)" class="mr-1" variant="outline-hub">
                    Add
        </b-button>
    </b-container>
    <b-modal 
        :id="addModal.id" 
        :title="addModal.title" 
        ok-only 
        v-on:ok='addDrug' 
        ok-variant="outline-hub"
        @hide="resetAddModal" 
        size="lg">
        <add-drug-form ref='add-drug-form'></add-drug-form>
    </b-modal>
    <b-modal 
        :id="editModal.id" 
        :title="editModal.title" 
        ok-only 
        ok-variant="outline-hub"
        v-on:ok='edit' 
        @hide="resetEditModal" 
        size="lg">
        <edit-drug-form 
            ref='edit-drug-form' 
            :form="this.editModal.drug"
            v-on:update-drug-success="updateDrugInTable">
        </edit-drug-form>
    </b-modal>
    <b-modal 
        :id="deleteModal.id" 
        :title="deleteModal.title" 
        ok-only 
        ok-variant="outline-hub"
        v-on:ok='deleteDrug' 
        @hide="resetDeleteModal" 
        size="lg">
        Are you sure that you want to delete this drug?
    </b-modal>
  </div>
</template>

<script>
import AddDrugForm from '../forms/drug/AddDrugForm';
import EditDrugForm from '../forms/drug/EditDrugForm';

export default {
    name: "AdminDrugTable",
    components: {
        AddDrugForm,
        EditDrugForm
        },
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
        addModal: {
            id: 'add-modal',
            title: 'Add Drug',
        },
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
      showAddModal: function(button){
            this.$root.$emit('bv::show::modal', this.addModal.id, button);
      },
      resetAddModal: function(){
          
      },
      addDrug: function(event){
          this.$refs['add-drug-form'].addDrug(event);
      },
      showEditModal: function(rowItem, button){
            this.editModal.title = `drug: ${rowItem.name}`;
            this.editModal.drug = JSON.parse(JSON.stringify(rowItem));
            this.$root.$emit('bv::show::modal', this.editModal.id, button);
      },
      resetEditModal: function() {
            this.editModal.title = '';
      },
      edit: function(event){
          this.$refs['edit-drug-form'].editDrug(event);
      },
      showDeleteModal: function(rowItem, button){
            this.deleteModal.title = `drug: ${rowItem.name}`;
            this.deleteModal.drug = JSON.parse(JSON.stringify(rowItem));
            this.$root.$emit('bv::show::modal', this.deleteModal.id, button);
      },
      resetDeleteModal: function() {
            this.deleteModal.title = '';
      },
      deleteDrug: function(){
          this.$http.delete(`http://localhost:8081/drugs/${this.deleteModal.drug.id}`)
            .then(response => {
                let index = this.drugs.findIndex(drug => drug.id == this.deleteModal.drug.id);
                this.drugs.splice(index, 1);
                alert("success", response);
            })
            .catch(error => this.$toastr.e(error));
      },
      getDrugs: function () {
            this.$http.get('http://localhost:8081/drugs')
            .then(response => {
                this.drugs = response.data;
            })
            .catch(error => this.$toastr.e(error));
       },
       updateDrugInTable: function(updatedDrug) {
            const index = this.drugs.findIndex(drug => drug.id == updatedDrug.id);
            this.$set(this.drugs, index, updatedDrug);
            this.$root.$emit('bv::hide::modal', this.editModal.id);
       }
    },
    mounted: function(){
        this.getDrugs();
    }
}
</script>