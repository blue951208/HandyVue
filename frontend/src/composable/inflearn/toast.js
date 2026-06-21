import { ref, onUnmounted } from 'vue';
/**
 * export default useToast 로 하는 경우
 * import useToast from 으로 사용 가능하지만
 * export const useToast 로 하는 경우
 * import { useToast } from 으로 사용 가능하다.
 */
export const useToast = () => {
    const toastMessage = ref('');
    const toastAlertType = ref('');
    const showToast = ref(false);
    const timeout = ref(null);
    const triggerToast = (message, type = 'success') => {
        toastMessage.value = message;
        toastAlertType.value = type;
        showToast.value = true;
        timeout.value = setTimeout(() => {
            toastMessage.value = '';
            toastAlertType.value = '';
            showToast.value = false;
        }, 1000)
    }

    onUnmounted(() => {
        clearTimeout(timeout.value);
    })
    return {
        toastMessage,
        toastAlertType,
        showToast,
        triggerToast
    }
}