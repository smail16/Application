package com.soprasteria.osca.controller.page;

import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.model.PageAPI;
import com.soprasteria.osca.model.PageToCreate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PageAPIMapper {
    PageAPI toApi(Page page);

    List<PageAPI> toApiList(List<Page> pageList);

    Page toDomain(PageToCreate pageToCreate);

    Page toDomain(PageAPI pageAPI);

    List<Page> toDomainList(List<PageToCreate> pageToCreateList);
}
