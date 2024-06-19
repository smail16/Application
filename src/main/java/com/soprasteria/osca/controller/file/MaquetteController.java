package com.soprasteria.osca.controller.file;

import com.soprasteria.osca.api.MaquettesApi;
import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.domain.maquette.MaquetteService;
import com.soprasteria.osca.model.MaquetteAPI;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
class MaquetteController implements MaquettesApi {

    private final MaquetteService maquetteService;

    /**
     * Constructeur du contrôleur.
     *
     * @param maquetteService Service de gestion des fichiers.
     */

    public MaquetteController(MaquetteService maquetteService) {
        this.maquetteService = maquetteService;
    }
    private final MaquetteAPIMapper maquetteAPIMapper = Mappers.getMapper(MaquetteAPIMapper.class);
    /**
     * Charger un fichier.
     *
     * @param file Le fichier à charger.
     * @param keySousRubrique La clé de la sous-rubrique.
     * @param projectId L'identifiant du projet.
     * @param titrePage Le titre de la page
     * @return ResponseEntity avec le statut HTTP 200 OK en cas de succès ou HTTP 500 Internal Server Error en cas d'erreur..
     */

    @Override
    public ResponseEntity<MaquetteAPI> addMaquette(@RequestPart MultipartFile file,
                                                   @RequestParam String keySousRubrique,
                                                   @RequestParam String projectId,
                                                   @RequestParam String titrePage) {
        try {
            Maquette maquette = maquetteService.addMaquette(file, keySousRubrique, projectId, titrePage);
            return ResponseEntity.ok().body(maquetteAPIMapper.toApi(maquette));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Récupère une maquette par son identifiant.
     *
     * @param identifiant L'identifiant de la maquette à récupérer.
     * @return ResponseEntity contenant la représentation de la maquette récupérée, avec le statut HTTP 200 OK en cas de succès ou HTTP 500 Internal Server Error en cas d'erreur.
     */
    @Override
    public ResponseEntity<MaquetteAPI> getMaquetteByIdentifiant(@PathVariable String identifiant) {
        try {
            Maquette maquette = maquetteService.getMaquetteByIdentifiant(identifiant);
            MaquetteAPI maquetteAPI = maquetteAPIMapper.toApi(maquette);
            return ResponseEntity.ok().body(maquetteAPI);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Récupère la liste des maquettes associées à un projet par son identifiant de projet.
     *
     * @param projectId L'identifiant du projet pour lequel récupérer les maquettes.
     * @param keySousRubrique La clé de la sous-rubrique.
     * @param titrePage Le titre de la page.
     * @return ResponseEntity contenant la liste des représentations des maquettes récupérées, avec le statut HTTP 200 OK en cas de succès ou HTTP 500 Internal Server Error en cas d'erreur.
     */
    @Override
    public ResponseEntity<List<MaquetteAPI>> getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(@PathVariable String projectId,
                                                                                                          @RequestParam String keySousRubrique,
                                                                                                          @RequestParam String titrePage) {
        try {

            List<Maquette> maquettes = maquetteService.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(projectId, keySousRubrique, titrePage);

            List<MaquetteAPI> maquetteAPIs = maquetteAPIMapper.toApiList(maquettes);

            return ResponseEntity.ok().body(maquetteAPIs);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Supprimer une maquette par son identifiant.
     *
     * @param identifiant L'identifiant de la maquette à supprimer.
     * @return ResponseEntity avec le statut HTTP 200 OK en cas de succès, ou HTTP 404 Not Found si la maquette n'est pas trouvée
     */
    @Override
    public ResponseEntity<Void> deleteMaquette(@PathVariable String identifiant) {

        try {
            maquetteService.deleteMaquette(identifiant);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


}
