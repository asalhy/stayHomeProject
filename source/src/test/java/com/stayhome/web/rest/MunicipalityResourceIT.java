package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Municipality;
import com.stayhome.repository.MunicipalityRepository;
import com.stayhome.service.MunicipalityService;
import com.stayhome.service.dto.MunicipalityDTO;
import com.stayhome.service.mapper.MunicipalityMapper;

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
 * Integration tests for the {@link MunicipalityResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MunicipalityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private MunicipalityMapper municipalityMapper;

    @Autowired
    private MunicipalityService municipalityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMunicipalityMockMvc;

    private Municipality municipality;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipality createEntity(EntityManager em) {
        Municipality municipality = new Municipality()
            .name(DEFAULT_NAME);
        return municipality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Municipality createUpdatedEntity(EntityManager em) {
        Municipality municipality = new Municipality()
            .name(UPDATED_NAME);
        return municipality;
    }

    @BeforeEach
    public void initTest() {
        municipality = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllMunicipalities() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get all the municipalityList
        restMunicipalityMockMvc.perform(get("/api/municipalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(municipality.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getMunicipality() throws Exception {
        // Initialize the database
        municipalityRepository.saveAndFlush(municipality);

        // Get the municipality
        restMunicipalityMockMvc.perform(get("/api/municipalities/{id}", municipality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(municipality.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingMunicipality() throws Exception {
        // Get the municipality
        restMunicipalityMockMvc.perform(get("/api/municipalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
