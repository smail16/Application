package com.soprasteria.osca.domain.maquette;

import com.soprasteria.osca.domain.page.PageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MaquetteService {

    private final PageService pageService;
    private final MaquettePersistancePort maquettePersistancePort;
    /**
     * Constructeur du service.
     *
     * @param maquettePersistancePort Le port de persistance pour la gestion des fichiers.
     */
    public MaquetteService(PageService pageService, MaquettePersistancePort maquettePersistancePort) {
        this.pageService = pageService;
        this.maquettePersistancePort = maquettePersistancePort;
    }

    /**
     * ajouter une maquette.
     *
     * @param maquette        Le fichier à ajouter
     * @param keySousRubrique Le key de la sous-rubrique
     * @param projectId       L'identifiant du projet
     * @param titrePage       Le titre de la page
     * @return Le fichier ajouté.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'ajout du fichier
     */
    public Maquette addMaquette(MultipartFile maquette, String keySousRubrique, String projectId, String titrePage) throws IOException {

        Maquette savedMaquette =  maquettePersistancePort.addMaquette(maquette, keySousRubrique, projectId, titrePage);
        pageService.updateMaquette(savedMaquette);

        return savedMaquette;
    }

    /**
     * Récupère une maquette par son identifiant

     * @param identifiant L'identifiant de la maquette à récupérer
     * @return La maquette récupérée
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la récupération de la maquette
     */
    public Maquette getMaquetteByIdentifiant (String identifiant) throws IOException {
        return maquettePersistancePort.getMaquetteByIdentifiant(identifiant);
    }

    /**
     * Récupère la liste des maquettes associées à un projet par son identifiant
     *
     * @param identifiant     L'identifiant du projet pour lequel récupérer les maquettes.
     * @param keySousRubrique La clé de la sous-rubrique
     * @param titrepage       Le titre de la page
     * @return La liste des maquettes associées au projet
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la récupération des maquettes
     */
    public List<Maquette> getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(String identifiant, String keySousRubrique, String titrepage) throws IOException {
        return maquettePersistancePort.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(identifiant,keySousRubrique, titrepage);
    }

    /**
     * Supprime une maquette par son identifiant
     *
     * @param identifiant L'identifiant de la maquette à supprimer
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la suppression de la maquette.
     */
    public void deleteMaquette(String identifiant) throws IOException {
        Maquette maquetteToDelete = maquettePersistancePort.getMaquetteByIdentifiant(identifiant);

        // Supprimer la maquette de la page associée
        pageService.deleteMaquette(maquetteToDelete);

        // Supprimer la maquette de GridFS
        maquettePersistancePort.deleteMaquette(identifiant);

    }
}
