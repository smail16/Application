import { computed, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import AppMenuVue from '@/common/primary/components/AppMenu/AppMenuVue.vue';
import DialogueSupressionVue from '@/common/primary/components/DialogueSupression/DialogueSupressionVue.vue';
import ProjectServices from '@/common/domain/ProjectServices';
import DateUtils from '@/common/secondary/DateUtils';
import { useRouter } from 'vue-router';
import { useProjectStore } from '@/common/secondary/pinia/store';
import ExportService from '@/common/domain/ExportService';

export default {
    name: 'ProjectList',
    components: { AppMenuVue, DialogueSupressionVue },
    setup() {
        const { t } = useI18n();
        const visible = ref(false);
        const tableData = ref({});
        const projectService = new ProjectServices();
        const router = useRouter();
        const store = useProjectStore();
        const avancementGlobal = computed(() => Math.floor(store.progress));
        const projectIdToDelete = ref('');
        /**
         * Charge la liste des projets depuis le ProjectService et met à jour tableData
         * En cas d'erreur, un message d'erreur est affiché dans la console
         */
        async function loadProjects() {
            try {
                // Récupère la liste des projets depuis le service
                const response = await projectService.findAllProjects();

                // Map les données des projets pour ne conserver que celles dont on a besoin
                tableData.value = response.data.map(project => ({
                    NomProjet: project.titre,
                    CreationDate: DateUtils.toFrDateFormat(project.dateCreation),
                    DerniereModification: DateUtils.toFrDateFormat(project.dateDerniereModification),
                    identifiant: project.identifiant,
                    Avancement: `${Math.floor(project.avancementGlobal)} %`,
                }));
            } catch (error) {
                // En cas d'erreur, affiche un message dans la console
                console.error(t('ListProjectErrors.fetchError'), error);
            }
        }

        /**
         * Redirige vers la page d'un projet à partir de son identifiant
         * Charge les détails du projet depuis le service, met à jour le store et effectue la redirection
         *
         * @param {string} identifiant - L'identifiant du projet qui va être utilisé pour la redirection
         */
        function redirectToProjectPage(identifiant: string) {
            router.push({ name: 'Project', params: { identifiant } });
        }
        const handelChange = () => {
            loadProjects();
        };

        async function exportProject(identifiant: string) {
            try {
                await ExportService.exportProject(identifiant);
            } catch (error) {
                console.error('Error exporting project:', error);
            }
        }

        onMounted(loadProjects);
        return { t, visible, tableData, redirectToProjectPage, handelChange, projectIdToDelete, exportProject };
    },
};
