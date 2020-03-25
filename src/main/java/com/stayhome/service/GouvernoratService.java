package com.stayhome.service;

import com.stayhome.domain.Gouvernorat;
import com.stayhome.repository.GouvernoratRepository;
import com.stayhome.service.dto.GouvernoratDTO;
import com.stayhome.service.mapper.GouvernoratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Gouvernorat}.
 */
@Service
@Transactional
public class GouvernoratService {

    private final Logger log = LoggerFactory.getLogger(GouvernoratService.class);

    private final GouvernoratRepository gouvernoratRepository;

    private final GouvernoratMapper gouvernoratMapper;

    public GouvernoratService(GouvernoratRepository gouvernoratRepository, GouvernoratMapper gouvernoratMapper) {
        this.gouvernoratRepository = gouvernoratRepository;
        this.gouvernoratMapper = gouvernoratMapper;
    }

    /**
     * Save a gouvernorat.
     *
     * @param gouvernoratDTO the entity to save.
     * @return the persisted entity.
     */
    public GouvernoratDTO save(GouvernoratDTO gouvernoratDTO) {
        log.debug("Request to save Gouvernorat : {}", gouvernoratDTO);
        Gouvernorat gouvernorat = gouvernoratMapper.toEntity(gouvernoratDTO);
        gouvernorat = gouvernoratRepository.save(gouvernorat);
        return gouvernoratMapper.toDto(gouvernorat);
    }

    /**
     * Get all the gouvernorats.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GouvernoratDTO> findAll() {
        log.debug("Request to get all Gouvernorats");
        return gouvernoratRepository.findAll().stream()
            .map(gouvernoratMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gouvernorat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GouvernoratDTO> findOne(Long id) {
        log.debug("Request to get Gouvernorat : {}", id);
        return gouvernoratRepository.findById(id)
            .map(gouvernoratMapper::toDto);
    }

    /**
     * Delete the gouvernorat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Gouvernorat : {}", id);
        gouvernoratRepository.deleteById(id);
    }
}
