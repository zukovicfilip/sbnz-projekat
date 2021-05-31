<template>
  <div class="q-pa-md">
    <q-stepper
      v-model="step"
      ref="stepper"
      color="primary"
      animated
    >
      <q-step
        :name="1"
        title="Kids"
        icon="settings"
        :done="step > 1"
        style="width: 400px"
      >
        Do you have children?
        <q-select filled v-model="children" :options="yesnooptions" label="Filled" />
      </q-step>

      <q-step
        :name="2"
        title="Car"
        icon="settings"
        :done="step > 2"
        style="width: 400px"
      >
        Do you have car?
        <q-select filled v-model="car" :options="yesnooptions" label="Filled" />
      </q-step>

      <q-step
        :name="3"
        title="Family members"
        icon="settings"
      >
        Number of younger family members?
        <q-select v-model="younger" filled :options="numberoptions" label="Filled" style="width: 400px"/>
        Number of middle age family members?
        <q-select v-model="middleage" filled :options="numberoptions" label="Filled" style="width: 400px"/>
        Number of older family members?
        <q-select v-model="older" filled :options="numberoptions" label="Filled" style="width: 400px"/>
      </q-step>

      <q-step
        :name="4"
        title="Your occupations"
        icon="settings"
      >
        <q-select
        filled
        v-model="multiple"
        multiple
        :options="occupationoptions"
        label="Multiple"
        style="width: 400px"
      />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn @click="$refs.stepper.next()" color="primary" :label="step === 4 ? 'Finish' : 'Continue'" />
          <q-btn v-if="step > 1" flat color="primary" @click="$refs.stepper.previous()" label="Back" class="q-ml-sm" />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </div>
</template>

<script>
export default {
  data () {
    return {
      step: 1,
      multiple: null,
      yesnooptions: ["Yes", "No"],
      numberoptions: ["0", "1", "2", "3+"],
      occupationoptions: ["Food", "Nature", "Sport", "Culture", "Night life"],
      children: "",
      car: "",
      younger: "",
      middleage: "",
      older: "",
    }
  }
}
</script>