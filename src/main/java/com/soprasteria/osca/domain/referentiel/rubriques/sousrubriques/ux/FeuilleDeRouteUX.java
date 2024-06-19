package com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteUX {

    private String titre;
    private String keyFeuilleDeRouteUX;
    private List<CriteresUX> criteresUXList;
    private List<PratiquesUX> pratiquesUXList;
}
