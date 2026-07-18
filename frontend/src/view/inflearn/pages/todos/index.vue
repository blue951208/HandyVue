<template>
  <div>
    <div class="d-flex justify-content-between mb-3">
      <h2>To-Do List</h2>
      <button
          class="btn btn-primary"
          @click="moveToCreatePage"
      >
        Create Todo
      </button>
    </div>
  <hr/>
<!--  <TodoSimpleForm @add-todo="addTodo"/>-->
  <div style="color: red">
    {{error}}
  </div>
  <div v-if="!todos.length">
    There is nothing to display
  </div>
  <TodoList :todos="todos"
            @toggle-todo="toggleTodo"
            @delete-todo="deleteTodo"
  />
  <hr/>
  <Pagination
      v-if="todos.length"
      :numberOfPages="numberOfPages"
      :currentPage="currentPage"
      @click="getTodos"
  />
  </div>
<!--  <Toast v-if="showToast"-->
<!--         :message="toastMessage"-->
<!--         :type="toastAlertType"-->
<!--  />-->
</template>
<script>
import {ref, computed, watch} from 'vue';
import axios from "axios";
// import TodoSimpleForm from "@/components/inflearn/TodoSimpleForm.vue";
import TodoList from "@/components/inflearn/TodoList.vue";
import Toast from '@/components/inflearn/Toast.vue';
import {useToast} from "@/composable/inflearn/toast.js";
import {useRouter}  from "vue-router";
import Pagination from "@/components/inflearn/Pagination.vue";
/*
 ref, reactive 로 반응형 데이터 선언 가능
 reactive 는 객체,Array 형태의 데이터를 선언할 때 사용
 ref 는 원시 데이터 타입을 선언할 때 사용
  - ref 는 .value 로 접근해야 함
  - reactive 는 .value 없이 바로 접근 가능
 */

export default {
  components: {
    // TodoSimpleForm,
    TodoList,
    Toast,
    Pagination,
  },
  setup: function () {  /* <script setup> 태그랑 중목으로 사용 불가 */
    const router = useRouter();
    const toggle = ref(false);
    const name = ref("");
    const todos = ref([]);

    const error = ref('');
    const numberOfTodos = ref(0);
    const perPage = ref(5);
    const currentPage = ref(1);
    const searchText = ref('');


    // 반응성을 가진 데이터를 사용하는 경우 호출(ref, reactive)
    // watchEffect(() => {});

    const numberOfPages = computed(() => {
      return Math.ceil(numberOfTodos.value/perPage.value);
    })

    const { showToast, toastMessage, toastAlertType, triggerToast } = useToast();


    /**
     * page 매개변수값이 없을경우 currentPage.value 를 사용하여 현재 페이지를 유지하도록 함
     * @param page
     * @returns {Promise<void>}
     */
    const getTodos = async (page = currentPage.value) => {
      currentPage.value = page;
      try {
        const res = await axios.get(`http://localhost:3000/todos?_sort=id&_order=desc&subject_like=${searchText.value}&_page=${page}&_limit=${perPage.value}`);
        numberOfTodos.value = res.headers['x-total-count'];
        todos.value = res.data;
      } catch (err) {
        console.log(err);
        error.value = 'Something went wrong while fetching todos.';
        triggerToast('[ERROR]:getTodos :: Something went wrong while fetching todos.', 'danger');
      }
    }

    getTodos();

    // const filterdTodos = computed(() => {
    //   if (searchText.value) {
    //     return todos.value.filter(todo => {
    //       return todo.subject.includes(searchText.value);
    //     })
    //   }
    //
    //   return todos.value;
    // });

    const todoStyle = {
      textDecoration: 'line-through',
      color: 'gray'
    }

    let timeout = null;
    const searchTodo = () => {
      clearTimeout(timeout);
      getTodos(1);
    };

    watch(searchText, () => {
      clearTimeout(timeout);
      timeout = setTimeout(() => {
        getTodos(1);
      }, 2000);
    });

    // typescript 에서는 이벤트 객체의 타입을 명시적으로 선언해야 함(자바스크립트에서는 e , e.target.value 로 사용 가능)
    const updateName = (e) => {
      name.value = e.target.value;
    }

    const onToggle = () => {
      toggle.value = !toggle.value;
    }

    const deleteTodo = async (id) => {
      error.value = '';
      try {
        await axios.delete("http://localhost:3000/todos/" + id);
        // todos.value.splice(index, 1);
        getTodos(1);
      } catch (err) {
        console.log(err);
        tiggerToast('[ERROR]:deleteTodos :: Something went wrong while delete todo.', 'danger');
        error.value = 'Something went wrong while delete todo.';
      }
    };

    const toggleTodo = async (index, checked) => {
      console.log('toggleTodo');
      error.value = '';
      const id = todos.value[index].id;

      try {
        await axios.patch("http://localhost:3000/todos/" + id, {
          completed: checked
        });

        todos.value[index].completed = checked
      } catch(err) {
        console.log(err);
        error.value = 'Something went wrong while update todo.';
      }
    }

    const addTodo = async (newTodo) => {
      error.value = '';
      // 데이터베이스 Todos 에 저장
      try {
        await axios.post('http://localhost:3000/todos', {
          subject: newTodo.subject,
          completed: newTodo.completed
        });
        getTodos(1);
        // todos.value.push(res.data);
      } catch (err) {
        error.value = 'Something went wrong while adding todo.';
      }
      // .then(res => {
      //   todos.value.push(res.data);
      // }).catch(err => {
      //   console.log(err);
      //   error.value = 'Something went wrong while adding todo.';
      // });
    };

    const count = ref(1);
    /* computed 는 계산된 값을 반환하는 반응형 데이터 선언할 때 사용 */
    const doubleCount = computed(() => {
      return count.value * 2
    });

    const moveToCreatePage = () => {
      router.push({
        name: 'inflearnTodoCreate'
      })
    }

    return {
      toggle,
      onToggle,
      name,
      todos,
      todoStyle,
      updateName,
      addTodo,
      error,
      toggleTodo,
      deleteTodo,
      count,
      doubleCount,
      searchText,
      // filterdTodos,
      numberOfPages,
      currentPage,
      getTodos,
      searchTodo,
      showToast,
      toastMessage,
      toastAlertType,
      moveToCreatePage,
    };
  }
}
</script>
<style scoped>

</style>