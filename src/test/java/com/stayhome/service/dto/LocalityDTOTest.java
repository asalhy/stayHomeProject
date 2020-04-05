package com.stayhome.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class LocalityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalityDTO.class);
        LocalityDTO localityDTO1 = new LocalityDTO();
        localityDTO1.setId(1L);
        LocalityDTO localityDTO2 = new LocalityDTO();
        assertThat(localityDTO1).isNotEqualTo(localityDTO2);
        localityDTO2.setId(localityDTO1.getId());
        assertThat(localityDTO1).isEqualTo(localityDTO2);
        localityDTO2.setId(2L);
        assertThat(localityDTO1).isNotEqualTo(localityDTO2);
        localityDTO1.setId(null);
        assertThat(localityDTO1).isNotEqualTo(localityDTO2);
    }
}
