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
import store from './store/index'
import Chartkick from 'vue-chartkick'
import Chart from 'chart.js'

Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(BootstrapVueIcons)
Vue.use(vueCountryRegionSelect)
Vue.use(Chartkick.use(Chart))

export const eventBus = new Vue();

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
