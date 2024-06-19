import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';
import { defineComponent, ref, watchEffect } from 'vue';
import { useI18n } from 'vue-i18n';
export default defineComponent({
    // Nom du composant
    name: 'BreadCrumb',
    setup() {
        // Utilisation du hook useI18n
        const { t } = useI18n();
        // Utilisation du store de référentiel
        const refStore = useReferentielStore();
        // Référence réactive pour les éléments du fil d'ariane
        const breadCrumbItems = ref([{ label: '' }]);

        /** Observer les changements des éléments de la fil d'ariane*/
        watchEffect(() => {
            // Observer le changement de la rubrique sélectionnée
            const clickedRubrique = refStore.selectedRubrique;

            // Observer le changement de la Sous-rubrique sélectionnée
            const clickedSousRubrique = refStore.selectedSousRubriques;

            // mettre à jour la fil d'ariane avec la rubrique et la sous-rubrique sélectioné
            breadCrumbItems.value = [{ label: clickedRubrique }, { label: clickedSousRubrique }];
        });

        // retourne les propriétés et méthodes du composant
        return {
            t,
            breadCrumbItems,
        };
    },
});
