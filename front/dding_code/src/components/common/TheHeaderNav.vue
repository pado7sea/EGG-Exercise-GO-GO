<template>
    <div>
        <div v-if="!store.LoginUser.id" class="nav-top-before-login">
            <router-link to="/">로그인</router-link>/
            <router-link to="/sign">회원가입</router-link>
        </div>
        <div v-if="store.LoginUser.id" class="nav-top">
            <div style="font-size: large;" class="egg-count-container">
      <span class="egg-count-span" @mouseover="showEggCount" @mouseleave="hideEggCount" style="font-family: 'Jua', sans-serif; font-size: x-large;">양계장 상황</span>
      <div v-if="store.LoginUser.egg_count === null || store.LoginUser.egg_count === 0" class="egg-count-box">
        0개
      </div>
      <div v-if="store.LoginUser.egg_count > 0" class="egg-count-box">
        <template v-for="eggIndex in store.LoginUser.egg_count">
          🥚
        </template>
      </div>
    </div>
            <div v-if="isMyPageVisible">
                <div class="pop-up-mypage">
                    <UserMyPage />
                </div>
            </div>
            <div>
                <button type="button" class="toggle-page" @click="toggleMyPage">
                    <span style="font-family: 'Jua', sans-serif; font-size: x-large;">{{ store.LoginUser.name }}</span>
                </button>
                님 반갑습니다! 
                <!-- <button @click="confirmLogout" class="logout-button">로그아웃 </button> -->
            </div>
        </div>
        <div class="title">
            <router-link to="/home"><img src="@/assets/egglogo_all.png" alt="mainlogo"
                    style="width: 240px; height: 154px;"></router-link>
        </div>
        <header class="nav-container">
            <RouterLink to="/home" class="nv">Home</RouterLink>
            <RouterLink to="/board" class="nv">Board</RouterLink>
            <RouterLink to="/friend" class="nv">Friend</RouterLink>
            <div v-if="tmp < 10 || pty != 0 || tmp > 30">
                <RouterLink to="/iyoutube" class="nv">Youtube</RouterLink>
            </div>
            <div v-else-if="tmp>=10 && pty == 0 && tmp <= 30">
                <RouterLink to="/oyoutube" class="nv">Youtube</RouterLink>
            </div>
            <RouterLink :to="{ name: 'kakao' }" class="nv">Map</RouterLink>
        </header>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useUserStore } from '../../stores/user';
import { ref, onMounted, computed } from 'vue';
import UserMyPage from '@/components/common/UserMyPage.vue';
import axios from "axios";
// import 'bootstrap/dist/css/bootstrap.min.css';
// import 'bootstrap/dist/js/bootstrap.bundle.min.js';
// main.js


const store = useUserStore()
const router = useRouter()
const confirmLogout = () => {
    if (confirm('정말 로그아웃 하시겠습니까?')) {
        store.logoutUser();
        console.log("로그아웃 완료");
        // router.go(0);
    }
}
const isMyPageVisible = ref(false);
const toggleMyPage = () => {
    isMyPageVisible.value = !isMyPageVisible.value;
}

const showEggCount = () => {
  isEggCountVisible.value = true;
};

const hideEggCount = () => {
  isEggCountVisible.value = false;
};

const isEggCountVisible = ref(false);
//////// 날씨 갖고와 
const tmp_real = ref(0);
const tmp = ref(null);
const sky = ref(null);
const pty = ref(null);
const pop = ref(0);

const sky_real = ref(0);
const pty_real = ref(0);

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



const tmpDescription = computed(() => {
    if (tmp.value < 10) {
      return '춥습니다!';
    } else if (tmp.value < 20) {
      return '적당한 날씨입니다.';
    } else {
      return '덥습니다!';
    }
  })

  const ptyDescription = computed(() => {
  switch (pty.value) {
    case '1':
      return '비가 오고 있습니다.';
    case '2':
      return '비와 눈이 섞여 오고 있습니다.';
    case '3':
      return '눈이 오고 있습니다.';
    case '4':
      return '소나기가 오고 있습니다.';
    default:
      return '강수가 없습니다.';
  }
});

</script>

<style  scoped>
.title {
    margin-top: 7vh;
    margin-bottom: 2vh;
    display: flex;
    justify-content: center;
    color: rgb(255, 174, 0);
    font-size: larger;
}

.nav-container {
    margin: 0 10vw;
    text-align: center;
    background-color: #fcc87e;
    border: 2px solid #e9cc7b;
    display: flex;
    justify-content: space-around;
    padding: 10px;
}

.nav-top-before-login {
    margin: 0 10vw;
    display: flex;
    justify-content: flex-end;
    padding-top: 3vh;
}

.nav-top {
    margin: 0 10vw;
    display: flex;
    justify-content: space-between;
    padding-top: 3vh;
}

.nav1 {
    padding: 10px;
    display: flex;
}

.nv {
    font-weight: 200;
    text-decoration: none;
    color: black;
    display: flex;
    text-align: center;
}

.nav2 {
    padding: 10px;
    display: flex;
}

.nav-container a.router-link-exact-active {
    color: #6572fc;
}

a {
    text-decoration: none;
    color: black;
}

.logout-button {
    border: 0;
    background-color: transparent;
}

.user-name {
    font-size: large;
    font-weight: bold;
}

.pop-up-mypage {
    position: absolute;
    right: 0;
    top: 10%;
    /* Adjust this value based on your design */
    right: 10vw;
    z-index: 1000;
    /* display: none; */
}

.toggle-page {
    background-color: transparent;
    border: none;
}

.egg-count-container {
  position: relative;
  display: inline-block;
}

.egg-count-span {
  display: flex;
  cursor: pointer;
}

.egg-count-box {
  display: none;
  position: absolute;
  left: 0;
}

.egg-count-box::after {
  position: absolute;
  bottom: 100%;
  left: 50%;
  width: 0;
  height: 0;
  margin-left: -10px;
  border: solid transparent;
  border-color: rgba(51, 51, 51, 0);
  border-bottom-color: #333;
  border-width: 10px;
  pointer-events: none;
}

.egg-count-container:hover .egg-count-box {
  display: block;
}

</style>