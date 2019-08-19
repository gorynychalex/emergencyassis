import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import SocialServices from '../views/Services.vue'
import Account from '../components/account'
import Login from '../views/Login.vue'
import Users from '../views/Users.vue'
import Tasks from '../views/Tasks.vue'
import store from '../store'

Vue.use(Router)

const ifNotAuthenticated = (to, from, next) => {
  if (!store.getters.isAuthenticated) {
    next()
    return
  }
  next('/')
}

const ifAuthenticated = (to, from, next) => {
  if (store.getters.isAuthenticated) {
    next()
    return
  }
  next('/login')
}

const ifLoadedDatas = (to, from, next) => {
  if(!store.getters.hasLoadedServices){
    console.log('here load datas')
    store.dispatch('FETCH_SERVICES', 'service')
    // store.dispatch('FETCH_USERS', 'user')
    // store.dispatch('FETCH_TASKS', 'task')

    next()
    return
  }
  next('/home')
}

const afterLogin = (to, from, next) => {
  if(store.getters.isAuthenticated){
    console.log('forward to TASKS')
    next()
    return
  }
  next('/task')
}

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'root',
      beforeEnter: ifLoadedDatas
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/service',
      name: 'service',
      component: SocialServices
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: Users
      },
    {
      path: '/login',
      name: 'login',
      component: Login,
      beforeEnter: ifNotAuthenticated,
      afterEnter: afterLogin
    },
    {
      path: '/account',
      name: 'Account',
      component: Account,
      beforeEnter: ifAuthenticated,
    },
    {
      path: '/task',
      name: 'task',
      component: Tasks
    }
  ]
})
