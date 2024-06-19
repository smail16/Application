package com.soprasteria.osca.domain.page;

import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page {
    private String titrePage;
    private List<RubriquesPage> rubriquesPages;
}
