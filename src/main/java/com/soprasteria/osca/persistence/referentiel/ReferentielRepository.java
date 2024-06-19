package com.soprasteria.osca.persistence.referentiel;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReferentielRepository extends MongoRepository<ReferentielEntite, String> {
    ReferentielEntite findReferentielByVersion(String version);

}

