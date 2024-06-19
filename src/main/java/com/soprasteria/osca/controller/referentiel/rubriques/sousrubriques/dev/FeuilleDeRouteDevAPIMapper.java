package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.FeuilleDeRouteDev;
import com.soprasteria.osca.model.FeuilleDeRouteDevAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteDevAPIMapper {
    FeuilleDeRouteDevAPI toApi(FeuilleDeRouteDev dev);

    List<FeuilleDeRouteDevAPI> toApiList(List<FeuilleDeRouteDev> devList);
}
