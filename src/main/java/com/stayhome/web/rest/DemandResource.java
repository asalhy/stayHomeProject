package com.stayhome.web.rest;

import com.stayhome.service.DemandService;
import com.stayhome.exception.BadRequestAlertException;
import com.stayhome.service.dto.DemandDTO;

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
 * REST controller for managing {@link com.stayhome.domain.Demand}.
 */
@RestController
@RequestMapping("/api/demands")
public class DemandResource {

    private final Logger log = LoggerFactory.getLogger(DemandResource.class);

    private static final String ENTITY_NAME = "demand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandService demandService;

    public DemandResource(DemandService demandService) {
        this.demandService = demandService;
    }

    /**
     * {@code POST  /demands} : Create a new demand.
     *
     * @param demandDTO the demandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandDTO, or with status {@code 400 (Bad Request)} if the demand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<DemandDTO> createDemand(@Valid @RequestBody DemandDTO demandDTO) throws URISyntaxException {
        log.debug("REST request to save Demand : {}", demandDTO);
        if (demandDTO.getId() != null) {
            throw new BadRequestAlertException("A new demand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandDTO result = demandService.createDemand(demandDTO);
        return ResponseEntity.created(new URI("/api/demands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demands} : get all the demands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demands in body.
     */
    @GetMapping
    public List<DemandDTO> getAllDemands() {
        log.debug("REST request to get all Demands");
        return demandService.getDemands();
    }

    /**
     * {@code GET  /demands/:id} : get the "id" demand.
     *
     * @param id the id of the demandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DemandDTO> getDemand(@PathVariable Long id) {
        log.debug("REST request to get Demand : {}", id);
        // FIXME - Must fetch demands by current organization
        Optional<DemandDTO> demandDTO = demandService.getDemand(id);
        return ResponseUtil.wrapOrNotFound(demandDTO);
    }
}
