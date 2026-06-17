<template>
  <div>
  <h2>To-Do Pages</h2>
  <input class="form-control" type="text"
         v-model="searchText"
         placeholder="Search"
         @keyup.enter="searchTodo"
  >
  <hr/>
  <TodoSimpleForm @add-todo="addTodo"/>
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
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li
          v-if="currentPage !== 1"
          class="page-item"
      >
        <a class="page-link" href="#"
           @click="getTodos(currentPage - 1)">
          Previous
        </a>
      </li>
      <li class="page-item"
          v-for="pageNum in numberOfPages"
          :key="pageNum"
          :class="currentPage === pageNum ? 'active' : ''"
      >
        <a class="page-link"
           @click="getTodos(pageNum)"
           href="#">
          {{pageNum}}
        </a>
      </li>
      <li  v-if="numberOfPages !== currentPage"
           class="page-item"
      >
        <a class="page-link" href="#"
           @click="getTodos(currentPage + 1)">
          Next
        </a>
      </li>
    </ul>
  </nav>
  </div>
</template>
<script>
import {ref, computed, watch} from 'vue';
import axios from "axios";
import TodoSimpleForm from "@/components/inflearn/TodoSimpleForm.vue";
import TodoList from "@/components/inflearn/TodoList.vue";
/*
 ref, reactive 로 반응형 데이터 선언 가능
 reactive 는 객체,Array 형태의 데이터를 선언할 때 사용
 ref 는 원시 데이터 타입을 선언할 때 사용
  - ref 는 .value 로 접근해야 함
  - reactive 는 .value 없이 바로 접근 가능
 */

export default {
  components: {
    TodoSimpleForm,
    TodoList
  },
  setup: function () {  /* <script setup> 태그랑 중목으로 사용 불가 */
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

    const deleteTodo = async (index) => {
      console.log('deleteTodo');
      error.value = '';
      const id = todos.value[index].id;
      try {
        await axios.delete("http://localhost:3000/todos/" + id);
        // todos.value.splice(index, 1);
        getTodos(1);
      } catch (err) {
        console.log(err);
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
    };
  }
}
</script>
<style scoped>

</style>