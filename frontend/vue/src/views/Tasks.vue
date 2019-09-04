<template>
    <div>
        <UserList role="EMPLOYEE" :users="USERS" @selectuser="($event)=>this.selectemployee=$event"/>
        Социальный работник: {{ selectemployee }}

        <UserList role="HARDUP" :users="userslist" @selectuser="($event)=>this.selecthardup=$event" v-if="userslist.length"/>
        Обслуживаемый: {{ selecthardup }}



        <br> Планируется предоставить услуг: {{ tasklist? tasklist.length : '' }}
        <TaskComponent :tasklist="tasklist" v-if="selecthardup"/>
    </div>
</template>

<script>
    import TaskComponent from '@/components/TaskComponent.vue'
    import UserList from '@/components/UserListForChoice.vue'
    import { mapGetters } from 'vuex'

    export default {
        name: "Tasks",
        data() {
            return {
                userslist: '',
                selectemployee: '',
                selecthardup: '',
                tasklist: ''
            }
        },
        computed: {
            ...mapGetters([
                'USERS',
                'TASKS'
            ]),
        },
        components: {
            UserList,
            TaskComponent
        },
        watch: {
            selectemployee: function(newVal, oldVal) {
                this.userslist=this.USERS[this.USERS.findIndex(x=>x.nickname==newVal)].users
                if(!this.userslist.length){
                    this.selecthardup = ''
                    this.tasklist=''
                }
            },
            selecthardup: function (newVal, oldVal) {
                if(newVal)
                this.tasklist = this.TASKS.filter(x=>x.needy.nickname == newVal)
            }
        }
    }
</script>

<style scoped>

</style>