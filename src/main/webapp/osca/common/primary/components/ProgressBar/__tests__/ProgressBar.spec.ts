import { shallowMount } from '@vue/test-utils';
import { createPinia } from 'pinia';

import ProgressBar from '@/common/primary/components/ProgressBar/ProgressBarVue.vue';

vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

const pinia = createPinia();

// Mount le composant avec Vue Test Utils
const wrapper = shallowMount(ProgressBar, {
    global: {
        plugins: [pinia],
    },
});

describe('ProgressBar.vue', () => {
    // Given: Un composant ProgressBar avec le i18n configuré
    it('renders the ProgressBar component without errors', () => {
        // When: Nous montons le composant ProgressBar
        const wrapper = shallowMount(ProgressBar);
        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
