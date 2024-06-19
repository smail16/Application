package com.soprasteria.osca.persistence.referentiel.rubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.Rubriques;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RubriquesEntiteMapper {
    Rubriques toDomain(RubriquesEntite entite);

    List<Rubriques> toDomainList(List<RubriquesEntite> entiteList);
}
