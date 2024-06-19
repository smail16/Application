package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev.criteres;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.CriteresDev;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresDevEntiteMapper {
    CriteresDev toDomain(CriteresDevEntite criteresDevEntite);

    List<CriteresDev> toDomainList(List<CriteresDevEntite> criteresDevEntiteList);
}
