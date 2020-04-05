package com.stayhome.service;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Gouvernorat;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.repository.GouvernoratRepository;
import com.stayhome.service.dto.DelegationDTO;
import com.stayhome.service.mapper.DelegationMapper;
import com.stayhome.web.rest.errors.GouvernoratNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Delegation}.
 */
@Service
@Transactional
public class DelegationService {

    private final Logger log = LoggerFactory.getLogger(DelegationService.class);

    private final DelegationRepository delegationRepository;
    private final GouvernoratRepository gouvernoratRepository;
    private final DelegationMapper delegationMapper;

    public DelegationService(DelegationRepository delegationRepository, GouvernoratRepository gouvernoratRepository, DelegationMapper delegationMapper) {
        this.delegationRepository = delegationRepository;
        this.gouvernoratRepository = gouvernoratRepository;
        this.delegationMapper = delegationMapper;
    }

    /**
     * Save a delegation.
     *
     * @param delegationDTO the entity to save.
     * @return the persisted entity.
     */
    public DelegationDTO save(DelegationDTO delegationDTO) {
        log.debug("Request to save Delegation : {}", delegationDTO);
        Delegation delegation = delegationMapper.toEntity(delegationDTO);
        delegation = delegationRepository.save(delegation);
        return delegationMapper.toDto(delegation);
    }

    /**
     * Get all the delegations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DelegationDTO> findAll(Long gouvernoratId) {
        log.debug("Request to get all Delegations, gouvernoratId = {}", gouvernoratId);

        final List<Delegation> delegations;

        if (gouvernoratId != null) {
            Optional<Gouvernorat> gouvernorat = this.gouvernoratRepository.findById(gouvernoratId);
            if (!gouvernorat.isPresent()) {
                throw new GouvernoratNotFoundException(gouvernoratId);
            }

            delegations = this.delegationRepository.findAllByGouvernorat(gouvernorat.get());
        } else {
            delegations = this.delegationRepository.findAll();
        }

        return this.delegationMapper.toDto(delegations);
    }

    /**
     * Get one delegation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DelegationDTO> findOne(Long id) {
        log.debug("Request to get Delegation : {}", id);
        return delegationRepository.findById(id)
            .map(delegationMapper::toDto);
    }

    /**
     * Delete the delegation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Delegation : {}", id);
        delegationRepository.deleteById(id);
    }
}
