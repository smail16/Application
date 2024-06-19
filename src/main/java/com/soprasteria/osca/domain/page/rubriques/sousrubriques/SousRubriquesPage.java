package com.soprasteria.osca.domain.page.rubriques.sousrubriques;

import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.FeuilleDeRouteDevPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.FeuilleDeRouteFoncPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.FeuilleDeRouteUXPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SousRubriquesPage {

    private String keySousRubriquesPage;
    private List<FeuilleDeRouteDevPage> feuilleDeRouteDevPage;
    private List<FeuilleDeRouteUXPage> feuilleDeRouteUXPage;
    private List<FeuilleDeRouteFoncPage> feuilleDeRouteFoncPage;
    private List<Maquette> maquette;

}
