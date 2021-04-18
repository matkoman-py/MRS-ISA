<template>
  <div class='demo-app'>
    <div class='demo-app-sidebar'>
      <div class='demo-app-sidebar-section'>
        <h2>Instructions</h2>
        <ul>
          <li>Select dates and you will be prompted to create a new event</li>
          <li>Drag, drop, and resize events</li>
          <li>Click an event to delete it</li>
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
        <h2>All Events ({{ calendarOptions.events.length }})</h2>
        <ul>
          <li v-for='event in calendarOptions.events' :key='event.id'>
            <b>{{ event.start }}</b>
            <i>{{ event.title }}</i>
          </li>
        </ul>
      </div>
    </div>
    <div class='demo-app-main'>
      <FullCalendar
        class='demo-app-calendar'
        :options='calendarOptions'
      >
        <template v-slot:eventContent='arg'>
          <b>{{ arg.timeText }}</b>
          <i>{{ arg.event.title }}</i>
        </template>
      </FullCalendar>
    </div>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import axios from "axios"
//import { INITIAL_EVENTS, createEventId } from './event-utils'
export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data: function() {
    return {
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        events: [], // alternatively, use the `events` setting to fetch from a feed
        editable: false,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        slotEventOverlap:true,
        //eventsSet: this.handleEvents,
        
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: []
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
      console.log(this.currentEvents)
      console.log(this.calendarOptions.events)
    },
    handleDateSelect(selectInfo) {
      let title = prompt('Please enter a new title for your event')
      let calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (title) {
        calendarApi.addEvent({
          //id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    },
    handleEventClick(clickInfo) {
      console.log(`Are you sure you want to delete the event '${clickInfo.event.start}' end: '${clickInfo.event.end}'`)
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },
    handleEvents: function() {
      axios.get('http://localhost:8081/dermatologist-appointment/all-derm-app', {
                        params: {
                            dermatologistId: 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
                        }
                    })
                    .then(response => {
                        this.calendarOptions.events = response.data.map(currentEvent =>
                            ({
                                id: currentEvent.id,
                                dermatologist: currentEvent.dermatologist,
                                drugstore: currentEvent.drugstore.name,
                                allDay: false,
                                start: currentEvent.date.substring(0, 10)+"T"+currentEvent.time,
                                
                                
                                displayEventTime: true,
                                duration: {minutes: currentEvent.duration},
                                end: currentEvent.date.substring(0, 10)+"T"+currentEvent.timeEnd,
                                title: currentEvent.patient ? currentEvent.patient.name : "Available",
                            }));
                    })
        
        
    },
    addEmployment: function() {
        axios.get('http://localhost:8081/employment/employment-for-derm', {
                        params: {
                            dermatologistId: 'da9e4ee3-c67c-4511-ad43-82e34d10ddc2'
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
                    .then(() => {
                        console.log(this.currentEvents);
                        for(var emp of this.currentEvents){
                            this.calendarOptions.events.push(
                                {title: emp.drugstore,
                                 daysOfWeek: [1,2,3,4,5],
                                 startTime: emp.workingHoursFrom,
                                 endTime: emp.workingHoursTo,
                                 //allDay: true,
                                }
                            );
                        }
                        }
                    )
        
        
    }
  },
  mounted: function(){
      this.handleEvents();
      this.addEmployment();
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