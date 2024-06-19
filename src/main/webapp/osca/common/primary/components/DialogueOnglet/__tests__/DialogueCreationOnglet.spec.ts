import { describe, it, expect } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import DialogueOnglet from '@/common/primary/components/DialogueOnglet/DialogueCreationOnglet.component';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('DialogueOnglet.vue', () => {
    it('renders correctly', () => {
        // Given: Un composant DialogueOnglet
        // When: Le composant est monté
        const wrapper = shallowMount(DialogueOnglet, { propsData: { visible: true } });

        // Then: Le composant doit exister
        expect(wrapper.exists()).toBe(true);
    });

    it('shows the dialog when "visible" prop is true', () => {
        // Given: Un composant DialogueOnglet avec la prop "visible" à true
        // When: Le composant est monté
        const wrapper = shallowMount(DialogueOnglet, { propsData: { visible: true } });

        // Then: La propriété "visible" du composant doit être à true
        expect(wrapper.vm.visible).toBe(true);
    });

    it('hides the dialog when "visible" prop is false', () => {
        // Given: Un composant DialogueOnglet avec la prop "visible" à false
        // When: Le composant est monté
        const wrapper = shallowMount(DialogueOnglet, { propsData: { visible: false } });

        // Then: La propriété "visible" du composant doit être à false
        expect(wrapper.vm.visible).toBe(false);
    });
});

describe('Tests of UpdateVisible and closeDialog', () => {
    let wrapper;

    // Given: Avant chaque test, nous initialisons le composant DialogueOnglet.
    beforeEach(() => {
        wrapper = shallowMount(DialogueOnglet);
    });

    it('closes the dialog when closeDialog is called', () => {
        // When: La méthode closeDialog est invoquée.
        wrapper.vm.closeDialog();

        // Then: Nous verifions que l'événement 'update:visible' a été émis avec la valeur false.
        expect(wrapper.emitted('update:visible')).toBeTruthy();
        expect(wrapper.emitted('update:visible')[0]).toEqual([false]);
    });
});
