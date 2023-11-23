import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRoute, useRouter } from 'vue-router'

const REST_USER_API = `http://localhost:8080/userapi`

export const useUserStore = defineStore('user', () => {
  const LoginUser = ref({ id: '', password: '', name: '' });
  const loggedInUserId = ref(null); // 로그인한 사용자의 ID

  const router = useRouter();
  const userList = ref([])
  const signupUser = async function (user) {
    // 비밀번호 해싱
    const hashedPassword = await hashPassword(user.password);

    // 이 부분에서 서버로 전송하기 전에 HS256으로 서명된 토큰을 생성
    const token = createToken(user.id);

    try {
      await axios.post(`${REST_USER_API}/signup`, {
        ...user,
        password: hashedPassword,
        token,
      });

      // 회원가입 성공 처리
      router.push({ name: 'home' });
    } catch (error) {
      console.error('회원가입 중 오류 발생', error);
    }
  };

  const loginUser = async function (user) {
    // 비밀번호 해싱
    const hashedPassword = await hashPassword(user.password);

      const response = await axios.post(`${REST_USER_API}/login`, {
        ...user,
        password: hashedPassword,
      }).then((res)=>{
        sessionStorage.setItem('access-token', res.data["access-token"]);
        const token = res.data['access-token'].split('.');
        let id = token[1];
        id = atob(id);
        id = JSON.parse(id);
        console.log(id['id']);
        loggedInUserId.value = res.data.user['id'];
        LoginUser.value.id =  res.data.user['id'];
        LoginUser.value.name =  res.data.user['name'];
        router.push({ name: 'home' });
      }).catch((error)=>{
        console.log('잘못된 로그인 정보입니다.');
      });
    }
  const getUser = async function () {
    if (!loggedInUserId.value) {
      console.error('로그인한 사용자의 ID가 없습니다.');
      return;
    }

    try {
      const response = await axios.get(`${REST_USER_API}/${loggedInUserId.value}`);
      LoginUser.value = response.data;
    } catch (error) {
      console.error('사용자 정보 가져오기 중 오류 발생', error);
    }
  };

  const logoutUser = function () {
    LoginUser.value = { id: '', password: '' };
    loggedInUserId.value = null;
    console.log("로그아웃 완료")
  };

  const createToken = function (id) {
    // 서버와 동일한 방식으로 HS256 서명을 생성
    const header = btoa(JSON.stringify({ alg: 'HS256', typ: 'JWT' }));
    const payload = btoa(JSON.stringify({ sub: id }));
    const data = `${header}.${payload}`;
    const signature = btoa(
      new TextEncoder().encode(CryptoJS.HmacSHA256(data, 'SSAFIT').toString(CryptoJS.enc.Hex))
    );
    return `${data}.${signature}`;
  };

  const hashPassword = async function (password) {
    const encoder = new TextEncoder();
    const data = encoder.encode(password);
    const buffer = await crypto.subtle.digest('SHA-256', data);
    const hashedPassword = Array.from(new Uint8Array(buffer)).map(byte => byte.toString(16).padStart(2, '0')).join('');
    return hashedPassword;
  };

  const getUserList = function () {
    axios({
      url: REST_USER_API + '/users',
      method: 'GET',
    })
      .then((response) => {
        userList.value = response.data
      })

  }

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

  return { signupUser, loginUser, logoutUser, LoginUser, getUser, createToken, hashPassword, getUserList, searchUserList, userList };
}, { persist: true });
