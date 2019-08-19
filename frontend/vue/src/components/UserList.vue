<template>
    <div>
        <!--<p>User list</p>-->
        <b-table striped hover :items="USERS"/>
    </div>
</template>

<script>

    import { mapState } from 'vuex'
    import { mapGetters } from 'vuex'

    export default {
        name: "UserList",
        data(){
            return{
                // itemList
            }
        },
        computed: {
            ...mapState([
                'itemUserList'
            ]),
            ...mapGetters([
                'USERS'
            ])
        },
        mounted() {
            // this.$store.dispatch('FETCH_USERS', 'user')
        },
    }
</script>

<style scoped>

</style>