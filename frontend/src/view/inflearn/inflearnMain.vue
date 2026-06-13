<template>
<!--  <div v-show="toggle">true</div>-->
<!--  <div v-show="!toggle">false</div>-->
<!--  <button @click="onToggle">toggle</button>-->
  <h4>count : {{count}}</h4>
  <h4>double Count : {{doubleCount}}</h4>
  <button @click="count++">Add one</button>

  <div class="container">
    <h2>To-Do List</h2>
    <input class="form-control" type="text"
           v-model="searchText"
           placeholder="Search">
    <hr/>
    <TodoSimpleForm @add-todo="addTodo"/>
    <div v-if="!filterdTodos.length">
      There is nothing to display
    </div>
    <TodoList :todos="filterdTodos"
              @toggle-todo="toggleTodo"
              @delete-todo="deleteTodo"
    />
  </div>
</template>

<script>
import {ref,computed} from 'vue';
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

    const searchText = ref('');
    const filterdTodos = computed(() => {
      if (searchText.value) {
        return todos.value.filter(todo => {
          return todo.subject.includes(searchText.value);
        })
      }

      return todos.value;
    });

    const todoStyle = {
      textDecoration: 'line-through',
      color: 'gray'
    }

    // typescript 에서는 이벤트 객체의 타입을 명시적으로 선언해야 함(자바스크립트에서는 e , e.target.value 로 사용 가능)
    const updateName = (e) => {
      name.value = e.target.value;
    }

    const onToggle = () => {
      toggle.value = !toggle.value;
    }

    const deleteTodo = (index) => {
      todos.value.splice(index, 1);
    };

    const toggleTodo = (index) => {
      todos.value[index].completed = !todos.value[index].completed;
      console.log('completed : ', todos.value[index].completed);
    }

    const addTodo = (newTodo) => {
      todos.value.push(newTodo);
    }

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
      toggleTodo,
      deleteTodo,
      count,
      doubleCount,
      searchText,
      filterdTodos,
    };
  }
}
</script>

<style>
  .todo {
    color: gray;
    text-decoration: line-through;
  }
</style>