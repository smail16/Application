package com.soprasteria.osca.domain.referentiel;


public interface ReferentielPersistencePort {
    Referentiel findReferentielByVersion(String version);
}
