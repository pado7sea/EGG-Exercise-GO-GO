<!-- Carousel.vue -->
<template>
    <div class="carousel-wrapper">
      <div class="button-class">
        <button @click="prevSlide" class="prev" id="slide-button"><img src="@/assets/previous.png" style="width: 29px; height: 29px;"></button>
        <button @click="nextSlide" class="next" id="slide-button"><img src="@/assets/next.png" style="width: 29px; height: 29px;"></button>
      </div>
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
  import Map from '@/components/kakao/Map.vue';
  
  export default {
    components: {
      MapSlide,
      VideoSlide,
    },
    data() {
      return {
        carouselItems: [
          { type: 'video', content: 'https://youtu.be/4P-fUsQ3T-c?si=re3mUnRmpwdPjhFm' },
          { type: 'map', content: Map },
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
    overflow: hidden;
  }
  
  .carousel-wrapper>.carousel {
    display: flex;
    transition: transform 0.2s;
    background-color: #FFF;
  }
  
  .carousel-wrapper>.carousel>.carousel-item {
    flex-shrink: 0;
    width: 100%;
    display: flex;
    flex-direction: column;
    background-color: #fff;
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
  
  #slide-button{
    background-color:transparent;
    border: 0px;
  }
  
  .button-class{
    display: flex;
    padding-top: 5px;
    justify-content: space-between;
  }
  </style>
  