<template>
    <div class="ym-container">
      <div class="ym-backbox">
        <div class="ym-title">
          <div class="ym-whitecircle">
            <div class="ym-어디서운동하고싶으신가요">
              무슨 운동을 하고 싶으신가요?
            </div>
          </div>
          <div class="카테고리안내문구" style="margin: 0.2vh  2vw;">하고 싶은 운동을 골라보세요!</div>
          <div class="ym-img-container">
            <div> <img src="@/assets/yoga-pose-m.png" id="indoor-icon"></div>
            <div> <img src="@/assets/yoga-pose-w.png" id="indoor-icon"></div>
            <div> <img src="@/assets/yoga-pose.png" id="indoor-icon"></div>
            <div> <img src="@/assets/weight-lifting.png" id="indoor-icon"></div>
          </div>
        </div>
        <div class="ym-content">
          <div class="ym-search">
            <OutdoorYoutubeVideoSearch class="ym-카테고리안내문구" />
          </div>
          <div class="ym-rightbox">
            <OutdoorYoutubeVideoDetail />
          </div>
          <div class="ym-listcategory">
            <OutdoorYoutubeVideoList />
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import OutdoorYoutubeVideoSearch from '@/components/OutdoorYoutube/OutdoorYoutubeVideoSearch.vue';
  import OutdoorYoutubeVideoList from '@/components/OutdoorYoutube/OutdoorYoutubeVideoList.vue';
  import OutdoorYoutubeVideoDetail from '../components/OutdoorYoutube/OutdoorYoutubeVideoDetail.vue';
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
    padding-top: 5vh;
    padding-bottom: 5vh;
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
    flex-direction: column;
    margin: 0 2vw;
    /* justify-content: space-around; */
    /* background-color: rgba(255, 255, 255, 0.3);  */
  }
  
  .ym-leftbox {
    /* width: 50%; */
    display: flex;
    /* flex-direction: column; */
  }
  
  .ym-rightbox{
    /* width: 50%; */
    display: flex;
    margin-left: 4vw;
    justify-content: center;
  }
  
  
  .ym-카테고리안내문구 {
    color: #000;
    font-size: 15px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
  
    /* margin-left: 5%;
    margin-bottom: 5%; */
  }
  
  /* .장소카테고리 {
    
    margin-left: 5%;
    margin-right: 5%;
  } */
  
  /* .장소카테고리세부 {
    background-color: #F8BD2A; */
    /* width: 60px;
    height: 60px; */
    /* border-radius: 5em;
    border-style: none;
    color: #151515;
    font-weight: bold;
    box-shadow: inset 4px 4px 4px 0px #FFF5D3; */
  
    /* margin-left: 5%;
    margin-right: 5%; */
  /* } */
  #indoor-icon{
    width: 40px;
    height: 40px;
  }
  
  .ym-img-container{
    display: flex;
    justify-content: space-around;
  }
  </style>
  