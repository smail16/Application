package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.PratiqueFonc;
import com.soprasteria.osca.model.PratiquesFoncAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesFoncAPIMapper {
    PratiquesFoncAPI toApi(PratiqueFonc pratiquesFonc);

    List<PratiquesFoncAPI> toApiList(List<PratiqueFonc> pratiquesFoncList);
}
