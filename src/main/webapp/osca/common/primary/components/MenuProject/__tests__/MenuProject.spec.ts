import { shallowMount } from '@vue/test-utils';
import { createPinia } from 'pinia';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';
import MockAdapter from 'axios-mock-adapter';
import MenuProjectVue from '../MenuProjectVue.vue';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

const pinia = createPinia();

describe('MenuProjectVue.vue', () => {
    // Given un composant MenuProject avec pinia i18n mockés
    it('renders the MenuProjectVue correctly', () => {
        // Crée une instance de l'adaptateur mock avant chaque test
        const mock = new MockAdapter(axiosInstanceBackEnd);

        // Mock l'appel Axios
        mock.onGet('/referentiel/4.2').reply(200, { data: 'Referentiel chargé' });

        // Monte le composant avec pinia
        const wrapper = shallowMount(MenuProjectVue, {
            global: {
                plugins: [pinia],
            },
        });

        // Then le composant est rendu correctement
        expect(wrapper.exists()).toBe(true);
        mock.restore();
    });
});
