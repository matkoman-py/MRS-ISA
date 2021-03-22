import Vue from 'vue';
import VueRouter from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";
import PatientOverview from "./views/PatientOverview"
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: "HelloWorld",
        component: HelloWorld
    },
    {
        path: '/test',
        name: "Test",
        component: Test
    }
    ,
    {
        path: '/patientoverview',
        name: "PatientOverview",
        component: PatientOverview
    }
];

const router = new VueRouter({ 
    routes,
});

export default router;