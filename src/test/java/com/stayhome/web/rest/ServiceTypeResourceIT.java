package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.ServiceType;
import com.stayhome.repository.ServiceTypeRepository;
import com.stayhome.service.ServiceTypeService;
import com.stayhome.service.dto.ServiceTypeDTO;
import com.stayhome.service.mapper.ServiceTypeMapper;

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
 * Integration tests for the {@link ServiceTypeResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ServiceTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceTypeMockMvc;

    private ServiceType serviceType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceType createEntity(EntityManager em) {
        ServiceType serviceType = new ServiceType()
            .name(DEFAULT_NAME);
        return serviceType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceType createUpdatedEntity(EntityManager em) {
        ServiceType serviceType = new ServiceType()
            .name(UPDATED_NAME);
        return serviceType;
    }

    @BeforeEach
    public void initTest() {
        serviceType = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllServiceTypes() throws Exception {
        // Initialize the database
        serviceTypeRepository.saveAndFlush(serviceType);

        // Get all the serviceTypeList
        restServiceTypeMockMvc.perform(get("/api/service-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getServiceType() throws Exception {
        // Initialize the database
        serviceTypeRepository.saveAndFlush(serviceType);

        // Get the serviceType
        restServiceTypeMockMvc.perform(get("/api/service-types/{id}", serviceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingServiceType() throws Exception {
        // Get the serviceType
        restServiceTypeMockMvc.perform(get("/api/service-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
