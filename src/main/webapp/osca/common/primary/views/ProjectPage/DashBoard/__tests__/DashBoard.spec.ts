import { shallowMount } from '@vue/test-utils';
import { createPinia } from 'pinia';

import DashBoardVue from '../DashBoardVue.vue';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

const pinia = createPinia();

const wrapper = shallowMount(DashBoardVue, {
    global: {
        plugins: [pinia],
    },
});
describe('DashBoardVue.vue', () => {
    // Given: Un composant DashBoardVue avec le i18n configuré
    it('renders the DashBoardVue component without errors', () => {
        // When: Nous montons le composant DashBoardVue
        const wrapper = shallowMount(DashBoardVue);
        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
