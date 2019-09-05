<template>
  <div id="app">
    <div id="nav">
      <router-link to="/home">Главная</router-link> |
      <router-link to="/service">Тарифы</router-link> |
      <span v-if="isAuthenticated">
        <router-link to="/user">Пользователи</router-link> |
        <router-link to="/task">Услуги</router-link> |
      </span>

      <router-link to="/about">Журнал</router-link> |

      <span v-if="!isAuthenticated">
        <router-link to="/login">Login</router-link>
      </span>

      <span v-if="isAuthenticated" @click="logout">
        <router-link to="/logout">Выход</router-link>
      </span>

    </div>

    <div v-if="isAuthenticated">
      <Account/>
    </div>

    <b-container class="bv-example-row">
      <b-jumbotron>
        <b-row>
          <b-col><img alt="Vue logo" src="./assets/logo_pcson_small.png"></b-col>
          <b-col>Приморский центр социального обслуживания населения</b-col>
        </b-row>
      </b-jumbotron>
    </b-container>

    <b-container><router-view/></b-container>


  </div>
</template>

<script>

  import { mapGetters } from 'vuex'
  import {AUTH_LOGOUT} from "./store/actions/auth"
  import Account from './components/account'

export default {
  name: 'app',
  components: {
    Account
  },
  computed: {
    // isLoggedIn: ()=>{
    //   this.$store.state.isAuthenticated
    ...mapGetters([
      'isAuthenticated'
    ])
  },
  methods: {
    logout: function () {
      this.$store.dispatch(AUTH_LOGOUT).then(() => this.$router.push('/login'))
    }
  }
}
</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
