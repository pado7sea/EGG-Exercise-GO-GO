<template>
  <div @click="clickVideo">
    <img :src="video.snippet.thumbnails.default.url" class="thumbnail">
    <br>
    <span>{{video.snippet.title}}</span>
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
</style>
