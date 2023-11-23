<!-- kakaomap.vue -->
<template>
  <div class="ym-container">
    <div class="ym-backbox">
      <div class="ym-title">
        <div class="ym-whitecircle">
          <div class="어디서운동하고싶으신가요">
            어디서 운동하고 싶으신가요?
          </div>
        </div>
      </div>
      <div class="ym-content">
        <div class="ym-leftbox">
          <div class="카테고리안내문구">운동하고 싶은 장소를 골라보세요!</div>
          <div class="장소카테고리세부">
            <button @click="searchPlacesByCategory ('공원')" class="장소카테고리세부">공원</button>
            <button @click="searchPlacesByCategory ('산책로')" class="장소카테고리세부">산책로</button>
            <button @click="searchPlacesByCategory ('운동장')" class="장소카테고리세부">운동장</button>
          </div>
        </div>
        <div class="ym-rightbox">
          <Map v-model="selectedCategory" />
        </div>
      </div>
     
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import Map from './Map.vue';



const selectedCategory = ref('');

const searchPlacesByCategory =   (category) => {
   selectedCategory.value = category;
};

</script>

<style scoped>
.ym-container {
  margin: 0 10vw;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.ym-backbox {
  width: 100%;
  background: #b1c7fc;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.ym-title {
  /* margin: 0 10vw; */
  /* flex-shrink: 0; */
  background: #FEE58D;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
  border-radius: 20px;
  display: flex;
  justify-content: flex-start;
  padding: 1vh 1vw;
  align-items: center;
  margin: 2vh 2vw;
}

.ym-whitecircle {
  /* flex-shrink: 0; */
  background: white;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
  border-radius: 20px;
  display: flex;
  justify-content: center;
  padding: 0 10px 0 10px;
  align-items: center;

}

.ym-어디서운동하고싶으신가요 {
  color: #000;
  /* font-family: Inter; */
  font-size: 15px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.ym-content {
  display: flex;
  flex-direction: row;
  margin: 2vh 2vw;
  justify-content: space-around;
  /* background-color: rgba(255, 255, 255, 0.3);  */
}

.ym-leftbox {
  width: 50%;
  display: flex;
  flex-direction: column;
}


.카테고리안내문구 {
  color: #000;
  font-size: 15px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;

  margin-left: 5%;
  margin-bottom: 5%;
}


.장소카테고리세부 {
  background-color: #F8BD2A;
  width: 60px;
  height: 60px;
  border-radius: 5em;
  border-style: none;
  color: #151515;
  font-weight: bold;
  box-shadow: inset 4px 4px 4px 0px #FFF5D3;

  margin-left: 5%;
  margin-right: 5%;
}

.map {
  width: 800px;
  height: 400px;

}

</style>