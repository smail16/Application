import { toRefs } from 'vue';
import { useI18n } from 'vue-i18n';

export default {
    props: [
        'activePageDetails',
        'criteresUXList',
        'pratiquesUXList',
        'sousRubriqueUxTitles',
        'criteresDevList',
        'pratiquesDevList',
        'sousRubriqueDevTitles',
        'criteresFonctionelsList',
        'pratiquesFonctionellesList',
        'sousRubriqueFonctionelleTitles',
        'updatedProgressUX',
        'updatedProgressDevelop',
        'updatedProgressFunc',
    ],

    setup(props, { emit }) {
        const { t } = useI18n();

        const {
            criteresUXList,
            pratiquesUXList,
            sousRubriqueUxTitles,
            criteresDevList,
            pratiquesDevList,
            sousRubriqueDevTitles,
            criteresFonctionelsList,
            pratiquesFonctionellesList,
            sousRubriqueFonctionelleTitles,
        } = toRefs(props);

        const updateProgress = critere => {
            emit('critere-updated', critere);
        };

        return {
            t,
            criteresUXList,
            pratiquesUXList,
            sousRubriqueUxTitles,
            criteresDevList,
            pratiquesDevList,
            sousRubriqueDevTitles,
            sousRubriqueFonctionelleTitles,
            pratiquesFonctionellesList,
            criteresFonctionelsList,
            updateProgress,
        };
    },
};
