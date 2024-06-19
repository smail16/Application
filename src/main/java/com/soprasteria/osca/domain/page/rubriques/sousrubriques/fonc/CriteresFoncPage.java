package com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.EtatEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteresFoncPage {

    private String keyCritereFonc;
    private EtatEnum etat;
}
