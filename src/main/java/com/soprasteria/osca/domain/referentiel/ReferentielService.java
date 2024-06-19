package com.soprasteria.osca.domain.referentiel;

import org.springframework.stereotype.Service;

@Service
public class ReferentielService {
    private final ReferentielPersistencePort referentielPersistencePort;

    public ReferentielService(ReferentielPersistencePort referentielPersistencePort) {
        this.referentielPersistencePort = referentielPersistencePort;
    }

    public Referentiel findByVersion(String version) {
        return referentielPersistencePort.findReferentielByVersion(version);
    }
}
