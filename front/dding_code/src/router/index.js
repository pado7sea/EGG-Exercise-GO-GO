import { createRouter, createWebHistory } from "vue-router";
import beforeEachGuard from './beforeEach';
import checkAuthentication from '@/router/checkAuthentication';

import HomeView from "@/views/HomeView.vue";
import IndoorYoutubeView from "@/views/IndoorYoutubeView.vue";
import OutdoorYoutubeView from "@/views/OutdoorYoutubeView.vue";
import BoardView from "@/views/BoardView.vue";
import KakaoView from "@/views/KakaoView.vue";
import FriendView from "@/views/FriendView.vue";
import LoginView from "@/views/LoginView.vue";
import SignView from "@/views/SignView.vue"
import BoardList from "@/components/board/BoardList.vue";
import BoardCreate from "@/components/board/BoardCreate.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import FriendDetail from "@/components/friend/FriendDetail.vue";

import Indoor from "@/components/home/Indoor.vue";
import Outdoor from "@/components/home/Outdoor.vue";
import IndoorCarousel from "@/components/home/IndoorCarousel.vue";
import OutdoorCarousel from "@/components/home/OutdoorCarousel.vue";
import VideoSlide from "@/components/home/VideoSlide.vue";
import MapSlide from "@/components/home/MapSlide.vue";

import KakaoMap from "@/components/kakao/KakaoMap.vue";
import Map from "@/components/kakao/Map.vue";
import Path from "@/components/kakao/Path.vue";
import StreetView from "@/components/kakao/StreetView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/home",
      name: "home",
      component: HomeView,
    },
    {
      path: "/indoor",
      name: "indoor",
      component: Indoor,
    },
    {
      path: "/outdoor",
      name: "outdoor",
      component: Outdoor,
    },
    {
      path: "/indoorcarousel",
      name: "IndoorCarousel",
      component: IndoorCarousel,
    },
    {
      path: "/outdoorcarousel",
      name: "outdoorCarousel",
      component: OutdoorCarousel,
    },
    {
      path: "/videoslide",
      name: "videoslide",
      component: VideoSlide,
    },
    {
      path: "/mapslide",
      name: "mapslide",
      component: MapSlide,
    },
    {
      path: "/iyoutube",
      name: "IndoorYoutube",
      component: IndoorYoutubeView,
    },
    {
      path: "/oyoutube",
      name: "OutdoorYoutube",
      component: OutdoorYoutubeView,
    },
    {
      path: "/friend",
      name: "friend",
      component: FriendView,
      meta: { requiresAuth: true }, // 인증이 필요한 페이지에는 이 메타 필드를 추가
      beforeEnter: checkAuthentication,
      children: [
        {
          path: "",
          name: "friendDetail",
          component: FriendDetail,
          // meta: { requiresAuth: true }, // 인증이 필요한 페이지에는 이 메타 필드를 추가
          // beforeEnter: checkAuthentication,
        },
      ]
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList,
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate,
          meta: { requiresAuth: true }, // 인증이 필요한 페이지에는 이 메타 필드를 추가
          beforeEnter: checkAuthentication,
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail,
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate,
        },
      ],
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KakaoView,
    },
    {
      path: "/kakaomap",
      name: "kakaomap",
      component: KakaoMap,
    },
    
    {
      path: "/map",
      name: "map",
      component: Map,
    },
    {
      path: "/path",
      name: "path",
      component: Path,
    },
    {
      path: "/streetview",
      name: "streetview",
      component: StreetView,
    },
    {
      path:"/",
      name: "login",
      component: LoginView,
    },
    {
      path: "/sign",
      name: "sign",
      component: SignView,
    },
    
  ],
});

router.beforeEach(beforeEachGuard);
export default router;