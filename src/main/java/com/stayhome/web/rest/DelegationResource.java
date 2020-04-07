package com.stayhome.web.rest;

import com.stayhome.service.DelegationService;
import com.stayhome.service.dto.DelegationDTO;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.stayhome.domain.Delegation}.
 */
@RestController
@RequestMapping("/api")
public class DelegationResource {

    private final Logger log = LoggerFactory.getLogger(DelegationResource.class);

    private final DelegationService delegationService;

    public DelegationResource(DelegationService delegationService) {
        this.delegationService = delegationService;
    }

    /**
     * {@code GET  /delegations} : get all the delegations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of delegations in body.
     */
    @GetMapping("/delegations")
    public List<DelegationDTO> getAllDelegations(@RequestParam(required = false) Long governorateId) {
        log.debug("REST request to get all Delegations, governorateId = {}", governorateId);
        return delegationService.findAll(governorateId);
    }

    /**
     * {@code GET  /delegations/:id} : get the "id" delegation.
     *
     * @param id the id of the delegationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the delegationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delegations/{id}")
    public ResponseEntity<DelegationDTO> getDelegation(@PathVariable Long id) {
        log.debug("REST request to get Delegation : {}", id);
        Optional<DelegationDTO> delegationDTO = delegationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(delegationDTO);
    }

}
