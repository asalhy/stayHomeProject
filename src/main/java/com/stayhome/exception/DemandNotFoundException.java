package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class DemandNotFoundException extends AbstractThrowableProblem {

    public DemandNotFoundException(Long demandId) {
        super(ErrorConstants.DEMAND_NOT_FOUND_TYPE,"Demand not found, demandId = " + demandId, Status.NOT_FOUND);
    }
}
