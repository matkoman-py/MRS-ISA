import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './styles/app.scss'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { BootstrapVue, IconsPlugin, BootstrapVueIcons } from 'bootstrap-vue'
import vueCountryRegionSelect from 'vue-country-region-select'
import axios from './config/VueAxios'


Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(BootstrapVueIcons)
Vue.use(vueCountryRegionSelect)

export const eventBus = new Vue();

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
