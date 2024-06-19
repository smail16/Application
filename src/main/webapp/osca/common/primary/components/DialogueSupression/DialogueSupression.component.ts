import ProjectServices from '@/common/domain/ProjectServices';
import { useI18n } from 'vue-i18n';

export default {
    name: 'DialogueSupression',
    props: {
        visible: Boolean,
        identifiant: String,
        isProjectDeleted: Function,
    },
    setup(props, { emit }) {
        const { t } = useI18n();
        const projectService = new ProjectServices();

        function UpdateVisible(value) {
            emit('update:visible', value);
        }

        function closeDialog() {
            UpdateVisible(false);
        }

        async function deleteProject(identifiant: string) {
            try {
                // Appele le service avec la methode de suppression du projet
                await projectService.deleteProject(identifiant);

                // récuperer  la fonction isProjectDeleted passée en props pour reload la liste de projets
                props.isProjectDeleted();

                // Fermeture de la dialoguue de suppression
                closeDialog();
            } catch (error) {
                // en cas d'erreur, affiche un message dans la console
                console.error('Error deleting project:', error);
            }
        }

        return {
            t,
            UpdateVisible,
            closeDialog,
            deleteProject,
        };
    },
};
