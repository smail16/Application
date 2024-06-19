package com.soprasteria.osca.domain.project;

import com.soprasteria.osca.domain.page.Page;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Project {

    private String identifiant;

    @Size(min = 0, max = 50)
    private String titre;

    private Date dateCreation;
    private Date dateDerniereModification;
    private Integer avancementGlobal;

    @Size(max = 30)
    private List<Page> pages;
}
