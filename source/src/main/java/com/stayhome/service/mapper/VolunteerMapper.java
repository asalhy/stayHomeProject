package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.VolunteerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Volunteer} and its DTO {@link VolunteerDTO}.
 */
@Mapper(componentModel = "spring", uses = {MunicipalityMapper.class, ServiceTypeMapper.class})
public interface VolunteerMapper extends EntityMapper<VolunteerDTO, Volunteer> {

    @Mapping(source = "municipality.id", target = "municipalityId")
    @Mapping(source = "municipality.name", target = "municipalityName")
    VolunteerDTO toDto(Volunteer volunteer);

    @Mapping(source = "municipalityId", target = "municipality")
    Volunteer toEntity(VolunteerDTO volunteerDTO);

    default Volunteer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Volunteer volunteer = new Volunteer();
        volunteer.setId(id);
        return volunteer;
    }
}
