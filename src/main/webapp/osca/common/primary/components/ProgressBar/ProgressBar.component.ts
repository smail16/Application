import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useProjectStore } from '@/common/secondary/pinia/store';

export default {
    setup() {
        const store = useProjectStore();
        const { t } = useI18n();
        const progress = computed(() => Math.floor(store.progress));

        return { t, progress };
    },
};
