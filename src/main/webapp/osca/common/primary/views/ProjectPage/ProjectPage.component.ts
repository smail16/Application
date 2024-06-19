import { useI18n } from 'vue-i18n';
import PanelMenuCriteres from '@/common/primary/views/ProjectPage/PanelMenuCritere/PanelMenuCritereVue.vue';
import MaquetteProjectVue from '@/common/primary/views/ProjectPage/MaquetteProject/MaquetteProjectVue.vue';
import WrapperCriteresVue from './WrapperCriteres/WrapperCriteresVue.vue';
import BreadCrumbVue from '@/common/primary/views/ProjectPage/BreadCrumb/BreadCrumbVue.vue';

export default {
    setup() {
        const { t } = useI18n();
        return { t };
    },

    name: 'ProjectPage',
    components: { PanelMenuCriteres, WrapperCriteresVue, MaquetteProjectVue, BreadCrumbVue },
};
