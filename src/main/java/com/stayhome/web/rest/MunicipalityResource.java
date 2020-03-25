package com.stayhome.web.rest;

import com.stayhome.service.MunicipalityService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.MunicipalityDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.stayhome.domain.Municipality}.
 */
@RestController
@RequestMapping("/api")
public class MunicipalityResource {

    private final Logger log = LoggerFactory.getLogger(MunicipalityResource.class);

    private final MunicipalityService municipalityService;

    public MunicipalityResource(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    /**
     * {@code GET  /municipalities} : get all the municipalities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of municipalities in body.
     */
    @GetMapping("/municipalities")
    public List<MunicipalityDTO> getAllMunicipalities() {
        log.debug("REST request to get all Municipalities");
        return municipalityService.findAll();
    }

    /**
     * {@code GET  /municipalities/:id} : get the "id" municipality.
     *
     * @param id the id of the municipalityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the municipalityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/municipalities/{id}")
    public ResponseEntity<MunicipalityDTO> getMunicipality(@PathVariable Long id) {
        log.debug("REST request to get Municipality : {}", id);
        Optional<MunicipalityDTO> municipalityDTO = municipalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(municipalityDTO);
    }
}
