package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.GouvernoratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Gouvernorat} and its DTO {@link GouvernoratDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GouvernoratMapper extends EntityMapper<GouvernoratDTO, Gouvernorat> {



    default Gouvernorat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gouvernorat gouvernorat = new Gouvernorat();
        gouvernorat.setId(id);
        return gouvernorat;
    }
}
