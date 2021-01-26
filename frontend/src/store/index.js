import Vue from "vue";
import Vuex from "vuex";
// import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    server: SERVER_URL,
    accessToken: null,
    uid: "",
    email: "",
    name: "",
    createDate: "",
    code: "",
  },
  getters: {
    getServer(state) { 
      return state.server;
    },
    getAccessToken(state) {
      return state.accessToken;
    },
    getUid(state) { 
      return state.uid;
    },
    getEmail(state) {
      return state.email;
    },
    getName(state) {
      return state.name;
    },
    getCreateDate(state) {
      return state.createDate;
    },
    getCode(state) { 
      return state.code;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.uid = payload["uid"];
      state.accessToken = payload["authorizationToken"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.createDate = payload["createDate"];
    },
    LOGOUT(state) {
      state.uid = "";
      state.accessToken = null;
      state.email = "";
      state.name = "";
      state.createDate = "";
    },
    SETINFO(state, payload) { 
      state.name = payload["name"];
      state.email = payload["email"];
    },
    SETCODE(state, payload) { 
      state.code = payload;
    },
    SETEMAIL(state, payload) { 
      state.email = payload;
    }
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/login`, user)
        .then((response) => {
          console.log(response.data)
          context.commit("LOGIN", response.data);
          if(`${response.data["authorizationToken"]}` == "undefined") reject();
          axios.defaults.headers.common["auth-token"] = `${response.data["authorizationToken"]}`;
          sessionStorage.setItem('auth-token', `${response.data["authorizationToken"]}`)
        })
        .catch(() => {
          reject();
        });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
      sessionStorage.removeItem('auth-token');
    },
    SETINFO(context, user) {
      context.commit("SETINFO", user);
    },
    SETCODE(context, code) { 
      context.commit("SETCODE", code);
    },
    SETEMAIL(context, email) {
      context.commit("SETEMAIL", email);
    },
  },
  modules: {}
});