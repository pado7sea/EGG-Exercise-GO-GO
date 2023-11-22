<template>
    <div>
        <div>
            <WeatherForecast/>
        </div>
        <div>
            <div>
                <router-link to="/Indoor">로그인 후 실내운동</router-link>|
            </div>
            <div>

            </div>
            <router-link to="/outdoor">로그인 후 야외운동</router-link>
        </div>

        <!-- <LoginView/> -->
        
    </div>
</template>

<script setup>
import WeatherForecast from '../components/weather/WeatherForecast.vue';
import LoginView from './LoginView.vue';



</script>

<style scoped>

div{
    font-family: Poppins, 'Noto Sans KR', sans-serif;
}
    
</style>