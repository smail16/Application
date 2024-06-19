import { useI18n } from 'vue-i18n';
import AppSelecteurLangue from '@/common/primary/components/AppSelecteurLangue/AppSelecteurLangueVue.vue';

export default {
    setup() {
        const { t } = useI18n();
        return {
            t,
        };
    },
    components: {
        AppSelecteurLangue,
    },
};
