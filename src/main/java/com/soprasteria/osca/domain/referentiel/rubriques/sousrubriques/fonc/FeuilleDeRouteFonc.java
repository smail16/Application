package com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteFonc {

    private String titre;
    private String keyFeuilleDeRouteFonc;
    private List<CritereFonc> criteresFoncList;
    private List<PratiqueFonc> pratiquesFoncList;
}
