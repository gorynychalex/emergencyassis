<template>
  <div>
    <ol><li v-for="item in services" :key="item.id">{{ item }}</li></ol>
  </div>
</template>

<script>

export default {
  name: 'Service',
  data: function () {
    return {
      services: ''
    }
  },
  created: function () {
    this.getServices()
  },
  methods: {
    getServices: function () {
      fetch('/api/v1/service').then(r => r.json()).then(r => { this.services = r })
    }
  }
}

</script>

<style scoped>

</style>
