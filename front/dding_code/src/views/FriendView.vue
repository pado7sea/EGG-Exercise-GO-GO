<template>
    <div class="friend-container">
        <!-- <Mypage /> -->
        <!-- <div style="padding-left: 20px;">친구</div> -->
        <div style="margin-top: 3vh; margin-left: 2vw;">
            <FriendSearchInput />
        </div>
        <div>
            <FriendDetail />
        </div>
        <hr>
        <div class="user-grid-container">
            <div class="user-card" v-for="user in store.userList" :key="user.id">
                <div v-if="store.LoginUser.id" class="user-card-inner">
                    <div class="user-img">
                        <img src="@/assets/병아리.png" id="user-icon">
                    </div>
                        <div class="user-itw">
                            <div class="user-id">
                                {{ user.id }}
                            </div>
                            <div class="user-tw" style="padding-top: 3px; padding-bottom: 3px;">
                                <div style="font-weight: bold;">{{ user.name }}
                                </div>
                            </div>
                            <div>
                                <button @click="addFriend(user.id)">친구추가</button>
                            </div>
                        </div>
                        <div>
                        </div>
                </div>
            </div>
        </div>
        <!-- <RouterView /> -->
    </div>
</template>
  
<script setup>
// import friendDetail from '../components/friend/friendDetail.vue';
import Mypage from '@/components/friend/Mypage.vue';
import { useUserStore } from '@/stores/user';

import { onMounted, ref } from 'vue';
import axios from 'axios'
import FriendSearchInput from '@/components/friend/FriendSearchInput.vue';
import FriendDetail from '../components/friend/friendDetail.vue';
import { useFriendStore } from '../stores/friend';

const store = useUserStore()
const Fstore = useFriendStore()
const friend = ref({
    friend_id: '',
    user_id: store.LoginUser.id
})

const addFriend = async (userId) => {
    try {
        friend.value.friend_id = userId; // Set friend_id to userId
        const response = await axios.post('http://localhost:8080/friendapi/insert', friend.value);
        alert('친구 추가 완료');
        console.log(response.data);
    } catch (error) {
        console.log(error);
    }
}
onMounted(() => {
    store.getUserList()
})




</script>
  
<style scoped>
.friend-container {
    display: flex;
    flex-direction: column;
    background-color: #b1c7fc;
    margin: 3vh 20vw;
}

.friend-user-description {
    float: left;
    padding-top: 6vh;
}

#user-icon {
    width: 71px;
    height: 67px;
}

/* .user-grid-container{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
} */

.user-card{
    /* display: flex; */
    margin-bottom: 10px;
    
}
.user-card-inner {
  display: flex;
  justify-content: space-around;
}

.user-description{
    display: flex;
    justify-content: space-around;
}
.user-itw{
    display: flex;
    justify-content: space-around;
}
</style>
