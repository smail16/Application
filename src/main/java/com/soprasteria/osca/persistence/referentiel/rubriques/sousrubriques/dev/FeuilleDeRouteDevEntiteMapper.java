package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.FeuilleDeRouteDev;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteDevEntiteMapper {
    FeuilleDeRouteDev toDomain(FeuilleDeRouteDevEntite entite);

    List<FeuilleDeRouteDev> toDomainList(List<FeuilleDeRouteDevEntite> entiteList);

}
