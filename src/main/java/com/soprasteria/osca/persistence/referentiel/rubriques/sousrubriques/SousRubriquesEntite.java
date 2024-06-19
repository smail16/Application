package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques;

import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev.FeuilleDeRouteDevEntite;
import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc.FeuilleDeRouteFoncEntite;
import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux.FeuilleDeRouteUXEntite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SousRubriquesEntite {

    private String titre;
    private String keySousRubriques;
    private List<FeuilleDeRouteUXEntite> sousRubriqueUx;
    private List<FeuilleDeRouteFoncEntite> sousRubriqueFonctionnelle;
    private List<FeuilleDeRouteDevEntite> sousRubriqueDeveloppement;
}
