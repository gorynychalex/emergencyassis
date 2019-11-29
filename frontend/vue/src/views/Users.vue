<template>
    <div>
        <UserForm :userinput="user" v-if="buttonaddshow" :usersinnerselect="usersinnerselect" @event="($event)=>{userlist=USERS;}"/>
        <b-button :pressed.sync="buttonaddshow" variant="success">Добавить</b-button>
        <!--<b-button :pressed.sync="buttonaddshow" v-if="buttonaddshow" variant="info">Редактировать</b-button>-->
        User: {{ user.id }} {{ user.nickname }}
        <UserList
                v-if="userlistshow"
                :listitems="userlist"
                :fieldsitem="USERFIELDS"
                @selectuser="userselectevents"
                @usersinnerselect="($events)=>{usersinnerselect=$events}"
        />
        <!--@selectuser="($event)=>{this.user = $event; this.buttonaddshow=true;}"-->
    </div>
</template>

<script>
    import UserList from '@/components/UserList.vue'
    import UserForm from '@/components/UserForm.vue'
    import { mapGetters } from 'vuex'

    export default {
        name: "Users",
        data(){
            return {
                buttonaddshow: false,
                user: '',
                userlist: [],
                userlistshow: true,
                usersinnerselect: [],
            }
        },
        mounted(){
            this.userlist=this.USERS
        },
        computed: {
            ...mapGetters([
                'USERS',
                'USERFIELDS'
            ])
        },
        components: {
            UserList,
            UserForm
        },
        methods: {

            userselectevents(item){
                this.user = item;
                this.buttonaddshow=true;
                if(item.role == 'EMPLOYEE'){
                    this.userlist = this.USERS.filter(u=>u.role == 'HARDUP')
                } else if (item.role == 'SPECIALIST'){
                    this.userlist  = this.USERS.filter(u=>u.role == 'EMPLOYEE')
                } else if (item.role == 'ANALYST'){
                    this.userlist = this.USERS.filter(u=>u.role == 'SPECIALIST')
                } else if (item.role == 'HARDUP'){
                    this.userlist = []
                } else {
                    this.userlist = this.USERS
                }
                //
                // if(this.userlist.length == 0){
                //     this.userlist = this.USERS
                // }
            }

        },
        watch: {
            usersinnerselect(n,o){
                console.log("Users come selected: " + n)
            }
        }
    }
</script>

<style scoped>

</style>