package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DemandAuditMapperTest {

    private DemandAuditMapper demandAuditMapper;

    @BeforeEach
    public void setUp() {
        demandAuditMapper = new DemandAuditMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(demandAuditMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(demandAuditMapper.fromId(null)).isNull();
    }
}
