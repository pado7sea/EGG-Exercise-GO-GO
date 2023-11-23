<!-- YoutubeVideoList.vue -->
<template>
    <div class="youtube-list">
        <div>
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
    margin: 2vh 2vw;
}

</style>