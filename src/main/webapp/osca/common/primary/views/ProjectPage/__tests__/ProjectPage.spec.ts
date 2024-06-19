import { shallowMount } from '@vue/test-utils';
import { createPinia } from 'pinia';

import ProjectPageVue from '../ProjectPageVue.vue';
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

const pinia = createPinia();

const wrapper = shallowMount(ProjectPageVue, {
    global: {
        plugins: [pinia],
    },
});
describe('ProjectPageVue.vue', () => {
    // Given: Un composant ProjectPageVue avec le i18n configuré
    it('renders the ProjectPageVue component without errors', () => {
        // When: Nous montons le composant ProjectPageVue
        const wrapper = shallowMount(ProjectPageVue);
        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
