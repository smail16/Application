package com.soprasteria.osca.domain.maquette;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaquettePersistancePort {


    Maquette addMaquette(MultipartFile maquette, String keySousRubrique, String projectId, String titrePage) throws IOException;

    Maquette getMaquetteByIdentifiant (String identifiant) throws IOException;

    List<Maquette> getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage (String identifiant, String keySousRubrique, String titrePage) throws IOException;

    Maquette deleteMaquette (String identifiant);

}
