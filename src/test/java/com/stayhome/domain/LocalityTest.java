package com.stayhome.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.stayhome.web.rest.TestUtil;

public class LocalityTest {

    @Test
    public void equalsVerifier() throws Exception {
        Governorate governorate = new Governorate();
        governorate.setName("Tunis");

        Delegation delegation1 = new Delegation();
        delegation1.setName("Delegation 1");
        delegation1.setGovernorate(governorate);

        Delegation delegation2 = new Delegation();
        delegation2.setName("Delegation 2");
        delegation2.setGovernorate(governorate);

        TestUtil.equalsVerifier(Locality.class);

        Locality locality1 = new Locality();
        locality1.setName("Nebeur");
        locality1.setDelegation(delegation1);

        Locality locality2 = new Locality();
        locality2.setName(locality1.getName());
        locality2.setDelegation(locality1.getDelegation());
        assertThat(locality1).isEqualTo(locality2);

        locality2.setName("Sirs");
        assertThat(locality1).isNotEqualTo(locality2);

        locality2.setName(locality1.getName());
        locality2.setDelegation(delegation2);
        assertThat(locality1).isNotEqualTo(locality2);

        locality2.setName(null);
        assertThat(locality1).isNotEqualTo(locality2);

        locality2.setDelegation(null);
        assertThat(locality1).isNotEqualTo(locality2);
    }
}
