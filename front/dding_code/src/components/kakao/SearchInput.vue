<template>
  <div>
    <input @keyup.enter="searchPlaces" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
    <button @click="searchPlaces">검색</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const searchKeyword = ref('');

// 장소 검색 실행 함수
const searchPlaces = () => {
  const trimmedSearchKeyword = searchKeyword.value.trim();

  if (!trimmedSearchKeyword) {
    alert('검색어를 입력하세요!');
    return;
  }

  removeMarkers();
  ps.keywordSearch(trimmedSearchKeyword, placesSearchCB, { useMapBounds: true });

  // 부모 컴포넌트로 이벤트 발생
  emit('search', trimmedSearchKeyword);
};




</script>

<style scoped>
/* 스타일을 필요에 따라 추가할 수 있습니다. */
</style>
