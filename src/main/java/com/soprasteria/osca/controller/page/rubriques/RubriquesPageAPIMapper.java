package com.soprasteria.osca.controller.page.rubriques;

import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.model.RubriquesPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RubriquesPageAPIMapper {
    RubriquesPageAPI toApi(RubriquesPage rubriquesPage);

    List<RubriquesPageAPI> toApiList(List<RubriquesPage> rubriquesPages);

    RubriquesPage toDomain(RubriquesPageAPI rubriquesPageAPI);

    List<RubriquesPage> toDomainList(List<RubriquesPageAPI> rubriquesPageAPIList);
}
