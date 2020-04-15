package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Demand;
import com.stayhome.domain.Locality;
import com.stayhome.domain.Organization;
import com.stayhome.domain.ServiceType;
import com.stayhome.domain.enumeration.DemandStatus;
import com.stayhome.repository.DemandRepository;
import com.stayhome.service.DemandService;
import com.stayhome.service.dto.DemandDTO;
import com.stayhome.service.mapper.DemandMapper;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Integration tests for the {@link DemandResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DemandResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final DemandStatus DEFAULT_STATUS = DemandStatus.OPEN;
    private static final DemandStatus UPDATED_STATUS = DemandStatus.ASSIGNED;

    private static final LocalDateTime UPDATED_CREATION_DATE = LocalDateTime.now(ZoneId.systemDefault());

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private DemandService demandService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemandMockMvc;

    private Demand demand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demand createEntity(EntityManager em) {
        Demand demand = new Demand()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL)
            .description(DEFAULT_DESCRIPTION);
        // Add required entity
        Locality locality;
        if (TestUtil.findAll(em, Locality.class).isEmpty()) {
            locality = LocalityResourceIT.createEntity(em);
            em.persist(locality);
            em.flush();
        } else {
            locality = TestUtil.findAll(em, Locality.class).get(0);
        }
        demand.setLocality(locality);
        // Add required entity
        Organization organization = TestUtil.findAll(em, Organization.class).get(0);
        demand.setOrganization(organization);
        // Add required entity
        ServiceType serviceType;
        if (TestUtil.findAll(em, ServiceType.class).isEmpty()) {
            serviceType = ServiceTypeResourceIT.createEntity(em);
            em.persist(serviceType);
            em.flush();
        } else {
            serviceType = TestUtil.findAll(em, ServiceType.class).get(0);
        }
        demand.setServiceType(serviceType);
        return demand;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demand createUpdatedEntity(EntityManager em) {
        Demand demand = new Demand()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .creationDate(UPDATED_CREATION_DATE);
        // Add required entity
        Locality locality;
        if (TestUtil.findAll(em, Locality.class).isEmpty()) {
            locality = LocalityResourceIT.createUpdatedEntity(em);
            em.persist(locality);
            em.flush();
        } else {
            locality = TestUtil.findAll(em, Locality.class).get(0);
        }
        demand.setLocality(locality);
        // Add required entity
        Organization organization = TestUtil.findAll(em, Organization.class).get(0);
        demand.setOrganization(organization);
        // Add required entity
        ServiceType serviceType;
        if (TestUtil.findAll(em, ServiceType.class).isEmpty()) {
            serviceType = ServiceTypeResourceIT.createUpdatedEntity(em);
            em.persist(serviceType);
            em.flush();
        } else {
            serviceType = TestUtil.findAll(em, ServiceType.class).get(0);
        }
        demand.setServiceType(serviceType);
        return demand;
    }

    @BeforeEach
    public void initTest() {
        demand = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemand() throws Exception {
        int databaseSizeBeforeCreate = demandRepository.findAll().size();

        // Create the Demand
        DemandDTO demandDTO = demandMapper.toDto(demand);
        restDemandMockMvc.perform(post("/api/demands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
            .andExpect(status().isCreated());

        // Validate the Demand in the database
        List<Demand> demandList = demandRepository.findAll();
        assertThat(demandList).hasSize(databaseSizeBeforeCreate + 1);
        Demand testDemand = demandList.get(demandList.size() - 1);
        assertThat(testDemand.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testDemand.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testDemand.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testDemand.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDemand.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDemand.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDemand.getCreationDate()).isEqualTo(demand.getCreationDate());
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandRepository.findAll().size();
        // set the field null
        demand.setFirstName(null);

        // Create the Demand, which fails.
        DemandDTO demandDTO = demandMapper.toDto(demand);

        restDemandMockMvc.perform(post("/api/demands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
            .andExpect(status().isBadRequest());

        List<Demand> demandList = demandRepository.findAll();
        assertThat(demandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandRepository.findAll().size();
        // set the field null
        demand.setLastName(null);

        // Create the Demand, which fails.
        DemandDTO demandDTO = demandMapper.toDto(demand);

        restDemandMockMvc.perform(post("/api/demands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
            .andExpect(status().isBadRequest());

        List<Demand> demandList = demandRepository.findAll();
        assertThat(demandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandRepository.findAll().size();
        // set the field null
        demand.setPhone(null);

        // Create the Demand, which fails.
        DemandDTO demandDTO = demandMapper.toDto(demand);

        restDemandMockMvc.perform(post("/api/demands")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandDTO)))
            .andExpect(status().isBadRequest());

        List<Demand> demandList = demandRepository.findAll();
        assertThat(demandList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDemands() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

        // Get all the demandList
        restDemandMockMvc.perform(get("/api/demands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demand.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(demand.getCreationDate().toString())));
    }

    @Test
    @Transactional
    public void getDemand() throws Exception {
        // Initialize the database
        demandRepository.saveAndFlush(demand);

        // Get the demand
        restDemandMockMvc.perform(get("/api/demands/{id}", demand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demand.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.creationDate").value(demand.getCreationDate().toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemand() throws Exception {
        // Get the demand
        restDemandMockMvc.perform(get("/api/demands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
