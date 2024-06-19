package com.soprasteria.osca.persistence.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.FeuilleDeRouteDevPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.FeuilleDeRouteFoncPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.FeuilleDeRouteUXPage;
import com.soprasteria.osca.domain.project.Project;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

@ComponentTest
class ProjectPersistenceAdapterTest {

    @Mock
    private ProjectRepository projectRepository;

    private ProjectPersistenceAdapter adapter;

    @Mock
    private ProjectMapper projectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new ProjectPersistenceAdapter(projectRepository);
    }

    /**
     * Tester la création d'un projet.
     * Vérifier si la méthode crée correctement un projet et renvoie l'objet de domaine attendu.
     */

    @Test
    void createProject() {
        // Given: Un projet et une entité de projet simulée
        Project project = new Project();
        ProjectEntite projectEntite = new ProjectEntite();

        when(projectRepository.save(any(ProjectEntite.class))).thenReturn(projectEntite);

        // When: createProject est appelé
        Project createdProject = adapter.createProject(project);

        // Then: Un projet est renvoyé et vérifie que les méthodes appropriées sont appelées
        assertNotNull(createdProject);
        verify(projectRepository).save(any(ProjectEntite.class));
    }

    /**
     * Tester la récupération de tous les projets de la base de données.
     * Vérifier si la méthode renvoie une liste non vide de projets.
     */
    @Test
    void findAllProjects() {
        // Given: Une liste d'entités de projet simulée
        List<ProjectEntite> entites = List.of(new ProjectEntite());
        when(projectRepository.findAll()).thenReturn(entites);

        // When: findAllProjects est appelé
        List<Project> projects = adapter.findAllProjects();

        // Then: Une liste de projets est renvoyée et n'est pas vide
        assertFalse(projects.isEmpty());
        verify(projectRepository).findAll();
    }

    /**
     * Tester la recherche d'un projet par son identifiant lorsque le projet existe.
     */
    @Test
    void findProjectByIdentifiant_withExistingId() {
        // Given: Un identifiant de projet existant
        String identifiant = "existing-id";
        ProjectEntite projectEntite = new ProjectEntite();
        projectEntite.setIdentifiant(identifiant);
        when(projectRepository.findProjectEntiteByIdentifiant(identifiant)).thenReturn(projectEntite);

        // When: findProjectByIdentifiant est appelé
        Project foundProject = adapter.findProjectByIdentifiant(identifiant);

        // Then: Le projet correspondant est retourné
        assertNotNull(foundProject);
        assertEquals(identifiant, foundProject.getIdentifiant());
        verify(projectRepository).findProjectEntiteByIdentifiant(identifiant);
    }

    /**
     * Tester la recherche d'un projet par son identifiant lorsqu'aucun projet correspondant n'existe.
     */
    @Test
    void findProjectByIdentifiant_withNonExistingId() {
        // Given: Un identifiant de projet non existant
        String identifiant = "non-existing-id";
        when(projectRepository.findProjectEntiteByIdentifiant(identifiant)).thenReturn(null);

        // When : findProjectByIdentifiant est appelé & Then: Une EmptyResultDataAccessException est levée
        assertThrows(
            EmptyResultDataAccessException.class,
            () -> {
                adapter.findProjectByIdentifiant(identifiant);
            }
        );

        verify(projectRepository).findProjectEntiteByIdentifiant(identifiant);
    }

    /**
     * Tester la mise à jour d'un projet existant avec des informations valides.
     */
    @Test
    public void testUpdateProject() {
        // Declaration du projet à update et du projet entité
        Project projectToUpdate = new Project();
        projectToUpdate.setIdentifiant("TEST");
        projectToUpdate.setTitre("Titre du projet");
        projectToUpdate.setDateDerniereModification(new Date());
        projectToUpdate.setAvancementGlobal(50);

        List<Page> existingPages = new ArrayList<>();
        Page page = new Page();
        List<RubriquesPage> rubriquesPages = new ArrayList<>();
        RubriquesPage rubrique = new RubriquesPage();
        List<SousRubriquesPage> sousRubriquesPages = new ArrayList<>();
        SousRubriquesPage sousRubrique = new SousRubriquesPage();

        // Initialisation des listes de feuilles de route
        List<FeuilleDeRouteUXPage> feuilleDeRouteUX = new ArrayList<>();
        List<FeuilleDeRouteFoncPage> feuilleDeRouteFonc = new ArrayList<>();
        List<FeuilleDeRouteDevPage> feuilleDeRouteDev = new ArrayList<>();

        // Ajout des feuilles de route à chaque liste
        feuilleDeRouteUX.add(new FeuilleDeRouteUXPage());
        feuilleDeRouteFonc.add(new FeuilleDeRouteFoncPage());
        feuilleDeRouteDev.add(new FeuilleDeRouteDevPage());

        // Affectation des listes de feuilles de route à la sous-rubrique
        sousRubrique.setFeuilleDeRouteUXPage(feuilleDeRouteUX);
        sousRubrique.setFeuilleDeRouteFoncPage(feuilleDeRouteFonc);
        sousRubrique.setFeuilleDeRouteDevPage(feuilleDeRouteDev);

        sousRubriquesPages.add(sousRubrique);
        rubrique.setSousRubriquesPage(sousRubriquesPages);
        rubriquesPages.add(rubrique);
        page.setRubriquesPages(rubriquesPages);
        existingPages.add(page);
        projectToUpdate.setPages(existingPages);

        // Creation du project existant
        ProjectEntite existingProject = new ProjectEntite();
        existingProject.setIdentifiant("TEST");

        when(projectRepository.findProjectEntiteByIdentifiant(any())).thenReturn(existingProject);
        when(projectRepository.save(any())).thenReturn(existingProject);
        when(projectMapper.toDomain(any())).thenReturn(projectToUpdate);

        // Appel de la méthode update
        Project result = adapter.updateProject(projectToUpdate);

        // Verification que la méthode donne les bons résultats
        assertEquals(projectToUpdate.getIdentifiant(), result.getIdentifiant());
        assertEquals(projectToUpdate.getTitre(), result.getTitre());
    }

    /**
     * Teste la méthode updateProject pour s'assurer qu'elle gère correctement la situation où l'identifiant du projet à mettre à jour n'existe pas dans la base de données.
     */
    @Test
    void updateProject_withNonExistingId() {
        // Given: Un identifiant inexistant pour mettre à jour un nouveau titre
        String identifiant = "non-existing-id";
        Project projectToUpdate = new Project();
        projectToUpdate.setIdentifiant(identifiant);
        projectToUpdate.setTitre("Updated Title");

        when(projectRepository.findProjectEntiteByIdentifiant(identifiant)).thenReturn(null);

        // When & Then: Vérifier qu'une NoSuchElementException est levée lorsque updateProject est appelé
        assertThrows(NoSuchElementException.class, () -> adapter.updateProject(projectToUpdate));

        verify(projectRepository, never()).save(any(ProjectEntite.class));
    }

    /**
     * Teste la suppression d'un projet avec un identifiant qui existe.
     */
    @Test
    void deleteProject_withExistingId() {
        // Given: Un identifiant de projet existant
        String identifiant = "existing-id";
        ProjectEntite projectEntite = new ProjectEntite();
        projectEntite.setIdentifiant(identifiant);
        when(projectRepository.findProjectEntiteByIdentifiant(identifiant)).thenReturn(projectEntite);

        // When: deleteProject est appelé avec l'identifiant existant
        adapter.deleteProject(identifiant);

        // Then: Vérifie que le projet correspondant a été supprimé
        verify(projectRepository).delete(projectEntite);
    }

    /**
     * Teste la suppression d'un projet avec un identifiant inexistant.
     * Vérifie que NoSuchElementException est levée.
     */
    @Test
    void deleteProject_withNonExistingId() {
        // Given: Un identifiant de projet inexistant
        String identifiant = "non-existing-id";
        when(projectRepository.findProjectEntiteByIdentifiant(identifiant)).thenReturn(null);

        // When & Then: Vérifie qu'une NoSuchElementException est levée lors de l'appel à deleteProject
        assertThrows(NoSuchElementException.class, () -> adapter.deleteProject(identifiant));

        verify(projectRepository, never()).delete(any(ProjectEntite.class));
    }
}
