import {FETCH_USERS, user} from "../../actions/users";
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
}
