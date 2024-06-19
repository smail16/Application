import { defineComponent } from 'vue';
import { useToast } from 'primevue/usetoast';

export default defineComponent({
    setup() {
        const toast = useToast();

        const showError = (message: string) => {
            toast.add({ severity: 'error', summary: 'Error', detail: message, life: 3000 });
        };

        return { showError };
    },
});
