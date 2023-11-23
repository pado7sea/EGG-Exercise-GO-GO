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
        <button @click="searchWithHashtag('#홈트')" class="you-hash-button">홈트</button>
        <button @click="searchWithHashtag('#실내운동')" class="you-hash-button">실내운동</button>
        <button @click="searchWithHashtag('#맨몸운동')" class="you-hash-button">맨몸운동</button>
        <button @click="searchWithHashtag('#요가 및 명상')" class="you-hash-button">요가 및 명상</button>
        <button @click="searchWithHashtag('#홈짐')" class="you-hash-button">홈짐</button>
        <button @click="searchWithHashtag('#실내사이클링')" class="you-hash-button">실내사이클링</button>
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
  