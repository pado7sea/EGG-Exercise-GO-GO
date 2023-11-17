<template>
    <div class="login-whole-container">
      <div class="login-img-container">
        <img src="@/assets/chickinegg.png" alt="병아리">
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

function onSignIn(googleUser) {
	  const profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}
</script>
  
<style scoped>
.login-whole-container{
  width: 100%;
  display: flex;
}

.login-img-container{
  display: flex;
  width: 50%;
  justify-content: center;
  margin-top: 3vh;
  padding-left: 2vw;
}

img{
  width: 50%;
}

.login-input-container{
  width: 50%;
  display: flex;
  text-align: center;
}

.description{
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

.sign-up{
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

a{
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
  padding: 5px;
  text-align: center;
  font-weight: lighter;
  font-family: Poppins, 'Noto Sans KR', sans-serif;
}
</style>
  