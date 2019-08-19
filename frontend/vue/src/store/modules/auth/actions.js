import apiCall from "../../../utils/api";
import {AUTH_ERROR, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "../../actions/auth";
import store from "../../index";
import {FETCH_USERS, REMOVE_USERS} from "../../actions/users";
import {FETCH_TASKS, task} from "../../actions/tasks";

export default {
    [AUTH_REQUEST]: ({commit, dispatch}, user) => {
        return new Promise((resolve, reject) => {
            commit(AUTH_REQUEST)
            console.log('API Call HERE!!!')
            // apiCall({commit, url: '/oauth/token', data: user, method: 'POST'})
            //     .then(r => {
            //         localStorage.setItem('access_token', r.token)
            //         // Here set the header of your ajax library to the token value.
            //         // example with axios
            //         // axios.defaults.headers.common['Authorization'] = resp.token
            //         commit(AUTH_SUCCESS, r)
            //         // dispatch(USER_REQUEST)
            //         resolve(r)
            //     })
            //     .catch(err => {
            //         commit(AUTH_ERROR, err)
            //         localStorage.removeItem('access_token')
            //         reject(err)
            //     })


            let formdata = new FormData()
            let headers=new Headers()
            formdata.append('grant_type','password')
            formdata.append('username',user.username)
            formdata.append('password',user.password)
            headers.append('Authorization', 'Basic ' + btoa("client:secret"))

            fetch('http://localhost:8080/oauth/token',
                { method: 'POST', headers: headers, body: formdata })
                .then(r=>r.json())
                .then(r=> {
                    localStorage.setItem('access_token',r.access_token)

                    //LOAD DATAS
                    dispatch(FETCH_USERS, 'user')
                    dispatch(FETCH_TASKS, task)

                    commit(AUTH_SUCCESS, r)
                })
                .catch(err => {
                    commit(AUTH_ERROR, err)
                    localStorage.removeItem('access_token')

                    reject(err)
                })
            ;
        })
    },
    [AUTH_LOGOUT]: ({commit, dispatch}) => {
        return new Promise((resolve, reject) => {
            commit(AUTH_LOGOUT)
            localStorage.removeItem('access-token')
            commit(REMOVE_USERS)
            resolve()
        })
    }
}
