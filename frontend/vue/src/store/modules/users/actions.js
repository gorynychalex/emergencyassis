import {FETCH_USERS, user, USER_ADD} from "../../actions/users";
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
            commit(USER_ADD)

            fetch("/api/v1/user",{method: 'POST',body: JSON.stringify(user), headers: new Headers({'Content-type': 'application/json'})})

            console.log("Here is add user: " + user)

        })
    }
}
