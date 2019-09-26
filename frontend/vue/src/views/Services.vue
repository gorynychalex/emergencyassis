<template>
  <div>
    <ServiceList />
    <!--<ol><li v-for="item in serviceList" :key="item.id">{{ item }}</li></ol>-->
      <!--<b-table striped hover :items="SERVICES" />-->
      <!--<b-table striped :items="getServices"/>-->

  </div>
</template>

<script>

// import { mapGetters } from 'vuex'

import ServiceList from '@/components/ServiceList.vue'

export default {
  name: 'SocialServices',
  components: {
    ServiceList
  },
  // data: function () {
  //   return {
  //     // services: ''
  //   }
  // },
  computed: {
    // ...mapGetters([
    //         'SERVICES'
    // ])
    // getServices(){ return this.$store.state.itemServiceList }
  },
  mounted() {

  },
  methods: {

  }
}

</script>

<style scoped>

</style>
