package com.soprasteria.osca.controller.referentiel;

import com.soprasteria.osca.api.ReferentielsApi;
import com.soprasteria.osca.domain.referentiel.Referentiel;
import com.soprasteria.osca.domain.referentiel.ReferentielService;
import com.soprasteria.osca.model.ReferentielAPI;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ReferentielsController implements ReferentielsApi {

    private final ReferentielService referentielService;
    private final ReferentielAPIMapper referentielAPIMapper = Mappers.getMapper(ReferentielAPIMapper.class);

    public ReferentielsController(ReferentielService referentielService) {
        this.referentielService = referentielService;
    }

    @Override
    public ResponseEntity<ReferentielAPI> findReferentielByVersion(@PathVariable String version) {
        Referentiel referentiel = referentielService.findByVersion(version);

        if (referentiel != null) {
            return ResponseEntity.ok(referentielAPIMapper.toApi(referentiel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
