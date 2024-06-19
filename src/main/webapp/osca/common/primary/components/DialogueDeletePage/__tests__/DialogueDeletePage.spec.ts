import { shallowMount } from '@vue/test-utils';
import DialogueDeletePageVue from '../DialogueDeletePageVue.vue';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('DialogueDeletePageVue.vue', () => {
    // Décrivez les tests pour DialogueDeletePageVue
    it('renders the component without errors', () => {
        // Créez un exemple de la prop active
        const active = 'ActiveTabLabel';

        // Montez le composant DialogueDeletePageVue avec les props nécessaires
        const wrapper = shallowMount(DialogueDeletePageVue, {
            props: {
                visible: true,
                active: active,
            },
        });

        // Assurez-vous que le composant est rendu sans erreur
        expect(wrapper.exists()).toBe(true);
    });
});
