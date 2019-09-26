import { task } from "../../actions/tasks";
import { FETCH_TASKS } from "../../actions/tasks";
import apiCall from "../../../utils/api";

export default {
    [FETCH_TASKS]: ({ commit, dispatch }) => {
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: task, mutation: FETCH_TASKS})
    },
}
