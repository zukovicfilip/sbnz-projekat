import { Notify } from 'quasar'

Notify.setDefaults({
  position: 'top',
  timeout: 4000,
  textColor: 'white',
  actions: [{ icon: 'close', color: 'white' }]
})

export function errorFetchingData () {
  const config = {
    type: 'negative',
    message: 'An error occurred while fetching data. Please reload the page and try again.'
  }
  Notify.create(config)
}

export function passwordsDoNotMatch () {
  const config = {
    type: 'negative',
    message: 'New password and confirm password do not match.'
  }

  Notify.create(config)
}

export function cantLeaveAnyFieldsEmpty () {
  const config = {
    type: 'negative',
    message: 'You can\'t leave any fields empty.'
  }

  Notify.create(config)
}