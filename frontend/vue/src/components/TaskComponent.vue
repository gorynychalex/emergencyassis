<template>
    <div>
        <b-table striped :items="TASKS"/>
    </div>
</template>

<script>

    import { mapGetters } from 'vuex'

    export default {
        name: "TaskComponent",
        computed: {
            ...mapGetters([
                'TASKS'
            ])
        }
    }
</script>

<style scoped>

</style>