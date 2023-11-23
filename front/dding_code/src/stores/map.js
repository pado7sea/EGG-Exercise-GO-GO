// src/stores/map.js
import { defineStore } from 'pinia';

const useMapStore = defineStore('map', {
  state: () => ({
    categoryKeyword: '',
  }),
  actions: {
    setCategoryKeyword(keyword) {
      this.categoryKeyword = keyword;
    },
  },
});

export { useMapStore };
