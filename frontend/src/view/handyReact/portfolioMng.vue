<script setup lang="ts">

</script>

<template>
  <span>포트폴리오 관리</span>

  <form>
    <!-- 경력명 -->
    <div>
      <label for="careerName">경력명:</label>
      <input type="text" id="careerName" name="careerName" />
    </div>
    <!-- 시작 기간 -->
    <div>
      <label for="startDate">시작 기간:</label>
      <input type="date" id="startDate" name="startDate" />
    </div>
    <!-- 종료 기간 -->
    <div>
      <label for="endDate">종료 기간:</label>
      <input type="date" id="endDate" name="endDate" />
    </div>
  </form>
</template>

<style scoped>

</style>