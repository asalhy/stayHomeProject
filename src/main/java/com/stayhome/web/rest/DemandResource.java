package com.stayhome.web.rest;

import com.stayhome.exception.BadRequestAlertException;
import com.stayhome.security.UserPrincipal;
import com.stayhome.service.DemandService;
import com.stayhome.service.dto.DemandAuditDTO;
import com.stayhome.service.dto.DemandDTO;
import com.stayhome.web.rest.vm.AssignRequestVM;
import com.stayhome.service.dto.DemandCriteria;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

    @PutMapping("/{id}/assign")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignDemand(@PathVariable Long id,
                             @Valid @RequestBody AssignRequestVM request,
                             @AuthenticationPrincipal UserPrincipal principal) {
        this.demandService.assignDemandTo(id, request.getAssigneeId(), principal);
    }

    @PutMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectDemand(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        this.demandService.rejectDemand(id, principal);
    }

    @PutMapping("/{id}/process")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processDemand(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        this.demandService.processDemand(id, principal);
    }

    @PutMapping("/{id}/done")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeDemand(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        this.demandService.closeDemand(id, principal);
    }

    /**
     * {@code GET  /demands} : get all the demands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demands in body.
     */
    @GetMapping
    public List<DemandDTO> getAllDemands(DemandCriteria criteria, @AuthenticationPrincipal UserPrincipal principal) {
        log.debug("REST request to get all Demands");

        if (!principal.isAdministrator()) {
            criteria.setAssignee(principal.getUsername());
        }

        return demandService.getDemands(criteria, principal);
    }

    /**
     * {@code GET  /demands/:id} : get the "id" demand.
     *
     * @param id the id of the demandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public DemandDTO getDemand(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        log.debug("REST request to get Demand : {}", id);

        return demandService.getDemand(id, principal);
    }

    @GetMapping("/{id}/audits")
    public List<DemandAuditDTO> getAllDemandAudits(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal principal) {
        log.debug("REST request to get Demand audits : {}", id);

        return this.demandService.getAudits(id, principal);
    }
}
