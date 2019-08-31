<template>
    <div>
        <b-table small :items="TASKS" :fields="TASKSFIELDS" caption-top >

            <template slot="[socialService]" slot-scope="data">
                {{ data.cityprice }}
            </template>

            <template slot="[needy]" slot-scope="data">
                {{ data.firstname }} {{ data.middlename }}
            </template>

        </b-table>

        <!--<table border="1">-->
            <!--<tr>-->
                <!--<th v-for="fieldTask in TASKSFIELDS">-->
                    <!--{{ fieldTask.label }}-->
                <!--</th>-->
            <!--</tr>-->

            <!--<tr v-for="item in TASKS" :key="item.id" v-if="item.enable === true">-->
                <!--<td>-->
                    <!--{{ item.id }}-->
                <!--</td>-->
                <!--<td>-->
                    <!--<span :title='item.socialService.title'>{{ item.socialService.title.substring(0, 30) }} ...</span>-->
                <!--</td>-->
                <!--<td>-->
                    <!--{{ item.needy.firstname }} {{ item.needy.middlename }}-->
                <!--</td>-->
                <!--<td>-->
                    <!--<span v-if="item.employee" :title="item.employee.users[0].middlename + ' ' + item.employee.users[0].firstname">-->
                        <!--{{ item.employee.firstname }} {{ item.employee.middlename }}-->
                    <!--</span>-->
                <!--</td>-->
                <!--<td>-->
                    <!--{{ item.duration }}-->
                <!--</td>-->
            <!--</tr>-->
        <!--</table>-->

    </div>
</template>

<script>

    import { mapGetters } from 'vuex'

    export default {
        name: "TaskComponent",
        computed: {
            ...mapGetters([
                'TASKS','TASKSFIELDS'
            ])
        }
    }
</script>

<style scoped>

</style>