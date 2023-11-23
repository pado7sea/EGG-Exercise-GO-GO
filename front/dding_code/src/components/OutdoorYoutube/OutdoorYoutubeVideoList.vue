<!-- YoutubeVideoList.vue -->
<template>
    <div >
        <div class="youtube-list">
            <OutdoorYoutubeVideoListItem v-for="(video, index) in store.videos.slice(0, 3)" :key="index" :video="video" />
        </div>
    </div>
</template>

<script setup>
import { useYoutubeStore } from '@/stores/youtube'
import OutdoorYoutubeVideoListItem from './OutdoorYoutubeVideoListItem.vue';

const store = useYoutubeStore()
</script>

<style scoped>
.youtube-list{
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    margin: 2vh 2vw;
}

</style>