package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.PratiquesDev;
import com.soprasteria.osca.model.PratiquesDevAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesDevAPIMapper {
    PratiquesDevAPI toApi(PratiquesDev pratiquesDev);

    List<PratiquesDevAPI> toApiList(List<PratiquesDev> pratiquesDevList);

}
