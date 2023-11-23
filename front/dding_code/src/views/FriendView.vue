<template>
    <div class="friend-container">
        <!-- <Mypage /> -->
        <!-- <div style="padding-left: 20px;">친구</div> -->
        <div style="margin-top: 3vh; margin-left: 2vw;">
            <FriendSearchInput @search-results-updated="updateSearchResults" :showSearchResults="showSearchResults" />
        </div>
        <div>
            <FriendDetail />
        </div>
        <hr>
        <div class="user-grid-container" v-show="showSearchResults">
            <table class="user-table">
                <thead id="user-table">
                    <tr>
                        <th></th>
                        <th>아이디</th>
                        <th>닉네임</th>
                        <th>모은 계란 개수</th>
                    </tr>
                </thead>
                <tbody id="user-table">
                    <tr v-for="user in store.userList" :key="user.id">
                        <td><img src="@/assets/병아리.png" id="user-icon"></td>
                        <td>{{ user.id }}</td>
                        <td>{{ user.name }}</td>
                        <td>{{ user.egg_count }}</td>
                        <td>
                            <button @click="addFriend(user.id)" class="f-addbtn">친구 추가</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- <RouterView /> -->
    </div>
</template>
  
<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import FriendSearchInput from '@/components/friend/FriendSearchInput.vue';
import FriendDetail from '../components/friend/friendDetail.vue';
import { useUserStore } from '@/stores/user';
import { useFriendStore } from '../stores/friend';

const store = useUserStore();
const Fstore = useFriendStore();
const friend = ref({
    friend_id: '',
    user_id: store.LoginUser.id
});

const addFriend = async (userId) => {
    try {
        friend.value.friend_id = userId;
        const response = await axios.post('http://localhost:8080/friendapi/insert', friend.value);
        alert('친구 추가 완료');
        console.log(response.data);
    } catch (error) {
        console.log(error);
    }
};

const showSearchResults = ref(false);

const updateSearchResults = (value) => {
    showSearchResults.value = value;
};

onMounted(() => {
    store.getUserList();
});

const searchUserList = async () => {
    console.log('Searching users with:', searchUser.value);
    await store.searchUserList(searchUser.value);
    updateSearchResults(store.userList.length > 0);
};
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

.user-grid-conatiner{
    display: flex;
    
}

.user-description {
    display: flex;
    justify-content: space-around;
}

.user-itw {
    display: flex;
    justify-content: space-around;

}

.user-table{
    border-spacing: 30px 30px;
    text-align: center;
    width: 100%;

}

.f-addbtn{
    border: none;
    transition:0.2s;
    padding: 3px;
    padding-left: 3px;
    padding-right: 3px;
    background-color: #fefefe;
    border-radius: 30px;
}

.f-addbtn:hover{
    transform: scale(1.12);
    background-color: #9db9fa;
}



</style>
