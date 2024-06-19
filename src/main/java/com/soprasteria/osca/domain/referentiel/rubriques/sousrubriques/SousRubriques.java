package com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques;

import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.dev.FeuilleDeRouteDev;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.fonc.FeuilleDeRouteFonc;
import com.soprasteria.osca.domain.referentiel.rubriques.sousrubriques.ux.FeuilleDeRouteUX;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SousRubriques {

    private String titre;
    private String keySousRubriques;
    private List<FeuilleDeRouteUX> sousRubriqueUx;
    private List<FeuilleDeRouteFonc> sousRubriqueFonctionnelle;
    private List<FeuilleDeRouteDev> sousRubriqueDeveloppement;
}
