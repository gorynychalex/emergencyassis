<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/service">Services</router-link> |
      <router-link to="/user">Users</router-link> |
      <router-link to="/task">Tasks</router-link> |
      <router-link to="/about">About</router-link>
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

export default {
  name: 'app',
  components: {

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
