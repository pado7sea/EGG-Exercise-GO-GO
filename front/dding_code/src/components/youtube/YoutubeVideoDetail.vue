<!-- YoutubeVideoDetail.vue -->

<template>
  <div v-if="store.selectedVideo">
    <h4>영상상세보기</h4>
    <iframe
      width="600px"
      height="400px"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen
    ></iframe>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';
import { computed } from 'vue';

const store = useYoutubeStore();

const videoURL = computed(() => {
  const videoId = store.selectedVideo.id.videoId;
  return `https://www.youtube.com/embed/${videoId}`;
});
</script>

<style scoped>
/* 필요한 스타일 추가 */
</style>
