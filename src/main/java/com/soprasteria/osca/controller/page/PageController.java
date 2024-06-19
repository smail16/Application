package com.soprasteria.osca.controller.page;

import com.soprasteria.osca.api.PagesApi;
import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.PageService;
import com.soprasteria.osca.model.PageAPI;
import com.soprasteria.osca.model.PageToCreate;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PageController implements PagesApi {

    private final PageService pageService;
    private final PageAPIMapper pageAPIMapper = Mappers.getMapper(PageAPIMapper.class);

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    /**
     * Crée une nouvelle page avec les informations fournies dans la requête POST
     *
     * @param pageToCreate Informations nécessaires pour la création de la page
     * @return ResponseEntity contenant la PageAPI créée en cas de succès, sinon une ResponseEntity avec HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<PageAPI> createPage(@RequestBody PageToCreate pageToCreate) {
        try {
            Page page = pageAPIMapper.toDomain(pageToCreate);
            Page createdPage = pageService.createPage(
                    pageToCreate.getIdentifiantProject(),
                    pageToCreate.getTitrePage(),
                    pageToCreate.getVersion()
            );
            PageAPI responsePageAPI = pageAPIMapper.toApi(createdPage);
            return new ResponseEntity<>(responsePageAPI, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Met à jour une page dans un projet spécifique avec les données fournies.
     *
     * @param identifiantProject L'identifiant du projet contenant la page à mettre à jour.
     * @param titrePage          Le titre de la page à mettre à jour.
     * @param avancementGlobal   Le nouvel avancement global de la page.
     * @param keySousRubrique    La clé de la sous-rubrique à laquelle appartient la page.
     * @param avancementUX       Le nouvel avancement UX de la sous-rubrique
     * @param avancementFonc     Le nouvel avancement Fonc de la sous-rubrique
     * @param avancementDev      Le nouvel avancement Dev de la sous-rubrique
     * @param updatedPageAPI     Les données mises à jour de la page
     * @return ResponseEntity contenant l'objet PageAPI représentant la page mise à jour et un code de statut HTTP approprié.
     * - HttpStatus OK (200) si la page a été mise à jour avec succès.
     * - HttpStatus NOT_FOUND (404) si la page ou le projet associé n'a pas été trouvé.
     * - HttpStatus BAD_REQUEST (400) si la requête est incorrecte ou les données sont invalides.
     */
    @Override
    public ResponseEntity<PageAPI> updatePage(
            @PathVariable String identifiantProject,
            @PathVariable String titrePage,
            @RequestParam Integer avancementGlobal,
            @RequestParam String keySousRubrique,
            @RequestParam Integer avancementUX,
            @RequestParam Integer avancementFonc,
            @RequestParam Integer avancementDev,
            @RequestBody PageAPI updatedPageAPI
    ) {
        try {
            Page updatedPage = pageAPIMapper.toDomain(updatedPageAPI);
            Page updatedPageResult = pageService.updatePage(
                    identifiantProject,
                    titrePage,
                    updatedPage,
                    avancementGlobal,
                    avancementUX,
                    avancementDev,
                    avancementFonc,
                    keySousRubrique
            );
            PageAPI responsePageAPI = pageAPIMapper.toApi(updatedPageResult);

            return ResponseEntity.ok(responsePageAPI);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Supprime une page spécifique d'un projet en fonction de son identifiant et de son titre.
     *
     * @param identifiant l'identifiant du projet à partir duquel la page doit être supprimée.
     * @param titrePage   le titre de la page à supprimer.
     * @return ResponseEntity contenant un message indiquant que la page a été supprimée avec succès.
     */
    @Override
    public ResponseEntity<String> deletePage(@RequestParam("identifiant") String identifiant, @RequestParam("titrePage") String titrePage) {
        // Appelle la méthode pour supprimer la page spécifiée du projet
        pageService.deletePage(identifiant, titrePage);

        // indique que la page a été supprimée avec succès
        return ResponseEntity.ok("Page supprimée");
    }
}
