<script setup lang="ts">
import { RouterView, useRouter } from 'vue-router';
import {onMounted, ref} from "vue";
import {getMenuList} from "@/api/menu.ts";

const router = useRouter();

// 1. 데이터 타입 정의
interface MenuItem {
  id: number;
  name: string;
  path?: string;
  isOpen?: boolean; // 메뉴가 펼쳐져 있는지 상태값
  children?: Menu[];
}
interface Menu {
  vmenuId: string;
  vmenuNm: string;
  vparentMenuId?: string;
  nlevel: number;
  nsort: number;
  vurl?: string;
  vuseYn?: string;
  isOpen: boolean; // false로 초기화
}

// 2. 반응형 데이터 변수
const menuData = ref<Menu[]>([]);
const menuList = ref<MenuItem[]>([]);

const loadMenu = async () => {
  console.log('loadMenu');
  try {
    const response = await getMenuList();
    menuData.value = response.data;
    // 메뉴 데이터를 MenuItem 형태로 변환
    menuList.value = menuData.value.map((item: any) => ({
      id: item.vmenuId,
      name: item.vmenuNm,
      isOpen: item.isOpen,
      children: item.children
    }));

  } catch (error) {
    console.log('메뉴 로드 error : ',error);
  }
}

// const menuList = ref<MenuItem[]>([
//   {
//     id: 1,
//     name: 'HandyExp',
//     isOpen: false,
//     children: [
//       { id: 11, name: '일정 화면', path: '/handyExp/calendar' }
//     ]
//   },
//   {
//     id: 2,
//     name: 'HandyReact',
//     isOpen: false,
//     children: [
//       { id: 21, name: '포트폴리오', path: '/handyReact/portfolio' }
//     ]
//   }
// ]);

// 메뉴 클릭 시 열고 닫는 함수
const toggleMenu = (item: MenuItem) => {
  if (item.children) {
    item.isOpen = !item.isOpen;
  }
};

// 홈으로 이동하는 함수
const goHome = () => {
  router.push('/');
};

onMounted(() => {
  loadMenu();
});

</script>

<template>
  <div class="admin-container">
    <!-- 왼쪽 메뉴바 -->
    <aside class="sidebar">
      <div class="home-button" @click="goHome">
        HOME
      </div>
      <nav class="menu-list">
<!--        <router-link to="/test" class="menu-item">테스트 화면</router-link>-->
<!--        <router-link to="/product" class="menu-item">상품 관리</router-link>-->
<!--        <router-link to="/setting" class="menu-item">시스템 설정</router-link>-->
        <div v-for="menu in menuList" :key="menu.id" class="menu-group">
          <div class="menu-lv1" @click="toggleMenu(menu)">
            {{ menu.name }}
            <span v-if="menu.children">{{ menu.isOpen ? '▼' : '▶' }}</span>
          </div>

          <div v-if="menu.isOpen && menu.children" class="menu-lv2-container">
            <router-link
                v-for="child in menu.children"
                :key="child.vmenuId"
                :to="child.vurl!"
                class="menu-item menu-lv2"
            >
              {{ child.vmenuNm }}
            </router-link>
          </div>
        </div>
      </nav>
    </aside>

    <!-- 내용 -->
    <main class="main-content">
      <header class="content-header">
        <h2>내용</h2>
      </header>
      <section class="page-view">
        <RouterView />
      </section>
    </main>
  </div>
</template>

<style scoped>
.admin-container {
  display: flex;
  height: 100vh;
  width: 100vw;
}

.sidebar {
  width: 200px;
  border-right: 1px solid #ccc;
  display: flex;
  flex-direction: column;
}

.home-button {
  height: 60px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-weight: bold;
}

.menu-list {
  flex: 1;
  padding: 10px;
}

.menu-item {
  display: block;
  padding: 10px;
  text-decoration: none;
  color: #333;
  margin-bottom: 5px;
}

.menu-item:hover {
  background-color: #f5f5f5;
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.content-header {
  height: 60px;
  border-bottom: 1px solid #ccc;
  display: flex;
  align-items: center;
  padding-left: 20px;
}

.page-view {
  flex: 1;
  padding: 20px;
}

.menu-group {
  margin-bottom: 5px;
}

.menu-lv1 {
  padding: 12px;
  background-color: #f8f9fa;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  font-weight: bold;
  border-bottom: 1px solid #eee;
}

.menu-lv1:hover {
  background-color: #e9ecef;
}

.menu-lv2-container {
  background-color: #fff;
}

.menu-lv2 {
  padding: 10px 10px 10px 30px; /* 왼쪽 들여쓰기로 Lv2 표시 */
  font-size: 0.9em;
  color: #666;
  text-decoration: none;
  display: block;
  border-bottom: 1px dotted #eee;
}

.menu-lv2:hover {
  color: #42b983; /* Vue 공식 컬러 */
  background-color: #f0fff4;
}

/* 현재 선택된 메뉴 강조 (Router 전용 클래스) */
.router-link-active {
  color: #42b983;
  font-weight: bold;
  background-color: #f0fff4;
}
</style>