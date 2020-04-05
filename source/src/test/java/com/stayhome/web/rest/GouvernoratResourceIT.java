package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Gouvernorat;
import com.stayhome.repository.GouvernoratRepository;
import com.stayhome.service.GouvernoratService;
import com.stayhome.service.dto.GouvernoratDTO;
import com.stayhome.service.mapper.GouvernoratMapper;

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
 * Integration tests for the {@link GouvernoratResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class GouvernoratResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private GouvernoratRepository gouvernoratRepository;

    @Autowired
    private GouvernoratMapper gouvernoratMapper;

    @Autowired
    private GouvernoratService gouvernoratService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGouvernoratMockMvc;

    private Gouvernorat gouvernorat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gouvernorat createEntity(EntityManager em) {
        Gouvernorat gouvernorat = new Gouvernorat()
            .name(DEFAULT_NAME);
        return gouvernorat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gouvernorat createUpdatedEntity(EntityManager em) {
        Gouvernorat gouvernorat = new Gouvernorat()
            .name(UPDATED_NAME);
        return gouvernorat;
    }

    @BeforeEach
    public void initTest() {
        gouvernorat = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllGouvernorats() throws Exception {
        // Initialize the database
        gouvernoratRepository.saveAndFlush(gouvernorat);

        // Get all the gouvernoratList
        restGouvernoratMockMvc.perform(get("/api/gouvernorats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gouvernorat.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getGouvernorat() throws Exception {
        // Initialize the database
        gouvernoratRepository.saveAndFlush(gouvernorat);

        // Get the gouvernorat
        restGouvernoratMockMvc.perform(get("/api/gouvernorats/{id}", gouvernorat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gouvernorat.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingGouvernorat() throws Exception {
        // Get the gouvernorat
        restGouvernoratMockMvc.perform(get("/api/gouvernorats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
