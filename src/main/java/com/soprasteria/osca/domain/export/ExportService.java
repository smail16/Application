package com.soprasteria.osca.domain.export;

import com.soprasteria.osca.domain.page.Page;
import com.soprasteria.osca.domain.page.rubriques.RubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.EtatEnum;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.SousRubriquesPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.dev.CriteresDevPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.fonc.CriteresFoncPage;
import com.soprasteria.osca.domain.page.rubriques.sousrubriques.ux.CriteresUXPage;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.referentiel.Referentiel;
import com.soprasteria.osca.domain.referentiel.ReferentielService;
import com.soprasteria.shared.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Service pour l'exportation des projets.
 */
@Service
public class ExportService {

    private static final String SEPARATOR = "|";
    private static final String SAUT_DE_LIGNE = "\n";
    private static final String NOM_DU_PROJET = "Nom du projet";
    private static final String DATE_CREATION = "Date de création";
    private static final String AVANCEMENT_GLOBAL = "Avancement global";
    private static final String PAGE = "Page";
    private static final String LISTE_DETAILLEE = "Liste détaillée";
    private static final String RUBRIQUE = "Rubrique";
    private static final String SOUS_RUBRIQUE = "Sous-rubrique";
    private static final String ACTION = "Action";
    private static final String STATUT = "Statut";
    private static final String FAIT = "Fait";
    private static final String NON_APPLICABLE = "Non applicable";

    private final ReferentielService referentielService;

    /**
     * Constructeur du service d'export.
     *
     * @param referentielService Service de gestion des référentiels.
     */
    @Autowired
    public ExportService(ReferentielService referentielService) {
        this.referentielService = referentielService;
    }

    /**
     * Exporte les données d'un projet au format CSV.
     *
     * @param project Le projet à exporter.
     * @return Les données du projet au format CSV sous forme de chaîne de caractères.
     */
    public String exportProject(Project project) {
        StringWriter stringWriter = new StringWriter();

        // Charger le référentiel
        Referentiel referentiel = referentielService.findByVersion("4.2");

        Map<String, String> rubriquesMap = new HashMap<>();
        Map<String, String> sousRubriquesMap = new HashMap<>();
        Map<String, String> criteresUXMap = new HashMap<>();
        Map<String, String> criteresFoncMap = new HashMap<>();
        Map<String, String> criteresDevMap = new HashMap<>();

        // Récupération du référentiel dans une HashMap
        transformRefInHashMap(referentiel, rubriquesMap, sousRubriquesMap, criteresUXMap, criteresFoncMap, criteresDevMap);

        // Exporter les données du projet
        genererInfosProjet(stringWriter, project);

        // Exporter les données des pages
        for (Page page : project.getPages()) {
            // Infos de la page
            genererInfosPage(stringWriter, page);

            for (RubriquesPage rubrique : page.getRubriquesPages()) {
                String rubriqueTitle = rubriquesMap.get(rubrique.getKeyRubriquesPage());
                for (SousRubriquesPage sousRubrique : rubrique.getSousRubriquesPage()) {
                    String sousRubriqueTitle = sousRubriquesMap.get(sousRubrique.getKeySousRubriquesPage());
                    // Vérifier et ajouter les Feuille de Route UX
                    if (!sousRubrique.getFeuilleDeRouteUXPage().isEmpty()) {
                        for (CriteresUXPage critereUX : sousRubrique.getFeuilleDeRouteUXPage().get(0).getCriteresUXPage()) {
                            String critereUXTitle = criteresUXMap.get(critereUX.getKeyCritereUX());
                            stringWriter.append(rubriqueTitle).append(SEPARATOR)
                                .append(sousRubriqueTitle).append(SEPARATOR)
                                .append(critereUXTitle).append(SEPARATOR)
                                .append(convertEtat(critereUX.getEtat()))
                                .append(SAUT_DE_LIGNE);
                        }
                    }
                    // Vérifier et ajouter les Feuille de Route Fonc
                    if (!sousRubrique.getFeuilleDeRouteFoncPage().isEmpty()) {
                        for (CriteresFoncPage critereFonc : sousRubrique.getFeuilleDeRouteFoncPage().get(0).getCriteresFoncPage()) {
                            String critereFoncTitle = criteresFoncMap.getOrDefault(critereFonc.getKeyCritereFonc(), critereFonc.getKeyCritereFonc());
                            stringWriter.append(rubriqueTitle).append(SEPARATOR)
                                .append(sousRubriqueTitle).append(SEPARATOR)
                                .append(critereFoncTitle).append(SEPARATOR)
                                .append(convertEtat(critereFonc.getEtat()))
                                .append(SAUT_DE_LIGNE);
                        }
                    }
                    // Vérifier et ajouter les Feuille de Route Dev
                    if (!sousRubrique.getFeuilleDeRouteDevPage().isEmpty()) {
                        for (CriteresDevPage critereDev : sousRubrique.getFeuilleDeRouteDevPage().get(0).getCriteresDevPage()) {
                            String critereDevTitle = criteresDevMap.getOrDefault(critereDev.getKeyCritereDev(), critereDev.getKeyCritereDev());
                            stringWriter.append(rubriqueTitle).append(SEPARATOR)
                                .append(sousRubriqueTitle).append(SEPARATOR)
                                .append(critereDevTitle).append(SEPARATOR)
                                .append(convertEtat(critereDev.getEtat()))
                                .append(SAUT_DE_LIGNE);
                        }
                    }
                }
            }
            stringWriter.append(SAUT_DE_LIGNE);
        }

        return stringWriter.toString();
    }

    /**
     * Convertir l'état de l'énumeration en une chaîne de caractères.
     *
     * @param etat L'état de l'énumération.
     * @return La chaîne de caractères correspondant à l'état.
     */
    private String convertEtat(EtatEnum etat) {
        switch (etat) {
            case TRAITE:
                return FAIT;
            case NONAPPLICABLE:
                return NON_APPLICABLE;
            case NONTRAITE:
            default:
                return "";
        }
    }

    private void transformRefInHashMap(Referentiel referentiel, Map<String, String> rubriquesMap, Map<String, String> sousRubriquesMap, Map<String, String> criteresUXMap, Map<String, String> criteresFoncMap, Map<String, String> criteresDevMap) {
        referentiel.getRubriques().forEach(rubrique -> {
            rubriquesMap.put(rubrique.getKeyRubriques(), rubrique.getTitre());
            rubrique.getSousRubriques().forEach(sousRubrique -> {
                sousRubriquesMap.put(sousRubrique.getKeySousRubriques(), sousRubrique.getTitre());
                sousRubrique.getSousRubriqueUx().forEach(feuilleDeRouteUX -> {
                    feuilleDeRouteUX.getCriteresUXList().forEach(critereUX ->
                        criteresUXMap.put(critereUX.getKeyCritereUX(), critereUX.getKeyCritereUX() + " " + critereUX.getCritereUX())
                    );
                });
                sousRubrique.getSousRubriqueFonctionnelle().forEach(feuilleDeRouteFonc -> {
                    feuilleDeRouteFonc.getCriteresFoncList().forEach(critereFonc ->
                        criteresFoncMap.put(critereFonc.getKeyCritereFonc(), critereFonc.getKeyCritereFonc() + " " + critereFonc.getCritereFonc())
                    );
                });
                sousRubrique.getSousRubriqueDeveloppement().forEach(feuilleDeRouteDev -> {
                    feuilleDeRouteDev.getCriteresDevList().forEach(critereDev ->
                        criteresDevMap.put(critereDev.getKeyCritereDev(), critereDev.getKeyCritereDev() + " " + critereDev.getCritereDev())
                    );
                });
            });
        });
    }

    private void genererInfosProjet(StringWriter stringWriter, Project project) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);

        stringWriter.append(NOM_DU_PROJET).append(SEPARATOR)
            .append(project.getTitre())
            .append(SAUT_DE_LIGNE)

            .append(DATE_CREATION).append(SEPARATOR)
            .append(dateFormatter.format(DateUtils.convertToLocalDate(project.getDateCreation())))
            .append(SAUT_DE_LIGNE)

            .append(AVANCEMENT_GLOBAL).append(SEPARATOR)
            .append(project.getAvancementGlobal().toString()).append(" %")
            .append(SAUT_DE_LIGNE)

            .append(SAUT_DE_LIGNE);
    }

    private void genererInfosPage(StringWriter stringWriter, Page page) {
        stringWriter.append(PAGE).append(SEPARATOR)
            .append(page.getTitrePage())
            .append(SAUT_DE_LIGNE)

            .append(LISTE_DETAILLEE)
            .append(SAUT_DE_LIGNE)

            .append(RUBRIQUE).append(SEPARATOR)
            .append(SOUS_RUBRIQUE).append(SEPARATOR)
            .append(ACTION).append(SEPARATOR)
            .append(STATUT)
            .append(SAUT_DE_LIGNE);
    }
}
