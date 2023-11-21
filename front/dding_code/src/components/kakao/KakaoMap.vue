<template>
  <div class="container">
    <div class="backbox">
      <div class="title">
        <div class="whitecircle">
          <div class="어디서운동하고싶으신가요">
            어디서 운동하고 싶으신가요?
          </div>
        </div>
      </div>
      <div class="content">
        <div class="content왼쪽">
          <div class="카테고리안내문구">운동하고 싶은 장소를 골라보세요!</div>
          <div class="장소카테고리">
            <button class="장소카테고리세부">공원</button>
            <button class="장소카테고리세부">산책로</button>
            <button class="장소카테고리세부">운동장</button>
          </div>
        </div>
        <div class="map">
          카카오맵 지도 들어갈 자리
          <Map />
        </div>
      </div>
     
    </div>
  </div>
</template>

<script setup>
import Map from './Map.vue';

</script>

<style scoped>
.container {
  width: 100%;
  display: flex;
  justify-content: center;
}

.backbox {
  background: #FFFCF0;
  border-radius: 20px;
  width: 1167px;
  height: 599px;
  flex-shrink: 0;
  display: flex;
  float: left;
  flex-direction: column;
  justify-content: center;
  
}

.title {
  width: 1067px;
  height: 50px;
  flex-shrink: 0;
  background: #FEE58D;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
  border-radius: 20px;
  
  display: flex;
  justify-content: left;
  padding-left: 1%;
  align-items: center;
  margin: 3% auto 0;
}

.whitecircle {
  width: 254px;
  height: 33px;
  flex-shrink: 0;
  background: white;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
  border-radius: 20px;

  display: flex;
  justify-content: center;
  align-items: center;

}

.어디서운동하고싶으신가요 {
  color: #000;
  font-family: Inter;
  font-size: 15px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.content {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 5%;
}

.content왼쪽 {
  width: 50%;
  display: flex;
  flex-direction: column;
  align-items: space-around;
}

.카테고리안내문구 {
  color: #000;
  font-family: Inter;
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