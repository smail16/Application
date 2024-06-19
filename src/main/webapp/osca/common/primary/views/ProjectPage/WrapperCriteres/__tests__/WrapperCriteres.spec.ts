import { shallowMount } from '@vue/test-utils';
import { WrapperCriteresVue } from '..';
import { createPinia } from 'pinia';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('PanelMenuCritereVue.vue', () => {
    let wrapper;
    const pinia = createPinia();
    beforeEach(() => {
        // Given: le composant WrapperCriteresVue avec le i18n configuré est monté avant le test
        wrapper = shallowMount(WrapperCriteresVue, {
            global: {
                plugins: [pinia],
            },
        });
    });
    it('renders correctly', () => {
        // Then: le composant WrapperCriteresVue devrait être rendu correctement
        expect(wrapper.exists()).toBe(true);
    });
});
