package com.soprasteria.osca.controller.referentiel.rubriques.sousrubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.SousRubriques;
import com.soprasteria.osca.model.SousRubriqueAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SousRubriquesAPIMapper {
    SousRubriqueAPI toApi(SousRubriques sousRubriques);

    List<SousRubriqueAPI> toApiList(List<SousRubriques> sousRubriquesList);
}
