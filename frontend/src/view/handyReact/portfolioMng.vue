<script setup lang="ts">
import axios from "axios";
import {onMounted, ref} from "vue";

interface Carrer {
  vCareerId: string;
  vCareerNm: string;
  dStartDtm: string;
  dEndDtm: string;
}
const careerInfo = ref({
  vCareerId: '',
  vCareerNm: '',
  dStartDtm: '',
  dEndDtm: ''
});

const careerList = ref<Carrer[]>([]);

const getCarrerList = async () => {
  console.log('getCarrerList');
  const response = await axios.get("/api/career/list");
  careerList.value = response.data;
}

const getCareerInfo = async (vcareerId: string) => {
  console.log('getCareerInfo : ', vcareerId);
  const response = await axios.get('/api/career/detail', {
    params: {
      vCareerId: vcareerId
    }
  });

  console.log('getCareerInfo : ',response);
  careerInfo.value = response.data;
}

const updateCareerInfo = async () => {
  console.log('chk : ',careerInfo.value);
  const url = careerInfo.value.vCareerId ? "/api/career/update" : "/api/career/insert";
  const res = await axios.post(url, careerInfo.value);
  console.log('res : ',res);
  if (res.status === 200) {
    alert('등록 성공');
    getCarrerList();
  } else {
    alert('등록 실패');
  }

  // 데이터 초기화
  careerInfo.value = {
    vCareerId: '',
    vCareerNm: '',
    dStartDtm: '',
    dEndDtm: ''
  };
}

const deleteCareerInfo = async (vcareerId: string) => {
  if (!confirm('정말 삭제하시겠습니까?')) {
    return;
  }

  console.log('deleteCareerInfo : ', vcareerId);
  const res = await axios.get('/api/career/delete', {
    params: {
      vCareerId: vcareerId
    }
  });

  console.log('deleteCareerInfo : ', res);
  if (res.status === 200) {
    alert('삭제 성공');
    getCarrerList();
  } else {
    alert('삭제 실패');
  }
};

onMounted(() => {
  getCarrerList();
})

</script>

<template>
  <span>포트폴리오 관리</span>

  <form style="margin-top: 30px; border: 1px solid black; padding: 20px; width: 400px">
    <div>
      <h2>경력</h2>
      <!-- 경력명 -->
      <div>
        <label for="vCareerNm">경력명:</label>
        <input type="text" id="vCareerNm" name="vCareerNm" v-model="careerInfo.vCareerNm"/>
      </div>
      <!-- 시작 기간 -->
      <div>
        <label for="dStartDtm">시작 기간:</label>
        <input type="date" id="dStartDtm" name="dStartDtm" v-model="careerInfo.dStartDtm"/>
      </div>
      <!-- 종료 기간 -->
      <div>
        <label for="dEndDtm">종료 기간:</label>
        <input type="date" id="dEndDtm" name="dEndDtm" v-model="careerInfo.dEndDtm"/>
      </div>
      <button type="button" @click="updateCareerInfo">등록/수정</button>
    </div>
  </form>

  <table border="1" style="margin-top: 30px; width: 100%">
    <colgroup>
      <col style="width: 14%" />
      <col style="width: 50%" />
      <col style="width: 16%" />
      <col style="width: 16%" />
      <col style="width: 3%; align-content: center"/>
    </colgroup>
    <thead>
      <tr>
        <th>ID</th>
        <th>경력명</th>
        <th>시작 기간</th>
        <th>종료 기간</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="career in careerList" :key="career.vCareerId">
        <td><a href="javascript:void(0)" @click="getCareerInfo(career.vCareerId)">{{ career.vCareerId }}</a></td>
        <td>{{ career.vCareerNm }}</td>
        <td>{{ career.dStartDtm }}</td>
        <td>{{ career.dEndDtm }}</td>
        <td style="text-align: center"><button @click="deleteCareerInfo(career.vCareerId)">삭제</button></td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>

</style>