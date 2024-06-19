import { shallowMount } from '@vue/test-utils';
import { createPinia } from 'pinia';
import BreadCrumbVue from '../BreadCrumbVue.vue';
import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';

// Mock de vue-i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('BreadCrumbVue.vue', () => {
    let wrapper;
    const pinia = createPinia();
    beforeEach(() => {
        // Given: le composant BreadCrumbVue avec le i18n configuré est monté avant le test
        wrapper = shallowMount(BreadCrumbVue, {
            global: {
                plugins: [pinia],
            },
        });
    });

    it('renders the BreadCrumbVue component without errors', () => {
        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });

    it('updates breadCrumbItems when selectedRubrique changes', async () => {
        const refStore = useReferentielStore();

        // Créer une référence à selected rubrique et sous rubrique
        const selectedRubrique = 'Test Rubrique';
        const selectedSousRubrique = 'Test SousRubrique';

        refStore.selectedRubrique = selectedRubrique;
        refStore.selectedSousRubriques = selectedSousRubrique;

        // attendre la mise a jour du composant
        await wrapper.vm.$nextTick();

        // Then: Fil d'ariane doit être mis à jour correctement
        expect(wrapper.vm.breadCrumbItems).toEqual([{ label: selectedRubrique }, { label: selectedSousRubrique }]);
    });
});
