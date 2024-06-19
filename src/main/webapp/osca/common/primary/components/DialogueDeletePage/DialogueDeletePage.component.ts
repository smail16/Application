import { useI18n } from 'vue-i18n';

export default {
    name: 'DialogueSupression',
    props: {
        visible: Boolean,
        active: String,
    },
    setup(props, { emit }) {
        const { t } = useI18n();

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
         * Fonction qui gère l'emit de l'event. Renvoie un booléen qui confirme oui ou non la suppression de la page dans le composant parent
         *
         * @function deleteQuery
         * @description Déclenche l'événement 'delete-confirmed' et ferme ensuite le dialogue
         */
        function deleteQuery() {
            emit('delete-confirmed', true);
            closeDialog();
        }
        return {
            t,
            UpdateVisible,
            closeDialog,
            deleteQuery,
        };
    },
};
