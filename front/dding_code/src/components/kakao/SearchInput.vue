<template>
    <div>
      <input @keyup.enter="search" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
      <button @click="search">검색</button>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const searchKeyword = ref('');
  
  const search = () => {
    emit('search', searchKeyword.value);
  };
  </script>
  