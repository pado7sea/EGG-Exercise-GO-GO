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
    }
</style>