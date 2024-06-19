package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.FeuilleDeRouteUX;
import com.soprasteria.osca.model.FeuilleDeRouteUXAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteUXAPIMapper {
    FeuilleDeRouteUXAPI toApi(FeuilleDeRouteUX sousRubriqueUX);

    List<FeuilleDeRouteUXAPI> toApiList(List<FeuilleDeRouteUX> sousRubriqueUXList);
}
