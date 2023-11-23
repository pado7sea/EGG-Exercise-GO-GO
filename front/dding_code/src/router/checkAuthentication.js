// router/checkAuthentication.js

import { useUserStore } from '@/stores/user';

export default async function checkAuthentication(to, from, next) {
  const userStore = useUserStore();

  try {
    // 사용자 정보를 가져와서 로그인 상태를 확인
    await userStore.getUser();

    // getUser 메서드가 예외를 던지지 않으면(정상적으로 사용자 정보를 가져오면) 로그인 상태로 간주
    // console.log('Authentication check successful. Proceeding to the next route.');
    next();
  } catch (error) {
    // getUser 메서드가 예외를 던지면 로그인하지 않은 상태로 간주
    // console.error('Authentication check failed. Redirecting to login page.');
    next('/login');
  }
}
