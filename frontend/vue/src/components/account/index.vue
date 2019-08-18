/**
* Created by vouill on 11/13/17.
* https://github.com/sqreen/vue-authentication-example.git
*/

<template>
  <div>
    <div class="headline">
      <!--<img src="../../assets/dog-profile.jpeg">-->
      <h1>Your profile</h1>
    </div>
    <p v-if="profile.name">
      <strong>Name:</strong> {{profile.title}} {{profile.name}}
    </p>
  </div>
</template>

<!--<style lang="scss" scoped>-->
<style lang="" scoped>
  /*.headline{*/
    /*margin-top: 1em;*/
    /*display: flex;*/
    /*img {*/
      /*height: 80px;*/
      /*width: 80px;*/
      /*border-radius: 50%;*/
    /*}*/

</style>

<script>
  import { mapGetters } from 'vuex'

export default {
    name: 'account',
    computed: mapGetters({
      // profile: state => state.user.profile
    })
  }
</script>
