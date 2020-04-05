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
@RequestMapping("/api")
public class ServiceTypeResource {

    private final Logger log = LoggerFactory.getLogger(ServiceTypeResource.class);

    private static final String ENTITY_NAME = "serviceType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeResource(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    /**
     * {@code POST  /service-types} : Create a new serviceType.
     *
     * @param serviceTypeDTO the serviceTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceTypeDTO, or with status {@code 400 (Bad Request)} if the serviceType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-types")
    public ResponseEntity<ServiceTypeDTO> createServiceType(@Valid @RequestBody ServiceTypeDTO serviceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceType : {}", serviceTypeDTO);
        if (serviceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceTypeDTO result = serviceTypeService.save(serviceTypeDTO);
        return ResponseEntity.created(new URI("/api/service-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-types} : Updates an existing serviceType.
     *
     * @param serviceTypeDTO the serviceTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceTypeDTO,
     * or with status {@code 400 (Bad Request)} if the serviceTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-types")
    public ResponseEntity<ServiceTypeDTO> updateServiceType(@Valid @RequestBody ServiceTypeDTO serviceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceType : {}", serviceTypeDTO);
        if (serviceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceTypeDTO result = serviceTypeService.save(serviceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-types} : get all the serviceTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceTypes in body.
     */
    @GetMapping("/service-types")
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
    @GetMapping("/service-types/{id}")
    public ResponseEntity<ServiceTypeDTO> getServiceType(@PathVariable Long id) {
        log.debug("REST request to get ServiceType : {}", id);
        Optional<ServiceTypeDTO> serviceTypeDTO = serviceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceTypeDTO);
    }

    /**
     * {@code DELETE  /service-types/:id} : delete the "id" serviceType.
     *
     * @param id the id of the serviceTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-types/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        log.debug("REST request to delete ServiceType : {}", id);
        serviceTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
