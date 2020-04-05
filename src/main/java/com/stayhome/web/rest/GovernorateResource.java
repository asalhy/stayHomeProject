package com.stayhome.web.rest;

import com.stayhome.service.GovernorateService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.GovernorateDTO;

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
 * REST controller for managing {@link com.stayhome.domain.Governorate}.
 */
@RestController
@RequestMapping("/api")
public class GovernorateResource {

    private final Logger log = LoggerFactory.getLogger(GovernorateResource.class);

    private final GovernorateService governorateService;

    public GovernorateResource(GovernorateService governorateService) {
        this.governorateService = governorateService;
    }

    /**
     * {@code GET  /governorates} : get all the governorates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of governorates in body.
     */
    @GetMapping("/governorates")
    public List<GovernorateDTO> getAllGovernorates() {
        log.debug("REST request to get all Governorates");
        return governorateService.findAll();
    }

    /**
     * {@code GET  /governorates/:id} : get the "id" governorate.
     *
     * @param id the id of the governorateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the governorateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/governorates/{id}")
    public ResponseEntity<GovernorateDTO> getGovernorate(@PathVariable Long id) {
        log.debug("REST request to get Governorate : {}", id);
        Optional<GovernorateDTO> governorateDTO = governorateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(governorateDTO);
    }
}
