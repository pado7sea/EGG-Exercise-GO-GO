<!-- Path.vue -->
<template>
    <div>
        <button @click="sendDirectionsRequest">Send Directions Request</button>
        <button @click="sendUpdatedRoutes">Send Updated Routes</button>
        <!-- <div v-if="responseData">
            <p>Response Data: {{ responseData }}</p>
            <div id="map"></div>
        </div> -->
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import axios from 'axios';

//Map.vue에서 Path.vue로 받은 데이터
const props = defineProps(['origin', 'destinations', 'customKey']);
const emit = defineEmits();



// Path.vue 내의 데이터
const mapRoutes = ref(null);
const responseData = ref(null);

// API 요청 함수
const sendDirectionsRequest = async () => {
    console.log('Origin:', props.origin);
    console.log('Destinations:', props.destinations);
    
    const data = {
        origin: {
            x: props.origin.La,
            y: props.origin.Ma
        },
        destinations: props.destinations.map((destination, index) => ({
            ...destination,
            key: props.customKey,
        })),
        radius: 5000, //반경 5km
    };

    console.log('Request data:', data);

    try {
        const response = await axios.post(
            'https://apis-navi.kakaomobility.com/v1/destinations/directions',
            data,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `KakaoAK ${import.meta.env.VITE_KAKAO_REST_API_KEY}`,
                },
            }
        );

        console.log('Response:', response.data);
        responseData.value = response.data;


    } catch (error) {
        console.error('Error sending directions request:', error);
    }
};
const sendUpdatedRoutes = () => {
    // 업데이트된 경로 데이터를 부모로 전달
    emit('updateRoutes', responseData);
};


</script>
  
<style scoped></style>
  