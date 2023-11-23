<!-- MapSlide.vue -->
<template>
  <div class="map-slide">
    <div class="map">
      <div>지도 콘텐츠</div>
      <div id="map"></div>
      <div>
        <!-- 검색어 초기값 설정 -->
        <input @keyup.enter="searchPlaces" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
        <button @click="searchPlaces">버튼을 눌러서 알을 얻어보세요!</button>
      </div>
      <div><a href="/kakao" style="text-decoration: none; color: black;">더 많은 장소 알아보기</a></div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';

let map = null;
let infowindow = null;
let ps = null;

// 기본 검색어 설정
const defaultSearchKeyword = '헬스장';

const searchKeyword = ref(defaultSearchKeyword);
const searchResults = ref([]);
let markers = [];
let currentLocationMarker = null;
const places = ref([]);

const initMap = async () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567),
    level: 5,
  };

  if (kakao.maps.Map) {
    map = new kakao.maps.Map(container, options);
    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    ps = new kakao.maps.services.Places(map);

    // '기본검색어'을 검색하여 마커 표시
    await searchPlaces();

    // 사용자의 현재 위치를 얻어오는 함수 호출
    getCurrentLocation();

  } else {
    console.error('Kakao Maps library not loaded.');
  }
};

onMounted(async () => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}&libraries=services`;
    document.head.appendChild(script);
    script.onload = () => {
      kakao.maps.load(() => {
        initMap();
      });
    };
  }
});

// 사용자의 현재 위치를 얻어오는 함수
const getCurrentLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const newCurrentLocation = new kakao.maps.LatLng(lat, lng);

        // currentLocation 변수가 선언되지 않았기 때문에 .value 대신에 newCurrentLocation 사용
        console.log("현재 위치 좌표 ", newCurrentLocation);

        // 지도의 중심을 현재 위치로 이동
        map.setCenter(newCurrentLocation);

        // 현재 위치 마커를 표시
        displayCurrentLocationMarker(newCurrentLocation);

      },
      (error) => {
        console.error('Error getting user location:', error);
      }
    );
  } else {
    console.error('Geolocation is not supported by this browser.');
  }
};

// 현재 위치를 지도에 표시하는 마커를 생성하고 지도에 표시하는 함수
const displayCurrentLocationMarker = (currentLocation) => {
  // 현재 위치 마커 초기화
  if (currentLocationMarker) {
    currentLocationMarker.setMap(null);
  }

  // 병아리 마커 이미지 정보
  const redCircleImageSrc = './src/assets/병아리.png';
  const redCircleImageSize = new kakao.maps.Size(50, 50);
  const redCircleImageOption = {
    offset: new kakao.maps.Point(25, 50),  // 이미지의 중심점
  };

  // 현재 위치 마커 생성
  currentLocationMarker = new kakao.maps.Marker({
    position: currentLocation, // .value 제거
    map: map,
    clickable: true,
    image: new kakao.maps.MarkerImage(redCircleImageSrc, redCircleImageSize, redCircleImageOption),
  });

  // 마커 클릭 시 인포윈도우 열기
  kakao.maps.event.addListener(currentLocationMarker, 'click', function () {
    infowindow.setContent('<div style="padding:5px;font-size:12px;">현재 위치</div>');
    infowindow.open(map, currentLocationMarker);
  });
};

// 장소 검색 실행 함수
const searchPlaces = async () => {

  // 검색어가 없으면 기본 검색어로 설정
  const keyword = searchKeyword.value.trim() ? searchKeyword.value : defaultSearchKeyword;

  removeMarkers();


  // 검색 결과를 지도에 표시하는 함수 호출
  await ps.keywordSearch(keyword, placesSearchCB, { useMapBounds: true });
};

// 검색 결과를 처리하는 콜백 함수
const placesSearchCB = (data, status) => {
  if (status === kakao.maps.services.Status.OK) {
    // 검색 결과가 있을 때 마커 표시
    displayPlaces(data);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    // 검색 결과가 없을 때 메시지 표시
    alert('검색 결과가 없습니다.');
  } else if (status === kakao.maps.services.Status.ERROR) {
    // 검색 중 에러 발생 시 메시지 표시
    alert('검색 중 오류가 발생했습니다.');
  }
};

// 검색 결과를 지도에 표시하는 함수
const displayPlaces = (places) => {
  places.forEach((place) => {
    const marker = new kakao.maps.Marker({
      map: map,
      position: new kakao.maps.LatLng(place.y, place.x),
      clickable: true,
    });

    // 마커 클릭 시 인포윈도우 열기
    kakao.maps.event.addListener(marker, 'click', function () {
      infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
      infowindow.open(map, marker);
    });

    // 생성된 마커를 markers 배열에 추가
    markers.push(marker);
  });
};

// 이전에 생성된 마커들을 제거하는 함수
const removeMarkers = () => {
  markers.forEach((marker) => {
    marker.setMap(null);
  });
  markers = [];
};

</script>

<style scoped>
.map-slide {
  width: 100%;
  height: 250px;
}

#map {
  width: 100%;
  height: 100%;
}

.map {
  width: 100%;
  height: 100%;
}
</style>
