<!-- YoutubeVideoList.vue -->
<template>
    <div>
        <ul>
            <YoutubeVideoListItem v-for="(video, index) in store.videos.slice(0, 3)" :key="index" :video="video" />
        </ul>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube'
import YoutubeVideoListItem from './YoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style scoped>
</style>