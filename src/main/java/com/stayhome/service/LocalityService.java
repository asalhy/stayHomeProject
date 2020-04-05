package com.stayhome.service;

import com.stayhome.domain.Locality;
import com.stayhome.repository.LocalityRepository;
import com.stayhome.service.dto.LocalityDTO;
import com.stayhome.service.mapper.LocalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    private final LocalityRepository localityRepository;

    private final LocalityMapper localityMapper;

    public LocalityService(LocalityRepository localityRepository, LocalityMapper localityMapper) {
        this.localityRepository = localityRepository;
        this.localityMapper = localityMapper;
    }

    /**
     * Save a locality.
     *
     * @param localityDTO the entity to save.
     * @return the persisted entity.
     */
    public LocalityDTO save(LocalityDTO localityDTO) {
        log.debug("Request to save Locality : {}", localityDTO);
        Locality locality = localityMapper.toEntity(localityDTO);
        locality = localityRepository.save(locality);
        return localityMapper.toDto(locality);
    }

    /**
     * Get all the localities.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LocalityDTO> findAll() {
        log.debug("Request to get all Localities");
        return localityRepository.findAll().stream()
            .map(localityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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

    /**
     * Delete the locality by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Locality : {}", id);
        localityRepository.deleteById(id);
    }
}
