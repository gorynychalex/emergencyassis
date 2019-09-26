<template>
    <div>
        <UserForm v-if="buttonaddshow"/>
        <b-button :pressed.sync="buttonaddshow" variant="success">Добавить пользователя</b-button>
        <UserList/>
    </div>
</template>

<script>
    import UserList from '@/components/UserList.vue'
    import UserForm from '@/components/UserForm.vue'
    export default {
        name: "Users",
        data(){
            return {
                buttonaddshow: false
            }
        },
        components: {
            UserList,
            UserForm
        }
    }
</script>

<style scoped>

</style>