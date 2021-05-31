<template>
  <q-page padding>
    <div
      style="margin: 0 0 2rem 0"
      class="row-xs-12 row-sm-4 row-md-3 row-lg-3"
    >
      <div class="text-h4">Properties</div>
    </div>
    <div class="row-xs-12 row-sm-4 row-md-3 row-lg-3">
      <div class="property-card">
        <div class="text-h6 no-properties">
          {{ properties.length == 0 ? "There are no properties for sale" : "" }}
        </div>
        <property-card
          v-for="property in properties"
          v-bind:key="property.id"
          :property="property"
        />
      </div>
    </div>
  </q-page>
</template>

<script>
import PropertyCard from "./../components/PropertyCard";
import PropertyService from "./../services/PropertyService";
import {errorFetchingData} from "./../notifications/globalErrors";

export default {
  components: { PropertyCard },
  beforeMount() {
    this.getAllProperties()
  },
  data() {
    return {
      properties: [
      ]
    };
  },
  methods: {
    async getAllProperties() {
      let response = await PropertyService.getAllProperties()
      if(response.status === 200){
        this.properties = [...response.data]
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
