package com.soprasteria.osca.controller.page.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.CriteresUXPage;
import com.soprasteria.osca.model.CriteresUXPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresUXPageAPIMapper {
    CriteresUXPageAPI toApi(CriteresUXPage criteresUXPage);

    List<CriteresUXPageAPI> toApiList(List<CriteresUXPage> criteresUXPageList);

    CriteresUXPage toDomain(CriteresUXPageAPI criteresUXPageAPI);

    List<CriteresUXPageAPI> toDomainList(List<CriteresUXPageAPI> criteresUXPageAPIList);
}
