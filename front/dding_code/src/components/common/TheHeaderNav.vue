<template>
    <div>
        <div class="nav-top">
            <router-link to="/login">로그인</router-link>/
            <router-link to="/sign">회원가입</router-link>
        </div>
        <div class="title">
            EGG
        </div>
        <header class="nav-container">
            <RouterLink to="/" class="nv">Home</RouterLink>
            <RouterLink to="/board" class="nv">BoardList</RouterLink>
            <RouterLink to="/friend" class="nv">Friend</RouterLink>
            <RouterLink to="/youtube" class="nv">Youtube</RouterLink>
            <RouterLink :to="{ name: 'kakao' }" class="nv">Map</RouterLink>
        </header>
    </div>
</template>

<script setup>

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
    margin: 0 10vh;
    text-align: center;
    border: 2px solid gray;
    display: flex;
    justify-content: space-around;
    padding: 10px;
}

.nav-top{
    margin-top: 2.5vh;
    margin: 2.5vh 10vh;
    display: flex;
    justify-content: flex-end;
}

.nav1 {
    padding: 10px;
    display: flex;
}

.nv {
    padding-right: 20px;
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
    color: rgb(255, 174, 0);
}

a{
    text-decoration: none;
    color: black;
}
</style>