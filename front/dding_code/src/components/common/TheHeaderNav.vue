<template>
    <div>
        <div v-if="!store.LoginUser.id" class="nav-top">
            <router-link to="/">로그인</router-link>/
            <router-link to="/sign">회원가입</router-link>
        </div>
        <div v-if="store.LoginUser.id" class="nav-top">
            <div>
                현재 알 개수: {{ store.LoginUser.egg_count }}개 
            </div>
            <div>
                {{ store.LoginUser.name }} 님 반갑습니다! / <button @click="confirmLogout">로그아웃 </button>
            </div>
        </div>
        <div class="title">
            <router-link to="/home"><img src="@/assets/egglogo_all.png" alt="mainlogo" style="width: 240px; height: 154px;"></router-link>
        </div>
        <header class="nav-container">
            <RouterLink to="/home" class="nv">Home</RouterLink>
            <RouterLink to="/board" class="nv">BoardList</RouterLink>
            <RouterLink to="/friend" class="nv">Friend</RouterLink>
            <RouterLink to="/youtube" class="nv">Youtube</RouterLink>
            <RouterLink :to="{ name: 'kakao' }" class="nv">Map</RouterLink>
        </header>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useUserStore } from '../../stores/user';
const store = useUserStore()
const router = useRouter()
const confirmLogout = () => {
  if (confirm('정말 로그아웃 하시겠습니까?')) {
    store.logoutUser();
    console.log("로그아웃 완료");
    // router.go(0);
  }
}

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

.nav-top-before-login{
    margin: 0 10vw;
    display: flex;
    justify-content: flex-end;
}
.nav-top{
    margin: 3vh 10vw;
    display: flex;
    justify-content: space-between;
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

a{
    text-decoration: none;
    color: black;
}

</style>