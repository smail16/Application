package com.soprasteria.osca.controller.project;

import com.soprasteria.osca.api.ProjectsApi;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.project.ProjectService;
import com.soprasteria.osca.model.ProjectAPI;
import com.soprasteria.osca.model.ProjectToCreate;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ProjectsController implements ProjectsApi {

    private final ProjectService projectService;
    private final ProjectAPIMapper projectAPIMapper = Mappers.getMapper(ProjectAPIMapper.class);

    /**
     * Constructeur du contrôleur des projets.
     *
     * @param projectService Service de gestion des projets.
     */
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Créer un nouveau projet à partir des données fournies.
     *
     * @param projectToCreate Les données du projet à créer.
     * @return Réponse HTTP avec le projet créé ou une erreur BadRequest si les données sont incorrectes.
     */
    @Override
    public ResponseEntity<ProjectAPI> createProject(ProjectToCreate projectToCreate) {
        try {
            Project project = projectAPIMapper.toDomain(projectToCreate);
            Project createdProject = projectService.createProject(project);
            ProjectAPI responseProjectAPI = projectAPIMapper.toApi(createdProject);

            return new ResponseEntity<>(responseProjectAPI, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Récupèrer la liste de tous les projets.
     *
     * @return Réponse HTTP avec la liste des projets.
     */
    @Override
    public ResponseEntity<List<ProjectAPI>> findAllProjects() {
        List<Project> projectList = projectService.findAllProjects();
        return ResponseEntity.ok(projectAPIMapper.toApiList(projectList));
    }

    /**
     * Mise à jour du projet existant en utilisant l'identifiant fourni.
     *
     * @param identifiant L'identifiant du projet à mettre à jour.
     * @param projectAPI  Les données mises à jour du projet.
     * @return Réponse HTTP avec le projet mis à jour ou une erreur BadRequest si les données sont incorrectes.
     */
    @Override
    public ResponseEntity<ProjectAPI> updateProject(@PathVariable("identifiant") String identifiant, ProjectAPI projectAPI) {
        try {
            Project project = projectAPIMapper.toDomain(projectAPI);

            Project updatedProject = projectService.updateProject(project, identifiant);

            ProjectAPI responseProjectAPI = projectAPIMapper.toApi(updatedProject);

            return ResponseEntity.ok(responseProjectAPI);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Met à jour l'avancement global d'un projet.
     *
     * @param identifiant      L'identifiant du projet à mettre à jour.
     * @param avancementGlobal Le nouvel avancement global du projet.
     * @return ResponseEntity<ProjectAPI> retourne le projet avec le nouvel avancement
     */
    @Override
    public ResponseEntity<ProjectAPI> updateAvancement(
            @PathVariable("identifiant") String identifiant,
            @RequestParam("avancementGlobal") Integer avancementGlobal
    ) {
        Project updatedProject = projectService.updateAvancement(identifiant, avancementGlobal);
        ProjectAPI responseProjectAPI = projectAPIMapper.toApi(updatedProject);
        return ResponseEntity.ok(responseProjectAPI);
    }

    /**
     * Récupèrer un projet en fonction de son identifiant.
     *
     * @param identifiant L'identifiant du projet à récupérer.
     * @return Réponse HTTP avec le projet correspondant à l'identifiant.
     */
    @Override
    public ResponseEntity<ProjectAPI> findProjectByIdentifiant(@PathVariable("identifiant") String identifiant) {
        Project project = projectService.findProjectByIdentifiant(identifiant);
        ProjectAPI projectAPI = projectAPIMapper.toApi(project);
        return ResponseEntity.ok(projectAPI);
    }

    /**
     * Supprime un projet en fonction de son identifiant.
     *
     * @param identifiant L'identifiant du projet à supprimer.
     * @return Réponse HTTP indiquant le succès de la suppression ou une erreur NotFound si le projet n'est pas trouvé.
     */
    @Override
    public ResponseEntity<Void> deleteProject(@PathVariable("identifiant") String identifiant) {
        try {
            projectService.deleteProject(identifiant);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
