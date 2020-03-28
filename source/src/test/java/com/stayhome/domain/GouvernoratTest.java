package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class GouvernoratTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gouvernorat.class);
        Gouvernorat gouvernorat1 = new Gouvernorat();
        gouvernorat1.setId(1L);
        Gouvernorat gouvernorat2 = new Gouvernorat();
        gouvernorat2.setId(gouvernorat1.getId());
        assertThat(gouvernorat1).isEqualTo(gouvernorat2);
        gouvernorat2.setId(2L);
        assertThat(gouvernorat1).isNotEqualTo(gouvernorat2);
        gouvernorat1.setId(null);
        assertThat(gouvernorat1).isNotEqualTo(gouvernorat2);
    }
}
