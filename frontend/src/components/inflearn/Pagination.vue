<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li
          v-if="currentPage !== 1"
          class="page-item"
      >
        <a class="page-link" href="#"
           @click="onClick(currentPage - 1)">
          Previous
        </a>
      </li>
      <li class="page-item"
          v-for="pageNum in numberOfPages"
          :key="pageNum"
          :class="currentPage === pageNum ? 'active' : ''"
      >
        <a class="page-link"
           @click="onClick(pageNum)"
           href="#">
          {{pageNum}}
        </a>
      </li>
      <li  v-if="numberOfPages !== currentPage"
           class="page-item"
      >
        <a class="page-link" href="#"
           @click="onClick(currentPage + 1)">
          Next
        </a>
      </li>
    </ul>
  </nav>
</template>
<script>
export default {
  props: {
    numberOfPages: {
      type: Number,
      required: true
    },
    currentPage: {
      type: Number,
      required: true
    }
  },
  emits: ['click'],
  setup(props, {emit}) {
    const onClick = (pageNum) => {
      emit('click', pageNum);
    }

    return {
      onClick
    }
  }
}
</script>
<style scoped>

</style>