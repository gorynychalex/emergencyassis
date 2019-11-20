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
        },
        {
            key: 'dateCreate',
            label: 'Дата создания',
            formatter: value => value && isNaN(value) && value.split("T").length > 1?
                value.split("T")[0].split("-")[2] + "/" + value.split("T")[0].split("-")[1] + "/" + value.split("T")[0].split("-")[0]
                :
                value,
            sortable: true
        },
        {
            key: 'dateStart',
            label: 'Начало исполнения заявки',
            formatter: value => value && isNaN(value) && value.split("T").length > 1?
                value.split("T")[0].split("-")[2] + "/" + value.split("T")[0].split("-")[1] + "/" + value.split("T")[0].split("-")[0]
                :
                value,
            sortable: true
        },
        {
            key: 'dateStop',
            label: 'Окончание',
            formatter: value => value && isNaN(value) && value.split("T").length > 1?
                value.split("T")[0].split("-")[2] + "/" + value.split("T")[0].split("-")[1] + "/" + value.split("T")[0].split("-")[0]
                :
                value,
            sortable: true
        },
        {
            key: 'duration',
            label: 'Продолжительность',
            sortable: true
        },
        {
            key: 'enable',
            label: 'Разрешено',
            sortable: true
        }
    ]
}
