import { ref } from "vue";
import { defineStore } from "pinia";
import router from "@/router";
import axios from "axios";

const REST_FRIEND_API = `http://localhost:8080/friendapi`;
const REST_USER_API = `http://localhost:8080/userapi`;

export const useFriendStore = defineStore("friend", () => {
  const friendList = ref([]);
  const getFuser = ref({});
  // 해당 아이디의 목록 불러오기
  const getFriendList = async function (id) {
    try {
      const response = await axios(`${REST_FRIEND_API}/friends/${id}`);
      friendList.value = response.data;
    } catch (error) {
      console.error("친구 목록을 불러오는 중 오류 발생", error);
    }
  };

  // 친구 상세

  const getfrienduser = async function (id) {
    try {
        console.log(id)
      const response = await axios(`${REST_USER_API}/userapi/${id}`);
      getFuser.value = response.data;
      console.log(getFuser.value)
    } catch (error) {
      console.log("친구 정보 가져오기 실패", error);
    }
  };

  return { friendList, getFriendList, getfrienduser, getFuser };
});