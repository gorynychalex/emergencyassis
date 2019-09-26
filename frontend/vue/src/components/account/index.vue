/**
* Created by vouill on 11/13/17.
* https://github.com/sqreen/vue-authentication-example.git
*/

<template>
  <div>
    <div v-if="getProfile.firstname">
      <div class="headline">
      <!--<img src="../../assets/dog-profile.jpeg">-->
        <h3>Your profile</h3>
      </div>
        <p><strong>Name:</strong> {{getProfile.nickname}} {{getProfile.firstname}} {{getProfile.lastname}}</p>
    </div>
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
    computed: {
      ...mapGetters([
              'getProfile'
      ])
    }
  }
</script>
