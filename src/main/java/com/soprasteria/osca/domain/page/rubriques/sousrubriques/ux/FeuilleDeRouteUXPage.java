package com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteUXPage {

    private String keyFeuilleDeRouteUXPage;
    private List<CriteresUXPage> criteresUXPage;
    private Integer avancementUX;

}
