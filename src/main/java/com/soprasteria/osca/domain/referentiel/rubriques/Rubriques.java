package com.soprasteria.osca.domain.referentiel.rubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.SousRubriques;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Rubriques {

    private String titre;
    private String keyRubriques;
    private List<SousRubriques> sousRubriques;
}
