import Vue from 'vue';
import VueRouter from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";
import EmployeesOverview from "./views/EmployeesOverview"
import PatientOverview from "./views/PatientOverview"
import RegistrationPage from "./views/RegistrationPage"
import AddDrugForm from "./views/AddDrugForm"
import DrugOverview from "./views/DrugOverview"
<<<<<<< HEAD
import DrugstoreUpdate from "./views/DrugstoreBasicInfoUpdate"
import AddPharmacistForm from "./views/AddPharmacistForm"
import DrugViewSearch from "./views/DrugViewSearch"
import DrugstoreSearch from "./views/DrugstoreSearch"
=======
import EmployeeTable from "./components/EmployeeTable"
>>>>>>> e3734a5fc1a36937dc6773c1e0f719d8b582f357

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
    {
        path: '/drugs-overview',
        name: "DrugOverview",
        component: DrugOverview
    },
    {
<<<<<<< HEAD
        path: '/drugstoreUpdate',
        name: "drugstoreUpdate",
        component: DrugstoreUpdate
    },
    {
        path: '/addPharmacistForm',
        name: "addPharmacistForm",
        component: AddPharmacistForm
    },
    {
        path:'/drugSearch',
        name: "DrugSearch",
        component: DrugViewSearch
    },
    {
        path: '/drugstoreSearch',
        name: "DrugstoreSearch",
        component: DrugstoreSearch
    }

=======
        path: '/employeeprofiles',
        name: "EmployeeProfiles",
        component: EmployeeTable
    },
>>>>>>> e3734a5fc1a36937dc6773c1e0f719d8b582f357
];

const router = new VueRouter({ 
    routes,
});

export default router;