import { FETCH_TASKS } from "../../actions/tasks"

export default {
    [FETCH_TASKS]: (state, itemTaskList) => {
        state.itemTaskList = itemTaskList
    },
}
