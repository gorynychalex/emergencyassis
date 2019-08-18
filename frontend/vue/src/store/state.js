export default {
    itemServiceList: [],
    itemUserList: [],
    itemTaskList: [],

    //Auth
    token: localStorage.getItem('access-token') || '',
    status: '',
    hasLoadedOnce: false
}
