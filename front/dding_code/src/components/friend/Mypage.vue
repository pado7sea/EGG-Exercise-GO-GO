<template>
    <div>
        <div class="friend-container">
            <div class="friend-user">
                <div>
                    <img src="@/assets/user-chick.png" style="width: 136px; height: 116px; padding-top: 20px;">
                </div>
                <div class="friend-user-description">
                    <span class="user-info">{{ store.LoginUser.name }} </span>님의
                    <div>
                        현재 알 개수는 <span class="user-info"> {{ store.LoginUser.egg_count }}</span>개 입니다.
                    </div>
                </div>
            </div>
            </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router'
import { ref, onMounted, computed } from 'vue'

const store = useUserStore()
const route = useRoute()

onMounted(async () => {
    store.getUser(route.params.id)
});
</script>

<style scoped>
.friend-container {
    display: flex;
    background-color: #b1c7fc;
    justify-content: space-around;
    text-align: center;
}

.friend-user {
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

</style>