package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.FeuilleDeRouteFonc;
import com.soprasteria.osca.model.FeuilleDeRouteFoncAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteAPIMapperFonc {
    FeuilleDeRouteFoncAPI toApi(FeuilleDeRouteFonc sousRubriqueFonc);

    List<FeuilleDeRouteFoncAPI> toApiList(List<FeuilleDeRouteFonc> sousRubriqueFoncList);
}
