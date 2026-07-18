<template>
  <h1 class="text-red">Todo Page Detail</h1>
  <TodoForm :editing="true" />
</template>
<script>
import TodoForm from "@/components/inflearn/TodoForm.vue";

export default {
  components: {
    TodoForm
  }
}
</script>
<style scoped>

</style>