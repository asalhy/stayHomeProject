package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class ServiceTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceType.class);

        ServiceType serviceType1 = new ServiceType();
        serviceType1.setName("Health");

        ServiceType serviceType2 = new ServiceType();
        serviceType2.setName(serviceType1.getName());
        assertThat(serviceType1).isEqualTo(serviceType2);

        serviceType2.setName("Food");
        assertThat(serviceType1).isNotEqualTo(serviceType2);

        serviceType2.setName(null);
        assertThat(serviceType1).isNotEqualTo(serviceType2);
    }
}
