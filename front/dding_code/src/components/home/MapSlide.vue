<!-- MapSlide.vue -->
<template>
  <div class="map-slide">
    <div>지도 콘텐츠</div>
    <div ref="map" class="map"></div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}`;
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const myCenter = new kakao.maps.LatLng(33.450701, 126.570667);
  const container = document.getElementById('map');
  const options = {
    center: myCenter,
    level: 3,
  };

  const map = new kakao.maps.Map(container, options);

  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는 줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  // 마커 추가
  const marker = new kakao.maps.Marker({
    position: myCenter,
    map: map,
  });
};
</script>

<style scoped>
.map-slide {
  width: 100%;
  height: 100%;
  
}

.map {
  width: 100%;
  height: 100%;
}
</style>
