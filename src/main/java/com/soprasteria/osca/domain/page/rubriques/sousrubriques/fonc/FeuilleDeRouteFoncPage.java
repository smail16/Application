package com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteFoncPage {

    private String keyFeuilleDeRouteFoncPage;
    private List<CriteresFoncPage> criteresFoncPage;
    private Integer avancementFonc;

}
