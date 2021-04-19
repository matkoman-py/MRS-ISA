import Vue from 'vue';
import VueRouter from 'vue-router';
import HomePage from "./views/HomePage";
import EmployeesOverview from "./views/EmployeesOverview"
import PatientOverview from "./views/PatientOverview"
import RegistrationPage from "./views/RegistrationPage"
import DrugOverview from "./views/DrugOverview"
import DrugstoreUpdate from "./views/DrugstoreBasicInfoUpdate"
import AddPharmacistForm from "./views/AddPharmacistForm"
import DrugViewSearch from "./views/DrugViewSearch"
import DrugstoreSearch from "./views/DrugstoreSearch"
import EmployeeTable from "./components/EmployeeTable"
import AddDrugstoreForm from "./views/AddDrugstoreForm"
import AddDermatologistForm from "./views/AddDermatologistForm"
import DrugStockOverview from "./views/DrugStockOverview"
import AppointmentsOverview from "./views/AppointmentsOverview"
import SelectDermatologistForm from "./components/forms/SelectDermatologistForm"
import AdminDrugstoreTable from "./components/tables/AdminDrugstoreTable"

import AppointmentDermatologist from "./views/AppointmentDermatologist"
import AppointmentPharmacist from "./views/AppointmentPharmacist"
import PharmDermProfile from "./views/PharmDermProfile"

import DrugstoreView from "./views/DrugstoreView"
import DermatologistAppointments from "./views/DermatologistAppointments"
import PatientProfile from "./views/PatientProfile"
import AdminDrugTable from "./components/tables/AdminDrugTable"
import AdminUserTable from "./components/tables/AdminUserTable"
import PharmacistOverview from "./views/PharmacistOverview"
import DermatologistOverview from "./views/DermatologistOverview"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: "HomePage",
        component: HomePage
    },
    {
        path: '/register',
        name: "RegistrationPage",
        component: RegistrationPage
    },
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
        path: '/SelectDermatologistForm',
        name: "SelectDermatologistForm",
        component: SelectDermatologistForm
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
    {
        path: '/drug-stock-overview',
        name: "DrugStockOverview",
        component: DrugStockOverview
    },
    {
        path: '/appointments-overview',
        name: "AppointmentsOverview",
        component: AppointmentsOverview
    },
    {
        path: '/admin-drugstore-table',
        name: "AdminDrugstoreTable",
        component: AdminDrugstoreTable
    },
    {
        path: '/appointment-dermatologist',
        name: "AppointmentDermatologist",
        component: AppointmentDermatologist
    },
    {
        path: '/drugstores/:id',
        name: "DrugstoreView",
        component: DrugstoreView
    },
    {
        path: '/dermatologist-appointments/:id',
        name: "DeramtologistAppointments",
        component: DermatologistAppointments 
    },
    {
        path: '/appointment-pharmacist',
        name: "AppointmentPharmacist",
        component: AppointmentPharmacist
    },
    {
        path: '/pharm-derm-profile',
        name: "PharmDermProfile",
        component: PharmDermProfile
    },
    {
        path: '/patient/:id',
        name: "PatientProfile",
        component: PatientProfile 
    },
    {  
        path: '/admin-drug-table',
        name: "AdminDrugTable",
        component: AdminDrugTable
    },
    {
        path: '/admin-user-table',
        name: "AdminUserTable",
        component: AdminUserTable
    },
    {
        path: '/pharmacist-overview',
        name: "PharmacistOverview",
        component: PharmacistOverview
    },
    {
        path: '/dermatologist-overview',
        name: "DermatologistOverview",
        component: DermatologistOverview
    },
];

const router = new VueRouter({ 
    routes,
});

export default router;