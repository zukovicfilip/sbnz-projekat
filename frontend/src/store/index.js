import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const store = new Vuex.Store({
  plugins: [createPersistedState({ storage: window.sessionStorage })],
  state: {
    jwt: "",
    id: "",
    email: "",
    role: ""
  },
  mutations: {
    'SET_JWT'(state, jwt) { state.jwt = jwt },
    'SET_ID'(state, id) { state.id = id },
    'SET_EMAIL'(state, email) { state.email = email },
    'SET_ROLE'(state, role) { state.role = role }
  },
  actions: {
    setJwt({ commit }, jwt) { commit("SET_JWT", jwt) },
    setId({ commit }, id) { commit("SET_ID", id) },
    setEmail({ commit }, email) { commit("SET_EMAIL", email) },
    setRole({ commit }, role) { commit("SET_ROLE", role) }
  },
  getters: {
    getJwt: (state) => state.jwt,
    getId: (state) => state.id,
    getEmail: (state) => state.email,
    getRole: (state) => state.role
  },
})

export default store;