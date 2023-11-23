<template>
    <div>
        <TheHeaderNav />
        <RouterView />
    </div>
</template>

<script setup>
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
</script>

<style  scoped>
    div{
        font-family: Poppins, 'Noto Sans KR', sans-serif;
        background-color: #7ea4fc;
        /* width: 100vw;
      height: 100vh; */
      padding-bottom: 5vh;
    }
</style>