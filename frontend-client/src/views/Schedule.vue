<template>
  <div class='demo-app'>
    <div class='demo-app-sidebar'>
      <div class='demo-app-sidebar-section'>
        <h2>Instructions</h2>
        <ul>
          <li>Select an appointment and you will be prompted to start it</li>
        </ul>
      </div>
      <div class='demo-app-sidebar-section'>
        <label>
          <input
            type='checkbox'
            :checked='calendarOptions.weekends'
            @change='handleWeekendsToggle'
          />
          toggle weekends
        </label>
      </div>
      <div class='demo-app-sidebar-section'>
        <h2>All Appointments ({{ calendarOptions.events.length }})</h2>
        <ul>
          <li v-for='event in calendarOptions.events' :key='event.id'>
            <b>{{ event.start.replace('T',' ') }}</b>
            <i>{{ event.title }}</i>
            <br>
            <i>at {{ event.drugstore.name }}</i>
          </li>
        </ul>
      </div>
    </div>
    <div class='demo-app-main'>
      <FullCalendar
        class='demo-app-calendar'
        :options='calendarOptions'
      >
        <!-- <template v-slot:eventContent='arg'>
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }} </i>
          {{arg.drugstoreText}}
        </template> -->
        
      </FullCalendar>
    </div>

    <div v-if="mountedTrue">

      <b-modal id="my-modal" hide-footer title="Start appointment">
        <div class="d-block text-center">
          <p v-if="patientNotNull">Patient: {{selected.extendedProps.patient.name}} {{selected.extendedProps.patient.surname}}</p>
          <p v-else>Available</p>
          
          <p>Drugstore: {{selected.extendedProps.drugstore.name}}</p>
          <p>Start: {{selected.start}}</p>
          <p>Duration: {{selected.extendedProps.durationn}} minutes</p>
        </div>
        <b-button class="mt-3" variant="danger" :disabled="!patientNotNull || !timeValid" block @click="patientNotShowup"  >Patient did not show up!</b-button>
        <b-button class="mt-2" variant="success" :disabled="!patientNotNull || !timeValid" block @click="startApp">Start appointment!</b-button>
      </b-modal>
    </div>

    <div v-if="mountedTrue">

      <b-modal id="passedModal" hide-footer title="Done appointment">
        <div class="d-block text-center">
          <p v-if="patientNotNull">Patient: {{selected.extendedProps.patient.name}} {{selected.extendedProps.patient.surname}}</p>
          <p v-else>Available</p>
          
          <p>Drugstore: {{selected.extendedProps.drugstore.name}}</p>
          <p>Start: {{selected.start}}</p>
          <p>Duration: {{selected.extendedProps.durationn}} minutes</p>
        </div>
        <b-button class="mt-3" variant="danger" block @click="close"  >Close</b-button>
        
      </b-modal>
    </div>

  </div>
  
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import resourceTimelinePlugin from '@fullcalendar/resource-timeline';
import { mapState } from 'vuex'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  computed:{
        ...mapState({
        user: state => state.userModule.loggedInUser,
        //email: state => state.userModule.loggedInUser.email,
        //role: state => state.userModule.loggedInUser.type
        }),
  },
  data: function() {
    return {
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin, // needed for dateClick
          resourceTimelinePlugin
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'timelineYear,dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        events: [], // alternatively, use the `events` setting to fetch from a feed
        editable: false,
        selectable: false,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: false,
        //select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        slotEventOverlap:true,
        //eventsSet: this.handleEvents,
        
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
      selected: {},
      mountedTrue: false,
      patientNotNull: false,
      timeValid: false,
    }
  },
  methods: {
    close: function(){
      
      this.$root.$emit('bv::hide::modal', 'passedModal');
      
    },
    patientNotShowup: function(){
      alert("Patient did not show up");
      this.$root.$emit('bv::hide::modal', 'my-modal');
      // OVDE TREBA DA SE POZOVE BEK I DA SE KAZNI PACIJENT,  TO RADIS PREKO SELECTED
      this.$http.get('http://localhost:8081/patients/penalty', {
                        params: {
                            patientId: this.selected.extendedProps.patient.id//'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                      console.log(response);
                      alert("Patient received a penalty.");
                    })
                    .catch(error => console.log(error));
    },
    startApp: function(){
      
      alert("Appointment started"+ this.selected.id);
      this.$root.$emit('bv::hide::modal', 'my-modal');
      this.$router.push({ name: 'AppointmentDermatologist', params: { passedId: this.selected.id } })
      // PREBACUJES NA APPPOINTMENT SA TIM PACIJENTOM I DERM 

    },
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleEventClick(clickInfo) {
      this.selected = clickInfo.event;
      if(clickInfo.event.extendedProps.patient != null){
        this.patientNotNull = true;
      }else{
        this.patientNotNull = false;
      }
      var d = new Date();
      var d1 = new Date(clickInfo.event.start);
      d1.setMinutes(d1.getMinutes()-10);
      var d2 = new Date(clickInfo.event.start);
      d2.setMinutes(d2.getMinutes()+10);
      if(d>d1 && d<d2 && this.patientNotNull){
          this.timeValid = true;
      }else{
          this.timeValid = false;
      }
      if(d2 < d){
          this.$root.$emit('bv::show::modal', 'passedModal');
      }else{
          this.$root.$emit('bv::show::modal', 'my-modal');
      }
      //this.$root.$emit('bv::show::modal', 'my-modal');
      console.log(clickInfo.event)

    },
    handleEvents: function() {
      this.$http.get('http://localhost:8081/dermatologist-appointment/all-derm-app', {
                        params: {
                            dermatologistId:  this.user.id// 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                        this.calendarOptions.events = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.id,
                                dermatologist: currentEvent.dermatologist,
                                drugstore: currentEvent.drugstore,
                                allDay: false,
                                start: currentEvent.date.substring(0, 10)+"T"+currentEvent.time,
                                displayEventTime: true,
                                durationn:  currentEvent.duration,
                                end: currentEvent.date.substring(0, 10)+"T"+currentEvent.timeEnd,
                                title: currentEvent.patient ? currentEvent.patient.name+" "+currentEvent.patient.surname : "No patient",
                                patient: currentEvent.patient,
                            }));
                    })
                    .then(() => {
                        this.mountedTrue = true;
                        }
                    )
                    
    },
    addEmployment: function() {
        this.$http.get('http://localhost:8081/employment/employment-for-derm', {
                        params: {
                            dermatologistId: this.user.id//'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                        this.currentEvents = response.data.map(currentEvent =>
                            ({
                                drugstore: currentEvent.drugstore,
                                name: currentEvent.name,
                                surname: currentEvent.surname,
                                workingHoursTo: currentEvent.workingHoursTo,
                                workingHoursFrom: currentEvent.workingHoursFrom,
                            }));
                        
                    })
                    // .then(() => {
                    //     console.log(this.currentEvents);
                    //     for(var emp of this.currentEvents){
                    //         this.calendarOptions.events.push(
                    //             {title: emp.drugstore,
                    //              daysOfWeek: [1,2,3,4,5],
                    //              startTime: emp.workingHoursFrom,
                    //              endTime: emp.workingHoursTo,
                    //              //allDay: true,
                    //             }
                    //         );
                    //     }
                    //     }
                    // )
        
        
    }
  },
  mounted: function(){
      this.handleEvents();
      //this.addEmployment();
      //this.calendarOptions.events = this.currentEvents;
      //MORAS SKONTATI KAKO DA UVUCES APP U OVO ZA APOTEKU GOVNO I TO BI BILO TO JA MSM 
  },
}
</script>

<style>
h2 {
  margin: 0;
  font-size: 16px;
}
ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}
li {
  margin: 1.5em 0;
  padding: 0;
}
b { /* used for event dates/times */
  margin-right: 3px;
}
.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}
.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}
.demo-app-sidebar-section {
  padding: 2em;
}
.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}
.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
</style>