package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.FeuilleDeRouteFonc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteFoncEntiteMapper {
    FeuilleDeRouteFonc toDomain(FeuilleDeRouteFoncEntite entite);

    List<FeuilleDeRouteFonc> toDomainList(List<FeuilleDeRouteFoncEntite> entiteList);

}
