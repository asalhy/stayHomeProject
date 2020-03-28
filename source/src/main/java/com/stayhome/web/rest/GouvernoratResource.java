package com.stayhome.web.rest;

import com.stayhome.service.GouvernoratService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.GouvernoratDTO;

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
 * REST controller for managing {@link com.stayhome.domain.Gouvernorat}.
 */
@RestController
@RequestMapping("/api/gouvernorats")
public class GouvernoratResource {

    private final Logger log = LoggerFactory.getLogger(GouvernoratResource.class);

    private final GouvernoratService gouvernoratService;

    public GouvernoratResource(GouvernoratService gouvernoratService) {
        this.gouvernoratService = gouvernoratService;
    }

    /**
     * {@code GET  /gouvernorats} : get all the gouvernorats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gouvernorats in body.
     */
    @GetMapping
    public List<GouvernoratDTO> getAllGouvernorats() {
        log.debug("REST request to get all Gouvernorats");
        return gouvernoratService.findAll();
    }

    /**
     * {@code GET  /gouvernorats/:id} : get the "id" gouvernorat.
     *
     * @param id the id of the gouvernoratDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gouvernoratDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GouvernoratDTO> getGouvernorat(@PathVariable Long id) {
        log.debug("REST request to get Gouvernorat : {}", id);
        Optional<GouvernoratDTO> gouvernoratDTO = gouvernoratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gouvernoratDTO);
    }
}
