package com.soprasteria.osca.controller.file;

import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.model.MaquetteAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MaquetteAPIMapper {

    MaquetteAPI toApi (Maquette maquette);

    List<MaquetteAPI> toApiList(List<Maquette> maquetteList);
}
