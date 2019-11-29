import {FETCH_USERS, user, USER_ADD, USER_EDIT_USERS} from "../../actions/users";
import apiCall from "../../../utils/api";

export default {
    [FETCH_USERS]: ({ commit, dispatch }) => {
        apiCall({
            commit,
            url: '/api/v1/',
            method: 'GET',
            urlservice: user,
            mutation: FETCH_USERS
        })
    },

    [USER_ADD]: ({ commit, dispatch }, user) => {

        return new Promise((resolve, reject)=>{


            commit(USER_ADD, user)

            fetch("/api/v1/user",{method: 'POST',body: user, headers: new Headers({'Content-type': 'application/json'})})

            console.log("Here is add user: " + user)

        })
    },

    [USER_EDIT_USERS]: ({ commit, dispatch }, user) => {



        return new Promise((resolve,reject) => {

            console.log("Dispatch user : " + user )
            // console.log("Dispatch users: " + users)

            // commit(USER_EDIT_USERS, user, users)

        })
    },
}
