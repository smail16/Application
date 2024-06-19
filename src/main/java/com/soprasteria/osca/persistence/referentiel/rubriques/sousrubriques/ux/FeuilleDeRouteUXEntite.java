package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux;

import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux.criteres.CriteresUXEntite;
import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.ux.pratiques.PratiquesUXEntite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteUXEntite {

    private String titre;
    private String keyFeuilleDeRouteUX;
    private List<CriteresUXEntite> criteresUXList;
    private List<PratiquesUXEntite> pratiquesUXList;
}
