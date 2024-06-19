package com.soprasteria.osca.persistence.referentiel;

import com.soprasteria.osca.persistence.referentiel.rubriques.RubriquesEntite;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "refRGAA")
public class ReferentielEntite {

    @Id
    private String _id;

    private String version;
    private List<RubriquesEntite> rubriques;
}
