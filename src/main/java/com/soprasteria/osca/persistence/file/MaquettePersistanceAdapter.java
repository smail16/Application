package com.soprasteria.osca.persistence.file;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.soprasteria.osca.domain.maquette.Maquette;
import com.soprasteria.osca.domain.maquette.MaquettePersistancePort;
import org.apache.commons.io.IOUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaquettePersistanceAdapter implements MaquettePersistancePort {

    /**
     * Constructeur pour initialiser l'adaptateur avec le template et les opérations nécessaires pour interagir avec GridFS.
     *
     * @param template Le GridFsTemplate utilisé pour les opérations de stockage sur les fichiers.
     * @param operations Les GridFsOperations utilisées pour les opérations  sur les fichiers GridFS.
     */
    private final GridFsTemplate template;
    private final GridFsOperations operations;

    private final  MaquetteMapper maquetteMapper = Mappers.getMapper(MaquetteMapper.class);

    public MaquettePersistanceAdapter(GridFsTemplate template, GridFsOperations operations) {
        this.template = template;
        this.operations = operations;
    }

    /**
     * Ajouter un fichier à la base de données (GridFS).
     *
     * @param maquette    Le fichier à ajouter.
     * @param keySousRubrique La clé de la sous-rubrique.
     * @param projectId   L'identifiant du projet.
     * @param titrePage   Le titre de la page.
     * @return L'identifiant unique du fichier ajouté.
     * @throws IOException si une erreur d'entrée/sortie se produit lors de l'ajout du fichier.
     */
    @Override
    public Maquette addMaquette(MultipartFile maquette, String keySousRubrique, String projectId, String titrePage) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", maquette.getSize());
        metadata.put("keySousRubrique",keySousRubrique);
        metadata.put("projectId",projectId);
        metadata.put("titrePage",titrePage);

        Object fileID = template.store(maquette.getInputStream(), maquette.getOriginalFilename(), maquette.getContentType(), metadata);
        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(fileID)) );
        MaquetteEntite savedMaquette = gridFsFileToMaquetteEntite(gridFSFile);
        return maquetteMapper.toDomain(savedMaquette);
    }
    /**
     * Récupère une maquette par son identifiant.
     *
     * @param identifiant L'identifiant de la maquette à récupérer.
     * @return La maquette récupérée.
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la récupération de la maquette.
     */
    @Override
    public Maquette getMaquetteByIdentifiant(String identifiant) throws IOException {
        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(identifiant)) );
        MaquetteEntite maquetteEntite = gridFsFileToMaquetteEntite(gridFSFile);

        return maquetteMapper.toDomain(maquetteEntite);

    }
    /**
     * Récupère la liste des maquettes associées à un projet par son identifiant
     *
     * @param identifiant     L'identifiant du projet pour lequel récupérer les maquettes
     * @param keySousRubrique key de la sous-rubrique
     * @param titrePage       Le titre de la page.
     * @return La liste des maquettes associées au projet.
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la récupération des maquettes.
     */
    @Override
    public List<Maquette> getMaquetteByIdentifiantProjetAndKeySousRubriqueAndTitrePage(String identifiant, String keySousRubrique, String titrePage) throws IOException {
        GridFSFindIterable list = template.find(new Query(Criteria.where("metadata.projectId").is(identifiant)
            .and("metadata.keySousRubrique").is(keySousRubrique).and("metadata.titrePage").is(titrePage)));
        List<MaquetteEntite> maquettesEntite = new ArrayList<>();

        list.forEach(gridFSFile->{

            try {
                maquettesEntite.add(gridFsFileToMaquetteEntite(gridFSFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return maquetteMapper.toDomainList(maquettesEntite);

    }
    /**
     * Supprime une maquette par son identifiant.
     *
     * @param identifiant L'identifiant de la maquette à supprimer.
     * @return
     */
    @Override
    public Maquette deleteMaquette(String identifiant) {
        Query query = Query.query(Criteria.where("_id").is(identifiant));
        template.delete(query);

        return null;
    }


    /**
     * Convertit un objet GridFSFile en une entité MaquetteEntite.
     *
     * @param gridFSFile Le fichier GridFSFile à convertir.
     * @return L'entité MaquetteEntite correspondante.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la conversion.
     */
    public MaquetteEntite gridFsFileToMaquetteEntite (GridFSFile gridFSFile) throws IOException {
        MaquetteEntite maquetteEntite = new MaquetteEntite();

        if (gridFSFile != null  && gridFSFile.getMetadata() != null){

            maquetteEntite.setIdentifiant(gridFSFile.getObjectId().toString());
            maquetteEntite.setFilename( gridFSFile.getFilename() );
            maquetteEntite.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );
            maquetteEntite.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );
            maquetteEntite.setKeySousRubrique(gridFSFile.getMetadata().get("keySousRubrique").toString());
            maquetteEntite.setProjectId(gridFSFile.getMetadata().get("projectId").toString());
            maquetteEntite.setTitrePage((gridFSFile.getMetadata().get("titrePage").toString()));
            maquetteEntite.setMaquette( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }
        return maquetteEntite;
    }
}
