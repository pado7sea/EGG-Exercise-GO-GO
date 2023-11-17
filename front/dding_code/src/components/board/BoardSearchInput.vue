<template>
    <div class="search-container">
        <div class="search-div">
            <label class="search-standard">검색 기준 </label>
            <select v-model="searchInfo.key" name="standard" class="search-standard-s">
                <option value='none'>없음</option>
                <option value="writer">글쓰니</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>
        </div>
        <div class="search-div">
            <label>검색 내용 </label>
            <input style="height: 32px;" class="search-standard-s" type="text" v-model="searchInfo.word" />
        </div>
        <div class="search-div">
            <label>정렬 기준 </label>
            <select v-model="searchInfo.orderBy" class="search-standard-s">
                <option value='none'>없음</option>
                <option value="writer">글쓰니</option>
                <option value="title">제목</option>
                <option value="view_cnt">조회수</option>
            </select>
        </div>
        <div class="search-div">
            <label>정렬 방향 </label>
            <select v-model="searchInfo.orderByDir" class="search-standard-s">
                <option value="asc">오름차순</option>
                <option value="desc">내림차순</option>
            </select>
        </div>
        <div class="search-div"> 
            <button @click="searchBoardList">검색</button>
        </div>
        <div>
            <button click="goCreate">글 등록</button>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import { useBoardStore } from '@/stores/board'

const store = useBoardStore()
const searchInfo = ref({
    key: 'none',
    word: '',
    orderBy: 'none',
    orderByDir: 'asc'
})
const searchBoardList = function () {
    store.searchBoardList(searchInfo.value)
}
</script>
  
<style scoped>
.search-container {
    display: flex;
    margin: 10px 10vw 0;
}

.search-div{
    margin: 0 5px;
    text-align: center;
}

.search-standard-s{
    border: 1px solid #C4C4C4;
    box-sizing: border-box;
    border-radius: 10px;
    padding: 5px;
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 16px;
}

.search-standard-s:focus{
    border: 1px solid #FEE58D;
    box-sizing: border-box;
    border-radius: 10px;
    outline: 3px solid #f5eac1;
    border-radius: 10px;
}



</style>
  