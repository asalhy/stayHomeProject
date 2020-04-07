package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class GovernorateNotFoundException extends AbstractThrowableProblem {

    public GovernorateNotFoundException(Long governorateId) {
        super(ErrorConstants.GOVERNORATE_NOT_FOUND_TYPE,"Governorate not found, governorateId = " + governorateId, Status.NOT_FOUND);
    }
}
