<template>
  <div class="q-pa-xl q-mt-xl row justify-center">
    <div class="q-gutter-y-md" style="max-width: 700px">
      <q-card square class="shadow-10">
        <q-tabs
          v-model="tab"
          dense
          class="text-grey"
          active-color="primary"
          indicator-color="primary"
          align="justify"
          narrow-indicator
        >
          <q-tab name="login" label="Log in" />
          <q-tab name="register" label="Register" />
        </q-tabs>

        <q-separator />

        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="login">
            <div class="column q-pa-lg">
              <div class="row">
                <q-card flat style="width: 625px; height: 350px">
                  <q-card-section>
                    <q-form
                      class="q-px-md q-pt-xl q-gutter-md"
                      @submit="onSubmitLogin"
                    >
                      <q-input
                        filled
                        label="Email *"
                        hint="Input your email"
                        v-model="emailLogin"
                        lazy-rules
                        :rules="[
                          (val) =>
                            (val && val.length > 0) ||
                            'Please input your email',
                        ]"
                      >
                        <template v-slot:prepend>
                          <q-icon name="email" />
                        </template>
                      </q-input>

                      <q-input
                        v-model="passLogin"
                        filled
                        :type="isPass ? 'passLogin' : 'password'"
                        label="Password *"
                        hint="Input your password"
                        lazy-rules
                        :rules="[
                          (val) =>
                            (val && val.length > 0) ||
                            'Please input your password',
                        ]"
                      >
                        <template v-slot:prepend>
                          <q-icon name="lock" />
                        </template>
                        <template v-slot:append>
                          <q-icon
                            :name="isPass ? 'visibility' : 'visibility_off'"
                            class="cursor-pointer"
                            @click="isPass = !isPass"
                          ></q-icon>
                        </template>
                      </q-input>
                      <div class="q-mt-lg">
                        <q-btn
                          unelevated
                          type="submit"
                          size="lg"
                          color="primary"
                          class="full-width text-white"
                          label="Log in"
                        />
                      </div>
                    </q-form>
                  </q-card-section>
                </q-card>
              </div>
            </div>
          </q-tab-panel>

          <q-tab-panel name="register">
            <div class="column q-pa-lg">
              <div class="row">
                <q-card flat style="width: 625px; height: 450px">
                  <q-card-section>
                    <q-form
                      class="q-px-md q-pt-xl q-gutter-md"
                      @submit="onSubmit"
                    >
                      <q-input
                        filled
                        label="Email *"
                        hint="Input your email"
                        v-model="pEmail"
                        lazy-rules
                        :rules="[
                          (val) =>
                            (val && val.length > 0) || 'Please input your email',
                        ]"
                      />

                      <q-input
                        v-model="pPass1"
                        filled
                        :type="isPass1 ? 'pPass1' : 'password'"
                        label="Password *"
                        hint="Input your password"
                        lazy-rules
                        :rules="[
                          (val) =>
                            (val && val.length > 0) ||
                            'Please input your password',
                        ]"
                      >
                        <template v-slot:append>
                          <q-icon
                            :name="isPass1 ? 'visibility' : 'visibility_off'"
                            class="cursor-pointer"
                            @click="isPass1 = !isPass1"
                          ></q-icon>
                        </template>
                      </q-input>

                      <q-input
                        v-model="pPass2"
                        filled
                        :type="isPass2 ? 'pPass2' : 'password'"
                        label="Password *"
                        hint="Confirm your password"
                        lazy-rules
                        v-bind:rules="[
                          (val) =>
                            (val && val.length > 0) ||
                            'Please confirm your password',
                          (val) =>
                            (val && val == pPass1) || 'Passwords do not match!',
                        ]"
                      >
                        <template v-slot:append>
                          <q-icon
                            :name="isPass2 ? 'visibility' : 'visibility_off'"
                            class="cursor-pointer"
                            @click="isPass2 = !isPass2"
                          ></q-icon>
                        </template>
                      </q-input>
                      <div class="q-mt-lg">
                        <q-btn
                          unelevated
                          type="submit"
                          size="lg"
                          color="primary"
                          class="full-width text-white"
                          label="Sign up"
                        />
                      </div>
                    </q-form>
                  </q-card-section>
                </q-card>
              </div>
            </div>
          </q-tab-panel>
        </q-tab-panels>
      </q-card>
    </div>
  </div>
</template>

<script>
import UserService from "./../services/UserService";
import {
  successfulRegistration,
  userAlreadyExists,
  successfulLogin,
  failedToLogin
} from './../notifications/UserNotifications';
import { mapActions } from 'vuex';
export default {
  data () {
    return {
      tab: 'login',
      isPass: false,
      isPass1: false,
      isPass2: false,
      emailLogin: '',
      passLogin: '',
      pEmail: '',
      pPass1: '',
      pPass2: ''
    }
  },
  methods: {
    ...mapActions({
      setJwt: 'setJwt',
      setId: 'setId',
      setEmail: 'setEmail',
      setRole: 'setRole'
    }),
    async onSubmitLogin () {
      const loginData = {
        email: this.emailLogin,
        password: this.passLogin
      }
      const response = await UserService.login(loginData)
      if (response.status == 200) {
        this.setJwt(response.data.token)
        this.setId(response.data.id)
        this.setEmail(response.data.email)
        this.setRole(response.data.role)
        successfulLogin(response.data.email)
        //this.$router.push({ path: '/chat' })
      } else {
        failedToLogin()
      }
    },
    async onSubmit () {
      const registerData = {
        email: this.pEmail,
        password: this.pPass1
      }
      const response = await UserService.registerNewUser(registerData)
      if (response.status == 200) {
        successfulRegistration()
      } else {
        userAlreadyExists()
      }
    }
  }
}
</script>