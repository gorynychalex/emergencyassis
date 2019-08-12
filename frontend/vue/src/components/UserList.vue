<template>
    <div>
        <!--<p>User list</p>-->
        <b-table striped hover :items="itemUserList"/>
    </div>
</template>

<script>

    import { mapState } from 'vuex'

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
            ])
        },
        mounted() {
            // this.$store.dispatch('FETCH_USERS', 'user')
        },
    }
</script>

<style scoped>

</style>