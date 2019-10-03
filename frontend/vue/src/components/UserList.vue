<template>
    <div>
        <!--<p>User list</p>-->
        <b-table striped hover :items="USERS">

            <template slot="organization" slot-scope="data">

                <span :title="data.value.name">{{ data.value.name }}</span>

            </template>

            <template slot="roles" slot-scope="data">

                <span v-for="i in data.value">{{ i }}, </span>

            </template>

            <template slot="organizations" slot-scope="data">

                <span v-for="i in data.value">{{ i.name }}, </span>

            </template>

            <template slot="users" slot-scope="data">

                <span v-for="i in data.value">{{ i.lastname }}, </span>

            </template>

        </b-table>
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