package com.stayhome.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class GouvernoratNotFoundException extends AbstractThrowableProblem {

    public GouvernoratNotFoundException(Long gouvernoratId) {
        super(ErrorConstants.GOUVERNORAT_NOT_FOUND_TYPE, "Gouvernorat not found, id = " + gouvernoratId, Status.NOT_FOUND);
    }
}
