import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layouts/MainLayout.vue'


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
                },
                {
                    path: '/inflearn/main',
                    name: 'inflearnMain',
                    component: () => import('../view/inflearn/inflearnMain.vue') as any /* 해당 파일에서 자바스크립트 사용 */
                }
            ]
        }
    ]
})

export default router;