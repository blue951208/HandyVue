<script setup lang="ts">
import axios from "axios";
import {onMounted, ref} from "vue";

interface Schedule {
  vscheduleId: string;
  vtitle: string;
  vcont: string;
  dtargetSdtm: string;
  dtargetEdtm: string;
}

const scheduleList = ref<Schedule[]>([]);

const getScheduleList = async () => {
  console.log('getScheduleList');
  const response = await axios.get("http://localhost:8080/api/scheduleMst/list");
  scheduleList.value = response.data;
}

onMounted(() => {
  getScheduleList();
})

</script>

<template>
  <span>일정 관리</span>

  <table border="1">
    <thead>
      <tr>
        <th>ID</th>
        <th>제목</th>
        <th>내용</th>
        <th>기간</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="schedule in scheduleList" :key="schedule.vscheduleId">
        <td>{{ schedule.vscheduleId }}</td>
        <td>{{ schedule.vtitle }}</td>
        <td>{{ schedule.vcont }}</td>
        <td>{{ schedule.dtargetSdtm }} ~ {{ schedule.dtargetEdtm }}</td>
      </tr>
    </tbody>
  </table>
</template>
