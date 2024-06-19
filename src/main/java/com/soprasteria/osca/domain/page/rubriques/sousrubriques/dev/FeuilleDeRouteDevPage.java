package com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeuilleDeRouteDevPage {

    private String keyFeuilleDeRouteDevPage;
    private List<CriteresDevPage> criteresDevPage;
    private Integer avancementDev;
}
