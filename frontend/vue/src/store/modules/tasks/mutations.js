import {FETCH_TASKS, TASK_ADD, TASK_DELETE, TASK_DONE, TASKS_DELETE} from "../../actions/tasks"

export default {
    [FETCH_TASKS]: (state, itemTaskList) => {
        state.itemTaskList = itemTaskList
    },
    [TASK_ADD]: (state, task) => {
        state.itemTaskList.push(task)
        console.log("push task via commit TASK ADD")
    },
    [TASKS_DELETE]: (state, tasks) => {
        tasks.forEach(x =>
            state.itemTaskList.splice(state.itemTaskList.findIndex(t=>t.id=x),1)
        )
    },
    [TASK_DELETE]: (state, task) => {

        var index=state.itemTaskList.findIndex(t=>t.id=task)
        console.log("task: " + task)
        console.log("index : " + index)
        console.log("task list length: " + state.itemTaskList.length)
        var item = state.itemTaskList.splice(index,1)
        console.log(item)
    },
    [TASK_DONE]: (state, task)=> {
        state.itemTaskList.find(x=>x.id == task.id).enable = !state.itemTaskList.find(x=>x.id == task.id).enable
    }
}
