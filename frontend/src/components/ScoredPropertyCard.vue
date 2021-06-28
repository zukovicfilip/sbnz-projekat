<template>
  <div class="q-pa-md">
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6">
          {{ property.address }}
        </div>
        <div class="text-weight-bolder">
          Score: {{ score.toFixed(1) }}
        </div>
        <div class="text-left">Build year: {{ property.buildDate }}</div>
        <div class="text-left">Price per square meter: {{ property.pricePerSquareM }}</div>
        <div class="text-left">Surface: {{ property.surface }} m2</div>
        <div class="text-left">Number of rooms: {{ property.numberOfRooms }}</div>
        <q-btn class="q-ma-sm" outline color="white" label="More info" @click="propertyInfo" />
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import moment from "moment";

export default {
  props: {
    property: {
      type: Object,
      required: false,
      default: function() {
        return {
          address: "",
          pricePerSquareM: 0.0,
          surface: 0.0,
          numberOfRooms: 0,
          buildDate: 0
        };
      }
    },
    score: {
      type: Float32Array,
      required: false,
    }
  },
  data() {
    return {};
  },
  methods: {
    dateFormat(date) {
      return moment(date).format("LL");
    },
    propertyInfo() {
      this.$router.push("search/property/" + this.property.id);
    }
  }
};
</script>

<style>
.my-card {
  width: 100%;
  max-width: 400px;
  width: 20rem;
  display: table;
  background-color: indianred;
}
</style>
