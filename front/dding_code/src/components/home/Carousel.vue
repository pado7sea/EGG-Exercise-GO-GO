<!-- Carousel.vue -->
<template>
  <div class="carousel-wrapper">
    <button @click="prevSlide" class="prev">이전</button>
    <button @click="nextSlide" class="next">다음</button>
    <div ref="carouselRef" class="carousel">
      <div v-for="(item, i) in carouselItems" :key="i" class="carousel-item">
        <VideoSlide v-if="item.type === 'video'" :videoSource="item.content" />
        <MapSlide v-if="item.type === 'map'" :mapSource="item.content" />
      </div>
    </div>
  </div>
</template>

<script>
import MapSlide from './MapSlide.vue';
import VideoSlide from './VideoSlide.vue';

export default {
  components: {
    MapSlide,
    VideoSlide,
  },
  data() {
    return {
      carouselItems: [
        { type: 'video', content: 'https://www.youtube.com/embed/kETh8T3it4k?si=p6ltz1G2TO4QL4lJ' },
        { type: 'map', content: 'https://your-map-api-url' },
        // Add more carousel items as needed
      ],
      index: 0,
    };
  },
  methods: {
    prevSlide() {
      if (this.index === 0) return;
      this.index -= 1;
      this.$refs.carouselRef.style.transform = `translate3d(-${100 * this.index}%, 0, 0)`;
    },
    nextSlide() {
      if (this.index === this.carouselItems.length - 1) return;
      this.index += 1;
      this.$refs.carouselRef.style.transform = `translate3d(-${100 * this.index}%, 0, 0)`;
    },
  },
};
</script>

<style scoped>
/* 캐러셀 */
* {
  box-sizing: border-box;
}

.carousel-wrapper {
  width: 500px;
  height: 300px;
  overflow: hidden;
}

.carousel-wrapper>.carousel {
  display: flex;
  transition: transform 0.2s;
}

.carousel-wrapper>.carousel>.carousel-item {
  flex-shrink: 0;
  width: 100%;
  display: flex;
  flex-direction: column;
}

.carousel-wrapper>.carousel>.carousel-item>video {
  width: 100%;
  height: auto;
}

.carousel-wrapper>.carousel>.carousel-item>.text-content {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  font-weight: bold;
}
</style>
