package com.soprasteria.osca.domain.referentiel;

import com.soprasteria.osca.domain.referentiel.rubriques.Rubriques;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Referentiel {

    @Getter
    @Setter
    private String version;
    private List<Rubriques> rubriques;

}
