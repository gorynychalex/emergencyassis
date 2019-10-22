<template>
    <div>
        <b-form-group :label="title">

            <b-form-radio-group buttons stacked button-variant="outline-primary"
                                v-model="selected"
                                @input="$emit('selectuser',selected)">
                <b-form-radio
                        v-for="user in users"
                        :key="users.id"
                        name="user-radios"
                        :value="user"
                        v-if="user.role===role"
                >
                    {{ user.firstname }} {{ user.middlename }} {{ user.lastname }}
                </b-form-radio>

            </b-form-radio-group>

        </b-form-group>


        <!--<b-form-group>-->
        <!--<b-form-radio-group v-model="selected" :options="USERS" valueField="nickname" :html-field="`${'middlename'}`" buttons stacked name="radio-btn-stacked" button-variant="outline-primary"/>-->
        <!--</b-form-group>-->
    </div>
</template>

<script>

    export default {
        name: "UserListForChoice",
        props: {
            users: Array,
            role: String,
            title: String
        },
        data(){
            return {
                selected: '',
            }
        }
    }
</script>

<style scoped>

</style>