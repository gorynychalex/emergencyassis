export default {
    SERVICES: state => state.itemServiceList,
    USERS: state => state.itemUserList,
    TASKS: state => state.itemTaskList,

    isAuthenticated: state => !!state.token,
    authStatus: state => state.status,
}