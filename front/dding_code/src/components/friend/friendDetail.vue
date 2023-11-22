<template>
    <div>
        <div class="friend-list-header">
                <div class="friend-list-header-d">
                    <div style="width: 71px; height: 67px;" class="friend-list-header-d-d"></div>
                    <div class="friend-list-header-d-d">아이디</div>
                    <div class="friend-list-header-d-d">이름</div>
                    <div class="friend-list-header-d-d">현재 계란 개수</div>
                </div>
                <div class="friend-list-container">
                    <div v-for="(friendId, index) in extractedFriendIds" class="friend-detail" :key="friendId">
                        <div>
                            <img class="friend-icon" src="@/assets/free-icon-chick.png" alt="친구병아리">
                        </div>
                        <div>
                            {{ friendId }}
                        </div>
                        <!-- 추출된 friendId 출력 -->
                        <div>
                            <!-- 친구의 정보를 가져와서 표시 -->
                            {{ friendInfo[index].name }}
                        </div>
                        <div>
                            {{ friendInfo[index].egg_count }}
                        </div>
                        <div>
                            <button @click="deleteFriend(friendId)">친구 삭제</button>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useFriendStore } from '@/stores/friend';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted, computed } from 'vue';
import axios from 'axios'

const store = useUserStore()
const Fstore = useFriendStore()
const route = useRoute()
const router = useRouter()

// 추출된 friend_id 목록을 계산하는 computed property
const extractedFriendIds = computed(() => {
    console.log(Fstore.friendList)
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
}


// //   // 각 친구의 이름을 가져오는 함수
//   const getFriendName = (friendId) => {

//     // const friend = Fstore.friendList.find(friend => friend.friend_id === friendId);
//     console.log(Fstore.getfrienduser(friendId) )
//     console.log(Fstore.getFuser)

//     return Fstore.getFuser ? Fstore.getFuser.name : '이름 없음';
//   };

//   onMounted(() => {
//     store.getUser(route.params.id)
//     Fstore.getFriendList(store.LoginUser.id)
//   });

// 각 친구의 이름을 가져오는 함수
const getFriendInfo = async (friendId) => {
    try {
        await Fstore.getfrienduser(friendId);
        console.log(Fstore.getFuser);
        return Fstore.getFuser;
    } catch (error) {
        console.error('친구 정보 가져오기 실패', error);
        return '이름 없음';
    }
}

const friendInfo = ref({})

onMounted(async () => {
    store.getUser(route.params.id)
    await Fstore.getFriendList(store.LoginUser.id);
    friendInfo.value = await Promise.all(extractedFriendIds.value.map(getFriendInfo));
});

</script>

<style scoped>
.friend-list-header {
    display: grid;
    margin-top: 3vh;
}

.friend-list-header-d{
    display: flex;
    justify-content: space-around;
    text-align: center;
    /* margin-left: 10px; */
}

.friend-detail {
    display: flex;
    justify-content: space-around;
}
.friend-icon {
    width: 71px;
    height: 67px;
}

</style>