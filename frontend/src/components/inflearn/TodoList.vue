<template>
<!--  <div v-for="(todo, index) in todos"-->
<!--       :key="todo.id"-->
<!--       class="card mt-2">-->
  <List :items="todos">
    <template v-slot:default="{item, index}">
      <div class="card-body p-2 d-flex align-items-center"
           @click="moveToPage(item.id)"
           style="cursor: pointer"
      >
        <div class="flex-grow-1">
          <input class="ml-2 mr-2"
                 type="checkbox"
                 :value="item.completed"
                 :checked="item.completed"
                 @change="toggleTodo(index, $event)"
                 @click.stop
          >
          <!-- @change 이벤트에 경우 click 이벤트 다음으로 체크되기 때문에 아래 따로 추가 -->
          <span :class="{ todo: item.completed }">  <!-- todo.completed 가 true 일때 todo 클래스를 지정 -->
          {{item.subject}}
        </span>
        </div>
        <div>
          <button class="btn btn-danger btn-sm"
                  @click.stop="openModal(item.id)"
          >
            <!-- @click.stop 으로 이벤트 버블링 차단 -->
            Delete
          </button>
        </div>
      </div>
    </template>
  </List>
<!--  </div>-->
  <teleport to="#modal">
    <Modal v-if="showModal"
           @close="closeModal"
           @delete="deleteTodo"
    >
      <template v-slot:title>
        Delete Todo!
      </template>
      <template v-slot:body>
        진짜 삭제하시겠습니까?
      </template>
      <template v-slot:footer>
        Delete Todo!
      </template>
    </Modal>
  </teleport>
</template>
<script>
import { useRouter } from "vue-router";
import Modal from "@/components/inflearn/DeleteModal.vue";
import { ref } from "vue";

import List from '@/components/inflearn/List.vue';
export default {
  components: {
    Modal,
    List,
  },
  props: ['todos'], /* 부모 컴포넌트에서 지정해 받아오는 props */
  emits:['toggle-todo','delete-todo'], /* 부모 컴포넌트로 이벤트 전달할 때 명시적으로 선언 */
  setup(props,{emit}) { /* conetxt 대신 {emit} 으로도 대체 가능*/
    const router = useRouter();
    const showModal = ref(false);
    const todoDeleteId = ref(null);

    const toggleTodo = (index, event) => {
      // context.emit('toggle-todo', index);
      emit('toggle-todo', index, event.target.checked);
    }

    const openModal = (id) => {
      todoDeleteId.value = id;
      showModal.value = true;
    }

    const closeModal = () => {
      todoDeleteId.value = null;
      showModal.value = false;
    }

    const deleteTodo = (index) => {
      // context.emit('delete-todo', index);
      emit('delete-todo', todoDeleteId.value);

      showModal.value = false;
      todoDeleteId.value = null;
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
      showModal,
      openModal,
      closeModal,
    }
  }

}
</script>
<style>
.todo {
  color: gray;
  text-decoration: line-through;
}
</style>