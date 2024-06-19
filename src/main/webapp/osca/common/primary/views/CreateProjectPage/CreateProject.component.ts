import { useToast } from 'primevue/usetoast';
import { useField, useForm } from 'vee-validate';
import { useI18n } from 'vue-i18n';
import AppMenuVue from '@/common/primary/components/AppMenu/AppMenuVue.vue';
import ProjectServices from '@/common/domain/ProjectServices';
import { useRouter } from 'vue-router';
import { AxiosResponse } from 'axios';
import { Project } from '@/common/domain/Project';
import { useProjectStore } from '@/common/secondary/pinia/store';

export default {
    setup() {
        const projectService = new ProjectServices();
        const { t } = useI18n();
        const { handleSubmit, resetForm } = useForm();
        const { value: NomProjet, errorMessage: errorName } = useField('NomProjet', validateName);
        const toast = useToast();
        const router = useRouter();
        const store = useProjectStore();

        function validateName(value: string): string | true {
            if (!value) {
                return t('CreateProject.NameRequired');
            }

            if (value.length > 50) {
                return t('CreateProject.NomTropLong');
            }
            return true;
        }

        const onSubmit = handleSubmit(async values => {
            toast.add({
                severity: 'info',
                summary: 'Form Submitted',
                detail: `Name: ${values.NomProjet}`,
                life: 3000,
            });
            const response = (await projectService.createProject(values.NomProjet)) as AxiosResponse<Project>;

            useProjectStore().createProject(response.data);
            const showMessage = true;
            store.setOnCreatedPopUp(showMessage);
            router.push({ name: 'Project', params: { identifiant: response.data.identifiant } });
        });

        resetForm();

        return { t, NomProjet, errorName, onSubmit, validateName };
    },
    name: 'CreateProjectVue',
    components: {
        AppMenuVue,
    },
};
