<template>
  <div v-for="(todo, index) in todos"
       :key="todo.id"
       class="card mt-2">
    <div class="card-body p-2 d-flex align-items-center" @click="moveToPage(todo.id)"
        style="cursor: pointer">
      <div class="form-check flex-grow-1">
        <input class="form-check-input" type="checkbox"
               :value="todo.completed" :checked="todo.completed"
               @change="toggleTodo(index, $event)"
               @click.stop
        >
        <!-- @change 이벤트에 경우 click 이벤트 다음으로 체크되기 때문에 아래 따로 추가 -->
        <label class="form-check-label"
               :class="{ todo: todo.completed }">  <!-- todo.completed 가 true 일때 todo 클래스를 지정 -->
          {{todo.subject}}
        </label>
      </div>
      <div>
        <button class="btn btn-danger btn-sm"
                @click.stop="deleteTodo(index)"
        >
          <!-- @click.stop 으로 이벤트 버블링 차단 -->
          Delete
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  props: ['todos'], /* 부모 컴포넌트에서 지정해 받아오는 props */
  emits:['toggle-todo','delete-todo'], /* 부모 컴포넌트로 이벤트 전달할 때 명시적으로 선언 */
  setup(props,{emit}) { /* conetxt 대신 {emit} 으로도 대체 가능*/

    const router = useRouter();

    const toggleTodo = (index, event) => {
      // context.emit('toggle-todo', index);
      emit('toggle-todo', index, event.target.checked);
    }

    const deleteTodo = (index) => {
      // context.emit('delete-todo', index);
      emit('delete-todo', index);
    };

    const moveToPage = (todoId) => {
      // router.push(`/inflearn/todos/${todoId}`);
      router.push({
        name: 'inflearnTodo',
        params: {
          id: todoId
        }
      })
    }

    return {
      toggleTodo,
      deleteTodo,
      moveToPage,
    }
  }

}
</script>
<style>

</style>