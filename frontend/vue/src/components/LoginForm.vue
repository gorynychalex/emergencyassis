<template>
    <div>

   <form class="login" @submit.prevent="login">
     <h1>{{ msg }}</h1>
     <!--<label>User name</label>-->
     <input required v-model="username" type="text" placeholder="Username"/>
       <br />
     <!--<label>Password</label>-->
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
        },
        data: function(){
            return {
                username: '',
                password: ''
            }
        },
        methods: {
            login: function () {
                const { username, password } = this


                let formdata = new FormData()
                let headers=new Headers()
                formdata.append('grant_type','password')
                // formdata.append('nickname',username)
                formdata.append('username',username)
                formdata.append('password',password)
                headers.append('Authorization', 'Basic ' + btoa("client:secret"))

                fetch('http://localhost:8080/oauth/token',
                    { method: 'POST', headers: headers, body: formdata, redirect: 'follow' })
                    .then(r=>r.json())
                    .then(r=>localStorage.setItem('access_token',r.access_token));



                // this.$store.dispatch(AUTH_REQUEST, { username, password }).then(() => {
                //     this.$router.push('/')
                // })
            }
        }
    }
</script>

<style scoped>

</style>