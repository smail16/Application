package com.soprasteria.osca.controller.page.rubriques.sousrubriques.dev;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.CriteresDevPage;
import com.soprasteria.osca.model.CriteresDevPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresDevPageAPIMapper {

    CriteresDevPageAPI toApi(CriteresDevPage criteresDevPage);

    List<CriteresDevPageAPI> toApiList(List<CriteresDevPage> criteresDevPageList);

    CriteresDevPage toDomain(CriteresDevPageAPI criteresDevPageAPI);

    List<CriteresDevPageAPI> toDomainList(List<CriteresDevPageAPI> criteresDevPageAPIList);
}
