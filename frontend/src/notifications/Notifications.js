import { Notify } from 'quasar'

Notify.setDefaults({
    position: 'top',
    timeout: 4000,
    textColor: 'white',
    actions: [{ icon: 'close', color: 'white' }]
})

export function createNegativeNotification(text) {
    const config = {
        type: 'negative',
        message: text
    }

    Notify.create(config)
}

export function createPositiveNotification(text) {
    const config = {
        type: 'positive',
        message: text
    }

    Notify.create(config)
}