import { createRouter, createWebHistory } from 'vue-router'
import { ref, onMounted } from 'vue';
import MainLayout from '../layouts/MainLayout.vue'
import {getMenuList} from "@/api/menu.ts";

// 1. 데이터 타입 정의
interface MenuItem {
    v_menu_id: string;
    v_menu_nm: string;
    v_prnt_menu_id?: string;
    n_level: number;
    n_sort: number;
    v_url?: string;
    v_use_yn?: string;
}

// 2. 반응형 데이터 변수
const menuList = ref<MenuItem[]>([]);

const loadMenu = async () => {
    try {
        const response = await getMenuList();
        menuList.value = response.data;
    } catch (error) {
        console.log('메뉴 로드 error : ',error);
    }
}

onMounted(() => {
    console.log('test');
    loadMenu();
})

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            component: MainLayout, // 기본 레이아웃 적용
            children: [
                {
                    path: '/handyExp/calendar',
                    name: 'calendarMng',
                    component: () => import('../view/handyExp/calendarMng.vue')
                },
                {
                    path: '/handyReact/portfolio',
                    name: 'portfolioMng',
                    component: () => import('../view/handyReact/portfolioMng.vue')
                }
            ]
        }
    ]
})

export default router