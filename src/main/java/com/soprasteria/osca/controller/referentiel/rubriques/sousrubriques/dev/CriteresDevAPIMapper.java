package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.CriteresDev;
import com.soprasteria.osca.model.CriteresDevAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresDevAPIMapper {
    CriteresDevAPI toApi(CriteresDev criteresDev);

    List<CriteresDevAPI> toApiList(List<CriteresDev> criteresDevList);

}
