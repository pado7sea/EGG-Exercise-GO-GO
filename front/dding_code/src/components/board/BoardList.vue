<template>
    <div>
        <BoardSearchInput />

        <div class="board-grid-container">
            <div class="board-card" v-for="board in store.boardList" :key="board.id">
                <div class="board-img">
                    <RouterLink :to="`/board/${board.id}`"><img :src="board.imgUrl"></RouterLink>
                </div>
                <div class="board-description">
                    <div class="board-itw">
                        <div class="board-id">
                            {{ board.id }}</div>
                        <div class="board-tw" style="padding-top: 3px; padding-bottom: 3px;">
                            <div style="font-weight: bold;">{{ board.title }}</div>
                            <div>{{ board.writer }}</div>
                        </div>
                    </div>
                    <div class="board-view">
                        <div>👁️‍🗨️{{ board.viewCnt }}</div>
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

</script>

<style scoped>
* {
    box-sizing: border-box;
}

.board-grid-container {
    margin: 0 10vw;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
}

.board-card {
    background-color: #FFFCF0;
    margin-top: 20px;
    margin-right: 10px; 
    box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.13);
}

.board-img {
    display: flex;
    background-color: #f5f3eb;
    justify-content: center;
    height: 260px;
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
.board-id{
    display: flex;
    background-color: #FFFCF0;
    border-radius: 50%;
    margin: 0 auto;
    padding: 10px;
}
.board-tw{
    display: flex;
    flex-direction: column;
}

.board-view{
    margin-right: 10px;
    padding: 10px;
}
img{
    height: 260px;
}
</style>