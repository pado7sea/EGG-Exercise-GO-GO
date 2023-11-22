import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { useRoute, useRouter } from 'vue-router'
const REST_USER_API = `http://localhost:8080/userapi`


export const useUserStore = defineStore('user', () => {
  const LoginUser = ref({ id: '', password: '' })
  const loggedInUserId = ref(null); // 로그인한 사용자의 ID
  const router = useRouter()
  const userList = ref([])
  const getUserList = function () {
    axios({
      url: REST_USER_API + '/users',
      method: 'GET',
    })
      .then((response) => {
        userList.value = response.data
      })

  }

  const signupUser = function (user) {
    axios({
      url: REST_USER_API + '/signup',
      method: 'POST',
      data: user,
    })
      .then(() => {
        router.push({ name: 'home' });
      })
      .catch((error) => {
        console.error('회원가입 중 오류 발생', error);
      });
  };

  const loginUser = function (user) {
    axios({
      url: REST_USER_API + '/login',
      method: 'POST',
      data: user,
    })
      .then((response) => {
        const loggedInUser = response.data;
        loggedInUserId.value = loggedInUser.id; // 로그인한 사용자의 ID 저장
        LoginUser.value = loggedInUser;
        router.push({ name: 'home' });
      })
      .catch((error) => {
        console.error('로그인 중 오류 발생', error);
      });
  };

  const getUser = function () {
    if (!loggedInUserId.value) {
      console.error('로그인한 사용자의 ID가 없습니다.');
      return;
    }

    axios.get(`${REST_USER_API}/${loggedInUserId.value}`)
      .then((response) => {
        LoginUser.value = response.data;
      })
      .catch((error) => {
        console.error('사용자 정보 가져오기 중 오류 발생', error);
      });
  };

  const logoutUser = function () {
    LoginUser.value = { id: '', password: '' };
    loggedInUserId.value = null;
    console.log("로그아웃 완료")
  };

  // const getFuser = function () {
  //     axios.get(`${REST_USER_API}/userapi/${loggedInUserId.value}`)
  // }

  const searchUserList = function (searchCondition) {
    axios({
      url: REST_USER_API + '/users',
      method: 'GET',
      params: searchCondition
    })
      .then((res) => {
        userList.value = res.data
      })
  }

  const updateUserEggCount = (count) => {
    LoginUser.egg_count += count;
  }

  return { signupUser, loginUser, logoutUser, LoginUser, getUser, getUserList, searchUserList, userList, updateUserEggCount }
})