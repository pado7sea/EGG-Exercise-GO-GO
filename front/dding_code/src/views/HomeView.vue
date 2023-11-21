<template>
    <div>
        <div>테스트용라우터..</div>
        <WeatherForecast/>
        <router-link to="/Indoor">로그인 후 실내운동</router-link>|
        <router-link to="/outdoor">로그인 후 야외운동</router-link>
        <!-- <LoginView/> -->
        
    </div>
</template>

<script setup>
import WeatherForecast from '../components/weather/WeatherForecast.vue';
import LoginView from './LoginView.vue';



</script>

<style scoped>

</style>