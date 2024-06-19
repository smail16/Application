package com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.EtatEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteresDevPage {

    private String keyCritereDev;
    private EtatEnum etat;
}
