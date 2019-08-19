<template>
    <b-table striped hover :items="SERVICES" />
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
                'SERVICES'
            ])
            // getServices(){ return this.$store.state.itemServiceList }
        },
    }
</script>

<style scoped>

</style>