import { useUserStore } from '@/stores/user';

export default async function checkAuthentication(to, from, next) {
    const userStore = useUserStore();

    try {
        // 비동기 작업 수행 (예: API 호출, 데이터 가져오기 등)
        await userStore.checkAuthenticationAsync();

        // 비동기 작업이 완료되면 계속 진행
        console.log('Authentication check successful. Proceeding to the next route.');
        next();
    } catch (error) {
        // 에러가 발생하면 로그인 페이지로 리디렉션
        console.error('Authentication check failed. Redirecting to login page.');
        next('/login');
    }
}
