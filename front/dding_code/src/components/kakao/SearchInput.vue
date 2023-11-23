<template>
  <div>
    <div class="map-search-container">
      <input @keyup.enter="searchPlaces" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요" @keypup.enter="searchPlaces" class="map-input">
      <button @click="searchPlaces" class="map-search-button">
        <img src="@/assets/돋보기.png" alt="Search Icon" width="16px" height="16px"/></button>
    </div>
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
.map-search-container {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 2vh 2vw;
}

.map-input {
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 20px;
  font-size: 16px;
  flex: 1;
  text-align: center; /* 가운데 정렬을 위해 추가 */
}

.map-input::placeholder{
  padding: 7px;
}

.map-search-button {
  padding: 10px 15px;
  background-color: #F8BD2A;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}

</style>
