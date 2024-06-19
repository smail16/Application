import { describe, expect, it } from 'vitest';
import { mount } from '@vue/test-utils';
import HomePageVue from '@/common/primary/views/HomePage/HomePageVue.vue';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: (key: string) => key,
    }),
}));
// Mock de useRouter et useRoute
vi.mock('vue-router', () => ({
    useRouter: () => ({
        resolve: vi.fn(route => ({ path: route })),
    }),
    useRoute: () => ({ path: '/CreateProject' }),
}));

describe('HomePageVue', () => {
    //Describe: un composant HomePage
    it('renders without errors', () => {
        //When: On monte le composant HomePage
        const wrapper = mount(HomePageVue);
        //Then: On vérifie qu'il est rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
