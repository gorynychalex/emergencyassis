export default {
    SERVICES: state => state.itemServiceList,
    loadStatus: state => state.status,
    hasLoadedServices: state => state.hasLoadedServices,
    SERVICESFIELD: state => state.servicesFiled
}