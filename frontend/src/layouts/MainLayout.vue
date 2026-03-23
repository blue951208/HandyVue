<script setup lang="ts">
import { RouterView, useRouter } from 'vue-router';

const router = useRouter();

// 홈으로 이동하는 함수
const goHome = () => {
  router.push('/');
};
</script>

<template>
  <div class="admin-container">
    <!-- 왼쪽 메뉴바 -->
    <aside class="sidebar">
      <div class="home-button" @click="goHome">
        HOME
      </div>
      <nav class="menu-list">
        <router-link to="/test" class="menu-item">테스트 화면</router-link>
        <router-link to="/product" class="menu-item">상품 관리</router-link>
        <router-link to="/setting" class="menu-item">시스템 설정</router-link>
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
  border: 1px solid #eee;
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
</style>