package com.soprasteria.osca.persistence.file;

import com.soprasteria.osca.domain.maquette.Maquette;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface MaquetteMapper {

    /**
     * Convertir une entité maquetteEntite en un objet de domaine maquette.
     *
     * @param entite L'entité maquetteEntite à convertir.
     * @return L'objet maquette de domaine correspondant.
     */
    Maquette toDomain(MaquetteEntite entite);
    /**
     * Convertir une liste d'entités maquetteEntite en une liste d'objets de domaine maquette.
     *
     * @param entiteList La liste d'entités maquetteEntite à convertir.
     * @return La liste d'objets Maquette de domaine correspondants.
     */
    List<Maquette> toDomainList(List<MaquetteEntite> entiteList);

}
