package com.soprasteria.osca.domain.project;

import java.util.List;

public interface ProjectPersistencePort {
    Project createProject(Project project);

    List<Project> findAllProjects();

    Project findProjectByIdentifiant(String idProject);

    Project updateProject(Project project);

    Project updateAvancement(Project project);

    void deleteProject(String identifiant);
}
