<template>
  <q-page padding>
    <div
      style="margin: 0 0 2rem 0"
      class="row-xs-12 row-sm-4 row-md-3 row-lg-3"
    >
      <div class="text-h4">My properties
        <q-btn round color="red" icon="add" @click="addPropertyDialog = true"/>
      </div>
    </div>
    <div class="row-xs-12 row-sm-4 row-md-3 row-lg-3">
      <div class="property-card">
        <div class="text-h6 no-properties">
          {{ properties.length == 0 ? "You have no properties for sell" : "" }}
        </div>
        <property-card
          v-for="property in properties"
          v-bind:key="property.id"
          :property="property"
        />
      </div>
    </div>
    
    <q-dialog v-model="addPropertyDialog" persistent>
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Add property</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input dense autofocus v-model="newProperty.address" @keyup.enter="prompt = false" hint="Address"/>
        </q-card-section>

        <q-card-section class="q-pt-none; row">
          <q-input class="q-ma-sm" v-model.number="newProperty.coordinates.lat" type="number" filled style="max-width: 200px" hint="Enter lat"/>
          <q-input class="q-ma-sm" v-model.number="newProperty.coordinates.lon" type="number" filled style="max-width: 200px" hint="Enter lon"/>
        </q-card-section>

        <q-separator inset class="q-ma-sm"/>

        <q-card-section class="q-pt-none">
          <q-input v-model.number="newProperty.surface" type="number" filled style="max-width: 200px" hint="Surface in m2"/>
        </q-card-section>

        <q-separator inset class="q-ma-sm"/>

        <q-card-section class="q-pt-none">
          <q-input v-model.number="newProperty.numberOfRooms" type="number" filled style="max-width: 200px" hint="Number of rooms"/>
        </q-card-section>

        <q-separator inset class="q-ma-sm"/>

        <q-card-section class="q-pt-none">
          <q-input v-model.number="newProperty.buildDate" type="number" filled style="max-width: 200px" hint="Build date"/>
        </q-card-section>

        <q-separator inset class="q-ma-sm"/>

        <q-card-section class="q-pt-none; row">
          <q-input class="q-ma-sm" v-model.number="newProperty.pricePerSquareM" type="number" filled style="max-width: 200px" hint="Enter price"/>
          <q-btn class="q-ma-sm" flat color="primary" label="Suggest price" />
        </q-card-section>

        <q-card-actions align="right" class="text-primary">
          <q-btn flat label="Cancel" v-close-popup />
          <q-btn flat label="Add" @click="addMyProperty" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import PropertyCard from "./../components/PropertyCard";
import SellerService from "./../services/SellerService";
import {errorFetchingData} from "./../notifications/globalErrors";

export default {
  components: { PropertyCard },
  beforeMount() {
    this.getMyProperties()
  },
  data() {
    return {
      addPropertyDialog: false,
      newProperty: {
        address: "",
        coordinates: {
          lat: 0.0,
          lon: 0.0
        },
        surface: 0.0,
        numberOfRooms: 0,
        buildDate: 0,
        pricePerSquareM: 0.0
      },
      properties: [
      ]
    };
  },
  methods: {
    async getMyProperties() {
      let response = await SellerService.getMyProperties()
      if(response.status === 200){
        this.properties = [...response.data]
      }else{
        errorFetchingData()
      } 
    },
    async addMyProperty() {
      let response = await SellerService.addMyProperty(this.newProperty)
      if(response.status === 200){
        this.properties.push(response.data)
      }else{
        errorFetchingData()
      } 
    },
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

.property-card {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  margin-top: -2rem;
}
</style>
