<template>
  <div class="q-pa-md">
    <q-stepper v-model="step" ref="stepper_score" color="primary" animated>
      <q-step
        :name="1"
        title="Kids"
        icon="settings"
        :done="step > 1"
        style="width: 400px"
      >
        Do you have children?
        <q-select
          filled
          v-model="children"
          :options="yesnooptions"
          label="Filled"
        />
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

      <q-step :name="3" title="Family members" icon="settings" :done="step > 3">
        Number of younger family members?
        <q-select
          v-model="younger"
          filled
          :options="numberoptions"
          label="Filled"
          style="width: 400px"
        />
        Number of middle age family members?
        <q-select
          v-model="middleage"
          filled
          :options="numberoptions"
          label="Filled"
          style="width: 400px"
        />
        Number of older family members?
        <q-select
          v-model="older"
          filled
          :options="numberoptions"
          label="Filled"
          style="width: 400px"
        />
      </q-step>

      <q-step
        :name="4"
        title="Your occupations"
        :done="step > 4"
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
      <q-step :name="5" title="Price" :done="step > 5" icon="settings">
        <q-range
          v-model="price"
          :min="0"
          :max="3000"
          :step="10"
          label-always
          color="blue"
        />
      </q-step>
      <q-step :name="6" title="Surface" :done="step > 6" icon="settings">
        <q-range
          v-model="surface"
          :min="0"
          :max="300"
          :step="1"
          label-always
          color="blue"
        />
      </q-step>
      <q-step
        :name="7"
        title="Number of rooms"
        :done="step > 7"
        icon="settings"
      >
        <q-range
          v-model="numberOfRooms"
          :min="0"
          :max="10"
          :step="1"
          label-always
          color="blue"
        />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn
            @click="$refs.stepper_score.next()"
            color="primary"
            label="Continue"
            v-if="step < 7"
          />
          <q-btn
            v-on:click="submitSearch"
            color="primary"
            label="Finish"
            v-if="step === 7"
          />
          <q-btn
            v-if="step > 1"
            flat
            color="primary"
            @click="$refs.stepper_score.previous()"
            label="Back"
            class="q-ml-sm"
          />
        </q-stepper-navigation>
      </template>
    </q-stepper>
    <div class="row-xs-12 row-sm-4 row-md-3 row-lg-3">
      <div class="property-card">
        <div class="text-h6 no-properties">
          {{
            scoredProperties.length == 0
              ? "There are no properties for sale"
              : ""
          }}
        </div>
        <div class="q-pa-md row items-start q-gutter-md">
          <scored-property-card
            v-for="scoredProperty in scoredProperties"
            v-bind:key="scoredProperty.property.id"
            :property="scoredProperty.property"
            :score="scoredProperty.score"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScoredPropertyCard from "./../components/ScoredPropertyCard";
import PropertyService from "./../services/PropertyService";
import { errorFetchingData } from "./../notifications/globalErrors";
export default {
  components: { ScoredPropertyCard },
  data() {
    return {
      step: 1,
      search: {
        filterDTO: {
          kids: false,
          hasCar: false
        },
        scoreDTO: {}
      },
      multiple: null,
      yesnooptions: ["Yes", "No"],
      numberoptions: ["0", "1", "2", "3"],
      occupationoptions: ["FOOD", "NATURE", "SPORT", "CULTURE", "NIGHT_LIFE"],
      children: "No",
      car: "No",
      younger: "0",
      middleage: "0",
      older: "0",
      price: {
        min: 800,
        max: 2500
      },
      surface: {
        min: 20,
        max: 100
      },
      numberOfRooms: {
        min: 1,
        max: 5
      },
      scoredProperties: []
    };
  },
  methods: {
    async submitSearch() {
      const search = {
        filterDTO: {
          priceMin: this.price.min,
          priceMax: this.price.max,
          surfaceMin: this.surface.min,
          surfaceMax: this.surface.max,
          numberOfRoomsMin: this.numberOfRooms.min,
          numberOfRoomsMax: this.numberOfRooms.max
        },
        scoreDTO: {
          kids: false,
          hasCar: false,
          older: parseInt(this.older),
          younger: parseInt(this.younger),
          middleAged: parseInt(this.middleage),
          occupations: this.multiple
        }
      };
      console.log(search);
      let response = await PropertyService.searchProperties(search);
      if (response.status === 200) {
        this.scoredProperties = [...response.data];
      } else {
        errorFetchingData();
      }
    }
  }
};
</script>

<style scoped>
.wrapper {
  display: flex;
  width: 66%;
  flex-direction: row;
  justify-content: space-evenly;
}

.scored-property-card {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin-top: -2rem;
}
</style>
