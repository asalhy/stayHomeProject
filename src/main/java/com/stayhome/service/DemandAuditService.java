package com.stayhome.service;

import com.stayhome.domain.DemandAudit;
import com.stayhome.repository.DemandAuditRepository;
import com.stayhome.service.dto.DemandAuditDTO;
import com.stayhome.service.mapper.DemandAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DemandAudit}.
 */
@Service
@Transactional
public class DemandAuditService {

    private final Logger log = LoggerFactory.getLogger(DemandAuditService.class);

    private final DemandAuditRepository demandAuditRepository;

    private final DemandAuditMapper demandAuditMapper;

    public DemandAuditService(DemandAuditRepository demandAuditRepository, DemandAuditMapper demandAuditMapper) {
        this.demandAuditRepository = demandAuditRepository;
        this.demandAuditMapper = demandAuditMapper;
    }

    /**
     * Save a demandAudit.
     *
     * @param demandAuditDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandAuditDTO save(DemandAuditDTO demandAuditDTO) {
        log.debug("Request to save DemandAudit : {}", demandAuditDTO);
        DemandAudit demandAudit = demandAuditMapper.toEntity(demandAuditDTO);
        demandAudit = demandAuditRepository.save(demandAudit);
        return demandAuditMapper.toDto(demandAudit);
    }

    /**
     * Get all the demandAudits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DemandAuditDTO> findAll() {
        log.debug("Request to get all DemandAudits");
        return demandAuditRepository.findAll().stream()
            .map(demandAuditMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one demandAudit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DemandAuditDTO> findOne(Long id) {
        log.debug("Request to get DemandAudit : {}", id);
        return demandAuditRepository.findById(id)
            .map(demandAuditMapper::toDto);
    }

    /**
     * Delete the demandAudit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DemandAudit : {}", id);
        demandAuditRepository.deleteById(id);
    }
}
