<!-- YoutubeVideoDetail.vue -->

<template>
  <div v-if="store.selectedVideo" class="video-detail-container">
    <!-- <h4>영상상세보기</h4> -->
    <iframe
      width="600px"
      height="400px"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen
      style="border-radius: 30px;"
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
.video-detail-container{
  margin-top: 3vh;
  padding: 3vh 5vw;
  background-color: rgba(255, 255, 255, 0.5);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 20px;
}
</style>
