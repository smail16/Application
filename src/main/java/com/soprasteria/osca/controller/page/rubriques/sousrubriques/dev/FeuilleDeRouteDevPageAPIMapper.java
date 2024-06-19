package com.soprasteria.osca.controller.page.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.FeuilleDeRouteDevPage;
import com.soprasteria.osca.model.FeuilleDeRouteDevPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteDevPageAPIMapper {

    FeuilleDeRouteDevPageAPI toApi(FeuilleDeRouteDevPage feuilleDeRouteDevPage);

    List<FeuilleDeRouteDevPageAPI> toApiList(List<FeuilleDeRouteDevPage> feuilleDeRouteDevPageList);

    FeuilleDeRouteDevPage toDomain(FeuilleDeRouteDevPageAPI feuilleDeRouteDevPageAPI);

    List<FeuilleDeRouteDevPage> toDomain(List<FeuilleDeRouteDevPageAPI> feuilleDeRouteDevPageAPIList);
}
