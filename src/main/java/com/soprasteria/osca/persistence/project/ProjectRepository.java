package com.soprasteria.osca.persistence.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<ProjectEntite, String> {
    ProjectEntite findProjectEntiteByIdentifiant(String idProject);

}
