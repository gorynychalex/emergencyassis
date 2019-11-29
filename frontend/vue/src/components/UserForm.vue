<template>
    <div class="text-left">

    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="input-group-nickname" label="Nickname:" label-for="input-nickname"
                       description="We'll never share your nickname with anyone else." >
            <b-form-input
                    id="input-nickname"
                    v-model="form.nickname"
                    type="text"
                    required
                    placeholder="Enter nickname"
            >
            </b-form-input>
        </b-form-group>
        <b-form-group id="input-group-password" label="Password:" label-for="input-password"
                description="Personal password">
            <b-form-input
                    id="input-password"
                    v-model="form.password"
                    type="password"
                    required
                    placeholder="Enter password"
            >
            </b-form-input>
        </b-form-group>

        <b-form-group id="input-group-email" label="Email address:" label-for="input-email"
                description="We'll never share your email with anyone else.">
            <b-form-input
                    id="input-email"
                    v-model="form.email"
                    type="email"
                    required
                    placeholder="Enter email"
            ></b-form-input>
        </b-form-group>

      <b-form-group id="input-group-firstname" label="Имя:" label-for="input-firstname">
        <b-form-input
                id="input-firstname"
                v-model="form.firstname"
                required
                placeholder="Введите имя"
        ></b-form-input>
      </b-form-group>

        <b-form-group id="input-group-middlename" label="Отчество:" label-for="input-middlename">
            <b-form-input
                    id="input-middlename"
                    v-model="form.middlename"
                    required
                    placeholder="Введите отчество"
            ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-lastname" label="Фамилия:" label-for="input-lastname">
            <b-form-input
                    id="input-lastname"
                    v-model="form.lastname"
                    required
                    placeholder="Введите фамилию"
            ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-address" label="Адрес проживания:" label-for="input-address">
            <b-form-input
                    id="input-addres"
                    v-model="form.address"
                    required
                    placeholder="Введите адрес проживания"
            ></b-form-input>
        </b-form-group>

      <!--<b-form-group id="input-group-locality" label="Местность:" label-for="input-locality">-->
        <!--<b-form-select-->
          <!--id="input-locality"-->
          <!--v-model="form.locale"-->
          <!--:options="locale"-->
          <!--required-->
        <!--&gt;</b-form-select>-->
      <!--</b-form-group>-->

      <b-form-group id="input-group-role" label="Основная роль:" label-for="input-role">
        <b-form-select
          id="input-role"
          v-model="form.role"
          :options="role"
          required
        >
        </b-form-select>
      </b-form-group>

      <b-form-group id="input-group-roles" label="Роли пользователя: ">
        <b-form-checkbox-group v-model="form.roles" id="checkboxes-roles">
          <b-form-checkbox value="HARDUP">Обслуживаемый</b-form-checkbox>
          <b-form-checkbox value="EMPLOYEE">Социальный работник</b-form-checkbox>
        </b-form-checkbox-group>
      </b-form-group>

        <b-form-group id="input-group-users" label="Подчиненные пользователи: ">
            <b-form-checkbox-group v-model="form.users" id="checkboxes-users">

            </b-form-checkbox-group>
        </b-form-group>

      <b-button type="submit" variant="primary">{{ userinput? 'Изменить' : 'Добавить'}}</b-button>
      <b-button type="reset" variant="danger">Сбросить</b-button>
    </b-form>


    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>

        <!--UserInput:  {{ userinput }}-->

  </div>
</template>

<script>
    import {USER_ADD, USER_EDIT_USERS} from "../store/actions/users";

    export default {
        name: "UserForm",
        props: { userinput: Object, usersinnerselect: Array, },
        data()
        {
            return {
                json_getAllKeys1: data => data.reduce((keys, obj) => keys.concat(Object.keys(obj).filter(key => keys.indexOf(key) === -1)), []),
                form: {
                    id: this.userinput.id ? this.userinput.id : '',
                    nickname: this.userinput.nickname ? this.userinput.nickname : '',
                    password: this.userinput.password ? this.userinput.password : '',
                    firstname: this.userinput.firstname ? this.userinput.firstname : '',
                    middlename: this.userinput.middlename ? this.userinput.middlename : '',
                    lastname: this.userinput.lastname ? this.userinput.lastname : '',
                    email: this.userinput.email ? this.userinput.email : '',
                    phone: this.userinput.phone ? this.userinput.phone : '',
                    address: this.userinput.address ? this.userinput.address : '',
                    role: this.userinput.role ? this.userinput.role : '',
                    roles: [],
                    users: [],
                },
                locale: [{ text: 'Выберите одно', value: null }, 'город', 'село'],
                role: [{ text: 'Выбор основной роли:', value: null }, { text: 'Обслуживаемый', value: 'HARDUP' },{ text: 'Социальный работник', value: 'EMPLOYEE' }],
                show: true
            }
        },
        mounted() {
            console.log(Object.keys(this.userinput))
        },
    methods: {
      onSubmit(evt) {
          evt.preventDefault()

          this.userinput.id ? console.log("METHOD PUT") : console.log("METHOD POST")

          alert(JSON.stringify(this.userinput))


          // console.log("here is add user in db")
          this.$store.dispatch(USER_ADD, JSON.stringify(this.form))
                    .then(()=>{
                        this.$router.push('/user')
                    })
              .catch(e => {
                  console.log(e)

          })
          this.$emit('event','done')
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = ''
        this.form.nickname = ''
        this.form.firstname = ''
        this.form.lastname = ''
        this.form.middlename = ''
        this.form.password = ''
        this.form.locale = null
        this.form.role = null
        this.form.roles = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
          this.$emit('event','done')
      },
      json_getAllKeys(data) {
          data.reduce((keys, obj) =>
              keys.concat(
                  Object.keys(obj).filter(key => keys.indexOf(key) === -1)
              ),
              [])
        },
    },
        watch: {
            usersinnerselect(newVal, oldVal){
                console.log("UserForm come users select " + newVal)

                this.form.users=newVal

                // console.log("Users: " + JSON.stringify(newVal))

                this.$store.dispatch(USER_EDIT_USERS, newVal)

            },
            userinput(newVal, oldVal){
                console.log("User change!!!")
                console.log(Object.keys(newVal).length)

            }
        }
    }
</script>

<style scoped>

</style>