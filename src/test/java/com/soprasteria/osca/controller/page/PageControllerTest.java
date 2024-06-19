package com.soprasteria.osca.controller.page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.PageService;
import com.soprasteria.osca.model.PageAPI;
import com.soprasteria.osca.model.PageToCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ComponentTest
class PageControllerTest {

    @Mock
    private PageService pageService;

    @Mock
    private PageAPIMapper pageAPIMapper;

    @InjectMocks
    private PageController pageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePage() {
        // Given: Un PageToCreate valide avec un identifiant de projet et un titre de page
        String identifiantProject = "project123";
        String titrePage = "titrePage";
        String version = "1.0";
        PageToCreate pageToCreate = new PageToCreate();
        pageToCreate.setIdentifiantProject(identifiantProject);
        pageToCreate.setTitrePage(titrePage);
        pageToCreate.setVersion(version);

        Page page = new Page();

        // When: La méthode createPage du pageService est appelée avec les valeurs valides
        when(pageAPIMapper.toDomain(pageToCreate)).thenReturn(page);
        when(pageService.createPage(eq(identifiantProject), eq(titrePage), anyString())).thenReturn(page);
        when(pageAPIMapper.toApi(page)).thenReturn(new PageAPI());

        // Then: Une réponse non nulle est renvoyée
        ResponseEntity<PageAPI> response = pageController.createPage(pageToCreate);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        // Vérifier que la méthode createPage du pageService a été appelée avec les bonnes valeurs
        verify(pageService).createPage(eq(identifiantProject), eq(titrePage), anyString());
    }

    @Test
    public void testUpdatePage() {
        // Given: Une page à update avec un identifiant de projet, titre, avancementGlobal, UX, Dev et fonc et une clé de sousrubrique
        String identifiantProject = "project1";
        String titrePage = "page1";
        Integer avancementGlobal = 50;
        String keySousRubrique = "sub1";
        Integer avancementUX = 70;
        Integer avancementFonc = 80;
        Integer avancementDev = 90;
        PageAPI updatedPageAPI = new PageAPI();
        Page updatedPage = new Page();
        Page updatedPageResult = new Page();
        updatedPageResult.setTitrePage("Updated Page");

        // When la methode updatepage est appelée
        when(pageAPIMapper.toDomain(updatedPageAPI)).thenReturn(updatedPage);
        when(
            pageService.updatePage(
                identifiantProject,
                titrePage,
                updatedPage,
                avancementGlobal,
                avancementUX,
                avancementDev,
                avancementFonc,
                keySousRubrique
            )
        )
            .thenReturn(updatedPageResult);
        when(pageAPIMapper.toApi(updatedPageResult)).thenReturn(updatedPageAPI);

        // Then une réponse non null est renvoyée
        ResponseEntity<PageAPI> response = pageController.updatePage(
            identifiantProject,
            titrePage,
            avancementGlobal,
            keySousRubrique,
            avancementUX,
            avancementFonc,
            avancementDev,
            updatedPageAPI
        );

        // Verification
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeletePage() {
        String identifiant = "identifiant";
        String titrePage = "titrePage";

        // Appel de la méthode deletePage du contrôleur
        ResponseEntity<String> response = pageController.deletePage(identifiant, titrePage);

        // Vérification que la méthode du service a bien été appelée avec les bons paramètres
        verify(pageService).deletePage(identifiant, titrePage);

        // Vérification du contenu de la réponse
        assertEquals("Page supprimée", response.getBody());
    }
}
