package com.soprasteria.osca.domain.page;

import static com.soprasteria.osca.domain.page.rubriques.sousrubriques.EtatEnum.NONTRAITE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.soprasteria.ComponentTest;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ComponentTest
class PageServiceTest {

    ProjectPersistencePort persistencePortMock;
    ReferentielService referentielServiceMock;
    ProjectService projectService;
    PageService pageService;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks pour chaque test
        persistencePortMock = mock(ProjectPersistencePort.class);
        referentielServiceMock = mock(ReferentielService.class);
        projectService = mock(ProjectService.class);
        pageService = new PageService(persistencePortMock, referentielServiceMock, projectService);
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité CheckboxDev dans PageService.
     */
    @Test
    public void testInitCheckboxDev() {
        // Prépare une liste de CriteresDev pour le test
        List<CriteresDev> criteresDevList = new ArrayList<>();
        CriteresDev critere1 = new CriteresDev();
        critere1.setKeyCritereDev("1");
        criteresDevList.add(critere1);

        // Appelle la méthode à tester
        List<CriteresDevPage> result = pageService.initCheckboxDev(criteresDevList);

        // Vérifie que la liste résultante contient le bon nombre d'éléments
        assertEquals(criteresDevList.size(), result.size());

        // Vérifie que les éléments de la liste résultante ont été correctement initialisés
        for (CriteresDevPage critereDevPage : result) {
            assertEquals(NONTRAITE, critereDevPage.getEtat());
        }
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité CheckboxFonc dans PageService.
     */
    @Test
    public void testInitCheckboxFonc() {
        // Prépare une liste de CriteresFonc pour le test
        List<CritereFonc> criteresFoncList = new ArrayList<>();
        CritereFonc critere1 = new CritereFonc();
        critere1.setKeyCritereFonc("1");
        criteresFoncList.add(critere1);

        // Appelle la méthode à tester
        List<CriteresFoncPage> result = pageService.initCheckboxFonc(criteresFoncList);

        // Vérifie que la liste résultante contient le bon nombre d'éléments
        assertEquals(criteresFoncList.size(), result.size());

        // Vérifie que les éléments de la liste résultante ont été correctement initialisés
        for (CriteresFoncPage critereFoncPage : result) {
            assertEquals(NONTRAITE, critereFoncPage.getEtat());
        }
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité CheckBoxUX dans PageService.
     */
    @Test
    public void testInitCheckBoxUX() {
        List<CriteresUX> criteresUXList = new ArrayList<>();
        CriteresUX critere1 = new CriteresUX();
        critere1.setKeyCritereUX("1");
        criteresUXList.add(critere1);

        // Appelle la méthode à tester
        List<CriteresUXPage> result = pageService.initCheckboxUX(criteresUXList);

        // Vérifie que la liste résultante contient le bon nombre d'éléments
        assertEquals(criteresUXList.size(), result.size());

        // Vérifie que les éléments de la liste résultante ont été correctement initialisés
        for (CriteresUXPage critereUXPage : result) {
            assertEquals(NONTRAITE, critereUXPage.getEtat());
        }
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité FeuilleDeRouteDev dans PageService.
     */
    @Test
    void testInitFeuilleDeRouteDev() {
        // Créez des données de test
        FeuilleDeRouteDev feuilleDeRouteDev = new FeuilleDeRouteDev();
        List<FeuilleDeRouteDev> feuilleDeRouteDevList = List.of(feuilleDeRouteDev);

        // Appelez la méthode que vous souhaitez tester
        List<FeuilleDeRouteDevPage> result = pageService.initFeuilleDeRouteDev(feuilleDeRouteDevList);

        // Vérifiez les résultats attendus
        assertNotNull(result);
        assertEquals(feuilleDeRouteDevList.size(), result.size());

        FeuilleDeRouteDevPage expectedPage = new FeuilleDeRouteDevPage();
        expectedPage.setKeyFeuilleDeRouteDevPage(feuilleDeRouteDev.getKeyFeuilleDeRouteDev());
        expectedPage.setCriteresDevPage(pageService.initCheckboxDev(feuilleDeRouteDev.getCriteresDevList()));

        assertEquals(expectedPage.getKeyFeuilleDeRouteDevPage(), result.get(0).getKeyFeuilleDeRouteDevPage());
        assertEquals(expectedPage.getCriteresDevPage(), result.get(0).getCriteresDevPage());
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité FeuilleDeRouteUX dans PageService.
     */
    @Test
    void testInitFeuilleDeRouteUX() {
        // Crée des données de test
        FeuilleDeRouteUX feuilleDeRouteUX = new FeuilleDeRouteUX();
        List<FeuilleDeRouteUX> feuilleDeRouteUXList = List.of(feuilleDeRouteUX);

        List<FeuilleDeRouteUXPage> result = pageService.initFeuilleDeRouteUX(feuilleDeRouteUXList);

        // Vérifie les résultats attendus
        assertNotNull(result);
        assertEquals(feuilleDeRouteUXList.size(), result.size());

        FeuilleDeRouteUXPage expectedPage = new FeuilleDeRouteUXPage();
        expectedPage.setKeyFeuilleDeRouteUXPage(feuilleDeRouteUX.getKeyFeuilleDeRouteUX());
        expectedPage.setCriteresUXPage(pageService.initCheckboxUX(feuilleDeRouteUX.getCriteresUXList()));

        assertEquals(expectedPage.getKeyFeuilleDeRouteUXPage(), result.get(0).getKeyFeuilleDeRouteUXPage());
        assertEquals(expectedPage.getCriteresUXPage(), result.get(0).getCriteresUXPage());
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité FeuilleDeRouteFonc dans PageService.
     */
    @Test
    void testInitFeuilleDeRouteFonc() {
        // Crée des données de test
        FeuilleDeRouteFonc feuilleDeRouteFonc = new FeuilleDeRouteFonc();
        List<FeuilleDeRouteFonc> feuilleDeRouteFoncList = List.of(feuilleDeRouteFonc);

        List<FeuilleDeRouteFoncPage> result = pageService.initFeuilleDeRouteFonc(feuilleDeRouteFoncList);

        // Vérifie les résultats attendus
        assertNotNull(result);
        assertEquals(feuilleDeRouteFoncList.size(), result.size());

        FeuilleDeRouteFoncPage expectedPage = new FeuilleDeRouteFoncPage();
        expectedPage.setKeyFeuilleDeRouteFoncPage(feuilleDeRouteFonc.getKeyFeuilleDeRouteFonc());
        expectedPage.setCriteresFoncPage(pageService.initCheckboxFonc(feuilleDeRouteFonc.getCriteresFoncList()));

        assertEquals(expectedPage.getKeyFeuilleDeRouteFoncPage(), result.get(0).getKeyFeuilleDeRouteFoncPage());
        assertEquals(expectedPage.getCriteresFoncPage(), result.get(0).getCriteresFoncPage());
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité SousRubriquesPages dans PageService.
     */
    @Test
    void testInitSousRubriquesPages() {
        SousRubriques sousRubriques1 = mock(SousRubriques.class);
        when(sousRubriques1.getKeySousRubriques()).thenReturn("1");
        when(sousRubriques1.getSousRubriqueFonctionnelle()).thenReturn(List.of());
        when(sousRubriques1.getSousRubriqueUx()).thenReturn(List.of());
        when(sousRubriques1.getSousRubriqueDeveloppement()).thenReturn(List.of());

        List<SousRubriquesPage> result = pageService.initSousRubriquesPages(List.of(sousRubriques1));

        assertNotNull(result);
        assertEquals(1, result.size());

        SousRubriquesPage firstPage = result.get(0);
        assertEquals("1", firstPage.getKeySousRubriquesPage());
        assertNotNull(firstPage.getMaquette());
        assertTrue(firstPage.getMaquette().isEmpty());
    }

    /**
     * Test unitaire pour l'initialisation de la fonctionnalité RubriquePages dans PageService.
     */
    @Test
    void testInitRubriquePages() {
        Rubriques rubrique1 = mock(Rubriques.class);
        when(rubrique1.getKeyRubriques()).thenReturn("1");
        when(rubrique1.getSousRubriques()).thenReturn(List.of());

        List<RubriquesPage> result = pageService.initRubriquePages(List.of(rubrique1));

        assertNotNull(result);
        assertEquals(1, result.size());

        RubriquesPage rubriquePage1 = result.get(0);
        assertEquals("1", rubriquePage1.getKeyRubriquesPage());
        assertEquals(Collections.emptyList(), rubriquePage1.getSousRubriquesPage());
    }

    /**
     * Test unitaire pour l'update des pages.
     */
    @Test
    void testUpdatePage() {
        // Given
        String identifiant = "123";
        String titrePage = "Page de test";
        Integer avancementGlobal = 24;
        Integer avancementUX = 50;
        Integer avancementFonc = 60;
        Integer avancementDev = 70;
        String keySousRubrique = "sousRubriqueKey";
        Page updatedPage = new Page();
        updatedPage.setTitrePage("Page mise à jour");
        List<RubriquesPage> rubriquesPages = new ArrayList<>();
        updatedPage.setRubriquesPages(rubriquesPages);

        // Mock les datas d'un nouveau projet
        Project project = new Project();
        project.setIdentifiant(identifiant);
        List<Page> pages = new ArrayList<>();
        Page existingPage = new Page();
        existingPage.setTitrePage(titrePage);
        existingPage.setRubriquesPages(new ArrayList<>());
        pages.add(existingPage);
        project.setPages(pages);

        // When les méthodes findprojectbyId et Update Project sont appelées
        when(persistencePortMock.findProjectByIdentifiant(identifiant)).thenReturn(project);
        when(projectService.updateProject(project, identifiant)).thenReturn(project);

        // Then
        Page updated = pageService.updatePage(
            identifiant,
            titrePage,
            updatedPage,
            avancementGlobal,
            avancementUX,
            avancementFonc,
            avancementDev,
            keySousRubrique
        );

        // verification du contenu de la réponse
        assertNotNull(updated);
        assertEquals(updatedPage.getTitrePage(), updated.getTitrePage());
        assertEquals(updatedPage.getRubriquesPages(), updated.getRubriquesPages());

        // Verification que la méthode a été appelé avec les bons param
        verify(persistencePortMock).findProjectByIdentifiant(identifiant);
        verify(projectService).updateProject(project, identifiant);
    }

    /**
     * Test unitaire pour pour la methode testGetMaquetteWithOnlyMetadata
     */
    @Test
    void testGetMaquetteWithOnlyMetadata() {
        // Given : maquette avec ces valeurs
        Maquette savedMaquette = new Maquette();
        savedMaquette.setIdentifiant("123");
        savedMaquette.setFilename("test.jpg");
        savedMaquette.setFileSize("1024");
        savedMaquette.setFileType("image/jpeg");

        // When: appel la methode getMaquetteWithOnlyMetadata
        Maquette resultMaquette = pageService.getMaquetteWithOnlyMetadata(savedMaquette);

        // Then : vérifier la maquette et retournée avec les mêmes valeurs
        assertNotNull(resultMaquette);
        assertEquals(savedMaquette.getIdentifiant(), resultMaquette.getIdentifiant());
        assertEquals(savedMaquette.getFilename(), resultMaquette.getFilename());
        assertEquals(savedMaquette.getFileSize(), resultMaquette.getFileSize());
        assertEquals(savedMaquette.getFileType(), resultMaquette.getFileType());
    }

}
