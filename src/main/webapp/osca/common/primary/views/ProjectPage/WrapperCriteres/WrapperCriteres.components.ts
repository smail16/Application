import { useProjectStore } from '@/common/secondary/pinia/store';
import CriteresVue from '../Criteres/CriteresVue.vue';
import MaquetteProjectVue from '../MaquetteProject/MaquetteProjectVue.vue';
import { usePageStore } from '@/common/secondary/pinia/PageStore';
import { ref, watchEffect, computed, watch } from 'vue';
import { useReferentielStore } from '@/common/secondary/pinia/ReferentielStore';
import { EnumEtat } from '@/common/domain/Page/Rubriques/SousRubriquesPage/EnumEtat';
import PageService from '@/common/domain/PageService';
import {
    avancementDevelop,
    avancementFunc,
    avancementUX,
    calculAvancementGlobal,
    updateProgress,
} from '@/common/secondary/AvancementUtils';
import { getAllCriteresDev, getAllCriteresFonc, getAllCriteresUX } from '@/common/secondary/CriteriaUtils';
import ProjectServices from '@/common/domain/ProjectServices';
import MaquetteService from '@/common/domain/MaquetteService';
import { Maquette } from '@/common/domain/Page/Rubriques/SousRubriquesPage/Maquette';
import { Page } from '@/common/domain/Page';

export default {
    setup() {
        const pageStore = usePageStore();
        const store = useProjectStore();
        const refStore = useReferentielStore();
        const projectService = new ProjectServices();
        const pageService = new PageService();
        const referentiel = refStore.referentiel;
        const activeProjectId = ref<string>('');
        const maquetteService = new MaquetteService();
        const updatedProgressUX = computed(() => Math.floor(store.progressUX));
        const updatedProgressDevelop = computed(() => Math.floor(store.progressDevelop));
        const updatedProgressFunc = computed(() => Math.floor(store.progressFunc));
        const activePageDetails = ref<Page>();
        const titrePage = ref<string>('');
        /**
         * Déclaration des variables d'avancement.
         */
        const progress = ref(0);

        /**
         * Déclaration de toutes les listes utilisées dans les watchEffect en dessous.
         */

        const criteresUXList = ref<{ name: string; key: string; state: any }[]>([]);
        const pratiquesUXList = ref<{ pratiqueUX: string; key: string }[]>([]);
        const sousRubriqueUxTitles = ref<string[]>([]);
        const criteresDevList = ref<{ name: string; key: string; state: any }[]>([]);
        const pratiquesDevList = ref<{ pratiqueDev: string; key: string }[]>([]);
        const sousRubriqueDevTitles = ref<string[]>([]);
        const criteresFonctionelsList = ref<{ name: string; key: string; state: any }[]>([]);
        const pratiquesFonctionellesList = ref<{ pratiqueFonc: string; key: string }[]>([]);
        const sousRubriqueFonctionelleTitles = ref<string[]>([]);

        const sousRubriqueMaquetteList = ref<Maquette[]>([]);

        /** Met à jour les listes des critères et l'avancement à l'ajout et à la suppression de page
         */
        watch(
            () => store.activeProjectDetails?.pages,
            () => {
                if (store.activeProjectDetails?.pages) {
                    // Mettre à jour les critères lorsque la liste d'onglets change
                    const criteresDev = getAllCriteresDev(store.activeProjectDetails);
                    const criteresFonctionels = getAllCriteresFonc(store.activeProjectDetails);
                    const criteresUX = getAllCriteresUX(store.activeProjectDetails);

                    // Mettre à jour les références globales avec les nouvelles valeurs
                    store.setCriteresDevListTotal(criteresDev);
                    store.setCriteresFonctionelsListTotal(criteresFonctionels);
                    store.setCriteresUXListTotal(criteresUX);

                    calculAvancementGlobal();
                    projectService.updateAvancement(store.activeProjectDetails?.identifiant, Math.floor(store.progress));
                }
            },
        );

        function getActiveProjectDetails() {
            // Récupération de l'objet activeProject depuis projectStore
            const activeProject = store.activeProjectDetails;
            // Récupération de l'onglet actif depuis projectStore
            const ongletActif = store.ongletActifLabel;

            // Vérification si activeProject défini et ongletActif sont définis
            if (activeProject && ongletActif) {
                // Recherche de la page correspondant à l'onglet actif dans les pages de l'objet activeProject
                const pageDetails = activeProject.pages.find(page => page.titrePage === ongletActif);

                // Vérification si la page a été trouvée
                if (pageDetails) {
                    // Mise à jour des détails de la page active dans le store
                    pageStore.setActivePageDetail(pageDetails);
                }
            }
            if (activeProject && activeProject.identifiant) {
                activeProjectId.value = activeProject.identifiant;
            }
        }
        watchEffect(() => {
            getActiveProjectDetails();
            updateProgress();
        });

        /**
         * récupère l'état actif d'un critère spécifique en fonction de sa clé et de son type.
         * @param key La clé du critère à rechercher.
         * @param type Le type de critère ('UX', 'Dev', 'Fonctionnel').
         * @returns Retourne true si le critère est traité, false s'il est non applicable, et null s'il est non traité
         */
        function getActiveCritereState(key: string, type: 'UX' | 'Dev' | 'Fonctionnel'): boolean | null {
            // Obtenir les détails de la page active
            const pageDetails = pageStore.activePageDetails;
            // Rechercher la rubrique active
            const rubrique = pageDetails?.rubriquesPages.find(rubrique => rubrique.keyRubriquesPage === refStore.selectedRubriquesKey);
            // Rechercher la sous-rubrique active
            const sousRubrique = rubrique?.sousRubriquesPage.find(
                sousRubrique => sousRubrique.keySousRubriquesPage === refStore.selectedSousRubriquesKey,
            );

            let feuilleDeRoute, critere;
            // Sélectionner la feuille de route et le critère en fonction du type
            switch (type) {
                case 'UX':
                    feuilleDeRoute = sousRubrique?.feuilleDeRouteUXPage.find(feuille =>
                        feuille.criteresUXPage.some(critere => critere.keyCritereUX === key),
                    );
                    critere = feuilleDeRoute?.criteresUXPage.find(critere => critere.keyCritereUX === key);
                    break;
                case 'Dev':
                    feuilleDeRoute = sousRubrique?.feuilleDeRouteDevPage.find(feuille =>
                        feuille.criteresDevPage.some(critere => critere.keyCritereDev === key),
                    );
                    critere = feuilleDeRoute?.criteresDevPage.find(critere => critere.keyCritereDev === key);
                    break;
                case 'Fonctionnel':
                    feuilleDeRoute = sousRubrique?.feuilleDeRouteFoncPage.find(feuille =>
                        feuille.criteresFoncPage.some(critere => critere.keyCritereFonc === key),
                    );
                    critere = feuilleDeRoute?.criteresFoncPage.find(critere => critere.keyCritereFonc === key);
                    break;
                default:
                    return null;
            }

            // Vérifier l'état du critère et le renvoyer
            if (critere) {
                switch (critere.etat) {
                    case EnumEtat.TRAITE:
                        return true;
                    case EnumEtat.NONTRAITE:
                        return null;
                    case EnumEtat.NONAPPLICABLE:
                        return false;
                }
            }
            return null;
        }
        //Utilisation de watcheffect pour observer les changement au clicksur la sous-rubrique
        watchEffect(async () => {
            //recupère la liste des critères depuis le referentiel et l'affiche selon l'état
            if (referentiel) {
                const activeRubrique = referentiel.rubriques.find(rubrique => rubrique.keyRubriques === refStore.selectedRubriquesKey);
                const activeSousRubrique = activeRubrique?.sousRubriques.find(
                    sousRubrique => sousRubrique.keySousRubriques === refStore.selectedSousRubriquesKey,
                );

                if (activeSousRubrique) {
                    try {
                        // Récupérer de la page active
                        const activePage = pageStore.activePageDetails;
                        if (activePage) {
                            titrePage.value = activePage?.titrePage;
                        }

                        const selectedSousRubriqueKey = refStore.selectedSousRubriquesKey;

                        // Récupération des maquettes en fonction de l'id projet keySousRubrique et titrePage
                        const maquette = await maquetteService.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(
                            activeProjectId.value,
                            selectedSousRubriqueKey,
                            titrePage.value,
                        );

                        const maquetteData: Maquette[] = maquette.data;
                        // Mettre a jour la liste de maquettes
                        sousRubriqueMaquetteList.value = maquetteData;
                    } catch (error) {
                        // erreur de la requette
                        console.error('Error lors de la récupérations des données des maquettes', error);
                    }

                    // Chargement des critères UX
                    criteresUXList.value = activeSousRubrique.sousRubriqueUx.flatMap(ux =>
                        ux.criteresUXList.map(critere => ({
                            name: critere.critereUX,
                            key: critere.keyCritereUX,
                            state: getActiveCritereState(critere.keyCritereUX, 'UX'),
                        })),
                    );
                    store.setCriteresUXList(criteresUXList.value),
                        avancementUX(),
                        // Liste des pratiques UX
                        (pratiquesUXList.value = activeSousRubrique.sousRubriqueUx.flatMap(ux =>
                            ux.pratiquesUXList.map(pratique => ({
                                pratiqueUX: pratique.pratiqueUX,
                                key: pratique.keyPratiqueUX,
                            })),
                        ));

                    // Titres des feuilles de routes UX
                    sousRubriqueUxTitles.value = activeSousRubrique.sousRubriqueUx.map(ux => ux.titre);

                    // Chargement des critères de développement
                    criteresDevList.value = activeSousRubrique.sousRubriqueDeveloppement.flatMap(dev =>
                        dev.criteresDevList.map(critere => ({
                            name: critere.critereDev,
                            key: critere.keyCritereDev,
                            state: getActiveCritereState(critere.keyCritereDev, 'Dev'),
                        })),
                    );
                    store.setCriteresDevList(criteresDevList.value), avancementDevelop();

                    // Liste des bonnes pratiques de développement
                    pratiquesDevList.value = activeSousRubrique.sousRubriqueDeveloppement.flatMap(dev =>
                        dev.pratiquesDevList.map(pratique => ({
                            pratiqueDev: pratique.pratiqueDev,
                            key: pratique.keyPratiqueDev,
                        })),
                    );

                    // Titres des feuilles de routes de développement
                    sousRubriqueDevTitles.value = activeSousRubrique.sousRubriqueDeveloppement.map(dev => dev.titre);

                    // Chargement des critères fonctionnels
                    criteresFonctionelsList.value = activeSousRubrique.sousRubriqueFonctionnelle.flatMap(fonctionnelle =>
                        fonctionnelle.criteresFoncList.map(critere => ({
                            name: critere.critereFonc,
                            key: critere.keyCritereFonc,
                            state: getActiveCritereState(critere.keyCritereFonc, 'Fonctionnel'),
                        })),
                    );
                    store.setCriteresFoncList(criteresFonctionelsList.value), avancementFunc();
                    // Liste des bonnes pratiques fonctionnelles
                    pratiquesFonctionellesList.value = activeSousRubrique.sousRubriqueFonctionnelle.flatMap(fonctionnelle =>
                        fonctionnelle.pratiquesFoncList.map(pratique => ({
                            pratiqueFonc: pratique.pratiqueFonc,
                            key: pratique.keyPratiqueFonc,
                        })),
                    );

                    // Titres des feuilles de routes fonctionnelles
                    sousRubriqueFonctionelleTitles.value = activeSousRubrique.sousRubriqueFonctionnelle.map(
                        fonctionnelle => fonctionnelle.titre,
                    );
                }
            }
        });

        /**
         * "Map" l'état de l'énum en fonction de null false ou true
         * @param critere
         * @param state
         */
        const updateCritereState = (critere, state) => {
            if (critere) {
                if (state === true) {
                    critere.etat = EnumEtat.TRAITE;
                } else if (state === false) {
                    critere.etat = EnumEtat.NONAPPLICABLE;
                } else {
                    critere.etat = EnumEtat.NONTRAITE;
                }
            }
        };

        /**
         * Met à jour l'état des checkbox, calcul l'avancement et update la page avec le nouvel avancement
         * @param critereToUpdate
         */
        const updateState = async (critereToUpdate: { key: string; state: boolean }) => {
            const { key, state } = critereToUpdate;

            const activePage = pageStore.activePageDetails;
            if (activePage) {
                activePage.rubriquesPages.forEach(rubrique => {
                    rubrique.sousRubriquesPage.forEach(sousRubrique => {
                        const critereDev = sousRubrique.feuilleDeRouteDevPage
                            ? sousRubrique.feuilleDeRouteDevPage
                                  .flatMap(dev => dev.criteresDevPage)
                                  .find(critereDev => critereDev.keyCritereDev === key)
                            : null;

                        const critereFonc = sousRubrique.feuilleDeRouteFoncPage
                            ? sousRubrique.feuilleDeRouteFoncPage
                                  .flatMap(fonc => fonc.criteresFoncPage)
                                  .find(critereFonc => critereFonc.keyCritereFonc === key)
                            : null;

                        const critereUX = sousRubrique.feuilleDeRouteUXPage
                            ? sousRubrique.feuilleDeRouteUXPage
                                  .flatMap(ux => ux.criteresUXPage)
                                  .find(critereUX => critereUX.keyCritereUX === key)
                            : null;

                        updateCritereState(critereDev, state);
                        updateCritereState(critereFonc, state);
                        updateCritereState(critereUX, state);
                    });
                });
                updateProgress();
                const projectId = activeProjectId.value;
                const keySousRubrique = refStore.selectedSousRubriquesKey;

                const updatedPage = await pageService.updatePage(
                    projectId,
                    activePage.titrePage,
                    activePage,
                    Math.round(store.progress),
                    Math.round(store.progressUX),
                    Math.round(store.progressDevelop),
                    Math.round(store.progressFunc),
                    keySousRubrique,
                );

                if (updatedPage) {
                    pageStore.setActivePageDetail(updatedPage);
                }
            }
        };

        /**
         * Récupère le critères envoyé par l'event du composant enfant pour mettre à jour l'avancement et l'état des checkbox
         * @param critere
         */
        const handleUpdate = critere => {
            updateState(critere);
        };

        return {
            progress,
            updatedProgressDevelop,
            updatedProgressUX,
            updatedProgressFunc,
            criteresUXList,
            pratiquesUXList,
            sousRubriqueUxTitles,
            criteresDevList,
            pratiquesDevList,
            sousRubriqueDevTitles,
            sousRubriqueFonctionelleTitles,
            pratiquesFonctionellesList,
            criteresFonctionelsList,
            updateState,
            handleUpdate,
            sousRubriqueMaquetteList,
            activeProjectId,
            titrePage,
        };
    },

    name: 'WrapperCriteres',
    components: { CriteresVue, MaquetteProjectVue },
};
