package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux.criteres;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.CriteresUX;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresUXEntiteMapper {
    CriteresUX toDomain(CriteresUXEntite criteresUXEntite);

    List<CriteresUX> toDomainList(List<CriteresUXEntite> criteresUXEntiteList);
}
