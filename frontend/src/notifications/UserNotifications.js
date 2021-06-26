import { Notify } from 'quasar'

Notify.setDefaults({
    position: 'top',
    timeout: 4000,
    textColor: 'white',
    actions: [{ icon: 'close', color: 'white' }]
})

export function successfulRegistration() {
    const config = {
        type: 'positive',
        message: 'You have successfuly registrated!'
    }

    Notify.create(config)
}

export function userAlreadyExists() {
    const config = {
        type: 'negative',
        message: 'User whit this username already exists!'
    }

    Notify.create(config)
}

export function successfulLogin(username) {
    const config = {
        type: 'positive',
        message: 'Hello ' + username + '! You have successfuly logged in.'
    }

    Notify.create(config)
}

export function failedToLogin() {
    const config = {
        type: 'negative',
        message: 'Wrong username or password!'
    }

    Notify.create(config)
}