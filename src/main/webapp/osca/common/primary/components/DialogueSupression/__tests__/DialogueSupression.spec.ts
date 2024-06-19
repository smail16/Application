import { describe, expect, it } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import DeleteProjectDialogVue from '@/common/primary/components/DialogueSupression/DialogueSupressionVue.vue';
import ProjectServices from '@/common/domain/ProjectServices';
import axiosInstanceBackEnd from '@/http/AxiosInstanceBackEnd';
import MockAdapter from 'axios-mock-adapter';

// Mock i18n
vi.mock('vue-i18n', () => ({
    useI18n: () => ({
        t: key => key,
    }),
}));

describe('DeleteProjectDialogVue.vue', () => {
    it('renders correctly', () => {
        // Given: Un composant DeleteProjectDialogVue
        // When: Le composant est monté
        const wrapper = shallowMount(DeleteProjectDialogVue, { propsData: { visible: true } });

        // Then: Le composant doit exister
        expect(wrapper.exists()).toBe(true);
    });

    it('shows the dialog when "visible" prop is true', () => {
        // Given: Un composant DeleteProjectDialogVue avec la prop "visible" à true
        // When: Le composant est monté
        const wrapper = shallowMount(DeleteProjectDialogVue, { propsData: { visible: true } });

        // Then: La propriété "visible" du composant doit être à true
        expect(wrapper.vm.visible).toBe(true);
    });

    it('hides the dialog when "visible" prop is false', () => {
        // Given: Un composant DeleteProjectDialogVue avec la prop "visible" à false
        // When: Le composant est monté
        const wrapper = shallowMount(DeleteProjectDialogVue, { propsData: { visible: false } });

        // Then: La propriété "visible" du composant doit être à false
        expect(wrapper.vm.visible).toBe(false);
    });
});

describe('Tests of UpdateVisible and closeDialog', () => {
    let wrapper;

    // Given: Avant chaque test, nous initialisons le composant DeleteProjectDialogVue.
    beforeEach(() => {
        wrapper = shallowMount(DeleteProjectDialogVue);
    });

    it('emits update:visible when UpdateVisible is called', () => {
        // When: La méthode UpdateVisible est invoquée avec l'argument true.
        wrapper.vm.UpdateVisible(true);

        // Then: Nous verifions que l'événement 'update:visible' a été émis avec la valeur true.
        expect(wrapper.emitted('update:visible')).toBeTruthy();
        expect(wrapper.emitted('update:visible')[0]).toEqual([true]);
    });

    it('closes the dialog when closeDialog is called', () => {
        // When: La méthode closeDialog est invoquée.
        wrapper.vm.closeDialog();

        // Then: Nous verifions que l'événement 'update:visible' a été émis avec la valeur false.
        expect(wrapper.emitted('update:visible')).toBeTruthy();
        expect(wrapper.emitted('update:visible')[0]).toEqual([false]);
    });
});
describe('DeleteProjectDialogVue Component', () => {
    let wrapper;
    let mock: MockAdapter;

    const projectService = new ProjectServices();

    beforeEach(() => {
        wrapper = shallowMount(DeleteProjectDialogVue);
        mock = new MockAdapter(axiosInstanceBackEnd);
    });

    afterEach(() => {
        mock.restore();
    });

    it('should delete a project', async () => {
        const projectId = 'id';

        // Mock Axios call for project deletion
        mock.onDelete(`/projects/${projectId}`).reply(204, { data: 'Project deleted' });

        await projectService.deleteProject(projectId);

        // Verify Axios call
        expect(mock.history.delete.length).toBe(1);
        expect(mock.history.delete[0].url).toBe(`/projects/${projectId}`);
    });

    it('should handle deletion error', async () => {
        const projectId = 'id';
        const errorMessage = 'Deletion failed';

        // Mock Axios call for project deletion with an error
        mock.onDelete(`/projects/${projectId}`).reply(500, { error: errorMessage });

        // Use expect().rejects to handle rejections
        await expect(projectService.deleteProject(projectId)).rejects.toThrow('Request failed with status code 500');
    });
});
