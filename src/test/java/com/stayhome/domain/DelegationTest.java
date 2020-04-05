package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class DelegationTest {

    @Test
    public void equalsVerifier() throws Exception {
        Governorate governorate1 = new Governorate();
        governorate1.setName("Tunis");

        Governorate governorate2 = new Governorate();
        governorate2.setName("Le Kef");

        TestUtil.equalsVerifier(Delegation.class);

        Delegation delegation1 = new Delegation();
        delegation1.setName("Delegation 1");
        delegation1.setGovernorate(governorate1);

        Delegation delegation2 = new Delegation();
        delegation2.setName(delegation1.getName());
        delegation2.setGovernorate(delegation1.getGovernorate());

        assertThat(delegation1).isEqualTo(delegation2);

        delegation2.setName("Delegation 2");
        assertThat(delegation1).isNotEqualTo(delegation2);

        delegation2.setName(delegation1.getName());
        delegation2.setGovernorate(governorate2);
        assertThat(delegation1).isNotEqualTo(delegation2);

        delegation2.setGovernorate(null);
        assertThat(delegation1).isNotEqualTo(delegation2);

        delegation2.setName(null);
        delegation2.setGovernorate(delegation1.getGovernorate());
        assertThat(delegation1).isNotEqualTo(delegation2);
    }
}
