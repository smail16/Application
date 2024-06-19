import { useI18n } from 'vue-i18n';
import AppMenuVue from '@/common/primary/components/AppMenu/AppMenuVue.vue';

export default {
    setup() {
        const { t } = useI18n();
        return { t };
    },
    name: 'HomePageVue',
    components: {
        AppMenuVue,
    },
};
