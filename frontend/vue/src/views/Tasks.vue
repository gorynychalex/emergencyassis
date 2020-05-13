<template>
    <div>
        <b-container>
            <b-row>
                <b-col>
                    <UserList
                            role="HARDUP"
                            title="Облуживаемые"
                            :users="userslist"
                            @selectuser="($event)=>this.selecthardup=$event"
                            v-if="userslist.length"
                    />
                </b-col>
                <b-col>
                    <UserList
                            role="EMPLOYEE"
                            title="Социальные работники"
                            :users="USERS"
                            @selectuser="($event)=>this.selectemployee=$event"
                            v-if="!onlyemployee"
                    />
<!--                    {{ selectemployee? "Выбран: " + selectemployee.lastname : "Никто не выбран" }}-->
                </b-col>
            </b-row>
        </b-container>

        <b-card
            header="Обслуживаемый"
            :title="selecthardupfullname"
            v-if="selecthardup"
        >
        <!--<h3> {{ selecthardupfullname? selecthardupfullname : '' }}</h3>-->
        <!--<br>  {{ tasklist? "Планируется предоставить услуг: " + tasklist.length : '' }}-->

        <TaskComponent
                v-if="selecthardup"
                :tasklist="tasklist"
                :selectemployee="selectemployee"
                :selecthardup="selecthardup"
        />
        </b-card>

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
                // userslist: this.getProfile.role === 'EMPLOYEE' && this.getProfile.users? this.getProfile.users : [],
                userslist: [],
                // selectemployee: this.getProfile.role == 'EMPLOYEE'? this.getProfile : '',
                selectemployee: '',
                selecthardup: '',
                selecthardupfullname: '',
                tasklist: '',
                // onlyemployee: this.getProfile.role ==='EMPLOYEE' ? true: false,
                onlyemployee: false,
            }
        },
        computed: {
            ...mapGetters([
                'USERS',
                'TASKS',
                'getProfile'
            ]),
        },
        mounted() {
            console.log("profile: " + this.getProfile.nickname)
            this.onlyemployee = this.getProfile.role === 'EMPLOYEE'? true : false
            this.selectemployee = this.getProfile.role == 'EMPLOYEE'? this.getProfile : ''
            this.userslist = this.getProfile.role === 'EMPLOYEE' && this.getProfile.users? this.getProfile.users : []
        },
        components: {
            UserList,
            TaskComponent,
        },
        watch: {
            selectemployee: function(newVal) {
                this.userslist=this.USERS[this.USERS.findIndex(x=>x.id==newVal.id)].users
                if(!this.userslist.length){
                    this.selecthardup = ''
                    this.tasklist=''
                }
            },
            selecthardup: function (newVal) {
                if(newVal) {
                    // Replace to Vuex getters
                    // this.tasklist = this.TASKS.filter(x => x.needy.id == newVal.id)
                    let user = this.USERS[this.USERS.findIndex(x => x.id == newVal.id)]
                    this.selecthardupfullname = user.firstname + " " + user.lastname + " " + user.middlename
                }
            }
        }
    }
</script>

<style scoped>

</style>