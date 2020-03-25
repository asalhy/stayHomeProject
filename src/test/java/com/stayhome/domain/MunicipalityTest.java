package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class MunicipalityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Municipality.class);
        Municipality municipality1 = new Municipality();
        municipality1.setId(1L);
        Municipality municipality2 = new Municipality();
        municipality2.setId(municipality1.getId());
        assertThat(municipality1).isEqualTo(municipality2);
        municipality2.setId(2L);
        assertThat(municipality1).isNotEqualTo(municipality2);
        municipality1.setId(null);
        assertThat(municipality1).isNotEqualTo(municipality2);
    }
}
