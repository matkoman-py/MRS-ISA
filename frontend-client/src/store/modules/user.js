import VueAxios from "../../config/VueAxios";
import router from "../../router";

const state = () => ({
    loggedInUser: null,
    role: ''
});

const getters = {
    getLoggedInUser(state) {
        return state.loggedInUser;
    },

    getLoggedInUserRole(state, getters) {
        return getters.getLoggedInUser().role;
    }
}

const mutations = {
    setLoggedInUser(state, user) {
        state.loggedInUser = user;
    },
    setLoggedInUserRole(state, {role}) {
        state.role = role;
    },
    logout(state) {
        state.role = '';
        state.loggedInUser = null;
        localStorage.removeItem("token");
        sessionStorage.clear();
    },
}

const actions = {
    login({ commit }, credentials) {    
        VueAxios.post("http://localhost:8081/auth/login", credentials)
                .then(response => {
                    localStorage.setItem('token', response.data.accessToken);
                    commit('setLoggedInUser', response.data.user);
                    router.push('/');
                })
                .catch(error => console.log(error));
    },
    logout({ commit }) {
        commit('logout');
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
  }