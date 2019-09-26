import {AUTH_ERROR, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "../../actions/auth";

export default {
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
