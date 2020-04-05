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
    public void createServiceType() throws Exception {
        int databaseSizeBeforeCreate = serviceTypeRepository.findAll().size();

        // Create the ServiceType
        ServiceTypeDTO serviceTypeDTO = serviceTypeMapper.toDto(serviceType);
        restServiceTypeMockMvc.perform(post("/api/service-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceType in the database
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceType testServiceType = serviceTypeList.get(serviceTypeList.size() - 1);
        assertThat(testServiceType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createServiceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceTypeRepository.findAll().size();

        // Create the ServiceType with an existing ID
        serviceType.setId(1L);
        ServiceTypeDTO serviceTypeDTO = serviceTypeMapper.toDto(serviceType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceTypeMockMvc.perform(post("/api/service-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceType in the database
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceTypeRepository.findAll().size();
        // set the field null
        serviceType.setName(null);

        // Create the ServiceType, which fails.
        ServiceTypeDTO serviceTypeDTO = serviceTypeMapper.toDto(serviceType);

        restServiceTypeMockMvc.perform(post("/api/service-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeTest);
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

    @Test
    @Transactional
    public void updateServiceType() throws Exception {
        // Initialize the database
        serviceTypeRepository.saveAndFlush(serviceType);

        int databaseSizeBeforeUpdate = serviceTypeRepository.findAll().size();

        // Update the serviceType
        ServiceType updatedServiceType = serviceTypeRepository.findById(serviceType.getId()).get();
        // Disconnect from session so that the updates on updatedServiceType are not directly saved in db
        em.detach(updatedServiceType);
        updatedServiceType
            .name(UPDATED_NAME);
        ServiceTypeDTO serviceTypeDTO = serviceTypeMapper.toDto(updatedServiceType);

        restServiceTypeMockMvc.perform(put("/api/service-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ServiceType in the database
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeUpdate);
        ServiceType testServiceType = serviceTypeList.get(serviceTypeList.size() - 1);
        assertThat(testServiceType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceType() throws Exception {
        int databaseSizeBeforeUpdate = serviceTypeRepository.findAll().size();

        // Create the ServiceType
        ServiceTypeDTO serviceTypeDTO = serviceTypeMapper.toDto(serviceType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceTypeMockMvc.perform(put("/api/service-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceType in the database
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceType() throws Exception {
        // Initialize the database
        serviceTypeRepository.saveAndFlush(serviceType);

        int databaseSizeBeforeDelete = serviceTypeRepository.findAll().size();

        // Delete the serviceType
        restServiceTypeMockMvc.perform(delete("/api/service-types/{id}", serviceType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAll();
        assertThat(serviceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
