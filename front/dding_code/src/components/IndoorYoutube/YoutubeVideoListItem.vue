<template>
  <div @click="clickVideo" class="youtube-card">
    <img :src="video.snippet.thumbnails.medium.url" class="thumbnail" style="border-radius: ;">
    <div class="youtube-card-des">
      <div>
        <img src="@/assets/youtube-icon.png" style="width: 48px; margin-left: 3px;">
      </div>
      <div class="youtube-card-des-txt" style="margin-left: 10px;">
        <div style="font-weight: bold;">{{video.snippet.title}}</div>
        <div>{{ video.snippet.channelTitle }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube';

const store = useYoutubeStore();

const props = defineProps({
  video: {
    type: Object,
    required: true,
  },
});

const clickVideo = function() {
  store.clickVideo(props.video);
}
</script>

<style scoped>
.thumbnail {
  width: 100%;
  cursor: pointer;
}

.youtube-card{
  background-color: rgba(255, 255, 255, 0.4);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  margin-top: 20px;
  margin-left: 10px;
  margin-right: 10px;
}

.youtube-card-des{
  display: flex;
  margin-top: 3px;
}

.youtube-card-des-txt{
  display: flex;
  flex-direction: column;
}
</style>
