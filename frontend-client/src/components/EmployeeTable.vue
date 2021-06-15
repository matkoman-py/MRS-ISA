<template>
    <b-container>
        <b-table id="tabela_zaposlenih" striped hover :items="employees" @row-clicked="showModal">
        </b-table>

        <b-modal id="my-modal" title="Your profile" hide-footer>
        <b-form @submit="onSubmit">
        <b-form-group
          label="Name"
          label-for="name-input"
          invalid-feedback="Name is required"
         
        >
          <b-form-input
            id="name-input"
            v-model="selected.name"
            
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Surame"
          label-for="surname-input"
          invalid-feedback="Surname is required"
          
        >
          <b-form-input
            id="surname-input"
            v-model="selected.surname"
            
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="E-mail"
          label-for="email-input"
          invalid-feedback="E-mail is required"
          
        >
          <b-form-input
            id="email-input"
            type="email"
            v-model="selected.email"
            
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Password"
          label-for="password-input"
        >
          <b-form-input
            id="password-input"
            type="password"
            v-model="selected.password"
            
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Repeat password"
          label-for="repeatpassword-input"
          
        >
          <b-form-input
            id="repeatpassword-input"
            type="password"
            v-model="selected.repeatPassword"
            
          ></b-form-input>
        </b-form-group>

        <b-form-group
          label="Phone number"
          label-for="phonenumber-input"
          invalid-feedback="Phone number is required"
         
        >
          <b-form-input
            id="phonenumber-input"
            v-model="selected.phoneNumber"
            
            required
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="outline-hub">Save</b-button>
        <b-button type="button" variant="outline-hub" @click="handleClose" >Cancel</b-button>
        </b-form>
        </b-modal>
    
    
    </b-container>
  
  
</template>

<script>

export default {
    data: function(){
        return{
            employees:[],
            selected: {},
            modified: {},
        }
    },
    methods: {
        getEmployees : function(){
            
            this.$http.get('https://mrs-isa-usijani.herokuapp.com/employees')
            .then(response => {
                this.employees = response.data.map(employee => 
                (
                    {
                        id: employee.id,
                        name: employee.name,
                        surname: employee.surname,
                        email: employee.email,
                        password: employee.password,
                        phoneNumber: employee.phoneNumber,
                        type: employee.type,
                    }
                ));
            })
            .catch(error => console.log(error));
        },
        showModal(item){
            this.$root.$emit('bv::show::modal', 'my-modal');
            this.selected = JSON.parse(JSON.stringify(item));
            this.selected.repeatPassword = "";
            this.modified = item;
        },
        onSubmit(event){
            event.preventDefault();
            if(!this.validatePassword()){
                    this.$toastr.e("Passwords aren't matching!")
                    return;
            }
            this.modified.name = this.selected.name;
            this.modified.surname = this.selected.surname;
            this.modified.email = this.selected.email;
            this.modified.password = this.selected.password;
            this.modified.phoneNumber = this.selected.phoneNumber;
            this.$root.$emit('bv::hide::modal', 'my-modal');
            console.log(this.modified);
            this.$http.put("https://mrs-isa-usijani.herokuapp.com/employees", this.modified)
                .then(response => {
                console.log(response);
                console.log("ovde");
                })
                .catch(error => console.log(error));
        },
        validatePassword: function(){
            if(this.selected.repeatPassword == ""){
              return true;
            }
            return this.selected.password == this.selected.repeatPassword;
        },
        handleClose(){
            this.$root.$emit('bv::hide::modal', 'my-modal');
        }
    },
    mounted: function(){
        this.getEmployees();
    }
}
</script>

<style>

</style>