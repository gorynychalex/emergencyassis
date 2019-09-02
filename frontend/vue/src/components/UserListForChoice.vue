<template>
    <div>
        <ul>
            <li v-for="item in USERS" v-if="item.role == species">
                {{ item.nickname }} {{ item.firstname }} {{ item.middlename }}
            </li>

            <b-form-radio-group v-model="selected" :options="USERS" button stacked name="radio-btn-stacked"/>
        </ul>
    </div>
</template>

<script>

    import { mapGetters } from 'vuex'

    export default {
        name: "UserListForChoice",
        props: ['species'],
        data(){
            return {
                selected: 'selectUser'
            }
        },
        computed: {
            ...mapGetters([
                'USERS'
            ])
        }
    }
</script>

<style scoped>

</style>