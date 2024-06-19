package com.soprasteria.osca.persistence.referentiel.rubriques;

import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.SousRubriquesEntite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RubriquesEntite {

    private String titre;
    private String keyRubriques;
    private List<SousRubriquesEntite> sousRubriques;
}
