<template>
  <div class="board-container">
    <div class="board-nav">
      <BoardSearchInput />
      <div class="create-button">
        <router-link to="/board/create" class="router-create">
          <button class="custom-btn btn-6"><span>글 등록</span></button>
        </router-link>
      </div>
    </div>
    <div class="board-grid-container">
      <div class="board-card" v-for="board in store.boardList" :key="board.id">
        <div class="board-img">
          <RouterLink :to="`/board/${board.id}`"><img :src="`http://localhost:8080/upload/${board.img}`"></RouterLink>
        </div>
        <div class="board-description">
          <div class="board-itw">
            <div class="board-id">
              <RouterLink :to="`/board/${board.id}`">{{ board.id }}</RouterLink>
            </div>
            <div class="board-tw">
              <div style="font-weight: bold;">
                <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
              </div>
              <div>
                <RouterLink :to="`/board/${board.id}`">{{ board.writer }}</RouterLink>
              </div>
            </div>
          </div>
          <div class="board-view">
            <div style="display: flex; justify-content: flex-end;">👁️{{ board.viewCnt }}</div>
            <div>{{ board.regDate }}</div>
          </div>
        </div>
        <div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import BoardSearchInput from "./BoardSearchInput.vue";
import BoardCreate from "./BoardCreate.vue";
const store = useBoardStore()

onMounted(() => {
  store.getBoardList()
})

console.log(store.board.img)

</script>

<style scoped>
* {
  box-sizing: border-box;
}

.board-container{
  margin: 0 10vw;
  display: flex;
  flex-direction: column;
  background-color: #b1c7fc;
}

.board-nav {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  margin-left: 7vw;
}

.create-button {
  margin: 0 10vw;
  text-decoration: none;
}

a {
  text-decoration: none;
  color: rgb(4, 4, 4);
}

.board-grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  
}

.board-card {
  background-color: #FFFCF0;
  margin-top: 20px;
  margin-left: 10px;
  margin-right: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.13);
}

.board-img {
  display: flex;
  background-color: #f5f3eb;
  justify-content: center;
  align-items: center; /* 추가된 부분 */
  height: 265px;
}


.board-description {
  display: flex;
  justify-content: space-between;
}

.board-itw {
  display: flex;
  gap: 10px;
  margin-left: 10px;
  height: 50px;
}

.board-id {
  display: flex;
  background-color: #FFFCF0;
  border-radius: 50%;
  margin: 0 auto;
  padding: 20px;
}

.board-tw {
  display: flex;
  flex-direction: column;
  margin-right: 10px;
  padding: 10px;
}

.board-view {
  margin-right: 10px;
  padding: 10px;
  display: flex;
  flex-direction: column;
}

img {
  height: 260px;
}

.custom-btn {
  width: 130px;
  height: 28px;
  padding: 10px 25px;
  border: 2px solid #FEE58D;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  justify-content: center;
}

/* 6 */
.btn-6 {
  background: #FEE58D;
  color: black;
  line-height: 30px;
  padding: 0;
  border: none;
  border-radius: 50px;
}

.btn-6 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
  border-radius: 50px;
}

.btn-6:before,
.btn-6:after {
  position: absolute;
  content: "";
  height: 0%;
  width: 2px;
  background: white;
  border-radius: 50px;
}

.btn-6:before {
  right: 0;
  top: 0;
  transition: all 500ms ease;
  border-radius: 50px;
}

.btn-6:after {
  left: 0;
  bottom: 0;
  transition: all 500ms ease;
  border-radius: 50px;
}

.btn-6:hover {
  color: black;
  background: transparent;
  border-radius: 50px;
}

.btn-6:hover:before {
  transition: all 500ms ease;
  height: 100%;
  border-radius: 50px;
}

.btn-6:hover:after {
  transition: all 500ms ease;
  height: 100%;
  border-radius: 50px;
}

.btn-6 span:before,
.btn-6 span:after {
  position: absolute;
  content: "";
  background: #FEE58D;
  border-radius: 50px;
}

.btn-6 span:before {
  left: 0;
  top: 0;
  width: 0%;
  height: 2px;
  transition: all 500ms ease;
  border-radius: 50px;
}

.btn-6 span:after {
  right: 0;
  bottom: 0;
  width: 0%;
  height: 2px;
  transition: all 500ms ease;
  border-radius: 50px;
}

.btn-6 span:hover:before {
  width: 100%;
}

.btn-6 span:hover:after {
  width: 100%;
}</style>