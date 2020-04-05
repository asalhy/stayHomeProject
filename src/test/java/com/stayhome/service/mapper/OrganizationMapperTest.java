package com.stayhome.service.mapper;

import org.junit.jupiter.api.BeforeEach;

public class OrganizationMapperTest {

    private OrganizationMapper organizationMapper;

    @BeforeEach
    public void setUp() {
        organizationMapper = new OrganizationMapperImpl();
    }
}
