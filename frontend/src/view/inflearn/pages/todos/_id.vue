<template>
  <h1>Todo Page Detail</h1>
  <div v-if="loading">
    Loading...
  </div>
  <form v-else>
    <div class="form-group">
      <label>Todo Subject</label>
      <input type="text"  v-model="todo.subject" class="form-control">
    </div>
    <button class="btn btn-primary">저장</button>
  </form>
</template>
<script>
import {useRoute} from "vue-router";
import axios from "axios";
import {ref} from "vue";

export default {
  setup() {
    const route = useRoute();
    const todo = ref(null);
    const loading = ref(true);

    console.log(route.params);

    const getTodo = async () => {
      const res = await axios.get('http://localhost:3000/todos/' + route.params.id);
      todo.value = res.data;
      loading.value = false;
    }

    getTodo();

    return {
      todo,
      loading

    }
  }
}
</script>
<style scoped>

</style>