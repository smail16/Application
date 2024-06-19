import { describe, it, expect } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import ProjectListVue from '../ProjectListVue.vue';
import { createPinia } from 'pinia';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

const pinia = createPinia();

describe('ProjectListTabsVue.vue', () => {
    it('renders correctly', () => {
        // Given: Un composant ProjectList
        const wrapper = shallowMount(ProjectListVue, {
            global: {
                plugins: [pinia],
            },
        });
        // Then: Le composant doit être rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });

    it('verifies that the provided translation keys are correct', () => {
        // Given: Un composant ProjectList
        const wrapper = shallowMount(ProjectListVue);
        // Then: Les clés de traduction doivent être correctes
        expect(wrapper.text()).toContain('MyProjects.create');
        expect(wrapper.text()).toContain('MyProjects.projet');
    });

    it('rend correctement les traductions i18n', async () => {
        // Given: Nous montons le composant avec un mock pour i18n
        const wrapper = shallowMount(ProjectListVue);
        // Then: nous vérifions si les clés de traduction sont correctement rendues
        expect(wrapper.html()).toContain('MyProjects.projet');
        expect(wrapper.html()).toContain('MyProjects.create');
        expect(wrapper.html()).toContain('MyProjects.NomProjet');
        expect(wrapper.html()).toContain('MyProjects.CreationDate');
    });
});
