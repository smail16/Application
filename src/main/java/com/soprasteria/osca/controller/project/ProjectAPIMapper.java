package com.soprasteria.osca.controller.project;

import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.model.ProjectAPI;
import com.soprasteria.osca.model.ProjectToCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProjectAPIMapper {

    @Mapping(target = "dateDerniereModification", source = "project.dateDerniereModification", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "dateCreation", source = "project.dateCreation", dateFormat = "dd/MM/yyyy")
    ProjectAPI toApi(Project project);

    List<ProjectAPI> toApiList(List<Project> projectList);

    Project toDomain(ProjectToCreate projectToCreate);

    @Mapping(target = "dateDerniereModification", source = "projectAPI.dateDerniereModification", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "dateCreation", source = "projectAPI.dateCreation", dateFormat = "dd/MM/yyyy")
    Project toDomain(ProjectAPI projectAPI);
}
