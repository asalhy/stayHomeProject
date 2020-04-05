package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Locality;
import com.stayhome.domain.Delegation;
import com.stayhome.repository.LocalityRepository;
import com.stayhome.service.LocalityService;
import com.stayhome.service.dto.LocalityDTO;
import com.stayhome.service.mapper.LocalityMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LocalityResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LocalityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private LocalityMapper localityMapper;

    @Autowired
    private LocalityService localityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocalityMockMvc;

    private Locality locality;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Locality createEntity(EntityManager em) {
        Locality locality = new Locality()
            .name(DEFAULT_NAME)
            .postalCode(DEFAULT_POSTAL_CODE);
        // Add required entity
        Delegation delegation;
        if (TestUtil.findAll(em, Delegation.class).isEmpty()) {
            delegation = DelegationResourceIT.createEntity(em);
            em.persist(delegation);
            em.flush();
        } else {
            delegation = TestUtil.findAll(em, Delegation.class).get(0);
        }
        locality.setDelegation(delegation);
        return locality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Locality createUpdatedEntity(EntityManager em) {
        Locality locality = new Locality()
            .name(UPDATED_NAME)
            .postalCode(UPDATED_POSTAL_CODE);
        // Add required entity
        Delegation delegation;
        if (TestUtil.findAll(em, Delegation.class).isEmpty()) {
            delegation = DelegationResourceIT.createUpdatedEntity(em);
            em.persist(delegation);
            em.flush();
        } else {
            delegation = TestUtil.findAll(em, Delegation.class).get(0);
        }
        locality.setDelegation(delegation);
        return locality;
    }

    @BeforeEach
    public void initTest() {
        locality = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllLocalities() throws Exception {
        // Initialize the database
        localityRepository.saveAndFlush(locality);

        // Get all the localityList
        restLocalityMockMvc.perform(get("/api/localities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)));
    }
    
    @Test
    @Transactional
    public void getLocality() throws Exception {
        // Initialize the database
        localityRepository.saveAndFlush(locality);

        // Get the locality
        restLocalityMockMvc.perform(get("/api/localities/{id}", locality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(locality.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingLocality() throws Exception {
        // Get the locality
        restLocalityMockMvc.perform(get("/api/localities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
