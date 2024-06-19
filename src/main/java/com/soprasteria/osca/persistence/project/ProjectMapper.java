package com.soprasteria.osca.persistence.project;

import com.soprasteria.osca.domain.project.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /**
     * Convertir une entité ProjectEntite en un objet de domaine Project avec mapstruct
     *
     * @param entite L'entité ProjectEntite à convertir.
     * @return L'objet Project de domaine correspondant.
     */
    Project toDomain(ProjectEntite entite);

    /**
     * Convertir une liste d'entités ProjectEntite en une liste d'objets de domaine Project.
     *
     * @param entiteList La liste d'entités ProjectEntite à convertir.
     * @return projectList La liste d'objets Project de domaine correspondants.
     */

    List<Project> toDomainList(List<ProjectEntite> entiteList);

    /**
     * Convertir un objet de domaine Project en une entité ProjectEntite avec mapStruct
     *
     * @param project L'objet Project de domaine à convertir.
     * @return L'entité ProjectEntite correspondante.
     */
    ProjectEntite toEntite(Project project);
}
