import { Project } from '@/common/domain/Project';
import { CriteresDevPage } from '../domain/Page/Rubriques/SousRubriquesPage/Dev/CriteresDevPage';
import { CriteresFoncPage } from '../domain/Page/Rubriques/SousRubriquesPage/Fonc/CriteresFoncPage';
import { CriteresUXPage } from '../domain/Page/Rubriques/SousRubriquesPage/UX/CriteresUXPage';
import { useReferentielStore } from './pinia/ReferentielStore';

/**
 * Récupère tous les critères de développement d'un projet
 *
 * @param {Project} projet - Le projet en cours
 * @returns {CriteresDevPage[]} - Liste de tout les critères de développement du projet.
 */
export function getAllCriteresDev(projet: Project): CriteresDevPage[] {
    const criteresDev: CriteresDevPage[] = [];
    projet.pages.forEach(page => {
        page.rubriquesPages.forEach(rubrique => {
            rubrique.sousRubriquesPage.forEach(sousRubrique => {
                sousRubrique.feuilleDeRouteDevPage.forEach(feuilleDeRoute => {
                    criteresDev.push(...feuilleDeRoute.criteresDevPage);
                });
            });
        });
    });
    return criteresDev;
}

/**
 * Récupère tous les critères fonctionnels d'un projet
 *
 * @param {Project} projet - Le projet en cours
 * @returns {CriteresFoncPage[]} - Liste de tout les critères fonctionnels du projet
 */
export function getAllCriteresFonc(projet: Project): CriteresFoncPage[] {
    const criteresFonc: CriteresFoncPage[] = [];
    projet.pages.forEach(page => {
        page.rubriquesPages.forEach(rubrique => {
            rubrique.sousRubriquesPage.forEach(sousRubrique => {
                sousRubrique.feuilleDeRouteFoncPage.forEach(feuilleDeRoute => {
                    criteresFonc.push(...feuilleDeRoute.criteresFoncPage);
                });
            });
        });
    });
    return criteresFonc;
}

/**
 * Récupère tous les critères d'expérience utilisateur (UX) d'un projet
 *
 * @param {Project} projet - Le projet en cours
 * @returns {CriteresUXPage[]} - Liste de tout les critères UX du projet
 */
export function getAllCriteresUX(projet: Project): CriteresUXPage[] {
    const criteresUX: CriteresUXPage[] = [];
    projet.pages.forEach(page => {
        page.rubriquesPages.forEach(rubrique => {
            rubrique.sousRubriquesPage.forEach(sousRubrique => {
                sousRubrique.feuilleDeRouteUXPage.forEach(feuilleDeRoute => {
                    criteresUX.push(...feuilleDeRoute.criteresUXPage);
                });
            });
        });
    });
    return criteresUX;
}
