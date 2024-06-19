import MaquetteService from '@/common/domain/MaquetteService';
import { useToast } from 'primevue/usetoast';
import { useI18n } from 'vue-i18n';

export default {
    name: 'DialogueSupression',
    props: {
        visible: Boolean,
        maquetteToDelete: String,
        deleteFromSousRubriqueList: Function,
    },
    setup(props, { emit }) {
        const { t } = useI18n();
        const toast = useToast();
        const maquetteService = new MaquetteService();

        /**
         * Fonction qui gère l'update de la visibilité du dialogue
         *
         * @function UpdateVisible
         * @description Met à jour la visibilité du dialogue de suppression
         * @param {Boolean} value - La nouvelle valeur de visibilité
         */
        function UpdateVisible(value) {
            emit('update:visible', value);
        }
        /**
         * Fonction qui gère la fermeture du dialogue
         *
         * @function closeDialog
         * @description Ferme le dialogue de suppression en mettant à jour sa visibilité à false
         */
        function closeDialog() {
            UpdateVisible(false);
        }
        /**
         * Supprime une maquette.
         *
         * @param {string} maquetteToDelete - L'identifiant de la maquette à supprimer
         */
        async function deleteMaquette(maquetteToDelete: string) {
            try {
                // Appele le service avec la methode de suppression du projet
                await maquetteService.deleteMaquette(maquetteToDelete);

                // Appelle la fonction de suppression dans le composant parent
                props.deleteFromSousRubriqueList(maquetteToDelete);

                // Fermeture de la dialoguue de suppression
                closeDialog();
                toast.add({ severity: 'success', summary: 'Success', detail: t('AppMaquette.toastDelete'), life: 3000 });
            } catch (error) {
                // en cas d'erreur, affiche un message dans la console
                console.error('Error deleting maquette:', error);
            }
        }
        return {
            t,
            UpdateVisible,
            closeDialog,
            deleteMaquette,
        };
    },
};
