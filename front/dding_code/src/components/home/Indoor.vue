<template>
    <div class="container">
        <div class="backbox">
            <div class="leftbox">
                <div class="hibox">
                    <div class="hi">안녕하세요, 서윤님</div>
                    <div class="hi">실내 운동하기 좋은 날씨입니다.</div>
                </div>
                <div class="temperbox">
                    <div>
                        <div>현재기온</div>
                        <div>-5도</div>
                    </div>
                </div>

            </div>
            <div class="rightbox">
                <div class="title">
                    <div class="안내문구">현재 위치와 가까운 실내운동하기 좋은 장소들을 알려드려요!</div>
                </div>
                <div class="content">
                    <div class="whitebox">
                        <!-- <div class="address">주소</div> -->
                        <!-- <div class="map"></div> -->
                        <Carousel class="map"/>
                    </div>
                    <div class="contentmore">
                        <div class="whitecircle">
                            <div class="more">더 많은 장소 알아보기</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>





    </div>
</template>

<script>
import Carousel from './Carousel.vue';

export default {
  components: {
    Carousel,
  }
}

</script>


<style scoped>
div{
    font-family: Poppins, 'Noto Sans KR', sans-serif;
}
   
.container {
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 10vh;
}

.backbox {
    width: 1167px;
    height: 599px;
    flex-shrink: 0;
    display: flex;
    flex-direction: row;
    justify-content: center;

}

.leftbox {
    display: flex;
    flex-direction: column;

    margin-left: 5%;
    margin-bottom: 5%;
}

.hibox {
    width: 299px;
    height: 63px;
    flex-shrink: 0;

    display: flex;
    flex-direction: column;
    margin: 5%;
}


.hi {
    color: #000;
    font-size: 25px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}

.temperbox {
    width: 328px;
    height: 49px;
    flex-shrink: 0;
    color: #000;
    font-size: 30px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;

    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: auto 0;
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

.title {
    width: 648px;
    height: 50px;
    flex-shrink: 0;
    background: #FFF5D3;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
    border-radius: 20px;

    display: flex;
    justify-content: center;
    align-items: center;
    /* padding-left: 3%; */
    margin: 3% auto 0;
}

.안내문구 {

    color: #000;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
}

/* .content{

} */

.whitebox {
    width: 648px;
    height: 416px;
    flex-shrink: 0;
    border-radius: 20px;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.05);

    margin-top: 5%;
    margin-bottom: 5%;
}

.address {
    width: 531px;
    height: 33px;
    flex-shrink: 0;
    color: #000;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;

    padding-left: 3%;
    padding-top: 2%;


}

.map {
    width: 648px;
    height: 358px;
    flex-shrink: 0;
    border-radius: 20px;
    background: url(<path-to-image>), lightgray 50% / cover no-repeat;


}

.contentmore {
    display: flex;
    align-items: flex-end;
    margin-bottom: 5%;
    margin-left: 65%;
}

.whitecircle {
    width: 227px;
    height: 33px;
    flex-shrink: 0;
    border-radius: 20px;
    background: #FFF;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.05);

    display: flex;
    justify-content: center;
    align-items: center;
    margin: 3% 0;
}


</style>