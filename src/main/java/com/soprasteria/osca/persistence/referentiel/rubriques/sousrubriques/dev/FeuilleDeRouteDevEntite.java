package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev;

import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev.criteres.CriteresDevEntite;
import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.dev.pratiques.PratiquesDevEntite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteDevEntite {

    private String titre;
    private String keyFeuilleDeRouteDev;
    private List<CriteresDevEntite> criteresDevList;
    private List<PratiquesDevEntite> pratiquesDevList;
}
