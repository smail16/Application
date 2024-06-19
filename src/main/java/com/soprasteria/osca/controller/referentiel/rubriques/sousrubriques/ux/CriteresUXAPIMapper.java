package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.CriteresUX;
import com.soprasteria.osca.model.CriteresUXAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresUXAPIMapper {
    CriteresUXAPI toApi(CriteresUX criteresUX);

    List<CriteresUXAPI> toApiList(List<CriteresUX> criteresUXList);
}

