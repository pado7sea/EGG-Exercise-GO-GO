<!-- VideoSlide.vue -->
<template>
    <div class="video-slide">
        <div class="you-search-container">
            <input type="text" v-model="keyword" @keyup.enter="search" placeholder="기본 검색어를 입력하세요" class="youtube-input" />
            <button @click="search" class="you-search-button">
                <img src="@/assets/돋보기.png" alt="Search Icon" />
            </button>
        </div>
        <div class="you-hashtag-buttons">
            <button @click="searchWithHashtag('#홈트')" class="you-hash-button">홈트</button>
            <button @click="searchWithHashtag('#실내운동')" class="you-hash-button">실내운동</button>
            <button @click="searchWithHashtag('#맨몸운동')" class="you-hash-button">맨몸운동</button>
        </div>

        <div v-if="hasSearchResults" style="margin: 0 3vw;">
            <!-- 첫 번째 영상만 표시 -->
            <iframe style="display: flex; justify-content: center; " width="560" height="405" :src="firstVideoUrl" frameborder="0" allowfullscreen></iframe>
        </div>
        <div>hasSearchResults: {{ hasSearchResults }}</div>
        <div>firstVideoUrl: {{ firstVideoUrl }}</div>
        <div v-if="!hasSearchResults && keyword !== ''">No search results found.</div>
    </div>
</template>
  
<script setup>
import { ref, onBeforeMount, computed } from 'vue';
import { useYoutubeStore } from '@/stores/youtube';

// 검색어 초기값 설정
const keyword = ref('#맨몸운동'); // 초기 검색어 설정
const store = useYoutubeStore();

const search = async () => {
    try {
        await store.youtubeSearch(keyword.value);
        console.log(keyword.value);
        console.log('After search:', store.videos);
    } catch (error) {
        console.error('Error during search:', error);
    }
};

const searchWithHashtag = (hashtag) => {
    keyword.value = hashtag;
    search();
};

const hasSearchResults = computed(() => {
    return store.videos && store.videos.length > 0;
});

const firstVideoUrl = computed(() => {
    // videos 배열이 존재하고, 첫 번째 비디오도 존재하는지 확인
    const firstVideo = hasSearchResults.value ? store.videos[0] : null;

    // 비디오가 존재하면 비디오의 ID를 이용해 URL을 생성
    return firstVideo ? `https://www.youtube.com/embed/${firstVideo.id.videoId}` : '';
});

onBeforeMount(() => {
    search();
});
</script>
  
<style scoped>
.video-slide {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    /* align-items: center; */
    justify-content: center;
}

.video-container {
    width: 100%;
    position: relative;
    padding-bottom: 56.25%;
    /* 16:9 비율에 맞게 설정 (높이 = 너비 * 9 / 16) */
}

.video-container iframe {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}

.you-search-container {
    display: flex;
    align-items: center;
    gap: 10px;
    margin: 0 3vw;
}

.youtube-input {
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 20px;
    font-size: 16px;
    flex: 1;
}

.youtube-input::placeholder {
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

.you-hash-button {
    background-color: #F8BD2A;
    width: 60px;
    height: 30px;
    border-radius: 5em;
    border-style: none;
    color: #151515;
    /* font-weight: bold; */
    box-shadow: inset 4px 4px 4px 0px #FFF5D3;
    margin-left: 5%;
    margin-right: 5%;
    margin-bottom: 5px;
    font-size: 13px;
}

.you-search-button img {
    width: 16px;
    height: 16px;
}
</style>
  