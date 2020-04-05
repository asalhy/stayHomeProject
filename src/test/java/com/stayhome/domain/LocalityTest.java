package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class LocalityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Locality.class);
        Locality locality1 = new Locality();
        locality1.setId(1L);
        Locality locality2 = new Locality();
        locality2.setId(locality1.getId());
        assertThat(locality1).isEqualTo(locality2);
        locality2.setId(2L);
        assertThat(locality1).isNotEqualTo(locality2);
        locality1.setId(null);
        assertThat(locality1).isNotEqualTo(locality2);
    }
}
