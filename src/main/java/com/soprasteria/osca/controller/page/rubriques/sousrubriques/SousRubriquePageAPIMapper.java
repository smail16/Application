package com.soprasteria.osca.controller.page.rubriques.sousrubriques;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import com.soprasteria.osca.model.SousRubriquePageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SousRubriquePageAPIMapper {
    SousRubriquePageAPI toApi(SousRubriquesPage sousRubriquesPage);

    List<SousRubriquePageAPI> toApiList(List<SousRubriquesPage> sousRubriquesPages);

    SousRubriquesPage toDomain(SousRubriquePageAPI sousRubriquePageAPI);

    List<SousRubriquesPage> toDomainList(List<SousRubriquePageAPI> sousRubriquePageAPIList);

}
