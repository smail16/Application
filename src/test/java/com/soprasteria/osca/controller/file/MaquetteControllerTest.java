package com.soprasteria.osca.controller.file;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.domain.maquette.MaquetteService;
import com.soprasteria.osca.model.MaquetteAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ComponentTest
class MaquetteControllerTest {

    @Mock
    private MaquetteService maquetteService;
    @Mock
    private MaquetteAPIMapper maquetteAPIMapper;
    @InjectMocks
    private MaquetteController maquetteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test de l'ajout de la maquette avec succès.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testAddMaquette_Success() throws IOException {
        // Given : Mock du service MaquetteService pour simuler un succès
        when(maquetteService.addMaquette(any(MultipartFile.class), any(String.class), any(String.class), any(String.class)))
            .thenReturn(new Maquette());

        // When: Appel de la méthode du contrôleur avec ces paramètres
        ResponseEntity<MaquetteAPI> responseEntity = maquetteController.addMaquette(mock(MultipartFile.class), "keySousRubrique", "projectId", "titrePage");

        // Then: Vérification du statut de la réponse
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(maquetteService, times(1)).addMaquette(any(), any(), any(), any());
    }
    /**
     * Test de l'ajout sans succès.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testAddMaquette_Failure() throws IOException {
        // Given: Mock du service MaquetteService pour simuler une exception
        when(maquetteService.addMaquette(any(MultipartFile.class), any(String.class), any(String.class), any(String.class)))
            .thenThrow(new IOException());

        // When: Appel de la méthode du contrôleur avec les paramètres
        ResponseEntity<MaquetteAPI> responseEntity = maquetteController.addMaquette(mock(MultipartFile.class), "keySousRubrique", "projectId", "titrePage");

        // Then: Vérification du statut de la réponse
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(maquetteService, times(1)).addMaquette(any(), any(), any(), any());
    }
    /**
     * Test de récupérer la mquette avec son identifiant en cas de succés .
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiant_Success() throws IOException {
        // Given: Un identifiant simulé et une maquette simulée
        String Identifiant = "Identifiant";
        Maquette mockMaquette = new Maquette();
        when(maquetteService.getMaquetteByIdentifiant(Identifiant)).thenReturn(mockMaquette);
        when(maquetteAPIMapper.toApi(any())).thenReturn(new MaquetteAPI());

        // When: Le contrôleur appelle la méthode getMaquetteByIdentifiant avec l'identifiant.
        ResponseEntity<MaquetteAPI> response = maquetteController.getMaquetteByIdentifiant(Identifiant);

        // Then: Le contrôleur doit retourner un statut HTTP OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(maquetteService, times(1)).getMaquetteByIdentifiant(Identifiant);

    }
    /**
     * Test de récupérer la mquette avec son identifiant en cas de exhec .
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiant_Failure() throws IOException {
        // Given: Un identifiant
        String Identifiant = "Identifiant";
        when(maquetteService.getMaquetteByIdentifiant(Identifiant)).thenThrow(IOException.class);

        // When: Le contrôleur appelle la méthode getMaquetteByIdentifiant avec l'identifiant
        ResponseEntity<MaquetteAPI> response = maquetteController.getMaquetteByIdentifiant(Identifiant);

        // Then: Le contrôleur doit retourner un statut HTTP INTERNAL_SERVER_ERROR (500)
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(maquetteService, times(1)).getMaquetteByIdentifiant(Identifiant);
        verifyNoInteractions(maquetteAPIMapper);
    }
    /**
     * Test de récupérer la mquette avec son identifiant le key de la sous rubrique l'IdProjet et le titre de la page en cas de succés .
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage_Success() throws IOException {
        // Given: Un projectId simulé, key de la sous-rubrique simulée et un titre de page
        String ProjectId = "ProjectId";
        String KeySousRubrique = "ProjectId";
        String TirePage = "TitrePage";
        List<Maquette> mockMaquettes = Collections.singletonList(new Maquette());
        when(maquetteService.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TirePage)).thenReturn(mockMaquettes);
        when(maquetteAPIMapper.toApiList(any())).thenReturn(Collections.singletonList(new MaquetteAPI()));

        // When: Le contrôleur appelle la méthode getMaquetteByIdentifiantProjet avec les paramètres
        ResponseEntity<List<MaquetteAPI>> response = maquetteController.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(TirePage, ProjectId, KeySousRubrique);

        // Then: Le contrôleur doit retourner un statut HTTP OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Vérification que la méthode du service a été appelée une fois avec les paramètres
        verify(maquetteService, times(1)).getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(TirePage, ProjectId, KeySousRubrique);
    }
    /**
     * Test de récupérer la mquette avec son identifiant le key de la sous rubrique l'IdProjet et le titre de la page en cas de echec .
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage_Failure() throws IOException {
        // Given: Un projectId simulé, key de la sous-rubrique simulée et un titre de page
        String ProjectId = "ProjectId";
        String KeySousRubrique = "KeySousRubrique";
        String TitrePage = "TitrePage";

        // Mock du service pour simuler une IOException
        when(maquetteService.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage))
            .thenThrow(IOException.class);

        // When: Le contrôleur appelle la méthode getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage avec les paramètres
        ResponseEntity<List<MaquetteAPI>> response = maquetteController.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage);

        // Then: Le contrôleur doit retourner un statut HTTP 500 Internal Server Error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

        // Vérification que la méthode du service a été appelée une fois avec les paramètres
        verify(maquetteService, times(1)).getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage);
    }
    /**
     * Test unitaire pour vérifier le succès de la suppression d'une maquette.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testDeleteMaquette_Success() throws IOException {
        // Given: Un identifiant simulé
        String Identifiant = "Identifiant";

        // When: Le contrôleur appelle la méthode deleteMaquette avec l'identifiant simulé
        ResponseEntity<Void> response = maquetteController.deleteMaquette(Identifiant);

        // Then: Le contrôleur doit retourner un statut HTTP OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(maquetteService, times(1)).deleteMaquette(Identifiant);
    }
}
