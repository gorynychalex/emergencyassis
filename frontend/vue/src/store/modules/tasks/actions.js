import {task, TASK_ADD, TASK_DELETE, TASK_DONE, TASKS_DELETE} from "../../actions/tasks";
import { FETCH_TASKS } from "../../actions/tasks";
import apiCall from "../../../utils/api";

export default {
    [FETCH_TASKS]: ({ commit, dispatch }) => {
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: task, mutation: FETCH_TASKS})
    },
    [TASK_ADD]: ({ commit, dispatch }, task) => {

        return new Promise((resolve, reject)=>{

            var newtask={"sid":task.socialService.id,"userHardupId":task.needy.id, "userEmployeeId":task.employee.id}

            var newtask1;

            fetch("/api/v1/task/new",{method: 'POST',body: JSON.stringify(newtask), headers: new Headers({'Content-type': 'application/json'})})
                .then(r=>r.json())
                .then(r=>resolve(r.id))

            // console.log("task sid: " + task.socialService.id)

            commit(TASK_ADD, task)

            // console.log("Here is add task: " + task)

        })
    },
    [TASKS_DELETE]: ({ commit, dispatch }, tasks) => {

        return new Promise ((resolve, reject)=>{

            tasks.forEach( taskid=> {

                fetch("/api/v1/task/" + taskid, {method: "DELETE"})

                commit(TASK_DELETE, taskid)
            })

            // commit(TASKS_DELETE, tasks)

        })

        // commit(TASKS_DELETE, tasks)
    },
    [TASK_DONE]: ({commit, dispatch}, task) => {
        return new Promise((resolve, reject) => {

            fetch("/api/v1/task/" + task.id, {method: PUT, body: JSON.stringify(task), headers: new Headers({'Content-type': 'application/json'})})

            commit(TASK_DONE, task)
        })
    }
}
