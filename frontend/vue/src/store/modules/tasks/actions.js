import {task, TASK_ADD, TASK_DELETE, TASK_DONE, TASKS_DELETE} from "../../actions/tasks";
import { FETCH_TASKS } from "../../actions/tasks";
import apiCall from "../../../utils/api";

export default {
    [FETCH_TASKS]: ({ commit}) => {
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: task, mutation: FETCH_TASKS})
    },
    [TASK_ADD]: ({ commit }, task) => {

        return new Promise((resolve)=>{

            var newtask={"sid":task.socialService.id,"userHardupId":task.needy.id, "userEmployeeId":task.employee.id}

            fetch("/api/v1/task/new",{method: 'POST',body: JSON.stringify(newtask), headers: new Headers({'Content-type': 'application/json'})})
                .then(r=>r.json())
                .then(r=>resolve(r.id))

            // console.log("task sid: " + task.socialService.id)

            commit(TASK_ADD, task)

            // console.log("Here is add task: " + task)

        })
    },
    [TASKS_DELETE]: ({ commit }, tasks) => {

        return new Promise ((resolve)=>{

            tasks.forEach( taskid=> {

                fetch("/api/v1/task/" + taskid, {method: "DELETE"})
                    .then(r=>resolve(r))

                commit(TASK_DELETE, taskid)
            })

        })

    },
    [TASK_DONE]: ({commit}, task) => {
        return new Promise((resolve) => {

            commit(TASK_DONE, task)

            console.log("task.enabled: " + task.enable)
            console.log("task.id: " + task.id)
            fetch("/api/v1/task/" + task.id, {method: "PUT", body: JSON.stringify(task), headers: new Headers({'Content-type': 'application/json'})})
                .then(r=>resolve(r))


        })
    }
}
