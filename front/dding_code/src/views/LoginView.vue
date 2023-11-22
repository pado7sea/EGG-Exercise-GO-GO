<template>
    <div class="login-whole-container">

        <div class="login-img-container">
            <img src="@/assets/free-animated-icon-chick-12277910.gif" alt="병아리">
        </div>
        <div class="login-input-container">
            <div class="inpt">
                    <input v-model.trim="user.id" type="text" name="id" placeholder="아이디">
                    <div class="password-input">
                        <input v-model.trim="user.password" :type="showPassword ? 'text' : 'password'" placeholder="비밀번호">
                        <span class="show-password" @click="togglePasswordVisibility">
                            <div v-if="showPassword" class="close-eye"></div>
                            <div v-else class="open-eye"></div>
                        </span>
                    </div>
                <button @click="loginUser">Login</button>
                <div class="description">
                    아직 회원이 아니신가요? <a href="/sign">회원가입하기</a>
                </div>
            </div>

        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'


const router = useRouter()
const store = useUserStore()
const user = ref({
    id: '',
    password: ''
})

const showPassword = ref(false)
const togglePasswordVisibility = () => {
    showPassword.value = !showPassword.value
}

const loginUser = () => {
    console.log('로그인 버튼 클릭됨');
    store.loginUser(user.value);
}
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

.login-whole-container {
    width: 100%;
    display: flex;
    margin-top: 5vh;
}

.login-img-container {
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

.login-input-container {
    width: 50%;
    display: flex;
    text-align: center;
}

.description {
    font-weight: thin;
}

.inpt {
    display: flex;
    float: left;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 7vh;
}

.password-input {
    position: relative;
    width: 100%;
}

.show-password {
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
    cursor: pointer;
    width: 10px;
    height: 10px;
}

form {
    background-color: #FFFFFF;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    min-width: 300px;
    text-align: center;
}

.sign-up {
    text-decoration: none;
    color: #ceac02;
    font-weight: bold;

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

.close-eye{
    background: url("@/assets/감은눈.png");
    background-size: cover;
    position: absolute;
    width: 20px;
    height: 20px;
    right: 20px;
    bottom: 20px;
    z-index: 10;
}
.open-eye{
    background: url("@/assets/뜬눈.png");
    background-size: cover;
    position: absolute;
    width: 20px;
    height: 20px;
    right: 20px;
    bottom: 20px;
    z-index: 10;
}

</style>
  