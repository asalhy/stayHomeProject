package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.DemandAudit;
import com.stayhome.domain.User;
import com.stayhome.domain.Demand;
import com.stayhome.repository.DemandAuditRepository;
import com.stayhome.service.DemandAuditService;
import com.stayhome.service.dto.DemandAuditDTO;
import com.stayhome.service.mapper.DemandAuditMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.stayhome.domain.enumeration.DemandStatus;
/**
 * Integration tests for the {@link DemandAuditResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DemandAuditResourceIT {

    private static final DemandStatus DEFAULT_STATUS = DemandStatus.OPEN;
    private static final DemandStatus UPDATED_STATUS = DemandStatus.ASSIGNED;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DemandAuditRepository demandAuditRepository;

    @Autowired
    private DemandAuditMapper demandAuditMapper;

    @Autowired
    private DemandAuditService demandAuditService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandAuditMockMvc;

    private DemandAudit demandAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandAudit createEntity(EntityManager em) {
        DemandAudit demandAudit = new DemandAudit()
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .creationDate(DEFAULT_CREATION_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        demandAudit.setUser(user);
        // Add required entity
        Demand demand;
        if (TestUtil.findAll(em, Demand.class).isEmpty()) {
            demand = DemandResourceIT.createEntity(em);
            em.persist(demand);
            em.flush();
        } else {
            demand = TestUtil.findAll(em, Demand.class).get(0);
        }
        demandAudit.setDemand(demand);
        return demandAudit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemandAudit createUpdatedEntity(EntityManager em) {
        DemandAudit demandAudit = new DemandAudit()
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION)
            .ipAddress(UPDATED_IP_ADDRESS)
            .creationDate(UPDATED_CREATION_DATE);
        // Add required entity
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        demandAudit.setUser(user);
        // Add required entity
        Demand demand;
        if (TestUtil.findAll(em, Demand.class).isEmpty()) {
            demand = DemandResourceIT.createUpdatedEntity(em);
            em.persist(demand);
            em.flush();
        } else {
            demand = TestUtil.findAll(em, Demand.class).get(0);
        }
        demandAudit.setDemand(demand);
        return demandAudit;
    }

    @BeforeEach
    public void initTest() {
        demandAudit = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllDemandAudits() throws Exception {
        // Initialize the database
        demandAuditRepository.saveAndFlush(demandAudit);

        // Get all the demandAuditList
        restDemandAuditMockMvc.perform(get("/api/demand-audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDemandAudit() throws Exception {
        // Initialize the database
        demandAuditRepository.saveAndFlush(demandAudit);

        // Get the demandAudit
        restDemandAuditMockMvc.perform(get("/api/demand-audits/{id}", demandAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandAudit.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemandAudit() throws Exception {
        // Get the demandAudit
        restDemandAuditMockMvc.perform(get("/api/demand-audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
