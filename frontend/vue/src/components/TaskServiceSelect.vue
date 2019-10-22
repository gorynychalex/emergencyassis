<template>
    <div>
        Выбор услуги

        <b-form-select
                v-model="selected"
                :options="SERVICES"
                text-field="title"
                value-field="id"
                @change="$emit('serviceselected', selected)"
        >
        </b-form-select>

        <div>
            <!--Соцработник: {{ selectemployee }}-->
            <!--Обслуживаемый: {{ selecthardup }}-->
        </div>
    </div>
</template>

<script>

    import { mapGetters } from 'vuex'

    export default {
        name: "TaskServiceSelect",
        data(){
          return {
              selected: null
          }
        },
        computed: {
            ...mapGetters([
                'SERVICES'
            ])
        }
    }
</script>

<style scoped>

</style>