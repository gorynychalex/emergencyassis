import {FETCH_USERS, REMOVE_USERS} from "../../actions/users"

export default {
    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },
    [REMOVE_USERS]: (state) => state.itemUserList = ''
}