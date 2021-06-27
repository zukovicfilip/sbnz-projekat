import axios from 'axios'
import store from './../store/index'

class SellerService {
  constructor () {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080' + '/api/seller'
    })
  }

  async getMyProperties() {
    const properties = await this.apiClient
      .get('/' + store.getters.getId + '/property')
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

  async addMyProperty(property) {
    const properties = await this.apiClient
      .post('/' + store.getters.getId + '/property', property)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

}

const sellerService = new SellerService()

export default sellerService
