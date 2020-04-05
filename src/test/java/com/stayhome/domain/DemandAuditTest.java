package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class DemandAuditTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandAudit.class);
        DemandAudit demandAudit1 = new DemandAudit();
        demandAudit1.setId(1L);
        DemandAudit demandAudit2 = new DemandAudit();
        demandAudit2.setId(demandAudit1.getId());
        assertThat(demandAudit1).isEqualTo(demandAudit2);
        demandAudit2.setId(2L);
        assertThat(demandAudit1).isNotEqualTo(demandAudit2);
        demandAudit1.setId(null);
        assertThat(demandAudit1).isNotEqualTo(demandAudit2);
    }
}
