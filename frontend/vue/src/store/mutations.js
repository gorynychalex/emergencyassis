import {AUTH_ERROR, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "./actions/auth";
import {FETCH_SERVICES, FETCH_TASKS, FETCH_USERS} from "./actions/services";

export default {
    [FETCH_SERVICES]: (state, itemServiceList) => {
        state.itemServiceList = itemServiceList
    },

    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },

    [FETCH_TASKS]: (state, itemTaskList) => {
        state.itemTaskList = itemTaskList
    },

    [AUTH_REQUEST]: (state) => {
        state.status = 'loading'
    },
    [AUTH_SUCCESS]: (state, resp) => {
        state.status = 'success'
        state.token = resp.access_token
        state.hasLoadedOnce = true
    },
    [AUTH_ERROR]: (state) => {
        state.status = 'error'
        state.hasLoadedOnce = true
    },
    [AUTH_LOGOUT]: (state) => {
        state.token = ''
    }
}
