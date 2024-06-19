package com.soprasteria.osca.persistence.project;

import com.soprasteria.osca.domain.page.Page;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "projects")
public class ProjectEntite {

    @Id
    private String _id;

    private String identifiant;
    private String titre;
    private Date dateCreation;
    private Date dateDerniereModification;
    private Integer avancementGlobal;
    private List<Page> pages;
}
