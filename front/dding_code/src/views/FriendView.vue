<template>
    <div class="friend-container">
        <div class="friend-user">
            <div>
                <img src="@/assets/user-chick.png" style="width: 136px; height: 116px; padding-top: 20px;">
            </div>
            <div class="friend-user-description">
                <span class= "user-info">{{ store.LoginUser.name }} </span>님의 
                <div>
                    현재 알 개수는 <span class="user-info"> {{ store.LoginUser.egg_count }}</span>개 입니다.
                </div>
            </div>
        </div>
        <div class="friend-list-header">
            
            <div style="padding-left: 20px;">친구</div>
            <div class="friend-list-header-d">
                <div style="width: 71px; height: 67px;"></div>
                <div>아이디</div>
                <div>이름</div>
                <div>현재 계란 개수</div>
            </div>
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
            </div>
        </div>
    </div>
</template>
  
<script setup>
// import friendDetail from '../components/friend/friendDetail.vue';
import { useUserStore } from '@/stores/user';
import { useFriendStore } from '@/stores/friend';
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, computed } from 'vue'
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
.friend-container {
    display: flex;
    flex-direction: column;
    background-color: #FFFCF0;
    margin: 5vh 30vw;
}

.friend-user {
    display: flex;
    justify-content: space-around;
    justify-items: center;
    padding-left: 3vw;
    padding-right: 3vw;

}

.user-info{
    font-size: x-large;
    font-weight: 200;
    text-align: center;
    
}

.friend-user-description {
    float: left;
    padding-top: 6vh;
}

.friend-icon {
    width: 71px;
    height: 67px;
}

.friend-list-header{
    display: grid;
    grid-template-rows: repeat(4, 1fr);
}

.friend-detail {
    display: flex;
    justify-content: space-around;
}
</style>