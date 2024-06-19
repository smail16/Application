package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.SousRubriques;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SousRubriquesEntiteMapper {
    SousRubriques toDomain(SousRubriquesEntite entite);

    List<SousRubriques> toDomainList(List<SousRubriquesEntite> entiteList);

}
