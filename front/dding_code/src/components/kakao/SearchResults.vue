<template>
    <div id="placesList">
      <div v-for="(place, index) in places" :key="index" class="place-item" @click="selectPlace(place)">
        <span class="markerbg marker_{{ (currentPage - 1) * pageSize + index + 1 }}"></span>
        <div class="info">
          <h5>{{ place.place_name }}</h5>
          <span>{{ place.address_name }}</span>
          <span class="tel">{{ place.phone }}</span>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';
  
  const { places } = defineProps(['places']);
  const { emit } = defineEmits();
  
  const selectPlace = (place) => {
    emit('selectPlace', place);
  };
  </script>
  