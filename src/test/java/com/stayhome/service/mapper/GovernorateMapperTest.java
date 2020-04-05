package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GovernorateMapperTest {

    private GovernorateMapper governorateMapper;

    @BeforeEach
    public void setUp() {
        governorateMapper = new GovernorateMapperImpl();
    }
}
