package com.stayhome.service;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Locality;
import com.stayhome.exception.DelegationNotFoundException;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.repository.LocalityRepository;
import com.stayhome.service.dto.LocalityDTO;
import com.stayhome.service.mapper.LocalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Locality}.
 */
@Service
@Transactional
public class LocalityService {

    private final Logger log = LoggerFactory.getLogger(LocalityService.class);

    private final DelegationRepository delegationRepository;
    private final LocalityRepository localityRepository;

    private final LocalityMapper localityMapper;

    public LocalityService(LocalityRepository localityRepository, LocalityMapper localityMapper,DelegationRepository delegationRepository) {
        this.localityRepository = localityRepository;
        this.localityMapper = localityMapper;
        this.delegationRepository = delegationRepository;
    }

    /**
     * Get all the localities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LocalityDTO> findAll(Long delegationId) {
        log.debug("Request to get all Localities, delegationId = {}", delegationId);

        List<Locality> localities;
        if(delegationId != null) {
            Optional<Delegation> delegation = delegationRepository.findById(delegationId);
            if (!delegation.isPresent()) {
                throw new DelegationNotFoundException(delegationId);
            }

            localities = localityRepository.findByDelegation(delegation.get());
        } else {
            localities = localityRepository.findAll();
        }

        return localities
            .stream()
            .map(this.localityMapper::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Get one locality by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LocalityDTO> findOne(Long id) {
        log.debug("Request to get Locality : {}", id);
        return localityRepository.findById(id)
            .map(localityMapper::toDto);
    }
}
