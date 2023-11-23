<template>
    <div class="friend-detail-container">
        <div class="friend-list" style="margin-left: 2vw; background-color: rgba(255, 255, 255, 0.9); width: 10%; text-align: center; border-radius: 30px;">
            친구목록
        </div>
        <div class="friend-list-container">
            <table class="friend-table">
                <thead id="friend-table">
                    <tr>
                        <th></th>
                        <th>아이디</th>
                        <th>닉네임</th>
                        <th>모은 계란 개수</th>
                        <th>최근 운동일</th>
                    </tr>
                </thead>
                <tbody id="user-table">
                    <tr v-for="(friendId, index) in extractedFriendIds" class="friend-detail" :key="friendId">
                        <td><img src="@/assets/병프.png" class="friend-icon" alt="친구병아리"></td>
                        <td>{{ friendId }}</td>
                        <td v-if="friendInfo[index]">{{ friendInfo[index].name }}</td>
                        <td v-if="friendInfo[index]">{{ friendInfo[index].egg_count }}</td>
                        <td>{{ getRecentPostDate(friendInfo[index]) }}</td>
                        <td>
                            <button @click="deleteFriend(friendId)" class="f-deletebtn">친구 삭제</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useFriendStore } from '@/stores/friend';
import {useBoardStore} from '@/stores/board';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted, computed, reactive } from 'vue';
import axios from 'axios';

const store = useUserStore();
const Fstore = useFriendStore();
const Bstore = useBoardStore();
const route = useRoute();
const router = useRouter();

// 추출된 friend_id 목록을 계산하는 computed property
const extractedFriendIds = computed(() => {
    console.log(Fstore.friendList);
    return Fstore.friendList.map(friend => friend.friend_id);
});

const deleteFriend = function (friend_id) {
    const isDelete = confirm('정말 삭제하시겠습니까?');

    if (isDelete) {
        axios.delete(`http://localhost:8080/friendapi/friends/${store.LoginUser.id}/${friend_id}`)
            .then(() => {
                router.push({ name: 'friend' });
            })
            .catch((error) => {
                console.error('Error deleting friend:', error);
            });
    } else {
        console.log('Deletion canceled by the user');
    }
};

// 각 친구의 이름을 가져오는 함수
const getFriendInfo = async (friendId) => {
    try {
        await Fstore.getfrienduser(friendId);
        console.log(Fstore.getFuser);
        return Fstore.getFuser ? Fstore.getFuser : { friend_id: null, name: '이름 없음', egg_count: 0 };
    } catch (error) {
        console.error('친구 정보 가져오기 실패', error);
        return { friend_id: null, name: '이름 없음', egg_count: 0 };
    }
};

const getRecentPostDate = (friend) => {
  if (friend && friend.name) {
    const friendBoards = Bstore.boardList.filter((board) => board.writer === friend.name);
    const sortedBoards = friendBoards.sort((a, b) => new Date(b.regDate) - new Date(a.regDate));

    if (sortedBoards.length > 0) {
      return sortedBoards[0].regDate;
    } else {
      return "작성한 게시물이 없습니다.";
    }
  } else {
    return "이름 없음";
  }
};

const friendInfo = ref({})

onMounted(async () => {
    store.getUser(route.params.id)
    await Fstore.getFriendList(store.LoginUser.id);
    friendInfo.value = await Promise.all(extractedFriendIds.value.map(getFriendInfo));
});


</script>

<style scoped>
.friend-detail-container {
    /* display: flex; */
    /* flex-direction: column; */
    margin-top: 5vh;
    /* background-color: #b1c7fc; */
}

.friend-icon {
    width: 71px;
    height: 67px;
}

.friend-table {
    border-spacing: 50px 50px;
    text-align: center;
    width: 100%;
}

.friend-list-container {
    display: flex;
    margin-top: 3vh;
    padding-bottom: 8vh;
}

.f-deletebtn{
    border: none;
    transition:0.2s;
    padding: 3px;
    padding-left: 3px;
    padding-right: 3px;
    background-color: #fefefe;
    border-radius: 30px;
}

.f-deletebtn:hover{
    transform: scale(1.12);
    background-color: #ffdada;
}
</style>