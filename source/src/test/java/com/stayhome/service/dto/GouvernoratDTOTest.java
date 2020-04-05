package com.stayhome.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class GouvernoratDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GouvernoratDTO.class);
        GouvernoratDTO gouvernoratDTO1 = new GouvernoratDTO();
        gouvernoratDTO1.setId(1L);
        GouvernoratDTO gouvernoratDTO2 = new GouvernoratDTO();
        assertThat(gouvernoratDTO1).isNotEqualTo(gouvernoratDTO2);
        gouvernoratDTO2.setId(gouvernoratDTO1.getId());
        assertThat(gouvernoratDTO1).isEqualTo(gouvernoratDTO2);
        gouvernoratDTO2.setId(2L);
        assertThat(gouvernoratDTO1).isNotEqualTo(gouvernoratDTO2);
        gouvernoratDTO1.setId(null);
        assertThat(gouvernoratDTO1).isNotEqualTo(gouvernoratDTO2);
    }
}
