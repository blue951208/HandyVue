<template>
  <!-- @submit.prevent 는 함수에서 e.preventDefault() 와 동일 기능 -->
  <form @submit.prevent="onSubmit">
    <div class="d-flex">
      <div class="flex-grow-1 mr-2">
        <!--  v-bind 는 생략 가능, :value=~ -->
        <!--  <input type="text"-->
        <!--         v-bind:value="name"-->
        <!--        @input="updateName" />-->
        <!-- 위 코드와 아래 v-model 은 동일 -->
        <input class="form-control" type="text" v-model="todo"
               placeholder="Type new to-do">
      </div>
      <div>
        <!-- v-on: 은 @ 로 대체가능 -->
        <button class="btn btn-primary"
                type="submit">Add</button>
      </div>
    </div>
    <div v-show="hasError" style="color: red">
      ERROR:This field cannot be empty.
    </div>
  </form>
</template>

<script>
import {ref} from "vue";

export default {
  emits: ['add-todo'], /* 부모 컴포넌트로 이벤트 전달할 때 명시적으로 선언 */
  setup(porps, { emit }) {
    const todo = ref('');

    const hasError = ref(false);

    const onSubmit = (e) => {
      // e.preventDefault();
      if (todo.value.trim() === "") {
        hasError.value = true;
      } else {
        emit('add-todo', {
          id: Date.now(),
          subject: todo.value,
          completed: false
        }); // 부모컴포넌트로 이벤트, 값 전송

        hasError.value = false;
        todo.value = '';
      }
    }


    return {
      todo,
      hasError,
      onSubmit,
    }
  }
}
</script>
<style>

</style>