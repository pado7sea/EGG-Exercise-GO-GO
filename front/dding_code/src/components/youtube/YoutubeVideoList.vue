<!-- YoutubeVideoList.vue -->
<template>
    <div>
        <div class="youtube-list">
            <YoutubeVideoListItem v-for="(video, index) in store.videos.slice(0, 3)" :key="index" :video="video" />
        </div>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube'
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style scoped>
.youtube-list{
    display: grid;
    margin: 10px 20px;
    grid-template-columns: repeat(3, 1fr);
}
</style>