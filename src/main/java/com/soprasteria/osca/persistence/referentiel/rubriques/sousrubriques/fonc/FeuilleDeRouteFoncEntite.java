package com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc.criteres.CriteresFoncEntite;
import com.soprasteria.osca.persistence.referentiel.rubriques.sousrubriques.fonc.pratiques.PratiquesFoncEntite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteFoncEntite {

    private String titre;
    private String keyFeuilleDeRouteFonc;
    private List<CriteresFoncEntite> criteresFoncList;
    private List<PratiquesFoncEntite> pratiquesFoncList;
}
