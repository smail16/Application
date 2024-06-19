import { mount } from '@vue/test-utils';
import CriteresVue from '../CriteresVue.vue';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('CriteresVue.vue', () => {
    // Given: Un composant CriteresVue avec le i18n configuré
    it('renders the CriteresVue component without errors', () => {
        // When: Nous montons le composant CriteresVue
        const wrapper = mount(CriteresVue, {
            propsData: {
                activePageDetails: {},
                criteresUXList: [],
                pratiquesUXList: [],
                sousRubriqueUxTitles: [],
                criteresDevList: [],
                pratiquesDevList: [],
                sousRubriqueDevTitles: [],
                criteresFonctionelsList: [],
                pratiquesFonctionellesList: [],
                sousRubriqueFonctionelleTitles: [],
                updatedProgressUX: 0,
                updatedProgressDevelop: 0,
                updatedProgressFunc: 0,
            },
        });

        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
