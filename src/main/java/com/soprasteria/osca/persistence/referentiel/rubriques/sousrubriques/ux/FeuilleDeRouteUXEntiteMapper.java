package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.FeuilleDeRouteUX;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteUXEntiteMapper {
    FeuilleDeRouteUX toDomain(FeuilleDeRouteUXEntite entite);

    List<FeuilleDeRouteUX> toDomainList(List<FeuilleDeRouteUXEntite> entiteList);

}
