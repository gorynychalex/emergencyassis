<template>
    <div>
        <h3>Социальные работники</h3>
        <UserList species="EMPLOYEE"/>
        <h3>Обслуживаемые</h3>
        <UserList species="HARDUP"/>
        <TaskComponent/>
    </div>
</template>

<script>
    import TaskComponent from '@/components/TaskComponent.vue'
    import UserList from '@/components/UserListForChoice.vue'
    export default {
        name: "Tasks",
        components: {
            TaskComponent, UserList
        }
    }
</script>

<style scoped>

</style>