<!-- YoutubeVideoSearch.vue -->
<template>
    <div>
      <div class="search-container">
        <input
          type="text"
          v-model="keyword"
          @keyup.enter="search"
          placeholder="검색어를 입력하세요"
        />
        <button @click="search" class="search-button">
          <img src="@/assets/돋보기.png" alt="Search Icon" />
        </button>
      </div>
      <div class="hashtag-buttons">
        <button @click="searchWithHashtag('#홈트')">홈트</button>
        <button @click="searchWithHashtag('#실내운동')">실내운동</button>
        <button @click="searchWithHashtag('#맨몸운동')">맨몸운동</button>
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
  .search-container {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
    flex: 1;
  }
  
  button {
    padding: 10px 15px;
    background-color: #F8BD2A;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .hashtag-buttons {
    display: flex;
    gap: 10px;
  }
  
  .hashtag {
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
  }
  
  .search-button img {
    width: 16px;
    height: 16px;
  }
  </style>
  