package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GouvernoratMapperTest {

    private GouvernoratMapper gouvernoratMapper;

    @BeforeEach
    public void setUp() {
        gouvernoratMapper = new GouvernoratMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(gouvernoratMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gouvernoratMapper.fromId(null)).isNull();
    }
}
