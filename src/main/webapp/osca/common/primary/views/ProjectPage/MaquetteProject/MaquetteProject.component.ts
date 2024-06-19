import { Maquette } from '@/common/domain/Page/Rubriques/SousRubriquesPage/Maquette';
import MaquetteService from '@/common/domain/MaquetteService';
import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';
import { useToast } from 'primevue/usetoast';
import { ref, toRefs } from 'vue';
import { useI18n } from 'vue-i18n';
import DialogueDeleteMaquette from '../../../components/DialogueDeleteMaquette/DialogueDeleteMaquetteVue.vue';

export default {
    // Nom du composant
    name: 'MaquetteProject',
    components: { DialogueDeleteMaquette },
    props: ['sousRubriqueMaquetteList', 'activeProjectId', 'titrePage'],

    setup(props) {
        const { sousRubriqueMaquetteList, activeProjectId, titrePage } = toRefs(props);
        const { t } = useI18n();
        const visible = ref(false);
        const toast = useToast();
        const maquetteService = new MaquetteService();
        const refStore = useReferentielStore();
        const projectId = activeProjectId.value;

        /* Fonction pour eviter d'agrandir la maquette au click sur les icones */
        const eventStop = event => {
            event.stopPropagation();
        };

        /* Fonction pour supprimer une maquette de la liste*/
        const deleteFromSousRubriqueList = identifiant => {
            const maquetteIndex = sousRubriqueMaquetteList.value.findIndex(maquette => maquette.identifiant === identifiant);
            if (maquetteIndex !== -1) {
                // Supprimez le fichier de la liste des maquettes
                sousRubriqueMaquetteList.value.splice(maquetteIndex, 1);
            }
        };

        /* Fonction pour télécharger une maquette*/
        async function downloadMaquette(identifiant: string, event) {
            eventStop(event);

            try {
                // chercher la maquette par son identifiant
                const file = sousRubriqueMaquetteList.value.find(el => el.identifiant === identifiant);

                if (file) {
                    // Création d'un lien pour télécharger le fichier
                    const link = document.createElement('a');
                    link.href = `data:${file.fileType};base64,` + file.maquette;

                    link.download = file.filename;
                    // Cliquez sur le lien pour déclencher le téléchargement
                    link.click();
                } else {
                    console.error('Fichier non trouvé dans la liste des maquettes.');
                }
            } catch (error) {
                console.error('Erreur lors du téléchargement du fichier:', error);
            }
        }

        /* Fonction pour upload la maquette*/
        const onAdvancedUpload = async event => {
            const files = event.files;

            try {
                const file = files[0];
                //afficher une notifaction en cas de dépassement de la taille autorisé
                if (file.size > 1000000) {
                    toast.add({
                        severity: 'error',
                        summary: 'Erreur',
                        detail: t('AppMaquette.toastSizeFile'),
                        life: 3000,
                    });
                    return; // Arrête du processus de l'upload si la taille depasser 1 mo
                }

                //utiliser le service pour ajouter une maquette
                const response = await maquetteService.addMaquette(file, projectId, refStore.selectedSousRubriquesKey, titrePage.value);

                const maquetteData: Maquette = response.data as Maquette;

                // Ajouter la maquette à la liste des maquette de la sous-rubrique
                sousRubriqueMaquetteList.value.push(maquetteData);

                // Afficher une notification de succès
                toast.add({ severity: 'success', summary: 'Success', detail: t('AppMaquette.toastUpload'), life: 3000 });
            } catch (error) {
                // Gérer les erreurs
                console.error('Error uploading file:', error);
            }
        };

        // Retour des variables et fonctions
        return {
            visible,
            onAdvancedUpload,
            t,
            sousRubriqueMaquetteList,
            deleteFromSousRubriqueList,
            downloadMaquette,
            eventStop,
        };
    },
};
