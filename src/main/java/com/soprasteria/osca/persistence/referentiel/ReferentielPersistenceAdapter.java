package com.soprasteria.osca.persistence.referentiel;

import com.soprasteria.osca.domain.referentiel.Referentiel;
import com.soprasteria.osca.domain.referentiel.ReferentielPersistencePort;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

@Repository
public class ReferentielPersistenceAdapter implements ReferentielPersistencePort {

    private final ReferentielEntiteMapper referentielEntiteMapper = Mappers.getMapper(ReferentielEntiteMapper.class);
    private final ReferentielRepository referentielRepository;

    public ReferentielPersistenceAdapter(ReferentielRepository referentielRepository) {
        this.referentielRepository = referentielRepository;
    }

    @Override
    public Referentiel findReferentielByVersion(String version) {
        ReferentielEntite entite = referentielRepository.findReferentielByVersion(version);
        return referentielEntiteMapper.toDomain(entite);
    }
}
