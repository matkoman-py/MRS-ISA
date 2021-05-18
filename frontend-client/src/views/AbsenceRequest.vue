<template>
  <b-container  style="margin-top:3rem;" id="appinfo">
            <h1>Create absence request</h1>
            <b-form @submit="createRequest">

                <b-form-group
                label="Start date:"
                label-for="date-picker"
                invalid-feedback="Start date is required">
                    <b-form-datepicker
                    id="startdate-input"
                    :min="minDate"
                    v-model="startDate"
                    required >
                    </b-form-datepicker>
                </b-form-group>

                <b-form-group
                label="End date:"
                label-for="date-picker"
                invalid-feedback="End date is required">
                    <b-form-datepicker
                    id="enddate-input"
                    :min="minDate"
                    v-model="endDate"
                    required >
                    </b-form-datepicker>
                </b-form-group>

                <b-col style="text-align:center">
                <label for="reason"> <h6>Reason:</h6></label>
                <br>
                <textarea id="reason" v-model="reason" name="reason" rows="10" cols="50" style="resize: none" required>
                </textarea>
                </b-col>
                

                
                <br>
                <b-button type="submit" variant="outline-hub">Create request</b-button>
                

            </b-form>
        
        
        
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
    data: function(){
        const now = new Date()
        const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
        const minDate = new Date(today)
        return{
            minDate:minDate,
            startDate: null,
            endDate: null,
            reason: '',
            
        }
    },
    methods:{
        createRequest: function(event){
            event.preventDefault();
            if(this.startDate == null){
                alert("Please input a start date!");
                return;
            }
            if(this.endDate == null){
                alert("Please input an end date!");
                return;
            }
            if(Date.parse(this.startDate)>Date.parse(this.endDate)){
                alert("End date must be after start date!");
                return;
            }
            alert("Prosao" + this.startDate+ this.endDate+ this.user.id);
            var dto = {
                startDate:this.startDate,
                endDate:this.endDate,
                reason:this.reason,
                employeeId:this.user.id
            }
            this.$http.post('http://localhost:8081/absence-request/create',  JSON.parse(JSON.stringify(dto)))
                    .then(response => {
                        alert(response.data);
                    })
        },
    },
}
</script>

<style>

</style>