<template>
  <div class="container">
    <div class="backbox">
      <div class="title">
        <div class="whitecircle">
          <div class="어디서운동하고싶으신가요">
            무슨 운동을 하고 싶으신가요?
          </div>
        </div>
      </div>
      <div class="content">
        <div class="leftbox">
          <div class="카테고리안내문구">하고 싶은 운동을 골라보세요!</div>
          <YoutubeVideoSearch class="카테고리안내문구" />
          <div class="장소카테고리">

            <!-- <button class="장소카테고리세부">#실내운동</button>
            <button class="장소카테고리세부">#홈트</button>
            <button class="장소카테고리세부">#맨몸운동</button> -->

          </div>
          <YoutubeVideoList class="장소카테고리" />
        </div>
        <div class="rightbox">
          <YoutubeVideoDetail />


        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import YoutubeVideoSearch from '@/components/youtube/YoutubeVideoSearch.vue';
import YoutubeVideoList from '@/components/youtube/YoutubeVideoList.vue';
import YoutubeVideoDetail from '../components/youtube/YoutubeVideoDetail.vue';
</script>

<style scoped>
.container {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 10vh;
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

.leftbox {
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

/* .장소카테고리 {
  
  margin-left: 5%;
  margin-right: 5%;
} */

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

.rightbox {
  width: 693px;
  height: 599px;
  flex-shrink: 0;
  border-radius: 20px;
  background: #FFFCF0;

  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 5%;
  margin-bottom: 5%;
}
</style>
