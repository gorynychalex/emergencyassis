import {USER_ERROR, USER_REQUEST, USER_SUCCESS} from "../../actions/profile";
import {AUTH_LOGOUT} from "../../actions/auth";
// import {AUTH_LOGOUT} from "../../actions/auth";
// import apiCall from "../../../utils/api";

export default {
    [USER_REQUEST]: ({commit, dispatch}, username) => {
        commit(USER_REQUEST)


        fetch("/api/v1/user/name/" + username)
            .then(r=> {
                    // console.log(r.url)
                    return r.json()
                }
            ).then(r=> {
                console.log(r)
                return commit(USER_SUCCESS, r)
            }
            )
            .catch(r=> {
                    commit(USER_ERROR)
                    dispatch(AUTH_LOGOUT)
                }
            )

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