import Vue from 'vue';
import Vuex from 'vuex';
import userModule from './modules/user';
import createPersistedState from "vuex-persistedstate";
import helpers from '../helpers';

Vue.use(Vuex);

function helperPlugin () {
    Vue.helpers = helpers;
    Vue.prototype.$helpers = helpers;
}
  

export default new Vuex.Store({
    modules: {
        userModule
    },
    plugins: 
    [
        createPersistedState({ storage: window.sessionStorage }),
        helperPlugin
    ]
  })
