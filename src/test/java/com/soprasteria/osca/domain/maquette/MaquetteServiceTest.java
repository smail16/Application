package com.soprasteria.osca.domain.maquette;

import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.page.PageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@ComponentTest
public class MaquetteServiceTest {
    @Mock
    private MaquettePersistancePort maquettePersistancePort;

    @Mock

    private PageService pageService;

    @InjectMocks
    private MaquetteService maquetteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test de la méthode addMaquette du service.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testAddMaquette() throws IOException {
        //Given: Nous créons les objets nécessaires et configurons les comportements simulés avec Mockito.
        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        Maquette mockMaquette = new Maquette();
        String keySousRubrique = "key";
        String projectId = "projectId";
        String titrePage = "titrePage";

        when(maquettePersistancePort.addMaquette(mockMultipartFile, keySousRubrique, projectId, titrePage))
            .thenReturn(mockMaquette);

        // When:on appelle le service avec la methode a tester
        maquetteService.addMaquette(mockMultipartFile, keySousRubrique, projectId, titrePage);

        // Then :verifier si la methode et bien appeller
        verify(pageService, times(1)).updateMaquette(mockMaquette);

    }
    /**
     * Test de la méthode getMaquetteByIdentifiantProjet du service.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiant() throws IOException {
        // Given simuler un identifiant
        String Identifiant = "Identifiant";
        Maquette expectedMaquette = new Maquette();
        when(maquettePersistancePort.getMaquetteByIdentifiant(Identifiant)).thenReturn(expectedMaquette);

        // When :le service appelle la méthode getMaquetteByIdentifiant avec l'identifiant
        Maquette result = maquetteService.getMaquetteByIdentifiant(Identifiant);

        // Then :  La maquette retournée par le service doit être la même que la maquette simulée
        assertEquals(expectedMaquette, result);
    }
    /**
     * Test de la méthode getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage du service.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testGetMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage() throws IOException {
        // Given: Un identifiant de projet simulé et une liste simulée de maquettes
        String ProjectId = "ProjectId";
        String KeySousRubrique = "KeySousRubrique";
        String TitrePage = "TitrePage";
        List<Maquette> mockMaquettes = Collections.singletonList(new Maquette()); // Liste simulée de maquettes

        // Mocking
        when(maquettePersistancePort.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage)).thenReturn(mockMaquettes);

        // When: Appel de la méthode getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage du service
        List<Maquette> result = maquetteService.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage);

        // Then: Vérification que la liste de maquettes est correctement récupérée
        assertEquals(mockMaquettes, result);
        verify(maquettePersistancePort, times(1)).getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(ProjectId, KeySousRubrique, TitrePage);
    }
    /**
     * Test de la méthode du suppression de la maquette du service.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void testDeleteMaquette() throws IOException {
        // Given: Un identifiant de maquette simulé
        String Identifiant = "Identifiant";
        Maquette mockMaquette = new Maquette(); // Maquette simulée

        // Mocking
        when(maquettePersistancePort.getMaquetteByIdentifiant(Identifiant)).thenReturn(mockMaquette);

        // When: Appel de la méthode deleteMaquette du service
        maquetteService.deleteMaquette(Identifiant);

        // Then: Vérification que la maquette est correctement supprimée
        verify(pageService, times(1)).deleteMaquette(mockMaquette);
        verify(maquettePersistancePort, times(1)).deleteMaquette(Identifiant);
    }
}
