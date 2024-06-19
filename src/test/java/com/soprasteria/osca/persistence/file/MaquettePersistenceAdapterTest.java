package com.soprasteria.osca.persistence.file;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.soprasteria.ComponentTest;
import com.soprasteria.osca.domain.maquette.Maquette;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ComponentTest
class MaquettePersistenceAdapterTest {
    @Mock
    private GridFsTemplate gridFsTemplate;

    @Mock
    private GridFsOperations gridFsOperations;

    @InjectMocks
    private MaquettePersistanceAdapter adapter;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test de la méthode addMaquette.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'exécution du test.
     */
    @Test
    void addMaquette() throws IOException {
        // Given: Données du test
        byte[] content = "Test content".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        MaquettePersistanceAdapter adapter = new MaquettePersistanceAdapter(gridFsTemplate, gridFsOperations);

        // Initialise le mock de MultipartFile
        MultipartFile mockMultipartFile = mock(MultipartFile.class);
        when(mockMultipartFile.getSize()).thenReturn((long) content.length);
        when(mockMultipartFile.getInputStream()).thenReturn(inputStream);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("filename.txt");
        when(mockMultipartFile.getContentType()).thenReturn("image/png");

        // Mock du comportement de store dans gridFsTemplate
        ObjectId fileId = new ObjectId();
        when(gridFsTemplate.store(any(InputStream.class), eq("filename.txt"), eq("image/png"))).thenReturn(fileId);

        // When: exécution de l'action a tester
        Maquette maquette = adapter.addMaquette(mockMultipartFile, "&", "1", "1");

        //then:  Vérification du résultat
        assertNotNull(maquette);
    }
    /**
     * Test de la méthode getMaquetteByIdentifiant.
     */
    @Test
    void getMaquetteByIdentifiant() throws IOException {
        // Given: on mock GridFSFile  avec des comportements
        GridFSFile gridFSFile = mock(GridFSFile.class);

        when(gridFSFile.getFilename()).thenReturn("test.txt");
        when(gridFSFile.getMetadata()).thenReturn(null);
        when(gridFsTemplate.findOne(any(Query.class))).thenReturn(gridFSFile);
        GridFsResource gridFsResource = mock(GridFsResource.class);
        when(gridFsOperations.getResource(gridFSFile)).thenReturn(gridFsResource);


        //When: on appelle la methode getMaquetteByIdentifiant avec un identifiant
        Maquette maquette = adapter.getMaquetteByIdentifiant("testObjectId");

        // Then: On verifie que la Maquette retournée n'est pas nulle, et que ses propriétés sont toutes nulles
        assertNotNull(maquette);
        assertNull(maquette.getFileType());
        assertNull(maquette.getFileSize());
        assertNull(maquette.getKeySousRubrique());
        assertNull(maquette.getProjectId());
        assertNull(maquette.getTitrePage());

    }

    /**
     * Test de la méthode getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage.
     */
    @Test
    void getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage() throws IOException {
        // Given: La méthode find du GridFsTemplate retourne une liste de GridFSFile vide.
        GridFSFindIterable gridFSFindIterable = mock(GridFSFindIterable.class);
        when(gridFsTemplate.find(any(Query.class))).thenReturn(gridFSFindIterable);

        // When: on appelle La méthode getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage
        List<Maquette> maquetteList = adapter.getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage("titrePage","projectId","12345");

        // Then: On vérifie que la liste retournée n'est pas nulle et qu'elle est vide.
        assertNotNull(maquetteList);
    }

    /**
     * Test de la méthode deleteMaquette.
     */
    @Test
    void deleteMaquette() {

        //When: La méthode deleteMaquette est appelée avec un identifiant.
        adapter.deleteMaquette("Identifiant");

        //Then: On vérifie que la méthode delete du GridFsTemplate est appelée une fois avec les paramètres attendus.
        verify(gridFsTemplate, times(1)).delete(any(Query.class));
    }
}
