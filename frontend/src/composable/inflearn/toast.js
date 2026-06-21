// import { ref, onUnmounted } from 'vue';
import { useStore } from "vuex";
import {computed} from "vue";

/**
 * export default useToast 로 하는 경우
 * import useToast from 으로 사용 가능하지만
 * export const useToast 로 하는 경우
 * import { useToast } from 으로 사용 가능하다.
 */

export const useToast = () => {
    const store = useStore();
    const toastMessage = computed(() => store.getters['toast/toastMessageWithSmile']);
    const toastAlertType = computed(() => store.state.toast.toastAlertType);
    const showToast = computed(() => store.state.toast.showToast);
    // const timeout = computed(() => store.state.timeout);
    const triggerToast = (message, type = 'success') => {
        // toastMessage.value = message;
        // toastAlertType.value = type;
        // showToast.value = true;
        // timeout.value = setTimeout(() => {
        //     toastMessage.value = '';
        //     toastAlertType.value = '';
        //     showToast.value = false;
        // }, 5000)

        // store 에 actions 를 통해서 triggerToast 를 호출하도록 수정
        store.dispatch("toast/triggerToast", message, type);
    }

    // onUnmounted(() => {
    //     clearTimeout(timeout.value);
    // });

    return {
        toastMessage,
        toastAlertType,
        showToast,
        triggerToast
    }
}