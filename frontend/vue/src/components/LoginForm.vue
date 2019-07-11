<template>
    <div>
        <p>{{ msg }}</p>
   <form class="login" @submit.prevent="login">
     <h1>Sign in</h1>
     <label>User name</label>
     <input required v-model="username" type="text" placeholder="Snoopy"/>
     <label>Password</label>
     <input required v-model="password" type="password" placeholder="Password"/>
     <hr/>
     <button type="submit">Login</button>
   </form>
 </div>
</template>

<script>
    export default {
        name: "LoginForm",
        props: {
            msg: String
        }
    }
</script>

<style scoped>

</style>