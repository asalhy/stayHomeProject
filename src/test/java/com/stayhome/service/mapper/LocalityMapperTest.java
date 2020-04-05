package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalityMapperTest {

    private LocalityMapper localityMapper;

    @BeforeEach
    public void setUp() {
        localityMapper = new LocalityMapperImpl();
    }
}
