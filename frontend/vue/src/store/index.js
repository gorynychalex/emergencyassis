import Vue from 'vue'
import Vuex from 'vuex'

import auth from './modules/auth'
import profile from './modules/profile'
import users from './modules/users'
import services from './modules/services'
import tasks from './modules/tasks'

Vue.use(Vuex)

export default new Vuex.Store({

  strict: process.env.NODE_ENV !== 'production',

  modules: {
    auth,
    profile,
    users,
    services,
    tasks,
  }
})
