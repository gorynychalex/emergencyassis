<template>
    <div>
<!--        <UserForm :userinput="user" v-if="buttonaddshow" :usersinnerselect="usersinnerselect" />-->
        <UserForm :userinput="user" v-if="buttonaddshow" :usersinnerselect="usersinnerselect" @event="userforminputevent($event)"/>
<!--        <UserForm :userinput="user" v-if="buttonaddshow" :usersinnerselect="usersinnerselect" />-->


        <b-button v-if="!buttonaddshow" :pressed.sync="buttonaddshow" variant="success">Добавить</b-button>
        <!--<b-button :pressed.sync="buttonaddshow" v-if="buttonaddshow" variant="info">Редактировать</b-button>-->

        <b-button v-if="!buttonaddshow" type="submit" variant="outline-primary" @click="deleteItems">Удалить</b-button>

        User: {{ user.id }} {{ user.nickname }}
        <UserList
                v-if="userlistshow"
                :listitems="userlist"
                :fieldsitem="USERFIELDS"
                @selectuser="userselectevents"
                @usersinnerselect="($event)=>{usersinnerselect=$event}"
        />
        <!--@selectuser="($event)=>{this.user = $event; this.buttonaddshow=true;}"-->
    </div>
</template>

<script>
    import UserList from '@/components/UserList.vue'
    import UserForm from '@/components/UserForm.vue'
    import { mapGetters } from 'vuex'
    import {REMOVE_USERS} from "../store/actions/users";

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

            userforminputevent(item){
                console.log(item)
                if(item == 'done') {
                    this.buttonaddshow = false
                    this.userlist = this.USERS
                }
            },

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
            },

            //@click button delete
            deleteItems(event){
                event.preventDefault()

                if(this.usersinnerselect.length !== 0)
                    this.$store.dispatch(REMOVE_USERS, this.usersinnerselect)
                        .then(()=>this.$router.push('/user'))
                        .catch(e=>console.log(e))

                console.log("Pressed delete button, items for delete: " + this.usersinnerselect)
            },

        },
        watch: {
            usersinnerselect(n){
                console.log("Users come selected: " + n)

            }
        }
    }
</script>

<style scoped>

</style>