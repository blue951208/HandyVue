<script setup lang="ts">
import axios from "axios";
import {onMounted, ref} from "vue";

interface Carrer {
  vcareerId: string;
  vcareerNm: string;
  dstartDtm: string;
  dendDtm: string;
}

const careerList = ref<Carrer[]>([]);

const getCarrerList = async () => {
  console.log('getCarrerList');
  const response = await axios.get("http://localhost:8080/api/career/list");
  careerList.value = response.data;
}

onMounted(() => {
  getCarrerList();
})

</script>

<template>
  <span>포트폴리오 관리</span>

  <form>
    <div>
      <h2>경력</h2>
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
    </div>
  </form>

  <table border="1" style="margin-top: 30px; width: 100%">
    <colgroup>
      <col style="width: 14%" />
      <col style="width: 50%" />
      <col style="width: 18%" />
      <col style="width: 18%" />
    </colgroup>
    <thead>
      <tr>
        <th>ID</th>
        <th>경력명</th>
        <th>시작 기간</th>
        <th>종료 기간</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="career in careerList" :key="career.vcareerId">
        <td>{{ career.vcareerId }}</td>
        <td>{{ career.vcareerNm }}</td>
        <td>{{ career.dstartDtm }}</td>
        <td>{{ career.dendDtm }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>