package com.stayhome.web.rest;

import com.stayhome.service.DemandAuditService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.DemandAuditDTO;

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
 * REST controller for managing {@link com.stayhome.domain.DemandAudit}.
 */
@RestController
@RequestMapping("/api")
public class DemandAuditResource {

    private final Logger log = LoggerFactory.getLogger(DemandAuditResource.class);

    private final DemandAuditService demandAuditService;

    public DemandAuditResource(DemandAuditService demandAuditService) {
        this.demandAuditService = demandAuditService;
    }

    /**
     * {@code GET  /demand-audits} : get all the demandAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandAudits in body.
     */
    @GetMapping("/demand-audits")
    public List<DemandAuditDTO> getAllDemandAudits() {
        log.debug("REST request to get all DemandAudits");
        return demandAuditService.findAll();
    }

    /**
     * {@code GET  /demand-audits/:id} : get the "id" demandAudit.
     *
     * @param id the id of the demandAuditDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandAuditDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demand-audits/{id}")
    public ResponseEntity<DemandAuditDTO> getDemandAudit(@PathVariable Long id) {
        log.debug("REST request to get DemandAudit : {}", id);
        Optional<DemandAuditDTO> demandAuditDTO = demandAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandAuditDTO);
    }
}
