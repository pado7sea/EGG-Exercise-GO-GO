<template>
    <div>
        <THeHeaderNav />
        <RouterView />
    </div>
</template>

<script setup>
import THeHeaderNav from "@/components/common/THeHeaderNav.vue";
</script>

<style  scoped>
    div{
        font-family: Poppins, 'Noto Sans KR', sans-serif;
    }
</style>