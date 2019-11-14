<template>
    <div>

        <!--<TaskServiceSelect-->
                <!--v-if="taskaddbuttonshow"-->
                <!--@serviceselected="serviceselect($event)"-->
        <!--/>-->
        <!--<div>-->
            <!--Соцработник: {{ selectemployee.nickname }}-->
            <!--Обслуживаемый: {{ selecthardup.nickname }}-->
            <!--<br> Услуга: {{ serviceselected }}-->
        <!--</div>-->


        <b-form @submit="onSubmit" @reset="onReset" v-if="taskaddbuttonshow">
            <b-input-group>
                <b-form-select v-model="serviceselected" :options="SERVICES" :multiple="false" text-field="title" value-field="id"></b-form-select>
                <b-button type="submit" variant="outline-primary">Добавить</b-button>
                <b-button type="reset" variant="danger">Сброс</b-button>
            </b-input-group>
        </b-form>

            <b-button v-if="!taskaddbuttonshow && !taskaddformshow" :pressed.sync="taskaddbuttonshow" variant="success">Добавить</b-button>
            <b-button v-if="!taskaddbuttonshow && !taskaddformshow" :pressed.sync="taskaddformshow" variant="danger">Удалить</b-button>

        <!-- !!! --- FORM TASKS START --- !!! -->
        <b-form @submit="deleteItem">

        <b-form-group >

            <b-button v-if="!taskaddbuttonshow && taskaddformshow" type="submit" variant="outline-primary">Удалить</b-button>

            <!--SHOW SELECTED ITEMS-->
            <!--<br>{{ selectfordone }}-->
            <br>
        <template v-slot:label v-if="taskaddformshow">
            <!--<b>Choose your flavours:</b><br>-->
            <b-form-checkbox
              v-model="selectfordeleteall"
              :indeterminate="indeterminate"
              aria-describedby="tasklist"
              aria-controls="tasklist"
              @change="toggleAllForDelete"
            >
              <!--{{ selectfordeleteall ? 'Un-select All' : 'Select All' }}-->
              {{ selectfordeleteall ? 'Отменить выделение' : 'Выбрать все' }}
            </b-form-checkbox>
        </template>

        <b-table small :items="tasksbyuser(selecthardup)" :fields="TASKSFIELDS_noID">

            <!-- A virtual column -->
            <template slot="index" slot-scope="data">
                {{ data.index+1 }}

                <!--Tag for !DONE! and !SELECT! -->
                <b-form-checkbox-group v-model="selectfordone">
                    <b-form-checkbox

                            :value='data.item.id'
                    >
                    </b-form-checkbox>
                </b-form-checkbox-group>

            </template>

            <template slot="employee" slot-scope="data">

                {{ data.value.firstname }} {{ data.value.middlename }}
            </template>

            <template slot="needy" slot-scope="data">
                {{ data.value.firstname }} {{ data.value.middlename }}
            </template>

            <template slot="socialService" slot-scope="data">
                <span :title='data.value.title'>{{ data.value.title.substring(0, 30) }} ...</span>
            </template>

            <template slot="enable" slot-scope="data">
                <b-button :variant="data.item.enable? 'success':''" @click="enablechange(data.item, data.item.enable)">
                    {{ data.item.enable? "Выполняется":"Выполнено" }}
                </b-button>
                <b-form-checkbox-group v-model="data.item.enable">
                <b-form-checkbox  @change="enablechange(data.item.id, data.item.enable)">

                    {{ data.item.id }} {{ data.item.enable }}

                </b-form-checkbox>
                </b-form-checkbox-group>
            </template>

        </b-table>
        </b-form-group>
        </b-form>
    </div>
</template>

<script>

    import { mapGetters } from 'vuex'
    import {TASK_ADD, TASK_DONE, TASKS_DELETE} from "../store/actions/tasks";
    // import TaskServiceSelect from '@/components/TaskServiceSelect.vue'

    export default {
        name: "TaskComponent",
        props:['tasklist','selectemployee','selecthardup'],
        data(){
            return {
                taskaddbuttonshow: false,
                taskaddformshow: false,
                taskdelselected: '',
                indeterminate: false,
                service: '',
                serviceselected: '',
                selectfordelete: '',
                selectfordeleteall: false,
                selectfordone: '',
            }
        },
        components: {
            // TaskServiceSelect,
        },
        computed: {
            ...mapGetters([
                'SERVICES',
                'TASKS',
                'TASKSFIELDS',
                'TASKSFIELDS_noID'
            ])
        },
        methods: {
            enablechange(task, enable){
                console.log("data.item.id: " + task.id)
                console.log("data.item.enable: " + enable)

                alert(JSON.stringify(task))

                this.$store.dispatch(TASK_DONE, task)
                    .then(()=>{
                        this.$router.push('/task')
                    })
                    .catch(e=>
                        console.log(e)
                    )
                return !enable
            },
            tasksbyuser(user){
                console.log("this.TASKS.length = " + this.TASKS.length)
                console.log("user.id = " + user.id)
                if(this.TASKS.length) {
                    return this.TASKS.filter(x => x.needy.id == user.id)
                }
            },
            onSubmit(event){
                event.preventDefault()

                // console.log("sid = " + this.serviceselected)
                // console.log("userEmployeeId = " + this.selectemployee.id)
                // console.log("userHardupId = " + this.selecthardup.id)

                var servicefull=this.SERVICES[this.SERVICES.findIndex(x=>x.id=this.serviceselected)]

                var datecreate=new Date()

                // var taskitem = {"service":servicefull, "employee":this.selectemployee.id, "needy":this.selecthardup.id}
                var taskitem = {"socialService":servicefull, "employee":this.selectemployee, "needy":this.selecthardup, "dateCreate":datecreate}

                // alert(JSON.stringify(taskitem))

                this.$store.dispatch(TASK_ADD, taskitem)
                    .then(x => taskitem.id = x) // return id by Promise((resolve, ... ))
                    .then(()=>{
                        this.$router.push('/task')
                    })
                    .catch(e => { console.log(e) })

                // this.tasklist.push(taskitem)

                this.taskaddbuttonshow = false

            },
            onReset(event){
                event.preventDefault()
                // console.log(event)
                this.taskaddbuttonshow = false
            },
            serviceselect(item){
                this.service = item
            },
            deleteItem(event){
                event.preventDefault()

                console.log("Delete items: " + this.selectfordone)

                //Delete items
                // First check length items and one-by-one delete

                if(this.selectfordone.length !== 0)
                    this.$store.dispatch(TASKS_DELETE, this.selectfordone)
                        .then(()=>this.$route.push('/task'))
                        .catch(e=>console.log(e))

                    // this.selectfordelete.forEach(x=> {
                    //
                    //     // Delete from UI-list
                    //     this.tasklist.splice(this.tasklist.findIndex(t => t.id == x),1)
                    //
                    //     // Delete from REST API
                    //     fetch('/api/v1/task/' + x, {method: "DELETE"})
                    // })

                // Reset delete mode
                this.taskaddformshow = false

                // Reset select items
                this.selectfordone=[]
            },
            toggleAllForDelete(checked){
                console.log(checked)
                this.selectfordone = checked ? this.tasklist.map(x=>x.id):[]
            }
        },
        watch:{
            // Every select item for delete watch: all selected or not
            selectfordone(newVal, oldVal){
                if(newVal.length === 0) {
                    this.indeterminate = false
                    this.selectfordeleteall = false
                } else if(newVal.length === this.tasklist.length){
                    this.indeterminate = false
                    this.selectfordeleteall = true
                } else {
                    this.indeterminate = true
                    this.selectfordeleteall = false
                }
            }
        }
    }
</script>

<style scoped>

</style>