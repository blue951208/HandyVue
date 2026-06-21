<template>
  <div class="form-group">
    <label>{{ label }}</label>
    <input
        :value="subject"
        @input="oninput"
        type="text"
        class="form-control"
    >
  </div>
  <div v-if="error"
       class="text-red">
    {{error}}
  </div>
</template>
<script>
export default {
  props: {
    label: {
      type: String,
      required: true,
    },
    error: {
      type: String,
      required: true,
    },
    subject: {
      type: String,
      required: true,
    }
  },
  setup(props, { emit }) {
    const oninput = (e) => {
      emit('update:subject', e.target.value);
      // 컴포넌트에 v-model:~ 사용방법
    }

    return {
      oninput,
    }
  }
}
</script>
<style scoped>
.text-red{
  color: red;
}
</style>