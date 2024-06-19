package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.CritereFonc;
import com.soprasteria.osca.model.CriteresFoncAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresFoncAPIMapper {
    CriteresFoncAPI toApi(CritereFonc critereFonc);

    List<CriteresFoncAPI> toApiList(List<CritereFonc> criteresFoncList);

}
