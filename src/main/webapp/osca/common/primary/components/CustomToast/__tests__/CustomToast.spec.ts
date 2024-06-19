import CustomToast from '@/common/primary/components/CustomToast/CustomToast.component';
import { describe, it, expect, vi } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import PrimeVue from 'primevue/config';

// Création d'un mock pour la méthode add
const addMock = vi.fn();

// Mock useToast
vi.mock('primevue/usetoast', () => ({
    useToast: vi.fn(() => ({
        add: addMock,
    })),
}));

describe('CustomToast.vue', () => {
    // vérifier si le composant se monte correctement
    it('mounts correctly', () => {
        const wrapper = shallowMount(CustomToast, {
            global: {
                plugins: [PrimeVue],
            },
        });
        expect(wrapper.exists()).toBe(true);
    });

    it('shows an error with useToast', async () => {
        const wrapper = shallowMount(CustomToast, {
            global: {
                plugins: [PrimeVue],
            },
        });

        // Appeler la méthode showError
        await wrapper.vm.showError('Test Error Message');

        // Vérifier si add de useToast a été appelé correctement
        expect(addMock).toHaveBeenCalledWith({
            severity: 'error',
            summary: 'Error',
            detail: 'Test Error Message',
            life: 3000,
        });
    });
});
