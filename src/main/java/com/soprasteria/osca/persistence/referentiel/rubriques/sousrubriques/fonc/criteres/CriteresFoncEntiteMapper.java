package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc.criteres;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.CritereFonc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresFoncEntiteMapper {
    CritereFonc toDomain(CriteresFoncEntite criteresFoncEntite);

    List<CritereFonc> toDomainList(List<CriteresFoncEntite> criteresFoncEntiteList);
}
