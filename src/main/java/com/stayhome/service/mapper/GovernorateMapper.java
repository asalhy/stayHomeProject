package com.stayhome.service.mapper;

import com.stayhome.domain.Governorate;
import com.stayhome.service.dto.GovernorateDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Governorate} and its DTO {@link GovernorateDTO}.
 */
@Mapper(componentModel = "spring")
public interface GovernorateMapper {

    GovernorateDTO toDto(Governorate governorate);
}
