package com.soprasteria.osca.domain.page;

import static com.soprasteria.osca.domain.page.rubriques.sousrubriques.EtatEnum.NONTRAITE;

import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.CriteresDevPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.FeuilleDeRouteDevPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.CriteresFoncPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.FeuilleDeRouteFoncPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.CriteresUXPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.FeuilleDeRouteUXPage;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.project.ProjectPersistencePort;
import com.soprasteria.osca.domain.project.ProjectService;
import com.soprasteria.osca.domain.referentiel.Referentiel;
import com.soprasteria.osca.domain.referentiel.ReferentielService;
import com.soprasteria.osca.domain.referentiel.rubriques.Rubriques;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.SousRubriques;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.CriteresDev;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.FeuilleDeRouteDev;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.CritereFonc;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.FeuilleDeRouteFonc;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.CriteresUX;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.FeuilleDeRouteUX;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final ProjectPersistencePort projectPersistencePort;
    private final ReferentielService referentielService;
    private final ProjectService projectService;

    /**
     * Constructeur pour PageService.
     *
     * @param projectPersistencePort Le port de persistance pour les projets.
     * @param referentielService     Le service pour les référentiels.
     */
    public PageService(
        ProjectPersistencePort projectPersistencePort,
        ReferentielService referentielService,
        ProjectService projectService
    ) {
        this.projectPersistencePort = projectPersistencePort;
        this.referentielService = referentielService;
        this.projectService = projectService;
    }

    /**
     * Initialise une liste de pages de critères de développement en fonction de la liste de critères fournie.
     *
     * @param criteresDevList La liste de critères de développement.
     * @return Une liste de pages de critères de développement initialisées.
     */
    public List<CriteresDevPage> initCheckboxDev(List<CriteresDev> criteresDevList) {
        List<CriteresDevPage> criteresDevPageList = new ArrayList<>();

        if (criteresDevList != null) {
            for (CriteresDev critere : criteresDevList) {
                CriteresDevPage criteresDevPage = new CriteresDevPage();
                criteresDevPage.setKeyCritereDev(critere.getKeyCritereDev());
                criteresDevPage.setEtat(NONTRAITE);
                criteresDevPageList.add(criteresDevPage);
            }
            return criteresDevPageList;
        }
        return Collections.emptyList();
    }

    /**
     * Initialise une liste de pages de feuille de route de développement en fonction de la liste de feuilles de route fournies.
     *
     * @param feuilleDeRouteDevList La liste de feuilles de route de développement.
     * @return Une liste de pages de feuille de route de développement initialisées.
     */
    public List<FeuilleDeRouteDevPage> initFeuilleDeRouteDev(List<FeuilleDeRouteDev> feuilleDeRouteDevList) {
        List<FeuilleDeRouteDevPage> feuilleDeRouteDevPageList = new ArrayList<>();
        if (feuilleDeRouteDevList == null) {
            return Collections.emptyList();
        }
        for (FeuilleDeRouteDev feuilleDeRouteDev : feuilleDeRouteDevList) {
            FeuilleDeRouteDevPage feuilleDeRouteDevPage = new FeuilleDeRouteDevPage();
            feuilleDeRouteDevPage.setKeyFeuilleDeRouteDevPage(feuilleDeRouteDev.getKeyFeuilleDeRouteDev());
            feuilleDeRouteDevPage.setCriteresDevPage(initCheckboxDev(feuilleDeRouteDev.getCriteresDevList()));
            feuilleDeRouteDevPageList.add(feuilleDeRouteDevPage);
        }
        return feuilleDeRouteDevPageList;
    }

    /**
     * Initialise une liste de pages de critères fonctionnels en fonction de la liste de critères fournie.
     *
     * @param criteresFoncList La liste de critères fonctionnels.
     * @return Une liste de pages de critères fonctionnels initialisées.
     */
    public List<CriteresFoncPage> initCheckboxFonc(List<CritereFonc> criteresFoncList) {
        List<CriteresFoncPage> criteresFoncPageList = new ArrayList<>();

        if (criteresFoncList != null) {
            for (CritereFonc critere : criteresFoncList) {
                CriteresFoncPage criteresFoncPage = new CriteresFoncPage();
                criteresFoncPage.setKeyCritereFonc(critere.getKeyCritereFonc());
                criteresFoncPage.setEtat(NONTRAITE);
                criteresFoncPageList.add(criteresFoncPage);
            }
            return criteresFoncPageList;
        }
        return Collections.emptyList();
    }

    /**
     * Initialise une liste de pages de feuille de route fonctionnelle en fonction de la liste de feuilles de route fournies.
     *
     * @param feuilleDeRouteFoncList La liste de feuilles de route fonctionnelles.
     * @return Une liste de pages de feuille de route fonctionnelle initialisées.
     */
    public List<FeuilleDeRouteFoncPage> initFeuilleDeRouteFonc(List<FeuilleDeRouteFonc> feuilleDeRouteFoncList) {
        List<FeuilleDeRouteFoncPage> feuilleDeRouteFoncPageList = new ArrayList<>();
        if (feuilleDeRouteFoncList == null) {
            return Collections.emptyList();
        }
        for (FeuilleDeRouteFonc feuilleDeRouteFonc : feuilleDeRouteFoncList) {
            FeuilleDeRouteFoncPage feuilleDeRouteFoncPage = new FeuilleDeRouteFoncPage();
            feuilleDeRouteFoncPage.setKeyFeuilleDeRouteFoncPage(feuilleDeRouteFonc.getKeyFeuilleDeRouteFonc());
            feuilleDeRouteFoncPage.setCriteresFoncPage(initCheckboxFonc(feuilleDeRouteFonc.getCriteresFoncList()));
            feuilleDeRouteFoncPageList.add(feuilleDeRouteFoncPage);
        }
        return feuilleDeRouteFoncPageList;
    }

    /**
     * Initialise une liste de pages de critères UX en fonction de la liste de critères fournie.
     *
     * @param criteresUXList La liste de critères UX.
     * @return Une liste de pages de critères UX initialisées.
     */
    public List<CriteresUXPage> initCheckboxUX(List<CriteresUX> criteresUXList) {
        List<CriteresUXPage> criteresUXPageList = new ArrayList<>();

        if (criteresUXList != null) {
            for (CriteresUX critere : criteresUXList) {
                CriteresUXPage criteresUXPage = new CriteresUXPage();
                criteresUXPage.setKeyCritereUX(critere.getKeyCritereUX());
                criteresUXPage.setEtat(NONTRAITE);
                criteresUXPageList.add(criteresUXPage);
            }
            return criteresUXPageList;
        }
        return Collections.emptyList();
    }

    /**
     * Initialise une liste de pages de feuille de route UX en fonction de la liste de feuilles de route fournies.
     *
     * @param feuilleDeRouteUXList La liste de feuilles de route UX.
     * @return Une liste de pages de feuille de route UX initialisées.
     */
    public List<FeuilleDeRouteUXPage> initFeuilleDeRouteUX(List<FeuilleDeRouteUX> feuilleDeRouteUXList) {
        if (feuilleDeRouteUXList == null) {
            return Collections.emptyList();
        }

        List<FeuilleDeRouteUXPage> feuilleDeRouteUXPageList = new ArrayList<>();

        for (FeuilleDeRouteUX feuilleDeRouteUX : feuilleDeRouteUXList) {
            FeuilleDeRouteUXPage feuilleDeRouteUXPage = new FeuilleDeRouteUXPage();
            feuilleDeRouteUXPage.setKeyFeuilleDeRouteUXPage(feuilleDeRouteUX.getKeyFeuilleDeRouteUX());
            feuilleDeRouteUXPage.setCriteresUXPage(initCheckboxUX(feuilleDeRouteUX.getCriteresUXList()));
            feuilleDeRouteUXPageList.add(feuilleDeRouteUXPage);
        }

        return feuilleDeRouteUXPageList;
    }

    /**
     * Initialise une liste de pages de sous-rubriques en fonction de la liste de sous-rubriques fournie.
     *
     * @param sousRubriquesList La liste de sous-rubriques.
     * @return Une liste de pages de sous-rubriques initialisées.
     */
    public List<SousRubriquesPage> initSousRubriquesPages(List<SousRubriques> sousRubriquesList) {
        if (sousRubriquesList == null) {
            return Collections.emptyList();
        }
        List<SousRubriquesPage> sousRubriquesPageList = new ArrayList<>();
        for (SousRubriques sousRubriques : sousRubriquesList) {
            SousRubriquesPage sousRubriquesPage = new SousRubriquesPage();
            sousRubriquesPage.setKeySousRubriquesPage(sousRubriques.getKeySousRubriques());
            sousRubriquesPage.setFeuilleDeRouteFoncPage(initFeuilleDeRouteFonc(sousRubriques.getSousRubriqueFonctionnelle()));
            sousRubriquesPage.setFeuilleDeRouteUXPage(initFeuilleDeRouteUX(sousRubriques.getSousRubriqueUx()));
            sousRubriquesPage.setFeuilleDeRouteDevPage(initFeuilleDeRouteDev(sousRubriques.getSousRubriqueDeveloppement()));

            sousRubriquesPage.setMaquette(new ArrayList<>());
            sousRubriquesPageList.add(sousRubriquesPage);
        }
        return sousRubriquesPageList;
    }

    /**
     * Initialise une liste de pages de rubriques en fonction de la liste de rubriques fournie.
     *
     * @param rubriquesList La liste de rubriques.
     * @return Une liste de pages de rubriques initialisées.
     */
    public List<RubriquesPage> initRubriquePages(List<Rubriques> rubriquesList) {
        if (rubriquesList == null) {
            return Collections.emptyList();
        }
        List<RubriquesPage> rubriquesPagesList = new ArrayList<>();
        for (Rubriques rubriques : rubriquesList) {
            RubriquesPage rubriquesPage = new RubriquesPage();
            rubriquesPage.setKeyRubriquesPage(rubriques.getKeyRubriques());
            rubriquesPage.setSousRubriquesPage(initSousRubriquesPages(rubriques.getSousRubriques()));
            rubriquesPagesList.add(rubriquesPage);
        }
        return rubriquesPagesList;
    }

    /**
     * Vérifie si le titre de page est déjà utilisé dans le projet.
     *
     * @param project   Le projet à vérifier.
     * @param pageTitle Le titre à vérifier.
     * @return True si le titre est déjà utilisé, false sinon.
     */
    private boolean isPageTitleAlreadyUsed(Project project, String pageTitle) {
        for (Page page : project.getPages()) {
            if (pageTitle.equals(page.getTitrePage())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Récupèrer la maquette avec ces métadonnées
     *
     * @param savedMaquette La maquette sauvegardée dont on souhaite extraire les métadonnées.
     * @return la Maquette contenant uniquement les métadonnées
     */
    public Maquette getMaquetteWithOnlyMetadata(Maquette savedMaquette){
        Maquette maquette = new Maquette();
        maquette.setIdentifiant(savedMaquette.getIdentifiant());
        maquette.setFilename(savedMaquette.getFilename());
        maquette.setFileSize(savedMaquette.getFileSize());
        maquette.setFileType(savedMaquette.getFileType());
        return maquette;
    }
    /**
     * Met à jour une maquete  dans une page.
     *
     * @param maquette  l'objet maquette à mettre à jour
     */
    public void updateMaquette(Maquette maquette) {
        //  Trouver le Project associé à la Maquette
        Project project = projectPersistencePort.findProjectByIdentifiant(maquette.getProjectId());
        if (project == null) {

            throw new IllegalArgumentException("Le projet avec l'identifiant spécifié n'existe pas.");
        }

        List<Page> pages = project.getPages();

        if (pages == null || pages.isEmpty()) {
            throw new IllegalStateException("Le projet ne contient aucune page à mettre à jour.");
        }

        //  Parcourir la liste des Pages du Project
        for (Page page : project.getPages()) {
            if (page.getTitrePage().equals(maquette.getTitrePage())) {
                //  Parcourir les RubriquesPage de la Page trouvée
                for (RubriquesPage rubriquesPage : page.getRubriquesPages()) {
                    for (SousRubriquesPage sousRubriquesPage : rubriquesPage.getSousRubriquesPage()) {
                        if (sousRubriquesPage.getKeySousRubriquesPage().equals(maquette.getKeySousRubrique())) {
                            //  Mettre à jour les attributs de la Maquette dans la SousRubriquesPage trouvée
                            sousRubriquesPage.getMaquette().add(getMaquetteWithOnlyMetadata(maquette));
                            for (FeuilleDeRouteDevPage feuilleDeRouteDevPage : sousRubriquesPage.getFeuilleDeRouteDevPage()) {
                                for (FeuilleDeRouteUXPage feuilleDeRouteUXPage : sousRubriquesPage.getFeuilleDeRouteUXPage()) {
                                    for (FeuilleDeRouteFoncPage feuilleDeRouteFoncPage : sousRubriquesPage.getFeuilleDeRouteFoncPage()) {
                                        updatePage(
                                            maquette.getProjectId(),
                                            maquette.getTitrePage(),
                                            page,
                                            project.getAvancementGlobal(),
                                            feuilleDeRouteUXPage.getAvancementUX(),
                                            feuilleDeRouteFoncPage.getAvancementFonc(),
                                            feuilleDeRouteDevPage.getAvancementDev(),
                                            maquette.getKeySousRubrique()
                                        );
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Supprime une maquette du projet
     *
     * @param maquette La maquette à supprimer
     * @throws IllegalArgumentException Si la maquette ou le projet associé n'existe pas
     */
    public void deleteMaquette(Maquette maquette) {
        // Trouver le projet associé à la maquette
        Project project = projectPersistencePort.findProjectByIdentifiant(maquette.getProjectId());
        if (project == null) {
            throw new IllegalArgumentException("Le projet avec l'identifiant spécifié n'existe pas.");
        }

        // Parcourir les pages du projet
        for (Page page : project.getPages()) {
            if (page.getTitrePage().equals(maquette.getTitrePage())) {
                // Parcourir les rubriques de la page
                for (RubriquesPage rubriquesPage : page.getRubriquesPages()) {
                    // Parcourir les sous-rubriques de la rubrique
                    for (SousRubriquesPage sousRubriquesPage : rubriquesPage.getSousRubriquesPage()) {
                        // Vérifier si la maquette doit être supprimée
                        if (sousRubriquesPage.getKeySousRubriquesPage().equals(maquette.getKeySousRubrique())) {
                            // Supprimer la maquette de la liste des maquettes de la sous-rubrique
                            sousRubriquesPage.getMaquette().removeIf(m -> m.getIdentifiant().equals(maquette.getIdentifiant()));
                            // Mettre à jour la page
                            for (FeuilleDeRouteDevPage feuilleDeRouteDevPage : sousRubriquesPage.getFeuilleDeRouteDevPage()) {
                                for (FeuilleDeRouteUXPage feuilleDeRouteUXPage : sousRubriquesPage.getFeuilleDeRouteUXPage()) {
                                    for (FeuilleDeRouteFoncPage feuilleDeRouteFoncPage : sousRubriquesPage.getFeuilleDeRouteFoncPage()) {
                                        updatePage(
                                            maquette.getProjectId(),
                                            maquette.getTitrePage(),
                                            page,
                                            project.getAvancementGlobal(),
                                            feuilleDeRouteUXPage.getAvancementUX(),
                                            feuilleDeRouteFoncPage.getAvancementFonc(),
                                            feuilleDeRouteDevPage.getAvancementDev(),
                                            maquette.getKeySousRubrique()
                                        );
                                    }
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException("La maquette spécifiée n'existe pas dans le projet.");
    }

    /**
     * Crée une nouvelle page dans le projet avec le titre et la version spécifiés.
     *
     * @param identifiantProject L'identifiant du projet.
     * @param titrePage          Le titre de la nouvelle page.
     * @param version            La version du référentiel à utiliser.
     * @return La page créée.
     * @throws IllegalArgumentException Si le titre est déjà utilisé dans le projet.
     */
    public Page createPage(String identifiantProject, String titrePage, String version) {
        Project project = projectPersistencePort.findProjectByIdentifiant(identifiantProject);

        // Initialise la liste de pages si elle est nulle
        if (project.getPages() == null) {
            project.setPages(new ArrayList<>());
        }
        // Vérifie si le titre de la page est déjà utilisé dans le projet
        if (isPageTitleAlreadyUsed(project, titrePage)) {
            throw new IllegalArgumentException("Le titre de la page est déjà utilisé dans le projet.");
        }
        Referentiel referentiel = referentielService.findByVersion(version);
        List<RubriquesPage> rubriquesPages = initRubriquePages(referentiel.getRubriques());

        List<Page> existingPages = project.getPages();
        Page page = new Page();
        page.setTitrePage(titrePage);
        page.setRubriquesPages(rubriquesPages);

        // Mettre à jour les avancements des feuilles de route
        for (RubriquesPage rubrique : page.getRubriquesPages()) {
            for (SousRubriquesPage sousRubrique : rubrique.getSousRubriquesPage()) {
                for (SousRubriquesPage sousRubriqueToCreate : rubrique.getSousRubriquesPage()) {
                    for (FeuilleDeRouteUXPage feuilleUX : sousRubriqueToCreate.getFeuilleDeRouteUXPage()) {
                        feuilleUX.setAvancementUX(0);
                    }
                    for (FeuilleDeRouteFoncPage feuilleFonc : sousRubriqueToCreate.getFeuilleDeRouteFoncPage()) {
                        feuilleFonc.setAvancementFonc(0);
                    }
                    for (FeuilleDeRouteDevPage feuilleDev : sousRubriqueToCreate.getFeuilleDeRouteDevPage()) {
                        feuilleDev.setAvancementDev(0);
                    }
                }
            }
        }
        existingPages.add(page);

        project.setPages(existingPages);
        projectPersistencePort.updateProject(project);

        return page;
    }

    /**
     * Met à jour une page spécifique dans un projet.
     *
     * @param identifiant      L'identifiant du projet auquel la page appartient.
     * @param titrePage        Le titre de la page à mettre à jour.
     * @param updatedPage      La nouvelle version de la page avec les modifications à appliquer.
     * @param avancementGlobal
     * @return La page mise à jour.
     * @throws IllegalArgumentException Si le projet avec l'identifiant spécifié n'existe pas
     *                                  ou si la page spécifiée n'existe pas dans le projet.
     * @throws IllegalStateException    Si le projet ne contient aucune page à mettre à jour.
     */
    public Page updatePage(String identifiant, String titrePage, Page updatedPage, Integer avancementGlobal, Integer avancementUX, Integer avancementFonc, Integer avancementDev, String keySousRubrique) {
        Project project = projectPersistencePort.findProjectByIdentifiant(identifiant);

        if (project == null) {
            throw new IllegalArgumentException("Le projet avec l'identifiant spécifié n'existe pas.");
        }

        List<Page> pages = project.getPages();

        if (pages == null || pages.isEmpty()) {
            throw new IllegalStateException("Le projet ne contient aucune page à mettre à jour.");
        }

        // Recherche la page à mettre à jour par son titre
        Page pageToUpdate = null;
        for (Page page : pages) {
            if (page.getTitrePage().equals(titrePage)) {
                pageToUpdate = page;
                break;
            }
        }

        // Vérifie si la page à mettre à jour a été trouvée
        if (pageToUpdate == null) {
            throw new IllegalArgumentException("La page spécifiée n'existe pas dans le projet.");
        }

        // Met à jour les propriétés de la page avec les nouvelles valeurs
        pageToUpdate.setTitrePage(updatedPage.getTitrePage());
        pageToUpdate.setRubriquesPages(updatedPage.getRubriquesPages());

        // Mettre à jour les avancements des feuilles de route
        for (RubriquesPage rubrique : pageToUpdate.getRubriquesPages()) {
            if (rubrique.getKeyRubriquesPage().equals(rubrique.getKeyRubriquesPage())) {
                for (SousRubriquesPage sousRubriqueToUpdate : rubrique.getSousRubriquesPage()) {
                    if (sousRubriqueToUpdate.getKeySousRubriquesPage().equals(keySousRubrique)) {
                        for (FeuilleDeRouteUXPage feuilleUX : sousRubriqueToUpdate.getFeuilleDeRouteUXPage()) {
                            feuilleUX.setAvancementUX(avancementUX);
                        }
                        for (FeuilleDeRouteFoncPage feuilleFonc : sousRubriqueToUpdate.getFeuilleDeRouteFoncPage()) {
                            feuilleFonc.setAvancementFonc(avancementFonc);
                        }
                        for (FeuilleDeRouteDevPage feuilleDev : sousRubriqueToUpdate.getFeuilleDeRouteDevPage()) {
                            feuilleDev.setAvancementDev(avancementDev);
                        }
                        break;
                    }
                }
            }
        }

        project.setAvancementGlobal(avancementGlobal);
        // Met à jour le projet avec la liste de pages mise à jour
        projectService.updateProject(project, identifiant);

        return pageToUpdate;
    }

    /**
     * Supprime une page spécifique d'un projet.
     *
     * @param identifiant l'identifiant du projet à partir duquel la page doit être supprimée.
     * @param titrePage   le titre de la page à supprimer.
     * @throws IllegalArgumentException si la page spécifiée n'existe pas dans le projet.
     */
    public void deletePage(String identifiant, String titrePage) {
        // Récupère le projet correspondant à l'identifiant
        Project project = projectPersistencePort.findProjectByIdentifiant(identifiant);

        // Récupère la liste des pages du projet
        List<Page> pages = project.getPages();

        // Recherche la page à supprimer dans la liste des pages du projet
        Page pageToDelete = null;
        for (Page page : pages) {
            if (page.getTitrePage().equals(titrePage)) {
                pageToDelete = page;
                break;
            }
        }

        // Vérifie si la page à supprimer a été trouvée
        if (pageToDelete == null) {
            throw new IllegalArgumentException("La page spécifiée n'existe pas dans le projet.");
        }

        // Supprime la page de la liste des pages du projet
        pages.remove(pageToDelete);

        // Met à jour le projet avec la liste de pages mise à jour
        projectService.updateProject(project, identifiant);
    }
}
