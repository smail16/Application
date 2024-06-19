import AppFooterVue from '@/common/primary/components/AppFooter/AppFooterVue.vue';
import { describe, expect, it, vi } from 'vitest';
import { mount, shallowMount } from '@vue/test-utils';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: (key: string) => key,
    }),
}));

describe('AppDropDown.vue', () => {
    // Describe global pour les tests de AppFooter
    // Given: Un composant AppButton avec le i18n configuré
    it('renders the HomePage component without errors', () => {
        // When: Nous montons le composant AppFooter
        const wrapper = shallowMount(AppFooterVue);

        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
    // Given: Un composant AppFooter

    it('renders the logo', () => {
        // When: Nous montons le composant AppFooter
        const wrapper = mount(AppFooterVue);

        // Then: Le logo doit être rendu correctement
        const logo = wrapper.find('.logofooter');
        expect(logo.exists()).toBeTruthy;
    });

    it('renders the rights', () => {
        // When: Nous montons le composant AppFooter
        const wrapper = mount(AppFooterVue);

        // Then: La partie "right 2023" doit être rendue correctement
        const rights = wrapper.find('.rights');
        expect(rights.exists()).toBeTruthy;
    });

    it('renders the slogan', () => {
        // When: Nous montons le composant AppFooter
        const wrapper = mount(AppFooterVue);

        // Then: Le slogan doit être rendu correctement
        const slogan = wrapper.find('.motto');
        expect(slogan.exists()).toBeTruthy;
    });
});
