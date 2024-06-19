import { ref } from 'vue';
import { EnumEtat } from '../domain/Page/Rubriques/SousRubriquesPage/EnumEtat';
import { getAllCriteresDev, getAllCriteresFonc, getAllCriteresUX } from './CriteriaUtils';
import { useProjectStore } from './pinia/store';

/**
 * Calcule l'avancement global
 * Met à jour le pourcentage d'avancement global dans le store
 */
export function calculAvancementGlobal() {
    const store = useProjectStore();
    const progress = ref(0);
    const activeProject = store.activeProjectDetails;

    if (!activeProject) return;

    const criteresDev = getAllCriteresDev(activeProject);
    const criteresFonctionels = getAllCriteresFonc(activeProject);
    const criteresUX = getAllCriteresUX(activeProject);

    store.setCriteresDevListTotal(criteresDev);
    store.setCriteresFonctionelsListTotal(criteresFonctionels);
    store.setCriteresUXListTotal(criteresUX);

    const totalCategories = [...store.criteresDevListTotal, ...store.criteresUXListTotal, ...store.criteresFonctionelsListTotal].length;

    const totalCheckedDev = store.criteresDevListTotal.filter(
        cat => cat.etat === EnumEtat.NONAPPLICABLE || cat.etat === EnumEtat.TRAITE,
    ).length;
    const totalCheckedUX = store.criteresUXListTotal.filter(
        cat => cat.etat === EnumEtat.NONAPPLICABLE || cat.etat === EnumEtat.TRAITE,
    ).length;
    const totalCheckedFonctionels = store.criteresFonctionelsListTotal.filter(
        cat => cat.etat === EnumEtat.NONAPPLICABLE || cat.etat === EnumEtat.TRAITE,
    ).length;

    const totalChecked = totalCheckedDev + totalCheckedUX + totalCheckedFonctionels;

    progress.value = (totalChecked / totalCategories) * 100;
    store.setProgress(progress.value);
}

/**
 * Calcule l'avancement pour la catégorie UX en fonction des changements de la checkbox.
 * Met à jour le pourcentage d'avancement UX dans le store.
 */
export function avancementUX() {
    const store = useProjectStore();
    const progressUX = ref(0);
    const uxTotal = store.criteresUXList.length;
    const uxChecked = store.criteresUXList.filter(cat => cat.state === true || cat.state === false).length;
    progressUX.value = (uxChecked / uxTotal) * 100;
    store.setProgressUX(progressUX.value);

    if (uxTotal === 0) {
        store.setProgressUX(0);
    }
}

/**
 * Calcule l'avancement pour la catégorie développement en fonction des changements de la checkbox.
 * Met à jour le pourcentage d'avancement développement dans le store.
 */
export function avancementDevelop() {
    const store = useProjectStore();
    const progressDevelop = ref(0);
    const developChecked = store.criteresDevList.filter(cat => cat.state === true || cat.state === false).length;
    const developTotal = store.criteresDevList.length;
    progressDevelop.value = (developChecked / developTotal) * 100;
    store.setProgressDevelop(progressDevelop.value);

    if (developTotal === 0) {
        store.setProgressDevelop(0);
    }
}

/**
 * Calcule l'avancement pour la catégorie fonctionnelle en fonction des changements de la checkbox.
 * Met à jour le pourcentage d'avancement fonctionnel dans le store.
 */
export function avancementFunc() {
    const store = useProjectStore();
    const progressFunc = ref(0);
    const funcChecked = store.criteresFoncList.filter(cat => cat.state === true || cat.state === false).length;
    const funcTotal = store.criteresFoncList.length;
    progressFunc.value = (funcChecked / funcTotal) * 100;
    store.setProgressFunc(progressFunc.value);

    if (funcTotal === 0) {
        store.setProgressFunc(0);
    }
}

/**
 * Met à jour l'avancement pour toutes les catégories en appelant les méthodes spécifiques.
 * Met à jour le pourcentage d'avancement pour les catégories développement, UX et fonctionnel dans le store.
 */
export function updateProgress() {
    calculAvancementGlobal();
    avancementUX();
    avancementDevelop();
    avancementFunc();
}
