import axios from 'axios'
import store from './../store/index'

class UserService {
  constructor () {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080/api/auth'
    })
  }

  setupHeaders () {
    const jwt = store.getters.getJwt
    console.log(jwt)
    const headers = {
      Accept: 'application/json',
      Authorization: 'Bearer ' + jwt
    }
    return headers
  }


  async registerNewUser (user) {
    const success = this.apiClient
      .post('/register', user)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

  async login (user) {
    const success = this.apiClient
      .post('/login', user)
      .then(response => {
        return response
      })
      .catch(err => {
        return err.response
      })
    return success
  }

}

const userService = new UserService()

export default userService