import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import SocialServices from './views/Services.vue'
import Login from './views/Login.vue'
import Users from './views/Users.vue'
import Tasks from './views/Tasks.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
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
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: Users
      },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/task',
      name: 'task',
      component: Tasks
    }
  ]
})
