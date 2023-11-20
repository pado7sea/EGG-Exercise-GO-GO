<template>
    <div>
        <h4>게시글 작성</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="board.title">
            </div>
            <div>
                <label for="writer">쓰니 : </label>
                <input type="text" id="writer" v-model="board.writer">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="board.content"></textarea>
            </div>
            <div>
                <input type="file" @change="imageUpload" ref="boardImage">이미지 등록
                <img :src="imageUploaded" alt="사용자가 업로드한 이미지">
            </div>
            <div>
                <button @click="createBoard" class="button button--winona button--border-thin button--round-s"
                    data-text="Create New"><span>글 등록</span></button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from "vue";
import axios from 'axios';
// import { useBoardStore } from "@/stores/board";

// const store = useBoardStore()
const board = ref({
    title: '',
    writer: '',
    content: ''
})

const boardImage = ref(null)
const imageUploaded = ref(null)


const imageUpload = function () {
    const image = boardImage.value.files[0];
    console.log(image.value)
    imageUploaded.value = URL.createObjectURL(image)
}
const createBoard = async () => {
    // store.createBoard(board.value)
    try {
        const formData = new FormData()
        formData.append('files', boardImage.value.files[0])
        formData.append('title', board.value.title)
        formData.append('writer', board.value.writer)
        formData.append('content', board.value.content)

        const config = {
            headers: {
                'Content-Type': 'multipart/form-data' // 컨텐츠 타입 지정해줘야함
            }
        }

        const response = await axios.post('http://localhost:8080/api/board', formData, config)
        alert('생성 완료');
        console.log(response.data)
    } catch (error) {
        console.error(error);
    }
}

</script>

<style scoped>
*,
*:after,
*:before {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

.button {
    float: left;
    min-width: 150px;
    max-width: 250px;
    display: block;
    margin: 1em;
    padding: 1em 2em;
    border: none;
    background: none;
    color: inherit;
    vertical-align: middle;
    position: relative;
    z-index: 1;
    -webkit-backface-visibility: hidden;
    -moz-osx-font-smoothing: grayscale;
}

.button:focus {
    outline: none;
}

.button>span {
    vertical-align: middle;
}

.button--round-s {
    border-radius: 5px;
}

.button--border-thin {
    border: 1px solid;
}

.button--winona {
    overflow: hidden;
    padding: 0;
    -webkit-transition: border-color 0.3s, background-color 0.3s;
    transition: border-color 0.3s, background-color 0.3s;
    -webkit-transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
    transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
}

.button--winona::after {
    content: attr(data-text);
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    opacity: 0;
    color: #3f51b5;
    -webkit-transform: translate3d(0, 25%, 0);
    transform: translate3d(0, 25%, 0);
}

.button--winona>span {
    display: block;
}

.button--winona.button--inverted {
    color: #7986cb;
}

.button--winona.button--inverted:after {
    color: #fff;
}

.button--winona::after,
.button--winona>span {
    padding: 1em 2em;
    -webkit-transition: -webkit-transform 0.3s, opacity 0.3s;
    transition: transform 0.3s, opacity 0.3s;
    -webkit-transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
    transition-timing-function: cubic-bezier(0.2, 1, 0.3, 1);
}

.button--winona:hover {
    border-color: #3f51b5;
    background-color: rgba(63, 81, 181, 0.1);
}

.button--winona.button--inverted:hover {
    border-color: #21333C;
    background-color: #21333C;
}

.button--winona:hover::after {
    opacity: 1;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
}

.button--winona:hover>span {
    opacity: 0;
    -webkit-transform: translate3d(0, -25%, 0);
    transform: translate3d(0, -25%, 0);
}
</style>