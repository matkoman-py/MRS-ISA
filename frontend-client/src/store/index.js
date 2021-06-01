import Vue from "vue";
import VueToastr from "vue-toastr";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import helpers from "../helpers";
import userModule from "./modules/user";

Vue.use(Vuex);
Vue.use(VueToastr);

function helperPlugin() {
    Vue.helpers = helpers;
    Vue.prototype.$helpers = helpers;
}

export default new Vuex.Store({
    modules: {
        userModule,
    },
    plugins: [
        createPersistedState({ storage: window.localStorage }),
        helperPlugin,
    ],
});
