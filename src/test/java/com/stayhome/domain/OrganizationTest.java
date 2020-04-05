package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class OrganizationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organization.class);

        Organization organization1 = new Organization();
        organization1.setName("Scout");

        Organization organization2 = new Organization();
        organization2.setName(organization1.getName());
        assertThat(organization1).isEqualTo(organization2);

        organization2.setName("Admin");
        assertThat(organization1).isNotEqualTo(organization2);

        organization2.setName(null);
        assertThat(organization1).isNotEqualTo(organization2);
    }
}
