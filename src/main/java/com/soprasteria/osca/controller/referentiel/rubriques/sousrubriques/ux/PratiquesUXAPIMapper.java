package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.PratiquesUX;
import com.soprasteria.osca.model.PratiquesUXAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesUXAPIMapper {
    PratiquesUXAPI toApi(PratiquesUX pratiquesUX);

    List<PratiquesUXAPI> toApiList(List<PratiquesUX> pratiquesUXList);

}
