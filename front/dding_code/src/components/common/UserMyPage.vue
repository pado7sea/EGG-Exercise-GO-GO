<template>
    <div>
        <div class="user-mypage-container">
            <div class="user-mypage-box">
                <div>
                    <img src="@/assets/user-chick.png" style="width: 136px; height: 116px; padding: 20px;">
                </div>
                <div class="user-info-description">
                    <span class="user-info">{{ store.LoginUser.name }} </span>님의
                    <div>
                        현재 알 개수는 <span class="user-info"> {{ store.LoginUser.egg_count }}</span>개 입니다.
                    </div>
                    <div>
                        최근 운동 인증 날짜: 
                    </div>
                </div>
            </div>
            </div>
    </div>
</template>

<script setup>
import { useUserStore} from '@/stores/user';
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, computed } from 'vue'

const store = useUserStore()
// const Bstore = useBoardStore()
const route = useRoute()

onMounted(async () => {
    store.getUser(route.params.id)
    Bstore.getBoardList
});

</script>

<style scoped>
.user-mypage-container {
    display: flex;
    background-color: #FFFCF0;
    justify-content: space-around;
    text-align: center;
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

.user-info-description{
    padding: 20px;   
}
</style>