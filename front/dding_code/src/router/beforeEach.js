// router/beforeEach.js

import { useUserStore } from '@/stores/user';
import checkAuthentication from './checkAuthentication';

export default function (to, from, next) {
    // checkAuthentication(to, from, next);
    const userStore = useUserStore(); // 항상 새로운 스토어 인스턴스 생성
    const isAuthenticated = userStore.loggedInUserId !== null && userStore.loggedInUserId !== undefined;

    // console.log('loggedInUserId:', userStore.loggedInUserId);

    if (to.matched.some(record => record.meta.requiresAuth)) {
        // 이 라우트에는 인증이 필요
        if (!isAuthenticated) {
            // 로그인이 필요한데 사용자가 로그인되어 있지 않으면 알림 창 표시
            if (confirm('로그인이 필요한 페이지입니다. 로그인 하시겠습니까?')) {
                // 사용자가 확인을 누르면 로그인 페이지로 이동
                // console.log('Redirecting to login page.');
                // console.log('User not authenticated. Redirecting to login page.');
                next('/');
            } else {
                // 사용자가 취소를 누르면 현재 경로에서 이탈
                next(false);
            }
        } else {
            // 사용자가 로그인되어 있으면 허용
            // console.log('User authenticated. Proceeding to the next route.');
            next();
        }
    } else {
        // 이 라우트에는 인증이 필요하지 않음
        // console.log('Route does not require authentication. Proceeding to the next route.');
        next();
    }
}
