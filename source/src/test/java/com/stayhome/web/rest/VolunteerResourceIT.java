package com.stayhome.web.rest;

import com.stayhome.StayHomeApp;
import com.stayhome.domain.Volunteer;
import com.stayhome.repository.VolunteerRepository;
import com.stayhome.service.VolunteerService;
import com.stayhome.service.dto.VolunteerDTO;
import com.stayhome.service.mapper.VolunteerMapper;

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

/**
 * Integration tests for the {@link VolunteerResource} REST controller.
 */
@SpringBootTest(classes = StayHomeApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class VolunteerResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerMapper volunteerMapper;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVolunteerMockMvc;

    private Volunteer volunteer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Volunteer createEntity(EntityManager em) {
        Volunteer volunteer = new Volunteer()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .creationDate(DEFAULT_CREATION_DATE);
        return volunteer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Volunteer createUpdatedEntity(EntityManager em) {
        Volunteer volunteer = new Volunteer()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .creationDate(UPDATED_CREATION_DATE);
        return volunteer;
    }

    @BeforeEach
    public void initTest() {
        volunteer = createEntity(em);
    }

    @Test
    @Transactional
    public void createVolunteer() throws Exception {
        int databaseSizeBeforeCreate = volunteerRepository.findAll().size();

        // Create the Volunteer
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);
        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isCreated());

        // Validate the Volunteer in the database
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeCreate + 1);
        Volunteer testVolunteer = volunteerList.get(volunteerList.size() - 1);
        assertThat(testVolunteer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testVolunteer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testVolunteer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testVolunteer.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testVolunteer.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
    }

    @Test
    @Transactional
    public void createVolunteerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = volunteerRepository.findAll().size();

        // Create the Volunteer with an existing ID
        volunteer.setId(1L);
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Volunteer in the database
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = volunteerRepository.findAll().size();
        // set the field null
        volunteer.setFirstName(null);

        // Create the Volunteer, which fails.
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = volunteerRepository.findAll().size();
        // set the field null
        volunteer.setLastName(null);

        // Create the Volunteer, which fails.
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = volunteerRepository.findAll().size();
        // set the field null
        volunteer.setEmail(null);

        // Create the Volunteer, which fails.
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = volunteerRepository.findAll().size();
        // set the field null
        volunteer.setPhone(null);

        // Create the Volunteer, which fails.
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = volunteerRepository.findAll().size();
        // set the field null
        volunteer.setCreationDate(null);

        // Create the Volunteer, which fails.
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        restVolunteerMockMvc.perform(post("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVolunteers() throws Exception {
        // Initialize the database
        volunteerRepository.saveAndFlush(volunteer);

        // Get all the volunteerList
        restVolunteerMockMvc.perform(get("/api/volunteers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(volunteer.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getVolunteer() throws Exception {
        // Initialize the database
        volunteerRepository.saveAndFlush(volunteer);

        // Get the volunteer
        restVolunteerMockMvc.perform(get("/api/volunteers/{id}", volunteer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(volunteer.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVolunteer() throws Exception {
        // Get the volunteer
        restVolunteerMockMvc.perform(get("/api/volunteers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVolunteer() throws Exception {
        // Initialize the database
        volunteerRepository.saveAndFlush(volunteer);

        int databaseSizeBeforeUpdate = volunteerRepository.findAll().size();

        // Update the volunteer
        Volunteer updatedVolunteer = volunteerRepository.findById(volunteer.getId()).get();
        // Disconnect from session so that the updates on updatedVolunteer are not directly saved in db
        em.detach(updatedVolunteer);
        updatedVolunteer
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .creationDate(UPDATED_CREATION_DATE);
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(updatedVolunteer);

        restVolunteerMockMvc.perform(put("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isOk());

        // Validate the Volunteer in the database
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeUpdate);
        Volunteer testVolunteer = volunteerList.get(volunteerList.size() - 1);
        assertThat(testVolunteer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testVolunteer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testVolunteer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testVolunteer.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testVolunteer.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVolunteer() throws Exception {
        int databaseSizeBeforeUpdate = volunteerRepository.findAll().size();

        // Create the Volunteer
        VolunteerDTO volunteerDTO = volunteerMapper.toDto(volunteer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVolunteerMockMvc.perform(put("/api/volunteers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(volunteerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Volunteer in the database
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVolunteer() throws Exception {
        // Initialize the database
        volunteerRepository.saveAndFlush(volunteer);

        int databaseSizeBeforeDelete = volunteerRepository.findAll().size();

        // Delete the volunteer
        restVolunteerMockMvc.perform(delete("/api/volunteers/{id}", volunteer.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Volunteer> volunteerList = volunteerRepository.findAll();
        assertThat(volunteerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
