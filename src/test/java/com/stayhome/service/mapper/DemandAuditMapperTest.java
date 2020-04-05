package com.stayhome.service.mapper;

import com.stayhome.service.dto.DemandAuditDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DemandAuditMapperTest {

    private DemandAuditMapper demandAuditMapper;

    @BeforeEach
    public void setUp() {
        demandAuditMapper = new DemandAuditMapperImpl();
    }
}
