import HeaderLoggedIn from '@/common/primary/components/HeaderLoggedIn/HeaderLoggedInVue.vue';
import { describe, expect, it, vi } from 'vitest';
import { mount } from '@vue/test-utils';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: (key: string) => key,
    }),
}));

// Given: Un composant HeaderLoggedIn avec des composants enfants AppDropDown et AppLogin

describe('HeaderLoggedIn titles', () => {
    it('renders the logo, OSCA, and OUTIL', () => {
        const wrapper = mount(HeaderLoggedIn);

        // When: Nous cherchons les éléments du logo, OSCA et OUTIL
        const logo = wrapper.find('.logo');
        const osca = wrapper.find('.osca');
        const outils = wrapper.find('.outils');

        // Then: Nous devrions trouver le logo, OSCA et OUTIL
        expect(wrapper.exists()).toBe(true);
        expect(logo.exists()).toBeTruthy;
        expect(osca.exists()).toBeTruthy;
        expect(outils.exists()).toBeTruthy;
    });
});
