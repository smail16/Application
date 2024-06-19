package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev.pratiques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.PratiquesDev;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesDevEntiteMapper {
    PratiquesDev toDomain(PratiquesDevEntite pratiquesDevEntite);

    List<PratiquesDev> toDomainList(List<PratiquesDevEntite> pratiquesDevEntiteList);
}
