<!-- YoutubeVideoSearch.vue -->
<template>
  <div>
    <div class="you-search-container">
      <input
        type="text"
        v-model="keyword"
        @keyup.enter="search"
        placeholder="검색어를 입력하세요"
        class="youtube-input"
      />
      <button @click="search" class="you-search-button">
        <img src="@/assets/돋보기.png" alt="Search Icon" />
      </button>
    </div>
    <div class="you-hashtag-buttons">
      <button @click="searchWithHashtag('#러닝')" class="you-hash-button">러닝</button>
      <button @click="searchWithHashtag('#하이킹 코스')" class="you-hash-button">하이킹 코스</button>
      <button @click="searchWithHashtag('#조깅 경로')" class="you-hash-button">조깅 경로</button>
      <button @click="searchWithHashtag('#요가 및 명상')" class="you-hash-button">서핑</button>
      <button @click="searchWithHashtag('#홈짐')" class="you-hash-button">마라톤</button>
      <button @click="searchWithHashtag('#패들보딩')" class="you-hash-button">패들보딩</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';

const keyword = ref('');
const store = useYoutubeStore();

const search = () => {
  store.youtubeSearch(keyword.value);
};

const searchWithHashtag = (hashtag) => {
  keyword.value = hashtag;
  search();
};
</script>

<style scoped>
.you-search-container {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 2vh 2vw;
}

.youtube-input {
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 20px;
  font-size: 16px;
  flex: 1;
  text-align: center; /* 가운데 정렬을 위해 추가 */
}

.youtube-input::placeholder{
  padding: 7px;
}

.you-search-button {
  padding: 10px 15px;
  background-color: #F8BD2A;
  color: #fff;
  border: none;
  border-radius: 10px;
  cursor: pointer;
}

.you-hashtag-buttons {
  display: flex;
  justify-content: space-around;
}

/* .hashtag {
  background-color: #F8BD2A;
  width: 60px;
  height: 60px;
  border-radius: 5em;
  border-style: none;
  color: #151515;
  font-weight: bold;
  box-shadow: inset 4px 4px 4px 0px #FFF5D3;

  margin-left: 5%;
  margin-right: 5%;
} */

.you-hash-button{
  background-color: #F8BD2A;
width: 80px;
height: 80px;
border-radius: 5em;
border-style: none;
color: #151515;
font-weight: bold;
font-size: 12px;
box-shadow: inset 4px 4px 4px 0px #FFF5D3;
margin-left: 5%;
margin-right: 5%;
  
}

.you-search-button img {
  width: 16px;
  height: 16px;
}
</style>
