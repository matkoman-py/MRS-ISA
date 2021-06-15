import VueAxios from "../../config/VueAxios";
import router from "../../router";

const state = () => ({
    loggedInUser: null,
    error: "",
    role: "",
});

const getters = {
    getLoggedInUser(state) {
        return state.loggedInUser;
    },

    getLoggedInUserRole(state, getters) {
        return getters.getLoggedInUser().role;
    },
};

const mutations = {
    setLoggedInUser(state, user) {
        state.loggedInUser = user;
    },
    setLoggedInUserRole(state, { role }) {
        state.role = role;
    },
    logout(state) {
        state.role = "";
        state.loggedInUser = null;
        localStorage.removeItem("token");
        sessionStorage.clear();
    },
    setError(state, error) {
        state.error = error;
    },
};

const actions = {
    login({ commit }, credentials) {
        VueAxios.post("https://mrs-isa-usijani.herokuapp.com/auth/login", credentials)
            .then((response) => {
                localStorage.setItem("token", response.data.accessToken);
                commit("setLoggedInUser", response.data.user);
                commit("setError", "");
                if (response.data.user.firstLogin && (response.data.user.type == 'DrugstoreAdmin' || response.data.user.type == 'SystemAdmin' || response.data.user.type == 'Supplier' 
                || response.data.user.type == 'Dermatologist' || response.data.user.type == 'Pharmacist')) {
                    router.push("/first-login");
                } else {
                    router.push("/");
                }
            })
            .catch((error) => {
                if (error.response.status == "401") {
                    commit(
                        "setError",
                        "Failed to login, wrong password/username"
                    );
                } else {
                    commit("setError", error.response.data);
                }
            });
    },
    logout({ commit }) {
        commit("logout");
    },
};

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
