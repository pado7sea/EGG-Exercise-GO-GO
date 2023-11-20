import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import YoutubeView from "@/views/YoutubeView.vue";
import BoardView from "@/views/BoardView.vue";
import KakaoView from "@/views/KakaoView.vue";
import FriendView from "@/views/FriendView.vue";
import LoginView from "@/views/LoginView.vue";
import SignView from "@/views/SignView.vue"

import BoardList from "@/components/board/BoardList.vue";
import BoardCreate from "@/components/board/BoardCreate.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import Indoor from "@/components/home/Indoor.vue";
import Outdoor from "@/components/home/Outdoor.vue";
import Carousel from "@/components/home/Carousel.vue";
import VideoSlide from "@/components/home/VideoSlide.vue";
import MapSlide from "@/components/home/MapSlide.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
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
      path: "/carousel",
      name: "carousel",
      component: Carousel,
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
      path: "/youtube",
      name: "youtube",
      component: YoutubeView,
    },
    {
      path: "/friend",
      name: "friend",
      component: FriendView,
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
      path:"/login",
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

export default router;