package com.stayhome.service;

import com.stayhome.domain.Gouvernorat;
import com.stayhome.domain.Volunteer;
import com.stayhome.repository.DelegationRepository;
import com.stayhome.repository.GouvernoratRepository;
import com.stayhome.repository.MunicipalityRepository;
import com.stayhome.repository.VolunteerRepository;
import com.stayhome.service.dto.VolunteerDTO;
import com.stayhome.service.mapper.VolunteerMapper;
import com.stayhome.web.rest.errors.GouvernoratNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Volunteer}.
 */
@Service
@Transactional
public class VolunteerService {

    private final Logger log = LoggerFactory.getLogger(VolunteerService.class);

    private final VolunteerRepository volunteerRepository;
    private final GouvernoratRepository gouvernoratRepository;
    private final DelegationRepository delegationRepository;
    private final MunicipalityRepository municipalityRepository;

    private final VolunteerMapper volunteerMapper;

    public VolunteerService(VolunteerRepository volunteerRepository, VolunteerMapper volunteerMapper,GouvernoratRepository gouvernoratRepository
    		,DelegationRepository delegationRepository,MunicipalityRepository municipalityRepository) {
        this.volunteerRepository = volunteerRepository;
        this.volunteerMapper = volunteerMapper;
        this.gouvernoratRepository = gouvernoratRepository;
        this.delegationRepository = delegationRepository;
        this.municipalityRepository = municipalityRepository;
   }

    /**
     * Save a volunteer.
     *
     * @param volunteerDTO the entity to save.
     * @return the persisted entity.
     */
    public VolunteerDTO save(VolunteerDTO volunteerDTO) {
        log.debug("Request to save Volunteer : {}", volunteerDTO);
        Volunteer volunteer = volunteerMapper.toEntity(volunteerDTO);
        volunteer = volunteerRepository.save(volunteer);
        return volunteerMapper.toDto(volunteer);
    }

    /**
     * Get all the volunteers.
     * @param gouvernoratId 
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VolunteerDTO> findAll(Long gouvernoratId, Pageable pageable) {
        log.debug("Request to get all Volunteers");
        Optional<Gouvernorat> gouvernorat = gouvernoratRepository.findById(gouvernoratId);
        if ( !gouvernorat.isPresent() ) {
        	throw new GouvernoratNotFoundException();
        }
        return  gouvernoratId ==null ?
        		volunteerRepository.findAll(pageable).map(volunteerMapper::toDto) :
        		volunteerRepository.findAllByGouvernorat(gouvernoratId, pageable).map(volunteerMapper::toDto);
    }

    /**
     * Get one volunteer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VolunteerDTO> findOne(Long id) {
        log.debug("Request to get Volunteer : {}", id);
        return volunteerRepository.findById(id)
            .map(volunteerMapper::toDto);
    }

    /**
     * Delete the volunteer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Volunteer : {}", id);
        volunteerRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
////    @Query("SELECT v FROM Volunteer v,Municipality m,Delegation d WHERE v.municipality_id=m.id and m.delegation_id=d.id and d.gouvernorat_id= ?1 ")
//    @Query("SELECT v FROM Volunteer v WHERE v.municipality.delegation.gouvernorat.id= ?1 ")
//	public Page<VolunteerDTO> findWithGouvernorat(Long gouvernoratId, Pageable pageable) {
//        //log.debug("Request to get all Volunteers");
//    	
//        delegationRepository.findby
//    	return volunteerRepository.findByGouvernorat(gouvernoratId, pageable);
//	}

}
