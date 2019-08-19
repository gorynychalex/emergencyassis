import { FETCH_USERS } from "../../actions/users"

export default {
    [FETCH_USERS]: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },
}