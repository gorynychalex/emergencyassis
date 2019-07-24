Vue.component('task-list',{
    props: [],
    data: function () {
        return {
            tasks: []
        }
    },
    created: function(){
        this.getTaskList()
    },
    methods: {
        getTaskList: function () {
            // if (localStorage.access_token)
                fetch('/api/v1/task/',{ headers: {'Authorization': 'Bearer ' + localStorage.getItem('access_token')}})
                    .then(r=>r.json())
                    .then(r=>this.tasks=r)
        }
    },
    template: `
        <div>
        
        <ol>
            <li v-for="item in tasks" >{{ item.id }}&nbsp;&nbsp;{{ item.socialService }}</li>
        </ol>
        </div>
    `
})

Vue.component('login-frm',{
    props: ['msg'],
    data: function () {
        return {
            username: '',
            password: '',
            tasks: []
        }
    },
    created: function () {

    },
    methods: {
        login: function () {

                // if(!localStorage.getItem("access_token")) {

                    const {username, password} = this

                    let formdata = new FormData()
                    let headers = new Headers()
                    formdata.append('grant_type', 'password')
                    formdata.append('username', username)
                    formdata.append('password', password)
                    headers.append('Authorization', 'Basic ' + btoa("client:secret"))

                    fetch('http://localhost:8080/oauth/token',
                        {method: 'POST', headers: headers, body: formdata, redirect: 'follow'})
                        .then(r => r.json())
                        // .then(r=>localStorage.setItem('access_token',r.access_token))
                        .then(r => Object.keys(r).forEach((k, v) => localStorage.setItem(k, r[k]))).then(
                        fetch('/api/v1/task/', {headers: {'Authorization': 'Bearer ' + localStorage.getItem('access_token')}})
                            .then(r => r.json())
                            .then(r => this.tasks = r)
                    )
                // }

            // this.$store.dispatch(AUTH_REQUEST, { username, password }).then(() => {
            //     this.$router.push('/')
            // })
        }
    },
    template: `
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
    `
    }
)

const LoginPage = { template: `
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

`}

const SocialServices = { template: '<task-list />'}

const routes = [
    {path: '/login', component: LoginPage },
    {path: '/service', component: SocialServices }
]

// const router = new VueRouter({
//     routes
// })

var app = new Vue({
    // router,
    el: '#app',
    data: {
        tasks: [],
        message: "Новый соц помощник",
        login: true
    },
    methods: {
        loginenable: function () {
            if(localStorage.getItem("access_token")){
                this.login = false
            }
        }
    },
    template: `
    <div>
        <login-frm v-if="loginenable"/>
        <task-list />
    </div>
    `
})