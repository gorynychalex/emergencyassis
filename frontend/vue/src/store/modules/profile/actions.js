import {USER_REQUEST} from "../../actions/profile";
// import {AUTH_LOGOUT} from "../../actions/auth";
// import apiCall from "../../../utils/api";

export default {
    [USER_REQUEST]: ({commit}) => {
        commit(USER_REQUEST)
        // apiCall({url: 'user/me'})
        //     .then(resp => {
        //         commit(USER_SUCCESS, resp)
        //     })
        //     .catch(resp => {
        //         commit(USER_ERROR)
        //         // if resp is unauthorized, logout, to
        //         dispatch(AUTH_LOGOUT)
        //     })
    },
}