export default {
    TASKS: state => state.itemTaskList,
    TASKSBYUSER: (state,user) => state.itemTaskList.filter(x => x.needy.id == user.id),
    TASKSFIELDS: state => state.fieldsTask,
    TASKSFIELDS_noID: state => state.fieldsTaskNoId,
}