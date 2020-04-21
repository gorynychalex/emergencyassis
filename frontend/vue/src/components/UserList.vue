<template>
    <div>
        <!--<p>User list</p>-->
        <b-table striped hover :items="listitems" :fields="fieldsitem">

            <!-- A virtual column -->
            <template v-slot:cell(id)="data">
<!--                {{ data.index+1 }}-->

                <!--Tag for !DONE! and !SELECT! -->
                <b-form-checkbox-group v-model="usersselect" >
                    <b-form-checkbox
                            :value='data.item'
                    >
                    </b-form-checkbox>
                </b-form-checkbox-group>

            </template>

            <template slot="organization" slot-scope="data">
                <span :title="data.value.name">{{ data.value.name }}</span>
            </template>

            <template v-slot:cell(role)="data">
                 {{ data.value == 'SPECIALIST' ? "Специалист" : data.value == 'EMPLOYEE' ? "Соц.работник" : data.value == 'HARDUP' ? "Обслуживаемый" : "другой" }}
            </template>

            <template slot="roles" slot-scope="data">
                <span v-for="i in data.value" :key="i.id">{{ i }}, </span>
            </template>

            <template slot="organizations" slot-scope="data">

                <span v-for="i in data.value" :key="i.id">{{ i.name }}, </span>

            </template>

<!--            <template slot="users" slot-scope="data">-->
<!--                <span v-for="(usr,index) in data.value" :key="usr.id">{{ index }} - {{ usr.id }}, </span>-->
<!--            </template>-->

            <template v-slot:cell(users)="data">
                <span v-for="(usr) in data.value" :key="usr.id">{{ usr.lastname }}<br /> </span>
            </template>

            <!--Virtual slot-->
            <template v-slot:cell(edit)="data">

                <!--<b-button variant="success" @click="$emit('selectuser',data.item)">Редактировать</b-button>-->
                <b-button @click="selectuserforedit(data.item)" variant="success">Редактировать</b-button>
                <b-button @click="deleteItem(data.item)" variant="danger">Удалить</b-button>

                {{ data.item.id }}

            </template>

        </b-table>
    </div>
</template>

<script>

    import { mapState } from 'vuex'
    import { mapGetters } from 'vuex'

    export default {
        name: "UserList",
        props: {
            listitems: Array,
            fieldsitem: Array,
        },
        data(){
            return{
                userselect: '',
                usersselect: [],
                userlistfiltered: this.userlist,
                selectrole: ''
                // itemList
            }
        },
        computed: {
            ...mapState([
                'itemUserList',
                'fieldsUser'
            ]),
            ...mapGetters([
                'USERS',
                'USERFIELDS'
            ])
        },
        mounted() {
            // this.$store.dispatch('FETCH_USERS', 'user')
        },
        methods: {
            userlistfilterbyrole(){
                // this.userlistfiltered = this.userlist.filter(x=>x.role==role)
            },
            // Edit user
            selectuserforedit(item){
                // Emit throw
                this.$emit('selectuser',item)


                this.usersselect = item.users

                this.role = item.role
                console.log("item.role : " + item.role)
                if(item.role == 'EMPLOYEE'){
                    this.userlistfilterbyrole('HARDUP')
                } else if (item.role == 'SPECIALIST'){
                    this.userlistfilterbyrole('EMPLOYEE')
                }
                else {
                    this.userlistfiltered=this.userlist
                }
            },
            deleteItem(item){
                console.log("delete item " + item)
            },
        },
        watch: {
            usersselect(newVal){
                // newVal.forEach(x=>console.log(x.id))
                // console.log('usersSelect: ' + newVal.length)
                this.$emit('usersinnerselect',newVal)
            }
        },
    }
</script>

<style scoped>

</style>