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
                    path: '/',
                    name: 'home',
                    component: () => import('../view/HomeView.vue')
                },
                {
                    path: '/test',
                    name: 'test',
                    component: () => import('../view/TestView.vue')
                }
            ]
        }
    ]
})

export default router