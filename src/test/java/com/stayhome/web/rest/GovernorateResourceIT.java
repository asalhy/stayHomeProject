package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Governorate;
import com.stayhome.repository.GovernorateRepository;
import com.stayhome.service.GovernorateService;
import com.stayhome.service.dto.GovernorateDTO;
import com.stayhome.service.mapper.GovernorateMapper;

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
 * Integration tests for the {@link GovernorateResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class GovernorateResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private GovernorateRepository governorateRepository;

    @Autowired
    private GovernorateMapper governorateMapper;

    @Autowired
    private GovernorateService governorateService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGovernorateMockMvc;

    private Governorate governorate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Governorate createEntity(EntityManager em) {
        Governorate governorate = new Governorate()
            .name(DEFAULT_NAME);
        return governorate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Governorate createUpdatedEntity(EntityManager em) {
        Governorate governorate = new Governorate()
            .name(UPDATED_NAME);
        return governorate;
    }

    @BeforeEach
    public void initTest() {
        governorate = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllGovernorates() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get all the governorateList
        restGovernorateMockMvc.perform(get("/api/governorates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(governorate.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getGovernorate() throws Exception {
        // Initialize the database
        governorateRepository.saveAndFlush(governorate);

        // Get the governorate
        restGovernorateMockMvc.perform(get("/api/governorates/{id}", governorate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(governorate.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingGovernorate() throws Exception {
        // Get the governorate
        restGovernorateMockMvc.perform(get("/api/governorates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
