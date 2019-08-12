export default {
    FETCH_SERVICES: (state, itemServiceList) => {
        state.itemServiceList = itemServiceList
    },

    FETCH_USERS: (state, itemUserList) => {
        state.itemUserList = itemUserList
    },

    FETCH_TASKS: (state, itemTaskList) => {
        state.itemTaskList = itemTaskList
    }
}
