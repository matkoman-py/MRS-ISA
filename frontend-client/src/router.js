import Vue from 'vue';
import VueRouter from 'vue-router';
import HelloWorld from "./components/HelloWorld";
import Test from "./components/Test";
import RegistrationPage from "./views/RegistrationPage"
import vueCountryRegionSelect from 'vue-country-region-select'
import { BootstrapVue,BootstrapVueIcons  } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVueIcons)
Vue.use(BootstrapVue)
Vue.use(vueCountryRegionSelect)
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
    }
];

const router = new VueRouter({ 
    routes,
});

export default router;