import axios from 'axios'
import store from './../store/index'

class BuyerService {
  constructor () {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080/api/buyer'
    })
  }

  setupHeaders () {
    const jwt = store.getters.getJwt
    const headers = {
      Accept: 'application/json',
      Authorization: 'Bearer ' + jwt
    }
    return headers
  }


  async reserveProperty (buyerId, propertyId) {
    const success = this.apiClient
      .post('/' + buyerId + '/reserve/' + propertyId, null)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

  async cancelReservation (buyerId, propertyId) {
    const success = this.apiClient
      .post('/' + buyerId + '/reserve/' + propertyId + '/cancel', null)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

  async resolveReservation (buyerId, propertyId) {
    const success = this.apiClient
      .post('/' + buyerId + '/reserve/' + propertyId + '/resolve', null)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

  async refuseReservation (buyerId, propertyId) {
    const success = this.apiClient
      .post('/' + buyerId + '/reserve/' + propertyId + '/refuse', null)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

}

const buyerService = new BuyerService()

export default buyerService