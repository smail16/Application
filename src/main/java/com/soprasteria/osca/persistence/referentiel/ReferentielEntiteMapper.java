package com.soprasteria.osca.persistence.referentiel;

import com.soprasteria.osca.domain.referentiel.Referentiel;
import org.mapstruct.Mapper;

@Mapper
public interface ReferentielEntiteMapper {
    Referentiel toDomain(ReferentielEntite entite);
}
