package com.stayhome.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class DemandAuditDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandAuditDTO.class);
        DemandAuditDTO demandAuditDTO1 = new DemandAuditDTO();
        demandAuditDTO1.setId(1L);
        DemandAuditDTO demandAuditDTO2 = new DemandAuditDTO();
        assertThat(demandAuditDTO1).isNotEqualTo(demandAuditDTO2);
        demandAuditDTO2.setId(demandAuditDTO1.getId());
        assertThat(demandAuditDTO1).isEqualTo(demandAuditDTO2);
        demandAuditDTO2.setId(2L);
        assertThat(demandAuditDTO1).isNotEqualTo(demandAuditDTO2);
        demandAuditDTO1.setId(null);
        assertThat(demandAuditDTO1).isNotEqualTo(demandAuditDTO2);
    }
}
