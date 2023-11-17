<template>
    <div class="login-whole-container">
      <div class="login-img-container">
        <img src="@/assets/png-baby-chick-removebg-preview.png" alt="병아리">
      </div>
    <div class="login-input-container">
      <div class="inpt">
        <form>
          <input type="text" name="id" placeholder="아이디">
          <input type="password" placeholder="비밀번호">
        </form>
        <button>Login</button>
        <div class="description">
          아직 회원이 아니신가요? <a href="/sign">회원가입하기</a>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>


</script>
  
<style scoped>
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
    margin-top: 12vh;
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

.info {
    background-color: #FFFCF0;
    background-color: #FFFCF0;
    width: 100%;
    border: none;
    padding: 15px 0 15px;
    margin: 8px 0;
    border-radius: 30px;
    box-shadow: 0px 3px 3px #d9dcda;
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
  