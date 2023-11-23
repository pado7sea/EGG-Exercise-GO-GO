<template>
    <div>
        <div class="user-mypage-container">
            <div class="user-mypage-box">
                <div>
                    <img src="@/assets/user-chick.png" style="width: 136px; height: 116px; padding: 20px; margin-top: 10px;">
                </div>
                <div class="user-info-description">
                    <span class="user-info">{{ store.LoginUser.name }} </span>님의
                    <div>
                        현재 알 개수는 <span class="user-info"> {{ store.LoginUser.egg_count }}</span>개 입니다.
                    </div>
                    <div>
                        최근 운동 인증 날짜: <span class="">{{ recentPostDate }}</span>
                    </div>
                </div>
            </div>
            <div style="display: flex; justify-content: flex-end;">
                <button @click="logoutInBox" class="logoutbtn"><img src="@/assets/logout.png" style="width: 25px; height: 25px; margin-right: 5px;">로그아웃</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, computed } from 'vue'
import { useBoardStore } from '@/stores/board';

const store = useUserStore()
// const Bstore = useBoardStore()
const route = useRoute()

const Bstore = useBoardStore()


onMounted(async () => {
    await store.getUser(route.params.id);
    await Bstore.getBoardList();
});

const recentPostDate = computed(() => {
    const userBoards = Bstore.boardList.filter((board) => board.writer === store.LoginUser.name);
    const sortedBoards = userBoards.sort((a, b) => new Date(b.regDate) - new Date(a.regDate));

    if (sortedBoards.length > 0) {
        return sortedBoards[0].regDate;
    } else {
        return "작성한 게시물이 없습니다.";
    }
});

const logoutInBox = () => {
    store.logoutUser();
}

</script>

<style scoped>
.user-mypage-container {
    display: flex;
    background-color: #FFFCF0;
    justify-content: space-around;
    text-align: center;
    flex-direction: column;
}

.user-mypage-box {
    display: flex;
    justify-content: space-around;
    justify-items: center;
    padding-left: 3vw;
    padding-right: 3vw;

}

.user-info {
    font-size: x-large;
    font-weight: 200;
    text-align: center;

}

.user-info-description {
    padding: 20px;
}

.logoutbtn{
    background-color: transparent;
    border-radius: 20px;
    padding: 5px;
    margin-bottom: 5px;
    border: none;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>