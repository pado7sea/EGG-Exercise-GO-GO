<template>
  <div>
    <h4>오늘의 날씨</h4>
    <div>기온 : {{ tmp }}℃</div>
    <div>하늘상태 : {{ sky }}</div>
    <div>강수형태 : {{ pty }}</div>
    <div>강수확률 : {{ pop }}%</div>

    <div v-if="tmp < 10 || pty != 0 || tmp > 30">
      실내운동하기 좋은 날씨입니다!
    </div>
    <div v-else-if="tmp >= 10 && pty == 0 && tmp <= 30">
      실외운동하기 좋은 날씨입니다!
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";
import axios from "axios";
const tmp_real = ref(0);
const tmp = ref(null);
const sky = ref(null);
const pty = ref(null);
const pop = ref(0);
onMounted(() => {
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;
  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  const todayStr = `${year}${month}${day}`;
  console.log(todayStr);
  //발표시간을 전부 넣어둬
  const times = ['0200', '0500', '0800', '1100', '1400', '1700', '2000', '2300']//8개넣어 

  function findClosestTime(times) {
    const currentTime = new Date();
    const currentHours = currentTime.getHours();
    const currentMinutes = currentTime.getMinutes();

    const currentTotalMinutes = currentHours * 60 + currentMinutes;

    let closestTime = '';
    let minDifference = Infinity;

    for (const time of times) {
      const hours = parseInt(time.slice(0, 2));
      const minutes = parseInt(time.slice(2));

      const totalMinutes = hours * 60 + minutes;

      const timeDifference = Math.abs(totalMinutes - currentTotalMinutes);

      if (timeDifference < minDifference) {
        minDifference = timeDifference;
        closestTime = time;
      }
    }

    return closestTime;
  }

  console.log(findClosestTime(times))

  axios
    .get(API_URL, {
      params: {
        ServiceKey: import.meta.env.VITE_WEATHER_API_KEY,
        dataType: "JSON",
        base_date: todayStr, //20231105 형태
        base_time: findClosestTime(times),   //이것은 총 8회 발표 
        numOfRows: 15,
        nx: 66, // 온천2동
        ny: 101,
      },

    })
    .then((response) => {
      return response.data.response.body.items.item;
    })
    .then((response) => {
       
      //TMP : 1시간 기온 ℃
      //UUU : 풍속(동서) m/s
      //VVV : 풍속(남북) m/s
      //VEC : 풍향 deg
      //WSD : 풍속 m/s
      //SKY : 하늘상태 --> 코드 : 맑음(1), 구름많음(3), 흐림(4)
      //PTY : 강수형태 --> 코드 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
      //POP : 강수확률 %
      //WAV : 파고 M
      //PCP : 1시간 강수량 범주(1mm)
      //REH : 습도 %
      //SNO : 1시간 적설량
      //TMN : 일 최저기온 ℃ x
      //TMX : 일 최고기온 ℃ x
      response.forEach((item) => {
        if (item.category === "TMP") {
          tmp.value = item.fcstValue;
          tmp.real = tmp.value + 0
          console.log(tmp.real)
        } else if (item.category === "SKY") {
          switch (item.fcstValue) {
            case "1":
              sky.value = "맑음";
              break;
            case "3":
              sky.value = "구름많음";
              break;
            case "4":
              sky.value = "흐림";
              break;
          }
        } else if (item.category === "PTY") {
          pty.value = item.fcstValue;
        } else if (item.category === "POP") {
          pop.value = item.fcstValue;
        }
      });
    });

});

const weatherDescription = computed(() => {
  if (tmp.value !== null && pty.value !== null) {
    // TMP 조건
    let tmpDescription = '';
    if (tmp.value < 10) {
      tmpDescription = '춥습니다!';
    } else if (tmp.value < 20) {
      tmpDescription = '적당한 날씨입니다.';
    } else {
      tmpDescription = '덥습니다!';
    }

    // PTY 조건
    let ptyDescription = '';
    switch (pty.value) {
      case '1':
        ptyDescription = '비가 오고 있습니다.';
        break;
      case '2':
        ptyDescription = '비와 눈이 섞여 오고 있습니다.';
        break;
      case '3':
        ptyDescription = '눈이 오고 있습니다.';
        break;
      case '4':
        ptyDescription = '소나기가 오고 있습니다.';
        break;
      default:
        ptyDescription = '강수가 없습니다.';
    }

    return `기온 : ${tmp.value}℃ ${tmpDescription} 강수 형태 : ${ptyDescription}`;
  }

  return '';
});
</script>

<style scoped></style>
