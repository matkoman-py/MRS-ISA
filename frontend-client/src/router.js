import Vue from 'vue';
import VueRouter from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";
import EmployeesOverview from "./views/EmployeesOverview"
import PatientOverview from "./views/PatientOverview"
import RegistrationPage from "./views/RegistrationPage"
import AddDrugForm from "./views/AddDrugForm"


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
    },
    {
        path: '/register',
        name: "RegistrationPage",
        component: RegistrationPage
    },

    {
        path: '/add-drug-form',
        name: "Add drug",
        component: AddDrugForm
    }
    ,
    {
        path: '/patientoverview',
        name: "PatientOverview",
        component: PatientOverview
    },
    {
        path: '/employeesOverview',
        name: "EmployeesOverview",
        component: EmployeesOverview
    },
];

const router = new VueRouter({ 
    routes,
});

export default router;