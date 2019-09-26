import {FETCH_USERS, REMOVE_USERS, USER_ADD} from "../../actions/users"

export default {
    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },
    [USER_ADD]: (state, user) => {
        state.itemUserList.push(user)
    },
    [REMOVE_USERS]: (state) => state.itemUserList = ''
}