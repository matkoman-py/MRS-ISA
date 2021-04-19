import Vue from 'vue';
import Vuex from 'vuex';
import userModule from './modules/user';
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        userModule
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
    })]
  })
