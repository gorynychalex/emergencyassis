<template>
    <div>
        <LoginForm msg="Введите логин-пароль"/>
    </div>
</template>

<script>
    import LoginForm from '@/components/LoginForm.vue'

    export default {
        name: "Login",
        components: {
            LoginForm
        }
    }
</script>

<style scoped>

</style>