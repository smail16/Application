package com.soprasteria.osca.domain.project;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.page.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ComponentTest
class ProjectServiceTest {

    private ProjectService projectService;

    @Mock
    private ProjectPersistencePort mockProjectPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(mockProjectPersistencePort);
    }

    /**
     * Tester la création d'un projet avec un titre valide.
     */
    @Test
    void createProject_withValidProject() {
        // Given: Un projet avec un titre valide
        Project project = new Project();
        project.setTitre("Titre du projet");
        when(mockProjectPersistencePort.createProject(any(Project.class))).thenReturn(project);

        // When: createProject est appelé
        Project createdProject = projectService.createProject(project);

        // Then: Un projet est créé avec le titre spécifié
        assertNotNull(createdProject);
        assertEquals("Titre du projet", createdProject.getTitre());
        verify(mockProjectPersistencePort).createProject(project);
    }

    /**
     * Tester la création d'un projet avec un titre nul, ce qui devrait lever une exception.
     */
    @Test
    void createProject_withNullTitle() {
        // Given: Un projet sans titre
        Project project = new Project();

        // When: createProject est appelé  & Then: Une IllegalArgumentException est levée
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    projectService.createProject(project);
                }
        );

        String expectedMessage = "Le titre ne doit pas être vide";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tester la récupération de tous les projets.
     */
    @Test
    void findAllProjects() {
        // Given: Une liste de projets existants
        List<Project> mockProjects = new ArrayList<>();
        mockProjects.add(new Project());
        mockProjects.add(new Project());
        mockProjects.add(new Project());
        when(mockProjectPersistencePort.findAllProjects()).thenReturn(mockProjects);

        // When: findAllProjects est appelé
        List<Project> result = projectService.findAllProjects();

        // Then: La liste des projets est retournée
        assertNotNull(result);
        assertEquals(3, result.size());
        verify(mockProjectPersistencePort).findAllProjects();
    }

    /**
     * Tester la recherche d'un projet par son identifiant lorsque le projet existe.
     */
    @Test
    void findProjectByIdentifiant_withExistingId() {
        // Given: Un identifiant de projet existant
        String identifiant = "project-id";
        Project mockProject = new Project();
        mockProject.setIdentifiant(identifiant);
        when(mockProjectPersistencePort.findProjectByIdentifiant(identifiant)).thenReturn(mockProject);

        // When: findProjectByIdentifiant est appelé
        Project result = projectService.findProjectByIdentifiant(identifiant);

        // Then: Le projet correspondant est retourné
        assertNotNull(result);
        assertEquals(identifiant, result.getIdentifiant());
        verify(mockProjectPersistencePort).findProjectByIdentifiant(identifiant);
    }

    /**
     * Tester la recherche d'un projet par son identifiant lorsqu'aucun projet correspondant n'existe.
     */
    @Test
    void findProjectByIdentifiant_withNonExistingId() {
        // Given: Un identifiant de projet non existant
        String identifiant = "non-existing-id";
        when(mockProjectPersistencePort.findProjectByIdentifiant(identifiant))
                .thenThrow(new EmptyResultDataAccessException("Projet non trouvé avec l'ID : " + identifiant, 1));

        // When : findProjectByIdentifiant est appelé & Then: Une EmptyResultDataAccessException est levée
        assertThrows(
                EmptyResultDataAccessException.class,
                () -> {
                    projectService.findProjectByIdentifiant(identifiant);
                }
        );

        verify(mockProjectPersistencePort).findProjectByIdentifiant(identifiant);
    }

    /**
     * Tester la mise à jour d'un projet existant avec des informations valides.
     */
    @Test
    void updateProject_withExistingId() {
        // Given: Un identifiant pour un projet existant et un projet avec de nouvelles informations
        String identifiant = "existing-id";
        Project projectToUpdate = new Project();
        projectToUpdate.setTitre("Updated Title");

        // Ajouter des pages avec des titres uniques
        List<Page> pages = new ArrayList<>();
        Page page1 = new Page();
        page1.setTitrePage("Page Title 1");
        pages.add(page1);

        Page page2 = new Page();
        page2.setTitrePage("Page Title 2");
        pages.add(page2);

        projectToUpdate.setPages(pages);

        Project existingProject = new Project();
        existingProject.setIdentifiant(identifiant);
        when(mockProjectPersistencePort.findProjectByIdentifiant(identifiant)).thenReturn(existingProject);
        when(mockProjectPersistencePort.updateProject(any(Project.class))).thenReturn(projectToUpdate);

        // When: updateProject est appelé avec ces informations
        Project updatedProject = projectService.updateProject(projectToUpdate, identifiant);

        // Then: Le projet est mis à jour et les nouvelles informations sont retournées
        assertNotNull(updatedProject);
        assertEquals("Updated Title", updatedProject.getTitre());
        assertEquals(2, updatedProject.getPages().size()); // Vérifier le nombre de pages dans le projet mis à jour
        assertEquals("Page Title 1", updatedProject.getPages().get(0).getTitrePage());
        assertEquals("Page Title 2", updatedProject.getPages().get(1).getTitrePage());
        verify(mockProjectPersistencePort).updateProject(any(Project.class));
    }

    /**
     * Tester la mise à jour d'un projet avec des titres de pages non uniques, ce qui devrait lever une exception.
     */
    @Test
    void updateProject_withNonUniquePageTitles_shouldThrowIllegalArgumentException() {
        // Un identifiant pour un projet existant et un projet avec des pages ayant des titres dupliqués
        String identifiant = "existing-id";
        Project projectToUpdate = new Project();

        // Créer des pages avec des titres dupliqués
        Page page1 = new Page();
        page1.setTitrePage("Title1");
        Page page2 = new Page();
        page2.setTitrePage("Title1");

        List<Page> pages = new ArrayList<>();
        pages.add(page1);
        pages.add(page2);

        projectToUpdate.setPages(pages);

        Project existingProject = new Project();
        existingProject.setIdentifiant(identifiant);

        when(mockProjectPersistencePort.findProjectByIdentifiant(identifiant)).thenReturn(existingProject);

        // When: updateProject est appelé avec ces informations  &Then: Une IllegalArgumentException est levée, indiquant que les titres des pages ne sont pas uniques
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    projectService.updateProject(projectToUpdate, identifiant);
                }
        );

        // Vérifier que la mise à jour du projet n'a jamais été appelée en raison de l'exception
        verify(mockProjectPersistencePort, never()).updateProject(any(Project.class));
    }
}
