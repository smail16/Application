package com.soprasteria.osca.controller.page.rubriques.sousrubriques.fonc;

import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.CriteresFoncPage;
import com.soprasteria.osca.model.CriteresFoncPageAPI;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CriteresFoncPageAPIMapper {
    CriteresFoncPageAPI toApi(CriteresFoncPage criteresFoncPage);

    List<CriteresFoncPageAPI> toApiList(List<CriteresFoncPage> criteresFoncPageList);

    CriteresFoncPage toDomain(CriteresFoncPageAPI criteresFoncPageAPI);

    List<CriteresFoncPageAPI> toDomainList(List<CriteresFoncPageAPI> criteresFoncPageAPIList);
}
