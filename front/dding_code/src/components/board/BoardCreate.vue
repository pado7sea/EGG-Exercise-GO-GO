<template>
    <div class="board-create-container">
        <div class="board-create-backbox">
            <div class="bc-title">
                <div class="bc-whitecircle">
                    <div class="게시글작성">
                        게시글 작성
                    </div>
                </div>
            </div>
            <div class="bc-content">
                <fieldset>
                    <table>
                        <tr>
                            <td>
                                <div class="row g-3 align-items-center">
                                    <div class="col-md-auto">
                                        <label class="col-form-label" for="title"
                                            style="padding-left: 17px; margin-right: 8px;">제목</label>
                                    </div>
                                    <div class="col-md-auto">
                                        <input class="form-control" type="text" id="title" v-model="board.title">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="row g-3 align-items-center">
                                    <div class="col-md-auto">
                                        <label class="col-form-label" for="writer" style="padding-left: 10px;">닉네임</label>
                                    </div>
                                    <div class="col-md-auto">
                                        <div class="form-control" style="color: gray;">{{ userStore.LoginUser.name }}</div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="mb-3">
                                    <textarea class="form-control" id="content" cols="30" rows="10" v-model="board.content"
                                        placeholder="내용을 입력해주세요."></textarea>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <input class="form-control" type="file" @change="imageUpload" ref="boardImage"
                                        id="photo" accept="image/*">
                                    <img v-if="imageUploaded && imageUploaded.length > 0" :src="imageUploaded"
                                        alt="사용자가 업로드한 이미지">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="wrap">
                                    <button @click="createBoard" class="button"><span>등록</span></button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import axios from 'axios';
import { useBoardStore } from "@/stores/board";
import router from "@/router";
import { useUserStore } from "@/stores/user";

const store = useBoardStore()
const userStore = useUserStore()
const board = ref({
    title: '',
    writer: userStore.LoginUser.name,
    content: '',
})

const file = ref({})
const imageUploaded = ref({})

const imageUpload = function () {
    const photo = document.getElementById("photo")
    file.value = photo.files[0];
    const image = file.value
    console.log(file.value)
    imageUploaded.value = URL.createObjectURL(image)
}
// const getFile = function() {
//     const image = file.value;
//     console.log(image.value)
//     imageUploaded.value = URL.createObjectURL(image)
// }

const createBoard = async () => {
    // store.createBoard(board.value)
    try {
        const formData = new FormData()
        formData.append('file', file.value)
        formData.append('title', board.value.title)
        formData.append('writer', board.value.writer)
        formData.append('content', board.value.content)
        const config = {
            headers: {
                'Content-Type': 'multipart/form-data' // 컨텐츠 타입 지정해줘야함
            }
        }

        const response = await axios.post('http://localhost:8080/api/board', formData, config)
        // 인증하면 달걀 개수 하나 추가염
        userStore.LoginUser.egg_count += 1;;
        alert('생성 완료')
        console.log(response.data)
        router.push({ name: 'boardList' })
    } catch (error) {
        console.error(error);
    }
}


</script>

<style scoped>
.board-create-container {
    margin: 0 10vw;
    display: flex;
    flex-direction: row;
    justify-content: center;
}

.board-create-backbox {
    width: 100%;
    background: #b1c7fc;
    border-radius: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    background-color: #E3EBFC;
    /* 표 배경색 지정 */
    border: 1px solid #b1c7fc;
    /* 표 테두리 스타일 지정 */
    border-radius: 20px;
}

th,
td {
    padding: 10px;
    text-align: left;
    border-bottom: 3px solid #b1c7fc;
    /* 셀 사이의 경계선 스타일 지정 */
}

tr:hover {
    background-color: #f5f5f5;
    /* 행에 마우스를 올렸을 때 배경색 지정 */

}

.bc-title {
    /* margin: 0 10vw; */
    /* flex-shrink: 0; */
    background: #6572FC;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
    border-radius: 20px;
    display: flex;
    justify-content: flex-start;
    padding: 1vh 1vw;
    align-items: center;
    margin: 2vh 2vw;
}

.bc-whitecircle {
    /* background: #464F63;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.05);
    border-radius: 20px; */
    display: flex;
    justify-content: center;
    padding: 0 10px 0 10px;
    align-items: center;

}

.게시글작성 {
    color: aliceblue;
    font-size: 20px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-shadow: 1px 1px 3px #fcc87e;
}

.bc-content {
    /* display: flex;
    flex-direction: row;
    justify-content: space-around; */
    margin: 2vh 2vw;
    /* background-color: rgba(255, 255, 255, 0.3);  */
}

/* 버튼 */
.wrap {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 2vh 2vw;
}

.button {
    width: 140px;
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

.button:hover {
    color: aliceblue;
    background-color: #6572FC;
    box-shadow: 0px 15px 20px #464f63;
    transform: translateY(-4px);
}
</style>