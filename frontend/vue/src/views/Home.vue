<template>
  <div class="home">
    <!--<img alt="Vue logo" src="../assets/logo.png">-->
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <FetchDatas/>
  </div>
</template>

<script>
// @ is an alias to /src
import FetchDatas from '@/components/FetchDatas.vue'
import LoginForm from '@/components/LoginForm.vue'

export default {
  name: 'home',
  components: {
    FetchDatas
  },

}
</script>
