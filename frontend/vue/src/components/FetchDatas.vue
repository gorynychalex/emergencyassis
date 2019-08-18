<template>
    
</template>

<script>
    export default {
        name: "FetchDatas",
        mounted() {
            this.$store.dispatch('FETCH_SERVICES', 'service')
            this.$store.dispatch('FETCH_USERS', 'user')
            this.$store.dispatch('FETCH_TASKS', 'task')
        }
    }
</script>

<style scoped>

</style>