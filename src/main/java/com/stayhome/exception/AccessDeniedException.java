package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class AccessDeniedException extends AbstractThrowableProblem {

    public AccessDeniedException(String message) {
        super(ErrorConstants.ACCESS_DENIED_TYPE,"Access denied to the requested resource", Status.FORBIDDEN, message);
    }
}
