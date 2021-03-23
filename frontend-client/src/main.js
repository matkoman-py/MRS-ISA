import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './styles/app.scss'
import './styles/PatientTable.scss'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.config.productionTip = false
Vue.use(BootstrapVue)
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
