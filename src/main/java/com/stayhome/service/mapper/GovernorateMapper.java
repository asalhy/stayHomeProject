package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.GovernorateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Governorate} and its DTO {@link GovernorateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GovernorateMapper extends EntityMapper<GovernorateDTO, Governorate> {



    default Governorate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Governorate governorate = new Governorate();
        governorate.setId(id);
        return governorate;
    }
}
