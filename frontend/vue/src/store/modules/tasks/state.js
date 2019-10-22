export default {
    itemTaskList: [],
    fieldsTaskList: {

        'employee.middlename': {
            label: 'Работник'
        },
        'needy.middlename': {
            label: 'Обслуживаемый'
        },
        'socialService.title': {
            label: 'Услуга',
            values: {
            }
        },
        dateCreate: {
            label: 'Дата создания'
        },
        dateStart: {
            label: 'Начало исполнения заявки'
        },
        dateStop: {
            label: 'Окончание'
        },
        duration: {
            label: 'Продолжительность'
        },
        enable: {
            label: 'Разрешено'
        }
    },
    fieldsTask: [


        {
          key: 'index',
          label: 'п/п'
        },
        {
            key: 'employee',
            label: 'Работник',
            sortable: true
        },
        {
            key: 'needy',
            label: 'Обслуживаемый',
            sorted: true
        },
        {
            key: 'socialService',
            label: 'Услуга',
            values: {
            }
        },
        {
            key: 'dateCreate',
            label: 'Дата создания'
        },
        {
            key: 'dateStart',
            label: 'Начало исполнения заявки'
        },
        {
            key: 'dateStop',
            label: 'Окончание'
        },
        {
            key: 'duration',
            label: 'Продолжительность'
        },
        {
            key: 'enable',
            label: 'Разрешено'
        },
        {
            key: 'id',
            label: 'id'
        },
    ],
    fieldsTaskNoId: [
        {
            key: 'index',
            label: 'п/п',
        },
        {
            key: 'employee',
            label: 'Работник',
            sortable: true
        },
        {
            key: 'needy',
            label: 'Обслуживаемый',
            sorted: true
        },
        {
            key: 'socialService',
            label: 'Услуга',
            values: {
            }
        },
        {
            key: 'dateCreate',
            label: 'Дата создания'
        },
        {
            key: 'dateStart',
            label: 'Начало исполнения заявки'
        },
        {
            key: 'dateStop',
            label: 'Окончание'
        },
        {
            key: 'duration',
            label: 'Продолжительность'
        },
        {
            key: 'enable',
            label: 'Разрешено'
        }
    ]
}
