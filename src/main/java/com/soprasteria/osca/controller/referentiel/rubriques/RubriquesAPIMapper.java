package com.soprasteria.osca.controller.referentiel.rubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.Rubriques;
import com.soprasteria.osca.model.RubriqueAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RubriquesAPIMapper {
    RubriqueAPI toApi(Rubriques rubriques);

    List<RubriqueAPI> toApiList(List<Rubriques> rubriquesList);
}
