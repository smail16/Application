import { describe, expect, it } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import CreateProjectVue from '@/common/primary/views/CreateProjectPage/CreateProjectVue.vue';
import ToastService from 'primevue/toastservice';
import { createPinia } from 'pinia';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('CreateProjectVue.vue', () => {
    let wrapper;
    const pinia = createPinia();
    beforeEach(() => {
        // Given: le composant est monté avant le test

        wrapper = shallowMount(CreateProjectVue, {
            global: {
                plugins: [ToastService, pinia],
            },
        });
    });

    it('renders correctly', () => {
        // Then: le composant devrait être rendu correctement
        expect(wrapper.exists()).toBe(true);
    });

    describe('Validation tests', () => {
        it('Should show a name required error for empty name field', () => {
            // When: le champ nom est vide
            const errorName = wrapper.vm.validateName('');
            // Then: une erreur "nom requis" devrait être affichée
            expect(errorName).toBe('CreateProject.NameRequired');
        });

        it('Should validate name correctly for non empty name field', () => {
            // When: le champ nom n'est pas vide
            const validName = wrapper.vm.validateName('My name');
            // Then: le nom devrait être validé correctement
            expect(validName).toBe(true);
        });
    });
});
