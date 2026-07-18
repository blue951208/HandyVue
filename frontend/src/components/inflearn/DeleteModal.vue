<template>
  <Modal>
    <template #title>
      Delete Todo!
    </template>
    <!-- v-slot: => # 으로 대체가능 -->
    <template #body>
      진짜 삭제하시겠습니까?
    </template>
    <template v-slot:footer>
      <button
          type="button"
          class="btn btn-secondary"
          @click="onClose"
      >
        Close
      </button>
      <button type="button" class="btn btn-danger"
              @click="onDelete">
        Delete
      </button>
    </template>
  </Modal>
</template>
<script>
import Modal from '@/components/inflearn/Modal.vue';
export default {
  components: {
    Modal,
  },
  setup(props, { emit }) {
    const onClose = () => {
      emit('close');
    };

    const onDelete = () => {
      emit('delete'); // 삭제 이벤트 발생
    };

    return {
      onClose,
      onDelete,
    }
  }
}
</script>
<style scoped>

</style>