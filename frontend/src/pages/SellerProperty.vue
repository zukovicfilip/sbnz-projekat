<template>
  <div class="q-pa-md justify-center">
    <div class="text-h4">
      {{ property.address }}
      <span
        class="label bg-red text-white text-h6 q-pa-sm"
        v-if="property.propertyStatus == 'SOLD'"
        >You have sold this property!</span
      >
    </div>
    <q-card
      class="q-ma-md my-card"
      v-if="property.propertyStatus == 'RESERVED'"
    >
      <q-card-section>
        <div class="text-h6">Pending property reservation</div>
        <div class="text-left">{{ property.reservedBy.email }}</div>
        <q-btn
          class="q-ma-sm"
          outline
          color="white"
          label="Resolve this reservation"
          @click="resolveRequest"
        />
        <q-btn
          class="q-ma-sm"
          outline
          color="white"
          label="Refuse"
          @click="refuseRequest"
        />
      </q-card-section>
    </q-card>
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
    <q-uploader
      class="q-ma-md"
      :url="uploadUrl"
      label="Upload images"
      multiple
      batch
      style="max-width: 300px"
    />
    <q-carousel
      class="q-ma-md"
      animated
      v-model="slide"
      arrows
      navigation
      infinite
    >
      <q-carousel-slide
        v-for="(image, index) in property.images"
        :key="index"
        :name="index"
        :img-src="image.url"
      ></q-carousel-slide>
    </q-carousel>
  </div>
</template>

<script>
import PropertyService from "./../services/PropertyService";
import BuyerService from "./../services/BuyerService";
import {
  createNegativeNotification,
  createPositiveNotification
} from "./../notifications/Notifications";
export default {
  async beforeMount() {
    await this.getPropertyData();
    this.uploadUrl =
      "http://localhost:8080/property/" + this.$route.params.id + "/upload";
  },
  data() {
    return {
      property: {},
      advice: {},
      uploadUrl: "",
      slide: 1
    };
  },
  methods: {
    async getPropertyData() {
      let response = await PropertyService.getPropertyById(
        this.$route.params.id
      );
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
    async resolveRequest() {
      let response = await BuyerService.resolveReservation(
        this.property.reservedBy.id,
        this.$route.params.id
      );
      if (response.status === 200) {
        createPositiveNotification("You have successfully sold this property!");
        await this.getPropertyData();
      } else {
        createNegativeNotification("Failed to resolve...");
      }
    },
    async refuseRequest() {
      let response = await BuyerService.refuseReservation(
        this.property.reservedBy.id,
        this.$route.params.id
      );
      if (response.status === 200) {
        createPositiveNotification("You have successfully refused!");
        await this.getPropertyData();
      } else {
        createNegativeNotification("Failed to refuse...");
      }
    }
  }
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
