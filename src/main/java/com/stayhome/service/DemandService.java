package com.stayhome.service;

import com.stayhome.domain.Demand;
import com.stayhome.domain.Organization;
import com.stayhome.domain.User;
import com.stayhome.domain.specs.DemandSpecifications;
import com.stayhome.exception.AccessDeniedException;
import com.stayhome.exception.DemandNotFoundException;
import com.stayhome.exception.OrganizationNotFoundException;
import com.stayhome.exception.UserNotFoundException;
import com.stayhome.repository.DemandAuditRepository;
import com.stayhome.repository.DemandRepository;
import com.stayhome.repository.OrganizationRepository;
import com.stayhome.repository.UserRepository;
import com.stayhome.security.UserPrincipal;
import com.stayhome.service.dto.DemandAuditDTO;
import com.stayhome.service.dto.DemandDTO;
import com.stayhome.service.mapper.DemandAuditMapper;
import com.stayhome.service.mapper.DemandMapper;
import com.stayhome.service.dto.DemandCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Demand}.
 */
@Service
@Transactional
public class DemandService {

    private final Logger log = LoggerFactory.getLogger(DemandService.class);

    private final DemandRepository demandRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final DemandAuditRepository demandAuditRepository;
    private final DemandMapper demandMapper;
    private final DemandAuditMapper demandAuditMapper;

    public DemandService(DemandRepository demandRepository, UserRepository userRepository, OrganizationRepository organizationRepository,
                         DemandAuditRepository demandAuditRepository, DemandMapper demandMapper, DemandAuditMapper demandAuditMapper) {
        this.demandRepository = demandRepository;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.demandAuditRepository = demandAuditRepository;
        this.demandMapper = demandMapper;
        this.demandAuditMapper = demandAuditMapper;
    }

    /**
     * Save a demand.
     *
     * @param demandDTO the entity to save.
     * @return the persisted entity.
     */
    public DemandDTO createDemand(DemandDTO demandDTO) {
        log.debug("Creating new demand : {}", demandDTO);

        Demand demand = demandMapper.toEntity(demandDTO);
        demand.open();

        demand = demandRepository.save(demand);
        return demandMapper.toDto(demand);
    }

    public void closeDemand(Long demandId, UserPrincipal principal) {
        log.debug("Closing demand : {}", demandId);

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());
        demand.done(principal.getUsername());

        this.demandRepository.save(demand);
    }

    public void processDemand(Long demandId, UserPrincipal principal) {
        log.debug("Processing demand : {}", demandId);

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());
        demand.process(principal.getUsername());

        this.demandRepository.save(demand);
    }

    public void rejectDemand(Long demandId, UserPrincipal principal) {
        log.debug("Rejecting demand : {}", demandId);

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());
        demand.reject(principal.getUsername());

        this.demandRepository.save(demand);
    }

    public void assignDemandTo(Long demandId, Long assigneeId, UserPrincipal principal) {
        log.debug("Assigning demand {} to {}", demandId, assigneeId);

        User assignee = this.getRequiredUser(assigneeId, principal.getOrganizationId());

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());
        demand.assignTo(principal.getUsername(), assignee);

        this.demandRepository.save(demand);
    }

    /**
     * Get all the demands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DemandDTO> getDemands(DemandCriteria criteria, UserPrincipal principal) {
        log.debug("Request to get all Demands, criteria = {}", criteria);

        Organization organization = this.organizationRepository
            .findById(principal.getOrganizationId())
            .orElseThrow(() -> new OrganizationNotFoundException(principal.getOrganizationId()));

        return demandRepository
            .findAll(DemandSpecifications.createSpecification(organization, criteria))
            .stream()
            .map(demandMapper::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Get one demand by demandId.
     *
     * @param demandId the demandId of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public DemandDTO getDemand(Long demandId, UserPrincipal principal) {
        log.debug("Request to get Demand : {}", demandId);

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());

        return this.demandMapper.toDto(demand);
    }

    @Transactional(readOnly = true)
    public List<DemandAuditDTO> getAudits(Long demandId, UserPrincipal principal) {
        log.debug("Request to get Demand audits : {}", demandId);

        Demand demand = this.getRequiredDemand(demandId, principal.getOrganizationId());

        return this.demandAuditRepository.findAllByDemand(demand)
            .stream()
            .map(this.demandAuditMapper::toDto)
            .collect(Collectors.toList());
    }

    private Demand getRequiredDemand(Long demandId, Long organizationId) {
        Demand demand = this.demandRepository
            .findById(demandId)
            .orElseThrow(() -> new DemandNotFoundException(demandId));

        this.validateOrganization(organizationId, demand.getOrganization());

        return demand;
    }

    private User getRequiredUser(Long userId, Long organizationId) {
        User user = this.userRepository
            .findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

        this.validateOrganization(organizationId, user.getOrganization());

        return user;
    }

    private void validateOrganization(Long currentOrganizationId, Organization actualOrganization) {
        Objects.requireNonNull(currentOrganizationId);
        Objects.requireNonNull(actualOrganization);

        Organization currentOrganization = this.organizationRepository
            .findById(currentOrganizationId)
            .orElseThrow(() -> new OrganizationNotFoundException(currentOrganizationId));

        if (!currentOrganization.equals(actualOrganization)) {
            throw new AccessDeniedException("Access denied !");
        }
    }
}
