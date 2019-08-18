import { FETCH_SERVICES, FETCH_USERS, FETCH_TASKS } from "./actions/services";
import { service, user, task } from "./actions/services";
import apiCall from '@/utils/api'
import {AUTH_ERROR, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "./actions/auth";

export default {
    [FETCH_SERVICES]: ({ commit, dispatch }) => {
        // fetch('/api/v1/' + service)
        //     .then(r => r.json())
        //     .then(r => {
        //         commit(FETCH_SERVICES, r)
        //     })
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: service, mutation: FETCH_SERVICES})
    },
    [FETCH_USERS]: ({ commit, dispatch }) => {
        // fetch('/api/v1/' + user)
        //     .then(r => r.json())
        //     .then(r => {
        //         commit(FETCH_USERS, r)
        //     })
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: user, mutation: FETCH_USERS})
    },
    [FETCH_TASKS]: ({ commit, dispatch }) => {
        // fetch('/api/v1/' + task)
        //     .then(r => r.json())
        //     .then(r => {
        //         commit(FETCH_TASKS, r)
        //     })
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: task, mutation: FETCH_TASKS})
    },


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
                { method: 'POST', headers: headers, body: formdata, redirect: 'follow' })
                .then(r=>r.json())
                .then(r=> {
                    localStorage.setItem('access_token',r.access_token)
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
            localStorage.removeItem('user-token')
            resolve()
        })
    }
}
