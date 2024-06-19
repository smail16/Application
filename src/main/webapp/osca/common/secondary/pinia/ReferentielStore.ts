import { defineStore } from 'pinia';
import { Referentiel } from '@/common/domain/Referentiel/Referentiel';

interface State {
    referentiel: Referentiel | null;
    selectedSousRubriques: string;
    selectedRoadMap: string;
    selectedSousRubriquesKey: string;
    selectedRubriquesKey: string;
    selectedRubrique: string;
}

export const useReferentielStore = defineStore('referentiel', {
    state: (): State => ({
        referentiel: null,
        selectedSousRubriques: '',
        selectedSousRubriquesKey: '',
        selectedRoadMap: '',
        selectedRubriquesKey: '',
        selectedRubrique: '',
    }),

    actions: {
        setReferentiel(referentiel: Referentiel) {
            this.referentiel = referentiel;
        },
        setSelectedSousRubrique(selectedSousRubriques: string) {
            this.selectedSousRubriques = selectedSousRubriques;
        },
        setSelectedSousRubriqueKey(selectedSousRubriquesKey: string) {
            this.selectedSousRubriquesKey = selectedSousRubriquesKey;
        },
        setSelectedRubriquesKey(selectedRubriquesKey: string) {
            this.selectedRubriquesKey = selectedRubriquesKey;
        },
        setSelectedRoadMap(selectedRoadMap: string) {
            this.selectedRoadMap = selectedRoadMap;
        },
        setSelectedRubrique(selectedRubrique: string) {
            this.selectedRubrique = selectedRubrique;
        },
    },
});
