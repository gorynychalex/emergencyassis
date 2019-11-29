import {FETCH_USERS, REMOVE_USERS, USER_ADD, USER_EDIT_FIELDS, USER_EDIT_USERS} from "../../actions/users"

export default {
    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },

    [USER_ADD]: (state, user) => {
        state.itemUserList.push(user)
    },

    [REMOVE_USERS]: (state) => state.itemUserList = '',

    [USER_EDIT_USERS]: (state, user, users) => {
        // console.log("MUTATION: Users: " + users.length)
        // state.itemUserList.find(u=>u.id == user.id).users=users
    }
}