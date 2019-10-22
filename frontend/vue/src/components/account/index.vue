/**
* Created by vouill on 11/13/17.
* https://github.com/sqreen/vue-authentication-example.git
*/

<template>
  <div>
    <div v-if="getProfile.firstname">
      <div class="headline">
      <!--<img src="../../assets/dog-profile.jpeg">-->
        {{ getProfile.role == 'HARDUP' ? "Обслуживаемый" : getProfile.role == 'EMPLOYEE'? "Социальный работник": getProfile.role == 'SPECIALIST' ? "Специалист" : "Профиль" }}

        :

        {{getProfile.firstname}} {{getProfile.lastname}}

      </div>
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
