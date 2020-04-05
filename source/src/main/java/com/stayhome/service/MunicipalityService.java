package com.stayhome.service;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Municipality;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.repository.MunicipalityRepository;
import com.stayhome.service.dto.MunicipalityDTO;
import com.stayhome.service.mapper.MunicipalityMapper;
import com.stayhome.web.rest.errors.DelegationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Municipality}.
 */
@Service
@Transactional
public class MunicipalityService {

    private final Logger log = LoggerFactory.getLogger(MunicipalityService.class);

    private final MunicipalityRepository municipalityRepository;
    private final DelegationRepository delegationRepository;
    private final MunicipalityMapper municipalityMapper;

    public MunicipalityService(MunicipalityRepository municipalityRepository, DelegationRepository delegationRepository, MunicipalityMapper municipalityMapper) {
        this.municipalityRepository = municipalityRepository;
        this.delegationRepository = delegationRepository;
        this.municipalityMapper = municipalityMapper;
    }

    /**
     * Save a municipality.
     *
     * @param municipalityDTO the entity to save.
     * @return the persisted entity.
     */
    public MunicipalityDTO save(MunicipalityDTO municipalityDTO) {
        log.debug("Request to save Municipality : {}", municipalityDTO);
        Municipality municipality = municipalityMapper.toEntity(municipalityDTO);
        municipality = municipalityRepository.save(municipality);
        return municipalityMapper.toDto(municipality);
    }

    /**
     * Get all the municipalities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MunicipalityDTO> findAll(Long delegationId) {
        log.debug("Request to get all Municipalities, delegationId = {}", delegationId);

        final List<Municipality> municipalities;
        if (delegationId != null) {
            Optional<Delegation> delegation = this.delegationRepository.findById(delegationId);
            if (!delegation.isPresent()) {
                throw new DelegationNotFoundException(delegationId);
            }

            municipalities = this.municipalityRepository.findAllByDelegation(delegation.get());
        } else {
            municipalities = this.municipalityRepository.findAll();
        }

        return this.municipalityMapper.toDto(municipalities);
    }

    /**
     * Get one municipality by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MunicipalityDTO> findOne(Long id) {
        log.debug("Request to get Municipality : {}", id);
        return municipalityRepository.findById(id)
            .map(municipalityMapper::toDto);
    }

    /**
     * Delete the municipality by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Municipality : {}", id);
        municipalityRepository.deleteById(id);
    }
}
