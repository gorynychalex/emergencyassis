<template>
  <div class="home">
    <!--<img alt="Vue logo" src="../assets/logo.png">-->
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  },
  mounted() {
    this.$store.dispatch('FETCH_SERVICES', 'service')
    this.$store.dispatch('FETCH_USERS', 'user')
    this.$store.dispatch('FETCH_TASKS', 'task')
  }
}
</script>
