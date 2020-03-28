package com.stayhome.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class MunicipalityNotFoundException extends AbstractThrowableProblem {

    public MunicipalityNotFoundException(Long municipalityId) {
        super(ErrorConstants.MUNICIPALITY_NOT_FOUND_TYPE, "Municipality not found, id = " + municipalityId, Status.NOT_FOUND);
    }
}
