import { useI18n } from 'vue-i18n';
import { ref } from 'vue';
import { useProjectStore } from '@/common/secondary/pinia/store';

export default {
    setup() {
        const { t } = useI18n();
        const store = useProjectStore();
        let sticky = ref(false);
        const showMessage = ref(store.onCreatedPopUp);

        if (showMessage.value) {
            setTimeout(() => {
                store.setOnCreatedPopUp(false);
            }, 2000);
        }
        return { t, sticky, showMessage };
    },
    name: 'DashboardProject',
};
