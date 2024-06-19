package com.soprasteria.osca.domain.project;

import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectPersistencePort projectPersistencePort;

    /**
     * Constructeur du service de gestion des projets.
     *
     * @param projectPersistencePort Le port de persistance pour la gestion des projets.
     */
    public ProjectService(ProjectPersistencePort projectPersistencePort) {
        this.projectPersistencePort = projectPersistencePort;
    }

    /**
     * Créer un nouveau projet.
     *
     * @param project Le projet à créer.
     * @return Le projet créé.
     * @throws IllegalArgumentException Si le titre du projet est vide.
     */
    public Project createProject(Project project) {
        if (project.getTitre() == null || project.getTitre().isBlank()) {
            throw new IllegalArgumentException("Le titre ne doit pas être vide");
        }
        project.setIdentifiant(UUID.randomUUID().toString());
        project.setDateCreation(new Date());
        project.setAvancementGlobal(0);
        return projectPersistencePort.createProject(project);
    }

    /**
     * Récupérer la liste de tous les projets.
     *
     * @return La liste des projets.
     */
    public List<Project> findAllProjects() {
        return projectPersistencePort.findAllProjects();
    }

    /**
     * Récupérer un projet en fonction de son identifiant.
     *
     * @param idProject L'identifiant du projet à récupérer.
     * @return Le projet correspondant à l'identifiant.
     */
    public Project findProjectByIdentifiant(String idProject) {
        return projectPersistencePort.findProjectByIdentifiant(idProject);
    }

    /**
     * Mise à jour un projet existant en utilisant l'identifiant fourni.
     *
     * @param project     Les données mises à jour du projet.
     * @param identifiant L'identifiant du projet à mettre à jour.
     * @return Le projet mis à jour.
     * @throws NoSuchElementException   Si le projet avec l'identifiant spécifié n'existe pas.
     * @throws IllegalArgumentException Si le titre de la page n'est pas unique dans le projet.
     */
    public Project updateProject(Project project, String identifiant) {
        Project existingProject = projectPersistencePort.findProjectByIdentifiant(identifiant);
        if (existingProject == null) {
            throw new NoSuchElementException("Projet avec l'identifiant " + identifiant + " n'existe pas.");
        }

        Set<String> titles = new HashSet<>();
        for (Page page : project.getPages()) {
            if (!titles.add(page.getTitrePage())) {
                throw new IllegalArgumentException("Le titre de la page doit être unique dans le projet: " + page.getTitrePage());
            }
        }

        // Mettre à jour les pages
        existingProject.setPages(project.getPages());

        // Mettre à jour les autres attributs du projet
        existingProject.setTitre(project.getTitre());
        existingProject.setDateDerniereModification(new Date());
        existingProject.setAvancementGlobal(project.getAvancementGlobal());

        return projectPersistencePort.updateProject(existingProject);
    }

    /**
     * Met à jour l'avancement global d'un projet spécifié.
     *
     * @param identifiant      L'identifiant du projet à mettre à jour.
     * @param nouvelAvancement Le nouvel avancement global du projet.
     * @return Le projet mis à jour avec le nouvel avancement.
     */
    public Project updateAvancement(String identifiant, Integer nouvelAvancement) {
        Project existingProject = projectPersistencePort.findProjectByIdentifiant(identifiant);
        if (existingProject == null) {
            throw new NoSuchElementException("Projet avec l'identifiant " + identifiant + " n'existe pas.");
        }
        existingProject.setAvancementGlobal(nouvelAvancement);

        return projectPersistencePort.updateAvancement(existingProject);
    }

    /**
     * Supprimer un projet en fonction de son identifiant.
     *
     * @param identifiant L'identifiant du projet à supprimer.
     * @throws NoSuchElementException Si le projet avec l'identifiant spécifié n'existe pas.
     */
    public void deleteProject(String identifiant) {
        Project existingProject = projectPersistencePort.findProjectByIdentifiant(identifiant);
        if (existingProject == null) {
            throw new NoSuchElementException("Projet avec l'identifiant " + identifiant + " n'existe pas.");
        }
        projectPersistencePort.deleteProject(identifiant);
    }
}
