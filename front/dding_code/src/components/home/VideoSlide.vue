<!-- VideoSlide.vue -->
<template>
    <div class="video-slide">
        <div>영상 컨텐츠</div>
        <div class="video-container">
            <iframe :src="videoSource" frameborder="0" allowfullscreen></iframe>
        </div>
    </div>
</template>
  
<script>
export default {
    props: {
        videoSource: String,
    },
    watch: {
        videoSource(newSource) {
            // videoSource 변경 감지
            if (newSource.includes('youtube.com')) {
                // YouTube 비디오인 경우 API 호출 및 처리 로직을 여기에 추가
                // 예: YouTube API를 사용하여 동적으로 비디오 정보 가져오기
                // 비디오 정보를 가져온 후에는 iframe 소스를 업데이트
                this.updateYouTubeVideo(newSource);
            }
        },
    },
    methods: {
        updateYouTubeVideo(videoSource) {
            // YouTube API 호출 및 비디오 정보 업데이트 로직을 작성
            // 예: YouTube API를 사용하여 비디오 정보 가져오기
            // 가져온 정보를 기반으로 iframe 소스 업데이트
            // this.$refs.iframe.src = updatedYouTubeVideoSource;
        },
    },
};
</script>
  
<style scoped>
.video-slide {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
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
</style>
  