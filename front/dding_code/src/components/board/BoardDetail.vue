<template>
    <div class="post-detail-container">
        <div class="post-detail-backbox">
        <div class="post-header">
            <h4>{{ store.board.title }}</h4>
            <div class="post-meta">
                <span>{{ store.board.writer }}</span>
                <span>{{ store.board.regDate }}</span>
                <span>{{ store.board.viewCnt }} views</span>
            </div>
        </div>
        <div class="post-content">
            <div>{{ store.board.content }}</div>
            <img :src="`http://localhost:8080/upload/${store.board.img}`">
        </div>
        <div class="post-actions">
            <button @click="deleteBoard" class="action-button delete">삭제</button>
            <button @click="updateBoard" class="action-button update">수정</button>
        </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { useUserStore } from '../../stores/user';
import { onMounted } from "vue";
import axios from 'axios'

const store = useBoardStore()
const uStore = useUserStore()
const route = useRoute();
const router = useRouter();



onMounted(() => {
    store.getBoard(route.params.id)
})
const deleteBoard = function () {
    // 현재 로그인한 사용자 닉넴 
    const loggedUserName = uStore.LoginUser.name;
    // 게시글 작성자 닉넴 
const boardWriterName = store.board.writer;
    // 같으면 삭제 ㄱㄱ
    if(loggedUserName === boardWriterName){
        axios.delete(`http://localhost:8080/api/board/${route.params.id}`)
            .then(() => {
                router.push({ name: 'boardList' })
            })
    } else{
        window.alert('삭제 권한이 없습니다!');
    }

}

const updateBoard = function () {
    router.push({ name: 'boardUpdate' })
}


</script>

<style scoped>

.post-detail-container {
    margin: 0 10vw;
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.post-detail-backbox {
    width: 100%;
    padding: 20px;
    background-color: #fff;
    border-radius: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin: 2vh 2vw;
    padding: 2vh 2vw;
}

.post-header {
    margin-bottom: 20px;
}

.post-header h4 {
    font-size: 24px;
    margin: 0;
}

.post-meta {
    display: flex;
    gap: 10px;
    font-size: 14px;
    color: #888;
}

.post-content {
    margin-bottom: 20px;
}

.post-content img {
    width: 100%;
    max-height: 500px; /* Adjust the max-height as needed */
    object-fit: contain;
    padding: 2vh 2vw;
}

.post-actions {
    display: flex;
    justify-content: space-between;
}

.action-button {
    width: 80px;
    height: 45px;
    font-size: 15px;
    text-transform: uppercase;
    letter-spacing: 2.5px;
    font-weight: 500;
    color: #000;
    background-color: aliceblue;
    border: none;
    border-radius: 45px;
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease 0s;
    cursor: pointer;
    outline: none;
    cursor: pointer;
}

.action-button.delete {
    background-color: #fc847e;
}

.action-button.update {
    background-color: #7ea4fc;
}

.action-button.delete:hover {
    color: aliceblue;
    background-color: #fd5656;
    box-shadow: 0px 5px 10px #635746;
    transform: translateY(-4px);
}

.action-button:hover {
    color: aliceblue;
    background-color: #6572FC;
    box-shadow: 0px 5px 10px #464f63;
    transform: translateY(-4px);
}
</style>