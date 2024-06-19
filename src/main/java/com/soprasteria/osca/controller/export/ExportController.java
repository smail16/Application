package com.soprasteria.osca.controller.export;

import com.soprasteria.osca.domain.export.ExportService;
import com.soprasteria.osca.domain.project.Project;
import com.soprasteria.osca.domain.project.ProjectService;
import com.soprasteria.shared.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Controleur pour l'exportation des projets en format CSV
 */
@RestController
@RequestMapping("/api/projects")
class ExportController {

    private final ProjectService projectService;
    private final ExportService exportService;

    /**
     * Constructeur du controleur d'exportation.
     *
     * @param projectService Service de gestion des projets.
     * @param exportService  Service d'exportation des projets.
     */
    @Autowired
    public ExportController(ProjectService projectService, ExportService exportService) {
        this.projectService = projectService;
        this.exportService = exportService;
    }

    /**
     * Exporter un projet au format CSV.
     *
     * @param identifiant L'identifiant du projet à exporter.
     * @return Réponse HTTP avec le fichier CSV en pièce jointe ou une erreur 404 si le projet n'est pas trouvé.
     */
    @GetMapping("/{identifiant}/export/csv")
    public ResponseEntity<byte[]> exportProject(@PathVariable String identifiant) {
        try {
            Project project = projectService.findProjectByIdentifiant(identifiant);
            String csvData = exportService.exportProject(project);

            // Byte Order Mark (BOM) pour lire les fichiers UTF-8 csv et Excel
            byte[] bom = new byte[] {(byte)0xEF, (byte)0xBB, (byte)0xBF};
            byte[] csvBytes = csvData.getBytes(StandardCharsets.UTF_8);
            byte[] bomCsvBytes = new byte[bom.length + csvBytes.length];

            System.arraycopy(bom, 0, bomCsvBytes, 0, bom.length);
            System.arraycopy(csvBytes, 0, bomCsvBytes, bom.length, csvBytes.length);

            // Obtenir la date et l'heure actuelles du téléchargement du fichier csv
            Date uploadDate = new Date();
            String formattedUploadDate = DateUtils.formatUploadDate(uploadDate);

            // Créer le nom du fichier avec la date et l'heure actuelles
            String filename = project.getTitre().toUpperCase() + "_" + formattedUploadDate + ".csv";

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));

            return ResponseEntity.ok()
                .headers(headers)
                .body(bomCsvBytes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
