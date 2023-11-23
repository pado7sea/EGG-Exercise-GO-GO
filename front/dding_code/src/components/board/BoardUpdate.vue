<template>
    <div class="board-create-container">
        <div class="board-create-backbox">
            <div class="bc-title">
                <div class="bc-whitecircle">
                    <div class="게시글작성">
                        게시글 수정
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
                                        <input class="form-control" type="text" id="title" v-model="store.board.title">
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
                                        <input class="form-control" type="text" id="writer"  disabled readonly
                                            v-model="store.board.writer">
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="mb-3">
                                    <textarea class="form-control" id="content" cols="30" rows="10"
                                        v-model="store.board.content"></textarea>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="wrap">
                                    <button @click="updateBoard" class="button"><span>수정</span></button>
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
import { useBoardStore } from "@/stores/board";
const store = useBoardStore();

const updateBoard = function () {
    store.updateBoard();
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
    margin: 2vh 2vw;
}

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
    font-family: 'Roboto', sans-serif;
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
