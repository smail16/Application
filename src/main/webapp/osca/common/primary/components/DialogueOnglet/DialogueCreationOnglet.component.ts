import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

export default {
    name: 'DialogueCreationOnglet',
    props: {
        visible: Boolean,
        onTitleChange: Function,
    },
    setup(props, { emit }) {
        const { t } = useI18n();
        const nouvelOngletTitle = ref('');
        const errorMessage = ref('');

        function updateVisible(value) {
            emit('update:visible', value);
        }

        function closeDialog() {
            errorMessage.value = ''; // Reset l'erreur lors de la fermeture
            nouvelOngletTitle.value = '';
            updateVisible(false);
        }

        function validerModification() {
            if (!nouvelOngletTitle.value.trim()) {
                errorMessage.value = t('Le titre ne peut pas être vide');
                return;
            }
            props.onTitleChange(nouvelOngletTitle.value, error => {
                errorMessage.value = error;
            });
            if (!errorMessage.value) {
                closeDialog();
            }
        }

        return {
            t,
            closeDialog,
            validerModification,
            nouvelOngletTitle,
            errorMessage,
            updateVisible,
        };
    },
};
