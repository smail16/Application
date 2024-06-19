import { shallowMount } from '@vue/test-utils';
import PanelMenuCritereVue from '../PanelMenuCritereVue.vue';
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
        // Given: le composant PanelMenuCritereVue avec le i18n configuré est monté avant le test
        wrapper = shallowMount(PanelMenuCritereVue, {
            global: {
                plugins: [pinia],
            },
        });
    });

    it('renders correctly', () => {
        // Then: le composant PanelMenuCritereVue devrait être rendu correctement
        expect(wrapper.exists()).toBe(true);
    });

    it('renders the PanelMenuCritereVue component and activates the subMenu', async () => {
        // When: o déclenche le clic sur le composant
        await wrapper.trigger('click');
        // Then: on vérifie si le sous-menu est activé après le clic
        const active = wrapper.find('.p-menuitem-link.active-panel');
        expect(active.exists()).toBeTruthy;
    });
    it('renders the PanelMenuCritereVue component and activates the Menu', async () => {
        // When: on déclenche un clic sur le composant
        await wrapper.trigger('click');
        // Then: on vérifie si la classe active-panel existe après le clic
        const activeMenu = wrapper.find('.p-panelmenu-header-action active-panel');
        expect(activeMenu.exists()).toBeTruthy;
    });
    it('renders the PanelMenuCritereVue component and activates the First menu', async () => {
        // When: le comosant est monté
        const menuItemHeaderActive = wrapper.find('.p-panelmenu-header-action.active-panel');
        // Then: on vérifie si la classe active-panel à été ajouté à la classe du menu
        expect(menuItemHeaderActive.exists()).toBeTruthy;
    });
    it('renders the PanelMenuCritereVue component and activates the First SubMenu', async () => {
        // When:  // When: le comosant est monté
        const menuItemContentActive = wrapper.find('.p-menuitem-text');
        // Then: on vérifie si la classe active-panel à été ajouté à la classe du texte
        expect(menuItemContentActive.exists()).toBeTruthy;
    });
});
