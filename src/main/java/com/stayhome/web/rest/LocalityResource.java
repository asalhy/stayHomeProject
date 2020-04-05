package com.stayhome.web.rest;

import com.stayhome.service.LocalityService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.LocalityDTO;

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
 * REST controller for managing {@link com.stayhome.domain.Locality}.
 */
@RestController
@RequestMapping("/api")
public class LocalityResource {

    private final Logger log = LoggerFactory.getLogger(LocalityResource.class);

    private final LocalityService localityService;

    public LocalityResource(LocalityService localityService) {
        this.localityService = localityService;
    }

    /**
     * {@code GET  /localities} : get all the localities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localities in body.
     */
    @GetMapping("/localities")
    public List<LocalityDTO> getAllLocalities() {
        log.debug("REST request to get all Localities");
        return localityService.findAll();
    }

    /**
     * {@code GET  /localities/:id} : get the "id" locality.
     *
     * @param id the id of the localityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/localities/{id}")
    public ResponseEntity<LocalityDTO> getLocality(@PathVariable Long id) {
        log.debug("REST request to get Locality : {}", id);
        Optional<LocalityDTO> localityDTO = localityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localityDTO);
    }
}
