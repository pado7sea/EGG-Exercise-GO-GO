<template>
    <div class="sign-whole-container">
        <div class="sign-img-container">
            <img src="@/assets/free-animated-icon-eggs-12316833.gif" alt="병아리">
        </div>
        <div class="sign-input-container">
            <div class="sign-inpt">
                <input v-model.trim="user.id" type="text" name="id" id="userId" placeholder="아이디">
                <div class="password-input">
                    <input v-model.trim="user.password" :type="showPassword ? 'text' : 'password'" id="userPassword" placeholder="비밀번호">
                    <input v-model.trim="user.passwordCheck" :type="showPassword ? 'text' : 'password'" id="passwordCheck" placeholder="비밀번호 확인">
                    <span class="show-password" @click="togglePasswordVisibility">
                        <div v-if="showPassword" class="close-eye"></div>
                        <div v-else class="open-eye"></div>
                    </span>
                </div>

                <!-- 비밀번호 불일치 메시지 표시 -->
                <div v-if="!isPasswordMatch" class="validation-message">*비밀번호가 일치하지 않습니다.</div>
                <input v-model.trim="user.name" type="text" name="text" id="userName" placeholder="이름">
                <div class="field birth">
                    <div>
                        <input v-model.trim="year" name="year" id="birthYear" type="text" placeholder="연도">
                        <input v-model.trim="month" name="month" id="birthMonth" type="text" placeholder="월">
                        <input v-model.trim="day" name="day" id="birthDay" type="text" placeholder="일">
                    </div>
                </div>
                <!-- 연, 월, 일의 유효성 메시지 표시 -->
                <div class="validation-message" v-if="!isDateValid">*유효한 날짜를 입력하세요.</div>
                <!-- Id, 이름 입력 메시지 표시 -->
                <div class="validation-message" v-if="(!user.value || !user.value.id || !user.value.name) && !isPasswordMatch">
                    *아이디, 비밀번호, 이름을 입력하세요.
                </div>
                <button :disabled="!isSignupEnabled" @click="signupUser">회원가입 하기</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useUserStore } from '@/stores/user'

const store = useUserStore()
const year = ref('')
const month = ref('')
const day = ref('')
const user = ref({
    id: '',
    password: '',
    passwordCheck: '',
    name: '',
    birthday: ''
})

const showPassword = ref(false)
const togglePasswordVisibility = () => {
    showPassword.value = !showPassword.value
}

// computed property를 사용하여 비밀번호 일치 여부 확인
const isPasswordMatch = computed(() => user.value.password === user.value.passwordCheck)

// computed property를 사용하여 월의 유효성을 검증
const isMonthValid = computed(() => {
    const monthNumber = parseInt(month.value);
    return !isNaN(monthNumber) && monthNumber >= 1 && monthNumber <= 12;
});

// computed property를 사용하여 일의 유효성을 검증
const isDayValid = computed(() => {
    const dayNumber = parseInt(day.value);
    const maxDaysInMonth = getMaxDaysInMonth(parseInt(year.value), parseInt(month.value));
    return !isNaN(dayNumber) && dayNumber >= 1 && dayNumber <= maxDaysInMonth;
});

// computed property를 사용하여 입력된 날짜를 Date 객체로 변환
const inputDate = computed(() => {
    const yearNumber = parseInt(year.value);
    const monthNumber = parseInt(month.value);
    const dayNumber = parseInt(day.value);

    // 입력된 날짜가 유효한 경우에만 Date 객체 반환
    if (!isNaN(yearNumber) && !isNaN(monthNumber) && !isNaN(dayNumber)) {
        return new Date(yearNumber, monthNumber - 1, dayNumber); // month는 0에서 11까지의 값을 사용하므로 1을 빼줌
    } else {
        return null; // 입력된 날짜가 유효하지 않은 경우 null 반환
    }
});

// computed property를 사용하여 현재 날짜를 Date 객체로 반환
const currentDate = computed(() => new Date());

// computed property를 사용하여 입력된 날짜가 현재 날짜 이후인지 확인
const isDateValid = computed(() => {
    return inputDate.value !== null && inputDate.value < currentDate.value;
});

// computed property를 사용하여 모든 입력이 유효한지 확인
const isSignupEnabled = computed(() => {
    return isPasswordMatch.value && isMonthValid.value && isDayValid.value && user.value.id && user.value.name;
});

// 월에 따른 최대 일수를 반환하는 함수
function getMaxDaysInMonth(year, month) {
    const lastDayOfMonth = new Date(year, month, 0);
    return lastDayOfMonth.getDate();
}

const signupUser = () => {
    user.value.birthday = `${year.value}-${month.value}-${day.value}`;
    store.signupUser(user.value);
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
    margin-top: 7vh;
    width: 50%;
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

.close-eye {
    background: url("@/assets/감은눈.png");
    background-size: cover;
    position: absolute;
    width: 20px;
    height: 20px;
    right: 20px;
    bottom: 20px;
    z-index: 10;
}

.open-eye {
    background: url("@/assets/뜬눈.png");
    background-size: cover;
    position: absolute;
    width: 20px;
    height: 20px;
    right: 20px;
    bottom: 20px;
    z-index: 10;
}

.validation-message {
    color: red;
    margin-top: 5px;
    font-size: 12px;
}
</style>
  