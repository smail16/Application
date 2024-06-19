package com.soprasteria.osca.controller.project;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.project.ProjectService;
import com.soprasteria.osca.model.ProjectAPI;
import com.soprasteria.osca.model.ProjectToCreate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ComponentTest
@ExtendWith(MockitoExtension.class)
class ProjectsControllerTest {
    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectsController projectsController;

    /**
     * Tester la méthode de création de projet dans le contrôleur.
     * Vérifier si le projet est correctement créé et renvoyé avec le statut HTTP approprié.
     */
    @Test
    void createProject() {
        // Given: Un ProjectToCreate valide

        ProjectToCreate projectToCreate = new ProjectToCreate();
        Project project = new Project();

        // When: La méthode createProject du projectService est appelée avec n'importe quel objet Project
        when(projectService.createProject(any(Project.class))).thenReturn(project);

        // Then: Une réponse non nulle est renvoyée avec un code de statut HTTP 201 (Created)
        ResponseEntity<ProjectAPI> reponse = projectsController.createProject(projectToCreate);

        assertNotNull(reponse);
        assertEquals(HttpStatus.CREATED, reponse.getStatusCode());
        assertNotNull(reponse.getBody());

        // Vérifier que la méthode createProject du projectService a été appelée avec n'importe quel objet Project
        verify(projectService).createProject(any(Project.class));
    }

    /**
     * Tester la récupération de tous les projets dans le contrôleur.
     * Vérifier si une liste de projets est correctement retournée avec le statut HTTP approprié.
     */
    @Test
    void findAllProjects() {
        // Given: Une liste de projets
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectService.findAllProjects()).thenReturn(projects);

        // When: La méthode findAllProjects est appelée
        ResponseEntity<List<ProjectAPI>> response = projectsController.findAllProjects();

        // Then: Une liste de projets est retournée avec un statut HTTP OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    /**
     * Tester la recherche d'un projet par son identifiant dans le contrôleur.
     * Vérifier si le projet correspondant est correctement retourné avec le statut HTTP approprié.
     */
    @Test
    void findProjectByIdentifiant() {
        // Given: Un identifiant de projet
        String identifiant = "proj-123";
        Project project = new Project();
        project.setTitre("Titre du projet");

        ProjectAPI projectAPI = new ProjectAPI();
        projectAPI.setTitre("Titre du projet");

        when(projectService.findProjectByIdentifiant(identifiant)).thenReturn(project);

        // When: La méthode findProjectByIdentifiant est appelée
        ResponseEntity<ProjectAPI> response = projectsController.findProjectByIdentifiant("proj-123");

        // Then: Le projet correspondant est retourné avec un statut HTTP OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Titre du projet", response.getBody().getTitre());
    }

    /**
     * Tester la mise à jour d'un projet dans le contrôleur.
     * Vérifier si le projet est correctement mis à jour et retourné avec le statut HTTP approprié.
     */
    @Test
    void updateProject() {
        // Given: Un identifiant de projet et un objet ProjectAPI
        String identifiant = "proj-123";
        ProjectAPI projectAPI = new ProjectAPI();
        projectAPI.setTitre("Titre mis à jour");

        Project projectToUpdate = new Project();
        projectToUpdate.setTitre("Titre mis à jour");

        when(projectService.updateProject(any(Project.class), eq(identifiant))).thenReturn(projectToUpdate);

        // When: La méthode updateProject est appelée

        ResponseEntity<ProjectAPI> reponse = projectsController.updateProject(identifiant, projectAPI);

        // Then: Le projet est mis à jour et retourné avec un statut HTTP OK
        assertNotNull(reponse);
        assertEquals(HttpStatus.OK, reponse.getStatusCode());
        assertNotNull(reponse.getBody());
        assertEquals("Titre mis à jour", reponse.getBody().getTitre());

        // Vérifier que la méthode de service a été appelée
        verify(projectService).updateProject(any(Project.class), eq(identifiant));
    }

    /**
     * Tester la suppression d'un projet dans le contrôleur.
     * Vérifier si le projet est correctement supprimé et si le statut HTTP approprié est retourné.
     */
    @Test
    void deleteProject() {
        // Given: Un identifiant de projet existant
        String identifiant = "proj-123";

        // When: La méthode deleteProject du projectService est appelée avec l'identifiant de projet
        doNothing().when(projectService).deleteProject(identifiant);

        // Then: Une réponse avec un code de statut HTTP 200 (No Content) est renvoyée
        ResponseEntity<Void> responseEntity = projectsController.deleteProject(identifiant);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Vérifier que la méthode deleteProject du projectService a été appelée avec l'identifiant de projet
        verify(projectService, times(1)).deleteProject(identifiant);
    }
}
