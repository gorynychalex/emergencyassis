import { service } from "../../actions/services";
import {FETCH_SERVICES} from "../../actions/services";
import apiCall from "../../../utils/api";

export default {
    [FETCH_SERVICES]: ({ commit, dispatch }) => {
        // fetch('/api/v1/' + service)
        //     .then(r => r.json())
        //     .then(r => {
        //         commit(FETCH_SERVICES, r)
        //     })
        apiCall({commit, url: '/api/v1/', method: 'GET', urlservice: service, mutation: FETCH_SERVICES})
    }
}
