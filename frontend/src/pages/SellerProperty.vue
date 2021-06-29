<template>
  <div class="q-pa-md justify-center">
    <div class="text-h4">{{ property.address }}</div>
    <q-card class="q-ma-md my-card">
      <q-card-section>
        <div class="text-h6">Info</div>
        <div class="text-left">Build year: {{ property.buildDate }}</div>
        <div class="text-left">
          Price per square meter: {{ property.pricePerSquareM }}
        </div>
        <div class="text-left">Surface: {{ property.surface }} m2</div>
        <div class="text-left">
          Number of rooms: {{ property.numberOfRooms }}
        </div>
      </q-card-section>
    </q-card>
    <q-card class="q-ma-md my-card">
      <q-card-section>
        <div class="text-h6">Owner</div>
        <div class="text-left">Email: {{ property.owner.email }}</div>
      </q-card-section>
    </q-card>
    <q-card class="q-ma-md my-card">
      <q-card-section>
        <div class="text-h6">Advice</div>
        <div class="text-left">{{ advice.priceAdvice }}</div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import PropertyService from "./../services/PropertyService";
export default {
  async beforeMount() {
    let response = await PropertyService.getPropertyById(this.$route.params.id);
    if (response.status === 200) {
      this.property = response.data;
    } else {
    }
    response = await PropertyService.getAdvice(this.$route.params.id);
    if (response.status === 200) {
      this.advice = response.data;
    } else {
    }
  },
  data() {
    return {
      property: {},
      advice: {}
    };
  },
  methods: {}
};
</script>

<style>
.my-card {
  width: 100%;
  max-width: 800px;
  width: 40rem;
  display: table;
  background-color: rgb(79, 159, 212);
}
</style>
