<template>
    <div v-if="pagination.length > 0" id="pagination" class="pagination">
      <span @click="gotoPage(1)" :class="{ 'disabled': currentPage === 1 }">&lt;&lt;</span>
      <span @click="gotoPage(currentPage - 1)" :class="{ 'disabled': currentPage === 1 }">&lt;</span>
      <span v-for="page in pagination" :key="page" @click="gotoPage(page)" :class="{ 'active': page === currentPage }">{{ page }}</span>
      <span @click="gotoPage(currentPage + 1)" :class="{ 'disabled': currentPage === totalPage }">&gt;</span>
      <span @click="gotoPage(totalPage)" :class="{ 'disabled': currentPage === totalPage }">&gt;&gt;</span>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';
  
  const { currentPage, totalPage } = defineProps(['currentPage', 'totalPage']);
  const { emit } = defineEmits();
  
  const gotoPage = (page) => {
    if (page >= 1 && page <= totalPage) {
      emit('gotoPage', page);
    }
  };
  </script>
  