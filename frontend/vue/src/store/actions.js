import { FETCH_SERVICES, FETCH_USERS, FETCH_TASKS } from "./actions/services";
import { service, user, task } from "./actions/services";

export default {
    [FETCH_SERVICES]: ({ commit, dispatch }, species) => {
        fetch('/api/v1/' + service)
            .then(r => r.json())
            .then(r => {
                commit(FETCH_SERVICES, r)
            })
    },
    [FETCH_USERS]: ({ commit, dispatch }, species) => {
        console.log(dispatch)
        fetch('/api/v1/' + user)
            .then(r => r.json())
            .then(r => {
                commit(FETCH_USERS, r)
            })
    },
    [FETCH_TASKS]: ({ commit, dispatch }, species) => {
        fetch('/api/v1/' + task)
            .then(r => r.json())
            .then(r => {
                commit(FETCH_TASKS, r)
            })
    }
}
