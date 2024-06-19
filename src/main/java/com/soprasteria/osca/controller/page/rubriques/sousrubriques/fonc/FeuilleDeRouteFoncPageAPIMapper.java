package com.soprasteria.osca.controller.page.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.FeuilleDeRouteFoncPage;
import com.soprasteria.osca.model.FeuilleDeRouteFoncPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FeuilleDeRouteFoncPageAPIMapper {
    FeuilleDeRouteFoncPageAPI toApi(FeuilleDeRouteFoncPage feuilleDeRouteFoncPage);

    List<FeuilleDeRouteFoncPageAPI> toApiList(List<FeuilleDeRouteFoncPage> feuilleDeRouteFoncPageList);

    FeuilleDeRouteFoncPage toDomain(FeuilleDeRouteFoncPageAPI feuilleDeRouteFoncPageAPI);

    List<FeuilleDeRouteFoncPage> toDomain(List<FeuilleDeRouteFoncPageAPI> feuilleDeRouteFoncPageAPIList);
}
