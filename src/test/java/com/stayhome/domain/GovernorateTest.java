package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class GovernorateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Governorate.class);

        Governorate governorate1 = new Governorate();
        governorate1.setName("Le Kef");

        Governorate governorate2 = new Governorate();
        governorate2.setName(governorate1.getName());

        assertThat(governorate1).isEqualTo(governorate2);

        governorate2.setName("Tunis");
        assertThat(governorate1).isNotEqualTo(governorate2);

        governorate2.setId(null);
        assertThat(governorate1).isNotEqualTo(governorate2);
    }
}
