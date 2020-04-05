package com.stayhome.service;

import com.stayhome.domain.Demand;
import com.stayhome.repository.DemandRepository;
import com.stayhome.service.dto.DemandDTO;
import com.stayhome.service.mapper.DemandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Demand}.
 */
@Service
@Transactional
public class DemandService {

    private final Logger log = LoggerFactory.getLogger(DemandService.class);

    private final DemandRepository demandRepository;

    private final DemandMapper demandMapper;

    public DemandService(DemandRepository demandRepository, DemandMapper demandMapper) {
        this.demandRepository = demandRepository;
        this.demandMapper = demandMapper;
    }

    /**
     * Save a demand.
     *
     * @param demandDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandDTO save(DemandDTO demandDTO) {
        log.debug("Request to save Demand : {}", demandDTO);
        Demand demand = demandMapper.toEntity(demandDTO);
        demand = demandRepository.save(demand);
        return demandMapper.toDto(demand);
    }

    /**
     * Get all the demands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DemandDTO> findAll() {
        log.debug("Request to get all Demands");
        return demandRepository.findAll().stream()
            .map(demandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one demand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandDTO> findOne(Long id) {
        log.debug("Request to get Demand : {}", id);
        return demandRepository.findById(id)
            .map(demandMapper::toDto);
    }

    /**
     * Delete the demand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Demand : {}", id);
        demandRepository.deleteById(id);
    }
}
