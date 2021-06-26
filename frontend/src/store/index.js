import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

const store = new Vuex.Store({
  plugins: [createPersistedState({ storage: window.sessionStorage })],
  state: {
    jwt: "",
    username: ""
  },
  mutations: {
    'SET_JWT'(state, jwt) { state.jwt = jwt },
    'SET_USERNAME'(state, username) { state.username = username }
  },
  actions: {
    setJwt({ commit }, jwt) { commit("SET_JWT", jwt) },
    setUsername({ commit }, username) { commit("SET_USERNAME", username) }
  },
  getters: {
    getJwt: (state) => state.jwt,
    getUsername: (state) => state.username
  },
})

export default store;