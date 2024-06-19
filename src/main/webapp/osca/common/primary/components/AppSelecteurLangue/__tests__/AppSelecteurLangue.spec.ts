import AppSelecteurLangue from '@/common/primary/components/AppSelecteurLangue/AppSelecteurLangueVue.vue';
import { describe, expect, it, vi } from 'vitest';
import { shallowMount } from '@vue/test-utils';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: (key: string) => key,
    }),
}));

describe('AppSelecteurLangue.vue', () => {
    // Describe global pour les tests de AppSelecteurLangue
    // Given: Un composant AppButton avec le i18n configuré
    it('renders the HomePage component without errors', () => {
        // When: Nous montons le composant AppSelecteurLangue
        const wrapper = shallowMount(AppSelecteurLangue);

        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
