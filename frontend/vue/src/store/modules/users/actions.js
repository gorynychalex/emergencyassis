import {FETCH_USERS, REMOVE_USERS, user, USER_ADD, USER_EDIT_USERS, USER_UPDATE} from "../../actions/users";
import apiCall from "../../../utils/api";

export default {
    [FETCH_USERS]: ({ commit }) => {
        apiCall({
            commit,
            url: '/api/v1/',
            method: 'GET',
            urlservice: user,
            mutation: FETCH_USERS
        })
    },

    // [REMOVE_USERS]: ({ commit, dispatch}, users)=>{
    [REMOVE_USERS]: ({commit}, users)=>{
        return new Promise((resolve)=> {
            commit(REMOVE_USERS, users)

            users.forEach(u => {
                    fetch("/api/v1/user/" + u.id, {method: "DELETE"})
                        .then(r => resolve(r))
                }
            )
        }
        )
    },

    [USER_ADD]: ({ commit }, user) => {

        // return new Promise((resolve, reject)=>{
        return new Promise(()=>{


            commit(USER_ADD, user)

            fetch("/api/v1/user",{method: 'POST',body: JSON.stringify(user), headers: new Headers({'Content-type': 'application/json'})})

            console.log("Here is add user: " + user)

        })
    },

    [USER_UPDATE]: ({ commit }, user) => {
        return new Promise(()=>{

            console.log("USER_UPDATE: user.id: " + user.id)
            fetch("/api/v1/user/" + user.id,{method: 'PUT',body: JSON.stringify(user), headers: new Headers({'Content-type': 'application/json'})})

            commit(USER_UPDATE, user)
        })
    },

    // [USER_EDIT_USERS]: ({ commit, dispatch }, user, users) => {
    [USER_EDIT_USERS]: ({ commit }, user, users) => {

        // return new Promise((resolve,reject) => {
        return new Promise((resolve,reject) => {

            console.log("Dispatch user : " + user )
            // console.log("Dispatch users: " + users)

            console.log(resolve + " " + reject)

            commit(USER_EDIT_USERS, user, users)

        })
    },
}
