import Vue from "vue";
import VueRouter from "vue-router";
import EmployeeTable from "./components/EmployeeTable";
import SelectDermatologistForm from "./components/forms/SelectDermatologistForm";
import AdminDrugstoreTable from "./components/tables/AdminDrugstoreTable";
import AdminDrugTable from "./components/tables/AdminDrugTable";
import AdminUserTable from "./components/tables/AdminUserTable";
import DrugOrderTable from "./components/tables/DrugOrderTable";
import OfferTable from "./components/tables/OfferTable";
import PatientCategoryTable from "./components/tables/PatientCategoryTable";
import SupplierStockTable from "./components/tables/SupplierStockTable";
import AbsenceRequest from "./views/AbsenceRequest";
import AddDermatologistForm from "./views/AddDermatologistForm";
import AddDrugstoreForm from "./views/AddDrugstoreForm";
import AddPharmacistForm from "./views/AddPharmacistForm";
import AppointmentDermatologist from "./views/AppointmentDermatologist";
import AppointmentPharmacist from "./views/AppointmentPharmacist";
import AppointmentsOverview from "./views/AppointmentsOverview";
import AdminComplaintsView from "./views/complaints/AdminComplaintsView";
import UserComplaintsView from "./views/complaints/UserComplaintsView";
import CreateDrugOrder from "./views/CreateDrugOrder";
import DermatologistAbsenceRequests from "./views/DermatologistAbsenceRequests";
import DermatologistAppointments from "./views/DermatologistAppointments";
import DermatologistOverview from "./views/DermatologistOverview";
import DrugIssuing from "./views/DrugIssuing";
import DrugOrdersOverview from "./views/DrugOrdersOverview";
import DrugOverview from "./views/DrugOverview";
import DrugStockOverview from "./views/DrugStockOverview";
import DrugstoreAdministratorProfile from "./views/DrugstoreAdministratorProfile";
import DrugstoreUpdate from "./views/DrugstoreBasicInfoUpdate";
import DrugstoreSearch from "./views/DrugstoreSearch";
import DrugstoreView from "./views/DrugstoreView";
import DrugViewSearch from "./views/DrugViewSearch";
import EmployeesOverview from "./views/EmployeesOverview";
import ReceiptGeneratorView from "./views/eReceipt/ReceiptGeneratorView";
import ReceiptScannerView from "./views/eReceipt/ReceiptScannerView";
import HomePage from "./views/HomePage";
import LoginPage from "./views/LoginPage";
import MyDrugstore from "./views/MyDrugstore";
import PatientOverview from "./views/PatientOverview";
import PatientProfile from "./views/PatientProfile";
import PharmacistAbsenceRequests from "./views/PharmacistAbsenceRequests";
import PharmacistOverview from "./views/PharmacistOverview";
import PharmDermProfile from "./views/PharmDermProfile";
import RegistrationPage from "./views/RegistrationPage";
import Schedule from "./views/Schedule";
import SchedulePharmacist from "./views/SchedulePharmacist";
import SchedulePharmacistAppointment from "./views/SchedulePharmacistAppointment";
import SubscriptionsView from "./views/SubscriptionsView";
import TreatedPatients from "./views/TreatedPatients";
import PatientProfileView from "./views/PatientProfileView"
import AppointmentPage from "./views/AppointmentPage"
import DrugstoreReports from "./views/DrugstoreReports";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "HomePage",
        component: HomePage,
    },
    {
        path: "/register",
        name: "RegistrationPage",
        component: RegistrationPage,
    },
    {
        path: "/patientoverview",
        name: "PatientOverview",
        component: PatientOverview,
    },
    {
        path: "/employeesOverview",
        name: "EmployeesOverview",
        component: EmployeesOverview,
    },
    {
        path: "/drugs-overview",
        name: "DrugOverview",
        component: DrugOverview,
    },
    {
        path: "/drugstoreUpdate",
        name: "drugstoreUpdate",
        component: DrugstoreUpdate,
    },
    {
        path: "/addPharmacistForm",
        name: "addPharmacistForm",
        component: AddPharmacistForm,
    },
    {
        path: "/AddDermatologistForm",
        name: "AddDermatologistForm",
        component: AddDermatologistForm,
    },
    {
        path: "/SelectDermatologistForm",
        name: "SelectDermatologistForm",
        component: SelectDermatologistForm,
    },
    {
        path: "/drugSearch",
        name: "DrugSearch",
        component: DrugViewSearch,
    },
    {
        path: "/drugstoreSearch",
        name: "DrugstoreSearch",
        component: DrugstoreSearch,
    },
    {
        path: "/employeeprofiles",
        name: "EmployeeProfiles",
        component: EmployeeTable,
    },
    {
        path: "/add-drugstore-form",
        name: "AddDrugstoreForm",
        component: AddDrugstoreForm,
    },
    {
        path: "/drug-stock-overview",
        name: "DrugStockOverview",
        component: DrugStockOverview,
    },
    {
        path: "/appointments-overview",
        name: "AppointmentsOverview",
        component: AppointmentsOverview,
    },
    {
        path: "/admin-drugstore-table",
        name: "AdminDrugstoreTable",
        component: AdminDrugstoreTable,
    },
    {
        path: "/appointment-dermatologist",
        name: "AppointmentDermatologist",
        component: AppointmentDermatologist,
        props: true,
    },
    {
        path: "/drugstores/:id",
        name: "DrugstoreView",
        component: DrugstoreView,
    },
    {
        path: "/dermatologist-appointments/:id",
        name: "DeramtologistAppointments",
        component: DermatologistAppointments,
    },
    {
        path: "/appointment-pharmacist",
        name: "AppointmentPharmacist",
        component: AppointmentPharmacist,
        props: true,
    },
    {
        path: "/pharm-derm-profile",
        name: "PharmDermProfile",
        component: PharmDermProfile,
    },
    {
        path: "/patient/",
        name: "PatientProfile",
        component: PatientProfile,
    },
    {
        path: "/admin-drug-table",
        name: "AdminDrugTable",
        component: AdminDrugTable,
    },
    {
        path: "/admin-user-table",
        name: "AdminUserTable",
        component: AdminUserTable,
    },
    {
        path: "/login",
        name: "LoginPage",
        component: LoginPage,
    },
    {
        path: "/schedule-appointment",
        name: "ScheduleAppointment",
        component: SchedulePharmacistAppointment,
    },
    {
        path: "/schedule",
        name: "Schedule",
        component: Schedule,
    },
    {
        path: "/schedule-pharm",
        name: "SchedulePharmacist",
        component: SchedulePharmacist,
    },
    {
        path: "/pharmacist-overview",
        name: "PharmacistOverview",
        component: PharmacistOverview,
    },
    {
        path: "/dermatologist-overview",
        name: "DermatologistOverview",
        component: DermatologistOverview,
    },
    {
        path: "/supplier-stock",
        name: "SupplierStockTable",
        component: SupplierStockTable,
    },
    {
        path: "/supplier-orders",
        name: "DrugOrderTable",
        component: DrugOrderTable,
    },
    {
        path: "/supplier-offers",
        name: "OfferTable",
        component: OfferTable,
    },
    {
        path: "/treated",
        name: "Treated",
        component: TreatedPatients,
    },
    {
        path: "/receipt-generator",
        name: "ReceiptGeneratorView",
        component: ReceiptGeneratorView,
    },
    {
        path: "/receipt-scanner",
        name: "ReceiptScannerView",
        component: ReceiptScannerView,
    },
    {
        path: "/subscriptions",
        name: "SubscriptionsView",
        component: SubscriptionsView,
    },
    {
        path: "/create-drug-order",
        name: "CreateDrugOrder",
        component: CreateDrugOrder,
    },
    {
        path: "/drug-issuing",
        name: "DrugIssuing",
        component: DrugIssuing,
    },
    {
        path: "/absence-request",
        name: "AbsenceRequest",
        component: AbsenceRequest,
    },
    {
        path: "/drug-orders-overview",
        name: "DrugOrdersOverview",
        component: DrugOrdersOverview,
    },
    {
        path: "/drugstore-administrator-profile",
        name: "DrugstoreAdministratorProfile",
        component: DrugstoreAdministratorProfile,
    },
    {
        path: "/my-drugstore",
        name: "MyDrugstore",
        component: MyDrugstore,
    },
    {
        path: "/admin-patient-categories",
        name: "Patient Categories",
        component: PatientCategoryTable,
    },
    {
        path: "/admin-complaints",
        name: "Admin Complaints View",
        component: AdminComplaintsView,
    },
    {
        path: "/patient-complaints",
        name: "Patient Complaints View",
        component: UserComplaintsView,
    },
    {
        path: "/pharmacist-absence-requests",
        name: "PharmacistAbsenceRequests",
        component: PharmacistAbsenceRequests,
    },
    {
        path: "/dermatologist-absence-requests",
        name: "DermatologistAbsenceRequests",
        component: DermatologistAbsenceRequests,
    },
    {
        path: "/patient-profile-view",
        name: "PatientProfileView",
        component: PatientProfileView,
        props: true,
    },
    {
        path: "/appointment-page",
        name: "AppointmentPage",
        component: AppointmentPage,
    },
    {
        path: "/drugstore-reports",
        name: "DrugstoreReports",
        component: DrugstoreReports,
    },
];

const router = new VueRouter({
    routes,
});

export default router;
