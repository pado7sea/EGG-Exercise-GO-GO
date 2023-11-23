<template>
    <div>
      <h2>경로 정보</h2>
      <ul>
        <li v-for="(route, index) in routes" :key="index">
          <strong>목적지 {{ index + 1 }}:</strong>
          <p>거리: {{ route.summary.distance }} 미터</p>
          <p>소요 시간: {{ route.summary.duration }} 초</p>
        </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps } from 'vue';
  
  const { routes } = defineProps(['routes']);
  </script>
  