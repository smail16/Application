import { shallowMount } from '@vue/test-utils';
import DialogeDeleteMaquette from '@/common/primary/components/DialogueDeleteMaquette/DialogueDeleteMaquetteVue.vue';
import ToastService from 'primevue/toastservice';
import { createPinia } from 'pinia';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('DialogeDeleteMaquette.vue', () => {
    let wrapper;
    const pinia = createPinia();

    beforeEach(() => {
        // Given: le composant est monté avant le test
        wrapper = shallowMount(DialogeDeleteMaquette, {
            global: {
                plugins: [ToastService, pinia],
            },
        });
    });

    it('renders correctly', () => {
        // Then: le composant devrait être rendu correctement
        expect(wrapper.exists()).toBe(true);
    });
});


