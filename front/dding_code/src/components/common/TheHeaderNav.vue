<template>
    <div>
        <div class="title">
            EGG
        </div>
        <header class="nav-container">
            <nav>
                <div class="nav1">
                    <RouterLink to="/">Home</RouterLink> 
                    <RouterLink to="/board">BoardList</RouterLink> 
                    <RouterLink to="/friend">Friend</RouterLink>
                    <RouterLink to="/youtube">Youtube</RouterLink> 
                    <RouterLink :to="{name: 'kakao'}">Map</RouterLink> 
                </div>
                    <!-- <span class="nav2">
                        <router-link to="/login">로그인</router-link>
                        <router-link to="/sign">회원가입</router-link>
                    </span> -->
            </nav>
        </header>
    </div>
</template>

<script setup>

</script>

<style  scoped>
.title{
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
}
nav{
    display: flex;
    justify-content: space-around;
}
.nav1 {
    padding: 10px;
    display: flex;
}

 a {
    padding-right: 20px;
    font-weight: bold;
    text-decoration: none;
    color: black;
    display: flex;
    text-align: center;
}

.nav2{
    padding: 10px;
    display: flex;
}

nav a.router-link-exact-active {
    color: rgb(255, 174, 0);
}
</style>