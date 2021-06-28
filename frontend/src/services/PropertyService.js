import axios from 'axios'
import store from './../store/index'

class PropertyService {
  constructor () {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080' + '/api/property'
    })
  }

  async getAllProperties() {
    const properties = await this.apiClient
      .get('')
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

  async getPropertyById(id) {
    const properties = await this.apiClient
      .get('/' + id)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

  async getMyProperties() {
    const properties = await this.apiClient
      .get('/seller/' + store.getters.getId)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

  async recommendPrice(property) {
    const properties = await this.apiClient
      .post('/recommend-price', property)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }

  async searchProperties(data) {
    const properties = await this.apiClient
      .post('/search', data)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })

    return properties
  }
}

const propertyService = new PropertyService()

export default propertyService
