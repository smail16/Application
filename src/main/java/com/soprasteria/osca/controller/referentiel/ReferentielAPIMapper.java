package com.soprasteria.osca.controller.referentiel;

import com.soprasteria.osca.domain.referentiel.Referentiel;
import com.soprasteria.osca.model.ReferentielAPI;
import org.mapstruct.Mapper;

@Mapper
public interface ReferentielAPIMapper {
    ReferentielAPI toApi(Referentiel referentiel);
}
