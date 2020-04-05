package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Delegation;
import com.stayhome.domain.Governorate;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.service.DelegationService;
import com.stayhome.service.dto.DelegationDTO;
import com.stayhome.service.mapper.DelegationMapper;

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
 * Integration tests for the {@link DelegationResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DelegationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private DelegationMapper delegationMapper;

    @Autowired
    private DelegationService delegationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDelegationMockMvc;

    private Delegation delegation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegation createEntity(EntityManager em) {
        Delegation delegation = new Delegation()
            .name(DEFAULT_NAME);
        // Add required entity
        Governorate governorate;
        if (TestUtil.findAll(em, Governorate.class).isEmpty()) {
            governorate = GovernorateResourceIT.createEntity(em);
            em.persist(governorate);
            em.flush();
        } else {
            governorate = TestUtil.findAll(em, Governorate.class).get(0);
        }
        delegation.setGovernorate(governorate);
        return delegation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegation createUpdatedEntity(EntityManager em) {
        Delegation delegation = new Delegation()
            .name(UPDATED_NAME);
        // Add required entity
        Governorate governorate;
        if (TestUtil.findAll(em, Governorate.class).isEmpty()) {
            governorate = GovernorateResourceIT.createUpdatedEntity(em);
            em.persist(governorate);
            em.flush();
        } else {
            governorate = TestUtil.findAll(em, Governorate.class).get(0);
        }
        delegation.setGovernorate(governorate);
        return delegation;
    }

    @BeforeEach
    public void initTest() {
        delegation = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllDelegations() throws Exception {
        // Initialize the database
        delegationRepository.saveAndFlush(delegation);

        // Get all the delegationList
        restDelegationMockMvc.perform(get("/api/delegations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(delegation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getDelegation() throws Exception {
        // Initialize the database
        delegationRepository.saveAndFlush(delegation);

        // Get the delegation
        restDelegationMockMvc.perform(get("/api/delegations/{id}", delegation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(delegation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingDelegation() throws Exception {
        // Get the delegation
        restDelegationMockMvc.perform(get("/api/delegations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
