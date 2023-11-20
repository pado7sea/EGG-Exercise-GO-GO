<template>
  <div>
    <div id="map"></div>
    <div>
      <input v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
      <button @click="searchPlaces">검색</button>
    </div>
    <!-- 검색 결과 목록 -->
    <div id="placesList">
      <ul>
        <li v-for="(place, index) in searchResults" :key="index" @click="selectPlace(index)">
          {{ place.place_name }}
        </li>
      </ul>
    </div>

    <!-- 페이지 번호를 표시할 엘리먼트 -->
    <div id="pagination"></div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

let map = null;
let infowindow = null;
let ps = null;
const searchKeyword = ref('');
const searchResults = ref([]);
let markers = [];

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567),
    level: 5,
  };
  map = new kakao.maps.Map(container, options);

  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  ps = new kakao.maps.services.Places(map);

  // 사용자의 현재 위치를 얻어오는 함수 호출
  getCurrentLocation();
};

// 사용자의 현재 위치를 얻어오는 함수
const getCurrentLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const currentLocation = new kakao.maps.LatLng(lat, lng);

        // 지도의 중심을 현재 위치로 이동
        map.setCenter(currentLocation);
      },
      (error) => {
        console.error('Error getting user location:', error);
      }
    );
  } else {
    console.error('Geolocation is not supported by this browser.');
  }
};

function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    // 검색 결과를 저장하고 검색 결과 목록 갱신
    searchResults.value = data;

    // 마커 표시
    for (let i = 0; i < data.length; i++) {
      displayMarker(data[i]);
    }
  }
}

function displayMarker(place) {
  const marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(place.y, place.x),
  });

  markers.push(marker);

  kakao.maps.event.addListener(marker, 'click', function () {
    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
    infowindow.open(map, marker);
  });
}

function removeMarkers() {
  for (let i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

const searchPlaces = () => {
  removeMarkers();
  ps.keywordSearch(searchKeyword.value, placesSearchCB, { useMapBounds: true });
};

// 검색 결과 항목을 클릭했을 때 해당 장소의 위치로 지도를 이동하고 인포윈도우를 열도록 수정
const selectPlace = (index) => {
  const selectedPlace = searchResults.value[index];
  const position = new kakao.maps.LatLng(selectedPlace.y, selectedPlace.x);

  // 해당 장소의 위치로 지도를 이동
  map.panTo(position);

  // 해당 장소의 마커에 인포윈도우 열기
  displayInfowindow(markers[index], selectedPlace.place_name);
};

onMounted(async () => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}&libraries=services`;
    document.head.appendChild(script);
    script.onload = () => {
      kakao.maps.load(initMap);
    };
  }
});

</script>

<style scoped>
#map {
  width: 400px;
  height: 400px;
}

input {
  margin-top: 10px;
  padding: 5px;
}

button {
  margin-top: 10px;
  padding: 5px;
}

#placesList {
  margin-top: 10px;
}

#placesList ul {
  list-style: none;
  padding: 0;
}

#placesList li {
  cursor: pointer;
  padding: 5px;
  border: 1px solid #ddd;
  margin-bottom: 5px;
}

</style>
