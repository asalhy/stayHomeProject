package com.stayhome.service;

import com.stayhome.domain.Municipality;
import com.stayhome.repository.MunicipalityRepository;
import com.stayhome.service.dto.MunicipalityDTO;
import com.stayhome.service.mapper.MunicipalityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final MunicipalityMapper municipalityMapper;

    public MunicipalityService(MunicipalityRepository municipalityRepository, MunicipalityMapper municipalityMapper) {
        this.municipalityRepository = municipalityRepository;
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
    public List<MunicipalityDTO> findAll() {
        log.debug("Request to get all Municipalities");
        return municipalityRepository.findAll().stream()
            .map(municipalityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
