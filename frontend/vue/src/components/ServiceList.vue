<template>
    <b-table striped :items="SERVICES" :fields="SERVICESFIELD" />
</template>

<script>

    import { mapState } from 'vuex'
    import { mapGetters } from 'vuex'

    export default {
        name: "ServiceList",
        computed: {
            ...mapState([
                'itemServiceList'
            ]),
            ...mapGetters([
                'SERVICES', 'SERVICESFIELD'
            ])
            // getServices(){ return this.$store.state.itemServiceList }
        },
    }
</script>

<style scoped>

</style>