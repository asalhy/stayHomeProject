package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTypeMapperTest {

    private ServiceTypeMapper serviceTypeMapper;

    @BeforeEach
    public void setUp() {
        serviceTypeMapper = new ServiceTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(serviceTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(serviceTypeMapper.fromId(null)).isNull();
    }
}
