<template>
  <div class="q-pa-md">
    <q-card class="my-card">
      <q-card-section>
        <div class="text-h6">
          {{ property.address }}
        </div>
        <div class="text-left">Build year: {{ property.buildYear }}</div>
        <div class="text-left">Price per square meter: {{ property.pricePerSquareM }}</div>
        <div class="text-left">Surface: {{ property.surface }} m2</div>
        <div class="text-left">Number of rooms: {{ property.numberOfRooms }} m2</div>
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
          buildYear: 0
        };
      }
    }
  },
  data() {
    return {};
  },
  methods: {
    dateFormat(date) {
      return moment(date).format("LL");
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
}
</style>
