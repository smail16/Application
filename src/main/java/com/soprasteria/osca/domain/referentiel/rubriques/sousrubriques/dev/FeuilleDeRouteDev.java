package com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteDev {

    private String titre;
    private String keyFeuilleDeRouteDev;
    private List<CriteresDev> criteresDevList;
    private List<PratiquesDev> pratiquesDevList;
}
