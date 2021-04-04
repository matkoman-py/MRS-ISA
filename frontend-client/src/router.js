import Vue from 'vue';
import VueRouter from 'vue-router';
import HomePage from "./views/HomePage";
import Test from "./components/Test";
import EmployeesOverview from "./views/EmployeesOverview"
import PatientOverview from "./views/PatientOverview"
import RegistrationPage from "./views/RegistrationPage"
import AddDrugForm from "./views/AddDrugForm"
import DrugOverview from "./views/DrugOverview"
import DrugstoreUpdate from "./views/DrugstoreBasicInfoUpdate"
import AddPharmacistForm from "./views/AddPharmacistForm"
import DrugViewSearch from "./views/DrugViewSearch"
import DrugstoreSearch from "./views/DrugstoreSearch"
import EmployeeTable from "./components/EmployeeTable"
import AddDrugstoreForm from "./views/AddDrugstoreForm"
import AddDermatologistForm from "./views/AddDermatologistForm"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: "HomePage",
        component: HomePage
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
        path: '/AddDermatologistForm',
        name: "AddDermatologistForm",
        component: AddDermatologistForm
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
    },
    {
        path: '/employeeprofiles',
        name: "EmployeeProfiles",
        component: EmployeeTable
    },
    {
        path: '/add-drugstore-form',
        name: "AddDrugstoreForm",
        component: AddDrugstoreForm
    },
];

const router = new VueRouter({ 
    routes,
});

export default router;