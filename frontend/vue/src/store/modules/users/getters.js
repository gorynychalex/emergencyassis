export default {
    USERS: state => state.itemUserList,
    USERFIELDS: state => state.fieldsUser,
    loadUsers: state => !!state.itemUserList,
}