package com.stayhome.web.rest;

import com.stayhome.service.ServiceTypeService;
import com.stayhome.web.rest.errors.BadRequestAlertException;
import com.stayhome.service.dto.ServiceTypeDTO;

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
 * REST controller for managing {@link com.stayhome.domain.ServiceType}.
 */
@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeResource {

    private final Logger log = LoggerFactory.getLogger(ServiceTypeResource.class);

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeResource(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    /**
     * {@code GET  /service-types} : get all the serviceTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceTypes in body.
     */
    @GetMapping
    public List<ServiceTypeDTO> getAllServiceTypes() {
        log.debug("REST request to get all ServiceTypes");
        return serviceTypeService.findAll();
    }

    /**
     * {@code GET  /service-types/:id} : get the "id" serviceType.
     *
     * @param id the id of the serviceTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDTO> getServiceType(@PathVariable Long id) {
        log.debug("REST request to get ServiceType : {}", id);
        Optional<ServiceTypeDTO> serviceTypeDTO = serviceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceTypeDTO);
    }
}
