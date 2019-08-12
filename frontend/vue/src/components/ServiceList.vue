<template>
    <b-table striped hover :items="SERVICES" />
</template>

<script>

    import { mapGetters } from 'vuex'

    export default {
        name: "ServiceList",
        computed: {
            ...mapGetters([
                'SERVICES'
            ])
            // getServices(){ return this.$store.state.itemServiceList }
        },
    }
</script>

<style scoped>

</style>