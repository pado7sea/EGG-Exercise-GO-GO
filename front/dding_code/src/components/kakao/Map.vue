<template>
  <div>
    <div id="map"></div>
    <div>
      <input @keyup.enter="searchPlaces" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
      <button @click="searchPlaces">검색</button>
    </div>

    <!-- 검색 결과 목록 -->
    <div id="placesList">
      <div v-for="(place, index) in paginatedPlaces" :key="index" class="place-item" @click="selectPlace(place)">
        <span class="markerbg marker_{{ (currentPage - 1) * pageSize + index + 1 }}"></span>
        <div class="info">
          <h5>{{ place.place_name }}</h5>
          <span>{{ place.address_name }}</span>
          <span class="tel">{{ place.phone }}</span>
        </div>
      </div>
    </div>

    <!-- 페이지 번호를 표시할 엘리먼트 -->
    <div v-if="pagination.length > 0" id="pagination" class="pagination">
      <span @click="gotoPage(1)" :class="{ 'disabled': currentPage === 1 }">&lt;&lt;</span>
      <span @click="gotoPage(currentPage - 1)" :class="{ 'disabled': currentPage === 1 }">&lt;</span>
      <span v-for="page in pagination" :key="page" @click="gotoPage(page)" :class="{ 'active': page === currentPage }">{{
        page }}</span>
      <span @click="gotoPage(currentPage + 1)" :class="{ 'disabled': currentPage === totalPage }">&gt;</span>
      <span @click="gotoPage(totalPage)" :class="{ 'disabled': currentPage === totalPage }">&gt;&gt;</span>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';

let map = null;
let infowindow = null;
let ps = null;
const searchKeyword = ref('');
let markers = [];
const places = ref([]);
const currentPage = ref(1);
const pageSize = 3; // 페이지당 표시할 결과 수
const totalPlaces = ref(0);
const pagination = ref([]);
const totalPage = computed(() => Math.ceil(totalPlaces.value / pageSize)); // 전체 페이지 수 계산


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
    totalPlaces.value = pagination.totalCount; // 전체 장소 수 업데이트
    updatePagination(); // 페이지 번호 업데이트
    displayPlaces(data);
  }
}

function updatePagination() {
  pagination.value = Array.from({ length: totalPage.value }, (_, index) => index + 1);
}

// 검색된 장소를 지도에 표시하고 목록에 나열하는 함수
function displayPlaces(placesData) {
  places.value = placesData;
  // removeMarkers();
  for (let i = 0; i < placesData.length; i++) {
    displayMarker(placesData[i], i);
  }
  // // 현재 페이지의 첫 번째 장소로 지도 중심 이동
  // const startIndex = (currentPage.value - 1) * pageSize;
  // map.setCenter(new kakao.maps.LatLng(placesData[startIndex].y, placesData[startIndex].x));
}

// 마커를 지도에 추가하고 마커를 클릭했을 때 인포윈도우를 표시하는 함수
function displayMarker(place, index) {
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

// 마커를 추가하고 해당 마커를 반환하는 함수
const addMarker = (position, index) => {
  const marker = new kakao.maps.Marker({
    position: position,
    map: map,
    clickable: true,
  });

  // 마커 클릭 시 인포윈도우 열기
  kakao.maps.event.addListener(marker, 'click', function () {
    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + places.value[index].place_name + '</div>');
    infowindow.open(map, marker);
  });

  return marker;
};

// 마커를 지도에서 제거하는 함수
function removeMarkers() {
  for (let i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

// 장소 검색 실행 함수
const searchPlaces = () => {
  if (!searchKeyword.value.trim()) {
    alert('검색어를 입력하세요!');
    return;
  }
  removeMarkers();
  ps.keywordSearch(searchKeyword.value, placesSearchCB, { useMapBounds: true });
};

// 페이지 번호를 클릭했을 때 실행되는 함수
const gotoPage = (page) => {
  if (page < 1 || page > totalPage.value) {
    return;
  }
  currentPage.value = page;
  searchPlaces();
};

// 특정 장소를 선택했을 때 실행되는 함수
const selectPlace = (place) => {
  const placePosition = new kakao.maps.LatLng(place.y, place.x);

  // 새로운 마커 추가
  const marker = addMarker(placePosition, 0);
  markers.push(marker);

  // 인포윈도우 열기
  infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
  infowindow.open(map, marker);

  // 지도 중심 이동
  map.setCenter(placePosition);
};

const paginatedPlaces = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  return places.value.slice(startIndex, endIndex);
});

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

.place-item {
  cursor: pointer;
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 5px;
}

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pagination span {
  cursor: pointer;
  padding: 5px;
  margin-right: 5px;
  border: 1px solid #ddd;
}

.pagination span.active {
  background-color: #007BFF;
  color: #fff;
}

.pagination span.disabled {
  color: #ddd;
  cursor: not-allowed;
}
</style>
