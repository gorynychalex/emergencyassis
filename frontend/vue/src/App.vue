<template>
  <div id="app">

    <b-container class="bv-example-row">
      <b-jumbotron>
      <b-row>
        <b-col><img alt="Vue logo" src="./assets/logo_pcson_small.png"></b-col>
        <b-col>Приморский центр социального обслуживания населения</b-col>
      </b-row>
      </b-jumbotron>
    </b-container>

    <LoginForm msg="Введите логин-пароль"/>
  </div>
</template>

<script>
import LoginForm from './components/LoginForm.vue'

export default {
  name: 'app',
  components: {
    LoginForm
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
