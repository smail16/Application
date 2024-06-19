package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc.pratiques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.PratiqueFonc;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PratiquesFoncEntiteMapper {
    PratiqueFonc toDomain(PratiquesFoncEntite pratiquesFoncEntite);

    List<PratiqueFonc> toDomainList(List<PratiquesFoncEntite> pratiquesFoncEntiteList);

}
