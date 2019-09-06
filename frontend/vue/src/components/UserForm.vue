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

      <b-form-group id="input-group-locality" label="Местность:" label-for="input-locality">
        <b-form-select
          id="input-locality"
          v-model="form.locale"
          :options="locale"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-roles" label="Роли пользователя: ">
        <b-form-checkbox-group v-model="form.roles" id="checkboxes-roles">
          <b-form-checkbox value="HARDUP">Обслуживаемый</b-form-checkbox>
          <b-form-checkbox value="EMPLOYEE">Социальный работник</b-form-checkbox>
        </b-form-checkbox-group>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
  </div>
</template>

<script>
    import {USER_ADD} from "../store/actions/users";

    export default {
        name: "UserForm",
        data()
        {
            return {
                form: {
                    nickname: '',
                    password: '',
                    firstname: '',
                    middlename: '',
                    lastname: '',
                    email: '',
                    phone: '',
                    address: '',
                    roles: [],
                },
                locale: [{ text: 'Выберите одно', value: null }, 'город', 'село'],
                show: true
            }
        },
    methods: {
      onSubmit(evt) {
          evt.preventDefault()

          alert(JSON.stringify(this.form))

          console.log("here is add user in db")
          this.$store.dispatch(USER_ADD, JSON.stringify(this.form))
                    .then(()=>{
                        this.$router.push('/user')
                    })
              .catch(e => {
                  console.log(e)

          })
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
        this.form.roles = []
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
    }
    }
</script>

<style scoped>

</style>