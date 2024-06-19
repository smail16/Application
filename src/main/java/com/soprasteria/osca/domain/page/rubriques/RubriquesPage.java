package com.soprasteria.osca.domain.page.rubriques;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RubriquesPage {
    private String keyRubriquesPage;
    private List<SousRubriquesPage> sousRubriquesPage;
}
