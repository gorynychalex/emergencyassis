import {FETCH_USERS, REMOVE_USERS, USER_ADD, USER_EDIT_USERS, USER_UPDATE} from "../../actions/users"

export default {
    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },

    [USER_ADD]: (state, user) => {
        state.itemUserList.push(user)
    },

    [USER_UPDATE]: (state, user) => {
        state.itemUserList[state.itemUserList.findIndex(u=>u.id == user.id)]=user
    },

    [REMOVE_USERS]: (state,users) => {
        users.forEach(u =>
            state.itemUserList.splice(state.itemUserList.findIndex(i=>i.id == u.id),1)
            // console.log("remove_users mutation: " + u.id)
        )
    },

    [USER_EDIT_USERS]: (state, user, users) => {
        // console.log("MUTATION: Users: " + users.length)
        state.itemUserList.find(u=>u.id == user.id).users=users
    }
}