package com.stayhome.service;

import com.stayhome.domain.Governorate;
import com.stayhome.repository.GovernorateRepository;
import com.stayhome.service.dto.GovernorateDTO;
import com.stayhome.service.mapper.GovernorateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Governorate}.
 */
@Service
@Transactional
public class GovernorateService {

    private final Logger log = LoggerFactory.getLogger(GovernorateService.class);

    private final GovernorateRepository governorateRepository;

    private final GovernorateMapper governorateMapper;

    public GovernorateService(GovernorateRepository governorateRepository, GovernorateMapper governorateMapper) {
        this.governorateRepository = governorateRepository;
        this.governorateMapper = governorateMapper;
    }

    /**
     * Save a governorate.
     *
     * @param governorateDTO the entity to save.
     * @return the persisted entity.
     */
    public GovernorateDTO save(GovernorateDTO governorateDTO) {
        log.debug("Request to save Governorate : {}", governorateDTO);
        Governorate governorate = governorateMapper.toEntity(governorateDTO);
        governorate = governorateRepository.save(governorate);
        return governorateMapper.toDto(governorate);
    }

    /**
     * Get all the governorates.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GovernorateDTO> findAll() {
        log.debug("Request to get all Governorates");
        return governorateRepository.findAll().stream()
            .map(governorateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one governorate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GovernorateDTO> findOne(Long id) {
        log.debug("Request to get Governorate : {}", id);
        return governorateRepository.findById(id)
            .map(governorateMapper::toDto);
    }

    /**
     * Delete the governorate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Governorate : {}", id);
        governorateRepository.deleteById(id);
    }
}
