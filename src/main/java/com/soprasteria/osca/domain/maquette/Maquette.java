package com.soprasteria.osca.domain.maquette;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Maquette {

    private String identifiant;
    private String filename;
    private String fileType;
    private String fileSize;
    private String keySousRubrique;
    private String projectId;
    private String titrePage;
    private byte[] maquette;

}
