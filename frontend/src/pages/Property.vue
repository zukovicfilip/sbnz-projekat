<template>
  <div class="q-pa-md justify-center">
    <div class="text-h4">
      {{ property.address }}
      <q-btn
        class="q-ma-sm"
        outline
        color="primary"
        v-if="!reservedByMe && !reserved && !sold"
        @click="reserveProperty"
        label="Reserve this property"
      />
      <q-btn
        class="q-ma-sm"
        outline
        color="primary"
        v-if="reservedByMe"
        @click="cancelReservation"
        label="Cancel reservation"
      />
      <span class="label bg-red text-white text-h6 q-pa-sm" v-if="reserved">This property is already reserved</span>
      <span class="label bg-red text-white text-h6 q-pa-sm" v-if="sold">This property is already sold</span>
    </div>
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
    const detailsEvent = {
      propertyId: this.$route.params.id,
      userId: this.$store.state.id
    };
    console.log(detailsEvent);
    await PropertyService.newDetailsEvent(detailsEvent);
  },
  data() {
    return {
      property: {},
      reservedByMe: false,
      reserved: false,
      sold: false
    };
  },
  methods: {
    async getPropertyData() {
      this.reservedByMe = false
      this.reserved = false
      this.sold = false
      let response = await PropertyService.getPropertyById(
        this.$route.params.id
      );
      if (response.status === 200) {
        this.property = response.data;
        if (this.property.propertyStatus == "SOLD") {
          this.sold = true;
        } else if (this.property.propertyStatus == "RESERVED") {
          if (this.property.reservedBy.id == this.$store.state.id) {
            this.reservedByMe = true;
          } else {
            this.reserved = true;
          }
        }
      } else {
      }
    },
    async reserveProperty() {
      let response = await BuyerService.reserveProperty(this.$store.state.id, this.$route.params.id);
      if (response.status === 200) {
        createPositiveNotification(
          "You have successfully reserved this property!"
        );
        await this.getPropertyData();
      } else {
        createNegativeNotification("Failed to reserve this property...");
      }
    },
    async cancelReservation() {
      let response = await BuyerService.cancelReservation(
        this.$store.state.id,
        this.$route.params.id
      );
      if (response.status === 200) {
        createPositiveNotification(
          "You have successfully canceled reservation"
        );
        await this.getPropertyData();
      } else {
        createNegativeNotification("Failed to cancel reservation...");
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
