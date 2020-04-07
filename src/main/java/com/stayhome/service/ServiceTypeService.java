package com.stayhome.service;

import com.stayhome.domain.Organization;
import com.stayhome.domain.ServiceType;
import com.stayhome.repository.OrganizationRepository;
import com.stayhome.repository.ServiceTypeRepository;
import com.stayhome.service.dto.ServiceTypeDTO;
import com.stayhome.service.mapper.ServiceTypeMapper;
import com.stayhome.exception.OrganizationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ServiceType}.
 */
@Service
@Transactional
public class ServiceTypeService {

    private final Logger log = LoggerFactory.getLogger(ServiceTypeService.class);

    private final OrganizationRepository organizationRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository, OrganizationRepository organizationRepository, ServiceTypeMapper serviceTypeMapper) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.organizationRepository = organizationRepository;
        this.serviceTypeMapper = serviceTypeMapper;
    }

    /**
     * Save a serviceType.
     *
     * @param serviceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceTypeDTO save(ServiceTypeDTO serviceTypeDTO) {
        log.debug("Request to save ServiceType : {}", serviceTypeDTO);
        ServiceType serviceType = serviceTypeMapper.toEntity(serviceTypeDTO);
        serviceType = serviceTypeRepository.save(serviceType);
        return serviceTypeMapper.toDto(serviceType);
    }

    /**
     * Get all the serviceTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ServiceTypeDTO> findAll(Long organizationId) {
        log.debug("Request to get all ServiceTypes, organizationId = {}", organizationId);

        Collection<ServiceType> services;
        if(organizationId != null) {
            Optional<Organization> organization = organizationRepository.findById(organizationId);
            if(!organization.isPresent()) {
                throw new OrganizationNotFoundException(organizationId);
            }

            // FIXME - Use graph
            services = organization.get().getServiceTypes();
        }
        else{
            services = serviceTypeRepository.findAll();
        }

        // FIXME - Use Mapstruct
        return services.stream()
            .map(serviceTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one serviceType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ServiceTypeDTO> findOne(Long id) {
        log.debug("Request to get ServiceType : {}", id);
        return serviceTypeRepository.findById(id)
            .map(serviceTypeMapper::toDto);
    }

    /**
     * Delete the serviceType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ServiceType : {}", id);
        serviceTypeRepository.deleteById(id);
    }
}
