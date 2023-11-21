<!-- Map.vue -->
<template>
    <div>
      <div id="map"></div>
      <div>
        <input @keyup.enter="searchPlaces" v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
        <button @click="searchPlaces">검색</button>
      </div>
  
      <!-- 검색 결과 목록과 경로 정보 표시 -->
      <div>
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
  
        <!-- Path 컴포넌트 추가 -->
        <!-- :origin과 :destinations를 currentLocation과 placeCoordinates로 전달 -->
        <Path :customKey="generateCustomKey(index)" :origin="currentLocation" :destinations="placeCoordinates"
          @updateRoutes="updateRoutes" />
  
        <!-- 경로 정보 표시 -->
        <div v-if="mapRoutes && mapRoutes.length > 0">
          <h2>경로 정보</h2>
          <ul>
            <li v-for="(route, index) in mapRoutes" :key="index">
              <strong>목적지 {{ index + 1 }}:</strong>
              <p>거리: {{ route.summary.distance }} 미터</p>
              <p>소요 시간: {{ route.summary.duration }} 초</p>
            </li>
          </ul>
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
  import Path from './Path.vue';
  
  let map = null;
  let infowindow = null;
  let ps = null;
  const searchKeyword = ref('');
  const searchResults = ref([]);
  let markers = [];
  let currentLocationMarker = null;
  const places = ref([]);
  
  const currentPage = ref(1);
  const pageSize = 3; // 페이지당 표시할 결과 수
  const totalPlaces = ref(0);
  const pagination = ref([]);
  const totalPage = computed(() => Math.ceil(totalPlaces.value / pageSize)); // 전체 페이지 수 계산
  const paginatedPlaces = computed(() => {
    const startIndex = (currentPage.value - 1) * pageSize;
    const endIndex = startIndex + pageSize;
    return places.value.slice(startIndex, endIndex);
  });
  
  const generateCustomKey = (index) => `destination_${index + 1}_${Date.now()}`;
  const combinedList = computed(() => {
    const list = [];
    for (let i = 0; i < Math.max(paginatedPlaces.length, mapRoutes.length); i++) {
      if (i < paginatedPlaces.length) {
        list.push({
          type: 'place',
          name: paginatedPlaces[i].place_name,
          address: paginatedPlaces[i].address_name,
          phone: paginatedPlaces[i].phone,
        });
      }
      if (i < mapRoutes.length) {
        list.push({
          type: 'route',
          route: mapRoutes[i],
          index: i,
        });
      }
    }
    return list;
  });
  
  //Path.vue로 보내는 데이터들
  const currentLocation = ref(null); // 현재 위치 좌표
  const placeCoordinates = ref([]); // 장소 리스트 좌표
  
  // Path.vue로부터 받은 경로 데이터
  let mapRoutes = [];
  
  const initMap = () => {
    const container = document.getElementById('map');
    const options = {
      center: new kakao.maps.LatLng(37.566826, 126.9786567),
      level: 5,
    };
  
    if (kakao.maps.Map) {
      map = new kakao.maps.Map(container, options);
      infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      ps = new kakao.maps.services.Places(map);
  
      // 사용자의 현재 위치를 얻어오는 함수 호출
      getCurrentLocation();
  
    } else {
      console.error('Kakao Maps library not loaded.');
    }
  
  };
  
  // 사용자의 현재 위치를 얻어오는 함수
  const getCurrentLocation = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lng = position.coords.longitude;
          const newCurrentLocation = new kakao.maps.LatLng(lat, lng);
  
          currentLocation.value = newCurrentLocation;
          console.log("현재 위치 좌표 ", currentLocation.value);
  
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
  
  const getPlaceCoordinates = (placesData) => {
    if (placesData) {
      placeCoordinates.value = placesData.map(place => ({
        x: place.x,
        y: place.y
      }));
      console.log("placeCoordinates.value", placeCoordinates.value);
  
      // 데이터가 갱신되면 Path.vue로 routes를 업데이트
      updateRoutes();
    } else {
      console.error("placesData is undefined");
    }
  };
  
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
  
      totalPlaces.value = pagination.totalCount; // 전체 장소 수 업데이트
      updatePagination(); // 페이지 번호 업데이트
      displayPlaces(data);
  
      // 장소 검색 결과가 있을 때 장소 좌표 업데이트
      getPlaceCoordinates(data);
  
    }
  }
  
  function updatePagination() {
    pagination.value = Array.from({ length: totalPage.value }, (_, index) => index + 1);
  }
  
  
  // 검색된 장소를 지도에 표시하고 목록에 나열하는 함수
  function displayPlaces(placesData) {
    places.value = placesData;
    for (let i = 0; i < placesData.length; i++) {
      displayMarker(placesData[i], i);
    }
  
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
  
  // Path.vue로부터 경로 데이터를 받아서 지도에 표시하는 함수
  const updateRoutes = (routes) => {
    console.log('Received updated routes:', routes);
  
    // 기존에 표시된 Polyline 제거
    removeMapPolyline();
  
    // 새로 받은 경로 데이터로 Polyline 표시
    displayMapPolyline(routes);
  
    // 경로 정보를 전역 변수에 할당
    mapRoutes = routes.value.routes;
  };
  
  // 기존에 표시된 Polyline을 제거하는 함수
  const removeMapPolyline = () => {
    // 전역 변수 polyline이 존재하고 지도에 추가되어 있다면 제거
    if (polyline && polyline.getMap()) {
      polyline.setMap(null);
    }
  };
  
  let polyline = null; // 전역 변수로 선언
  
  // 새로 받은 경로 데이터로 Polyline을 표시하는 함수
  const displayMapPolyline = (routes) => {
    // routes가 정의되지 않았거나 routes.routes가 배열이 아니면 함수 종료
    if (!routes || !Array.isArray(routes.routes)) {
      return;
    }
  
    // 기존 Polyline 제거
    removeMapPolyline();
  
    // 새로운 Polyline 표시
    const path = routes.routes.reduce((acc, route) => {
      // 경로 데이터에서 각 좌표 추출
      const coordinates = route.path.map(point => ({
        x: point.x,
        y: point.y
      }));
  
      // acc 배열에 현재 route의 좌표를 추가
      return [...acc, ...coordinates];
    }, []);
  
  
    // 지도에 표시할 선을 생성
    // Polyline 옵션 설정 (선 색상, 두께 등)
    const newPolyline = new kakao.maps.Polyline({
      path: path,
      strokeWeight: 5,
      strokeColor: '#FFAE00',
      strokeOpacity: 0.7,
      strokeStyle: 'solid',
    });
  
    // 지도에 Polyline 추가
    newPolyline.setMap(map);
  
    // polyline을 전역 변수에 할당
    polyline = newPolyline;
  };
  
  
  onMounted(async () => {
    if (window.kakao && window.kakao.maps) {
      initMap();
      await getCurrentLocation();
      await getPlaceCoordinates();
    } else {
      const script = document.createElement('script');
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_API_KEY}&libraries=services`;
      document.head.appendChild(script);
      script.onload = () => {
        kakao.maps.load(() => {
          initMap();
          getCurrentLocation();
          getPlaceCoordinates();
        });
      };
    }
  });
  
  </script>
  
  <style scoped>
  #map {
    width: 700px;
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
  