package com.soprasteria.osca.controller.page.rubriques.sousrubriques.ux;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.FeuilleDeRouteUXPage;
import com.soprasteria.osca.model.FeuilleDeRouteUXPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteUXPageAPIMapper {
    FeuilleDeRouteUXPageAPI toApi(FeuilleDeRouteUXPage feuilleDeRouteUXPage);

    List<FeuilleDeRouteUXPageAPI> toApiList(List<FeuilleDeRouteUXPage> feuilleDeRouteUXPageList);

    FeuilleDeRouteUXPage toDomain(FeuilleDeRouteUXPageAPI feuilleDeRouteUXPageAPI);

    List<FeuilleDeRouteUXPage> toDomain(List<FeuilleDeRouteUXPageAPI> feuilleDeRouteUXPageAPIList);
}
