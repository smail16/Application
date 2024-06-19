package com.soprasteria.osca.persistence.project;

import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.project.ProjectPersistencePort;
import java.util.List;
import java.util.NoSuchElementException;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectPersistenceAdapter implements ProjectPersistencePort {

    /**
     * Constructeur pour initialiser l'adapter avec le repository et le mapper nécessaires.
     *
     * @param projectRepository Le repository pour les opérations de base de données.
     * @param projectMapper Le mapper pour convertir entre les entités et les objets de domaine.
     */
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    public ProjectPersistenceAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Créer un nouveau projet dans la base de données.
     *
     * @param project L'objet Project à créer.
     * @return Le projet créé.
     */
    @Override
    public Project createProject(Project project) {
        ProjectEntite entite = projectMapper.toEntite(project);
        entite = projectRepository.save(entite);
        return projectMapper.toDomain(entite);
    }

    /**
     * Récupérer tous les projets de la base de données.
     *
     * @return Une liste de projets.
     */
    @Override
    public List<Project> findAllProjects() {
        List<ProjectEntite> entites = projectRepository.findAll();
        return projectMapper.toDomainList(entites);
    }

    /**
     * Recherche un projet par son identifiant.
     *
     * @param idProject L'identifiant du projet à rechercher.
     * @return Le projet trouvé.
     * @throws EmptyResultDataAccessException si aucun projet n'est trouvé.
     */
    @Override
    public Project findProjectByIdentifiant(String idProject) {
        ProjectEntite entite = projectRepository.findProjectEntiteByIdentifiant(idProject);
        if (entite != null) {
            return projectMapper.toDomain(entite);
        } else {
            throw new EmptyResultDataAccessException("Projet non trouvé avec l'ID : " + idProject, 1);
        }
    }

    /**
     * Mise à jour un projet existant dans la base de données.
     *
     * @param projectToUpdate Le projet à mettre à jour.
     * @return Le projet mis à jour.
     */
    @Override
    public Project updateProject(Project projectToUpdate) {
        ProjectEntite existingProject = projectRepository.findProjectEntiteByIdentifiant(projectToUpdate.getIdentifiant());
        if (existingProject == null) {
            throw new NoSuchElementException("Aucun projet trouvé avec l'identifiant : " + projectToUpdate.getIdentifiant());
        }

        existingProject.setTitre(projectToUpdate.getTitre());
        existingProject.setDateDerniereModification(projectToUpdate.getDateDerniereModification());
        existingProject.setPages(projectToUpdate.getPages());
        existingProject.setAvancementGlobal(projectToUpdate.getAvancementGlobal());

        // Mettre à jour les avancements de feuilles de route
        for (Page existingPage : existingProject.getPages()) {
            for (RubriquesPage existingRubrique : existingPage.getRubriquesPages()) {
                for (SousRubriquesPage existingSousRubrique : existingRubrique.getSousRubriquesPage()) {
                    // Mise à jour des avancements de feuilles de route pour chaque sous-rubrique
                    existingSousRubrique.setFeuilleDeRouteUXPage(existingSousRubrique.getFeuilleDeRouteUXPage());
                    existingSousRubrique.setFeuilleDeRouteFoncPage(existingSousRubrique.getFeuilleDeRouteFoncPage());
                    existingSousRubrique.setFeuilleDeRouteDevPage(existingSousRubrique.getFeuilleDeRouteDevPage());
                }
            }
        }

        ProjectEntite updatedEntite = projectRepository.save(existingProject);
        return projectMapper.toDomain(updatedEntite);
    }

    /**
     * Supprime un projet de la base de données en fonction de son identifiant.
     *
     * @param identifiant L'identifiant du projet à supprimer.
     * @throws NoSuchElementException Si le projet avec l'identifiant spécifié n'existe pas.
     */
    @Override
    public void deleteProject(String identifiant) {
        ProjectEntite entite = projectRepository.findProjectEntiteByIdentifiant(identifiant);
        if (entite == null) {
            throw new NoSuchElementException("Aucun projet trouvé avec l'identifiant : " + identifiant);
        }
        projectRepository.delete(entite);
    }

    @Override
    public Project updateAvancement(Project projectAvancement) {
        ProjectEntite existingProject = projectRepository.findProjectEntiteByIdentifiant(projectAvancement.getIdentifiant());
        if (existingProject == null) {
            throw new NoSuchElementException("Aucun projet trouvé avec l'identifiant : " + projectAvancement.getIdentifiant());
        }
        existingProject.setAvancementGlobal(projectAvancement.getAvancementGlobal());
        ProjectEntite updatedEntite = projectRepository.save(existingProject);
        return projectMapper.toDomain(updatedEntite);
    }
}
