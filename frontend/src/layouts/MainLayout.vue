<template>
  <q-layout view="hHh lpR fFf">

    <q-header elevated class="bg-primary text-white">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://static.thenounproject.com/png/30626-200.png">
          </q-avatar>
          Real estate
        </q-toolbar-title>

        <q-btn flat color="white" label="Login / Register" @click="goToAuth" />

      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script>
export default {
  data () {
    return {
    }
  },
  methods: {
    goToAuth() {
      this.$router.push({ path: '/auth' })
    }
  }
}
</script>