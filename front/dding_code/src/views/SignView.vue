<template>
    <div class="sign-whole-container">
        <div class="sign-img-container">
            <img src="@/assets/before-egg.gif" alt="병아리">
        </div>
        <div class="sign-input-container">
            <div class="sign-inpt">
                <form>
                    <input type="text" name="id" placeholder="아이디">
                    <input type="password" placeholder="비밀번호">
                    <input type="password" placeholder="비밀번호 확인">
                    <div class="field birth">
                        <div>
                            <input type="number" placeholder="연도">
                            <input type="number" placeholder="월">
                            <input type="number" placeholder="일">
                        </div>
                    </div>
                </form>
                <button>회원가입 하기</button>
            </div>
        </div>
    </div>
</template>
  
<script setup>


</script>
  
<style scoped>
* {
    box-sizing: border-box;
}

input:focus {
    outline: 2px solid #FEE58D;
}

/* 색상 바꾸기 */
input {
    text-indent: 15px;
}
.sign-whole-container {
    width: 100%;
    display: flex;
    margin-top: 5vh;

}

.sign-img-container {
    display: flex;
    width: 50%;
    justify-content: center;
    margin-top: 3vh;
    margin-left: 10vw;
}

img {
    width: 50%;
    min-width: 300px;
}
.sign-input-container {
    width: 50%;
    display: flex;
    text-align: center;
}
.sign-inpt {
    display: flex;
    float: left;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 12vh;
    width: 50%;
}


form {
    background-color: #FFFFFF;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    text-align: center;
    width: 300px;
}

.sign-up {
    text-decoration: none;
    color: #ceac02;
    font-weight: bold;
    
}
.field.birth div {
    /*field 이면서 birth*/
    display: flex;
    gap: 10px;
    /*간격 벌려줄때 공식처럼 사용핟나 */
}

button {
    min-width: 300px;
    padding: 10px;
    margin: 8px 0;
    font-weight: lighter;
    font-family: Poppins, 'Noto Sans KR', sans-serif;
    border-radius: 20px;
    background: #FEE58D;
    border: transparent;

}

a {
    text-decoration: none;
    color: rgb(58, 58, 58);
    font-weight: bold;

}

input {
    background-color: #FFFCF0;
    width: 100%;
    border: none;
    padding: 15px 0 15px;
    margin: 8px 0;
    border-radius: 30px;
    box-shadow: 0px 3px 3px #d9dcda;
}

input::placeholder {
    text-indent: 10px;
    padding: 5px;
    font-weight: lighter;
    font-family: Poppins, 'Noto Sans KR', sans-serif;
}
</style>
  