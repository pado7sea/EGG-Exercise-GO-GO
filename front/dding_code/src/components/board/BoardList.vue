<template>
    <div class="board-container">
        <div class="board-card" v-for="board in store.boardList" :key="board.id">
            <div class="board-img">
                <img :src="board.imgUrl">
            </div>
            <div class="board-description">
                <div class="board-itb">
                    <div>{{ board.id }}</div>
                    <div>{{ board.title }}</div>
                    <div>{{ board.writer }}</div>
                </div>
                <div class="board-view">
                    <div>{{ board.viewCnt }}</div>
                </div>
            </div>
            <div>
            </div>
        </div>
<!-- 
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>쓰니</th>
                <th>조회수</th>
                <th>등록</th>
            </tr>
            <tr v-for="board in store.boardList" :key="board.id">
                <td>{{ board.id }}</td>
                <td>
                    <RouterLink :to="`/board/${board.id}`">{{ board.title }}</RouterLink>
                </td>
                <td>{{ board.writer }}</td>
                <td>{{ board.viewCnt }}</td>
                <td>{{ board.regDate }}</td>
            </tr>
        </table> -->

        <BoardSearchInput />

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

</script>

<style scoped>
*{
    box-sizing: border-box;
}

.board-card{
    display: grid;
    background-color:#FBE28C;
    margin-top: 10px;
}

</style>