package com.stayhome.service;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Governorate;
import com.stayhome.exception.GovernorateNotFoundException;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.repository.GovernorateRepository;
import com.stayhome.service.dto.DelegationDTO;
import com.stayhome.service.mapper.DelegationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final GovernorateRepository governorateRepository;
    private final DelegationRepository delegationRepository;

    private final DelegationMapper delegationMapper;

    public DelegationService(DelegationRepository delegationRepository, GovernorateRepository governorateRepository, DelegationMapper delegationMapper) {
        this.governorateRepository = governorateRepository;
        this.delegationRepository = delegationRepository;
        this.delegationMapper = delegationMapper;
    }

    /**
     * Get all the delegations.
     *
     * @return the list of entities.
     * @param governorateId
     */
    @Transactional(readOnly = true)
    public List<DelegationDTO> findAll(Long governorateId) throws GovernorateNotFoundException {
        log.debug("Request to get all Delegations, governorateId = {}", governorateId);

        final List<Delegation> delegations;
        if(governorateId != null) {
            Optional<Governorate> governorate = governorateRepository.findById(governorateId);
            if (!governorate.isPresent()) {
                throw new GovernorateNotFoundException(governorateId);
            }

            delegations = delegationRepository.findByGovernorate(governorate.get());
        } else {
            delegations = delegationRepository.findAll();
        }

        return delegations
            .stream()
            .map(this.delegationMapper::toDto)
            .collect(Collectors.toList());
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
}
