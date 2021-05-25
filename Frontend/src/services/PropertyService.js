import axios from 'axios'

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
}

const propertyService = new PropertyService()

export default propertyService
