import MenuProject from '@/common/primary/components/AppMenu/AppMenuVue.vue';
import { mount } from '@vue/test-utils';

// Mock de vue-router
vi.mock('vue-router', () => ({
    useRoute: () => ({ path: '/Project' }),
    useRouter: () => ({
        resolve: vi.fn(route => ({ path: route })),
    }),
}));

// Mock de vue-i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: (key: string) => key,
    }),
}));

describe('MenuProject.vue', () => {
    // Given: un composant MenuProject
    it('renders the component correctly', () => {
        // When: Le composant est monté
        const wrapper = mount(MenuProject);

        // Then: On vérifie que le composant est rendu sans erreur
        expect(wrapper.exists()).toBe(true);

        // Then: On vérifie que les éléments sont rendus correctement
        expect(wrapper.vm.items).toEqual([
            {
                label: 'AppMenu.Accueil',
                icon: 'pi pi-fw pi-home',
                route: '/',
            },
            {
                label: 'AppMenu.MesProjets',
                icon: 'pi pi-fw pi-folder',
                route: '/ProjectList',
            },
        ]);
    });

    it('selects the correct active page on mount', () => {
        //When: On monte le composant
        const wrapper = mount(MenuProject);
        //Then: la bonne page du menu est sélectionnée
        expect(wrapper.vm.active).toBe(-1);
    });
});
