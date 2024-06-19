package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux.pratiques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.PratiquesUX;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesUXEntiteMapper {
    PratiquesUX toDomain(PratiquesUXEntite pratiquesUXEntite);

    List<PratiquesUX> toDomainList(List<PratiquesUXEntite> pratiquesUXEntiteList);

}
