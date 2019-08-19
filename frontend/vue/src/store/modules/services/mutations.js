import { FETCH_SERVICES } from "../../actions/services"

export default {
    [FETCH_SERVICES]: (state, itemServiceList) => {
        state.itemServiceList = itemServiceList
    }
}
