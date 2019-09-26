export default {
    getProfile: state => state.profile,
    isProfileLoaded: state => !!state.profile.name,
}